package org.zkoss.todoZK.viewmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.todoZK.PageMapping;
import org.zkoss.todoZK.Utils;
import org.zkoss.todoZK.dao.AbstractDB;
import org.zkoss.todoZK.dao.DBProvider;
import org.zkoss.todoZK.exception.MilestoneNotExist;
import org.zkoss.todoZK.vo.Milestone;
import org.zkoss.todoZK.vo.Workspace;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.BookmarkEvent;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.TreeNode;

public class SidebarVM {
	private static AbstractDB db = DBProvider.getInstance();
	private List<Workspace> workspaces;	//Refactory Maybe not a good idea in product
	private DefaultTreeModel<BoardItem> boardModel;
	private Workspace nowWorkspace;
	private Milestone nowMilestone;
	private int finishedTask;
	private int totalTaskAmount;
	private int viewType;
	
	public SidebarVM() {
		fetchDB();
	}
	
	public DefaultTreeModel<BoardItem> getBoardModel() {
		return boardModel;
	}
	
	public void setSelectedItem(TreeNode<BoardItem> selectedItem) {
		BoardItem boardItem = selectedItem.getData();
		int type = boardItem.getType(); 
		switch (type) {
		case BoardItem.WORKSPACE_TYPE:
			gotoWorkspace(boardItem.getId());
			break;
		case BoardItem.MILESTONE_TYPE:
			gotoMilestone(boardItem.getId());
			break;
		case BoardItem.ABOUT_PAGE_TYPE:
		case BoardItem.LOG_PAGE_TYPE:
		case BoardItem.ROOT_PAGE_TYPE:
			processStaticPage(type);
			break;
		default:
			processStaticPage(BoardItem.ROOT_PAGE_TYPE);
			break;
		}
	}
		
	@Command
	public void bookmarkChange(@BindingParam("evnt") BookmarkEvent evnt){
		String bookmark = evnt.getBookmark();
		int type = Utils.getIndex(PageMapping.BOOKMARK_VALUE, bookmark);
		switch(type){
		case PageMapping.ABOUT_INDEX:
		case PageMapping.DOCUMENT_INDEX:
		case PageMapping.RELEASE_INDEX:
			processStaticPage(type);
			break;
		default:
			processStaticPage(BoardItem.ROOT_PAGE_TYPE);
			break;
		}

		if(bookmark.startsWith("ws=")){
			try{
				Long wid = Long.parseLong(bookmark.substring(3));
				gotoWorkspace(wid);
			}catch(Exception e){
				processStaticPage(PageMapping.DOCUMENT_INDEX);
			}
		} else if(bookmark.startsWith("ms=")){
			try{
				Long mid = Long.parseLong(bookmark.substring(3));
				gotoMilestone(mid);
			}catch(Exception e){
				processStaticPage(PageMapping.DOCUMENT_INDEX);
			}
		} else {
			processStaticPage(PageMapping.DOCUMENT_INDEX);
		}
	}
	
	
	@GlobalCommand
	public void viewChange(@BindingParam("type")int value){
		viewType = value;
		if (nowMilestone != null) {
			Utils.changeContent("content", "innerpage/zul/" + PageMapping.VIEW_URL[viewType] + "?ms=" + nowMilestone.getId());
			return;
		}
		if (nowWorkspace != null) {
			Utils.changeContent("content", "innerpage/zul/" + PageMapping.VIEW_URL[viewType] + "?ws=" + nowWorkspace.getId());
		}
	}

	private void gotoMilestone(Long id) {
		try {
			Milestone ms = db.getMilestoneById(id); //Refactory should not query DB
			Workspace ws = getWorkspaceById(ms.getWorkspaceId());
			if (!ws.equals(nowWorkspace)) {
				nowWorkspace = ws;
			}
			if (!ms.equals(nowMilestone)) {
				nowMilestone = ms;
			}
			finishedTask = nowMilestone.getFinishedTask();
			totalTaskAmount = nowMilestone.getTotalTaskAmount();
			changeContent("innerpage/zul/" + PageMapping.VIEW_URL[viewType] +
					"?ms=" + ms.getId(), ws.getTitle(), finishedTask, totalTaskAmount);
			Executions.getCurrent().getDesktop().setBookmark("ms="+id);
		} catch (MilestoneNotExist e) { }		
	}

	private void gotoWorkspace(Long id) {
		Workspace ws = getWorkspaceById(id);
		if (!ws.equals(nowWorkspace)) {
			nowWorkspace = ws;
		}
		nowMilestone = null;
		finishedTask = nowWorkspace.getFinishedTask();
		totalTaskAmount = nowWorkspace.getTotalTaskAmount();
		changeContent("innerpage/zul/" + PageMapping.VIEW_URL[viewType] +
				"?ws=" + ws.getId(), ws.getTitle(), finishedTask, totalTaskAmount);
		Executions.getCurrent().getDesktop().setBookmark("ws="+id);
	}
	
	private void processStaticPage(int type) {
		nowWorkspace = null;
		nowMilestone = null;
		totalTaskAmount = 0;
		finishedTask = 0;
		for (Workspace ws : workspaces) {
			totalTaskAmount += ws.getTotalTaskAmount();
			finishedTask += ws.getFinishedTask();
		}
		changeContent("innerpage/jsp/" + PageMapping.STATIC_URL[type], "All Workspace", finishedTask, totalTaskAmount);
		Executions.getCurrent().getDesktop().setBookmark(PageMapping.BOOKMARK_VALUE[type]);
	}
	
	private void changeContent(String url, String path, int finished, int total) {
		Utils.changeContent("content", url);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("path", path);
		param.put("finished", finished);
		param.put("total", total);
		BindUtils.postGlobalCommand(null, null, "updateUserStatus", param);
	}

	private void fetchDB() {
		DefaultTreeNode<BoardItem> root = new DefaultTreeNode<BoardItem>(null, new ArrayList<DefaultTreeNode<BoardItem>>());
		boardModel = new DefaultTreeModel<BoardItem>(root);
		
		// static page node
		DefaultTreeNode<BoardItem> pageRoot = new DefaultTreeNode<BoardItem>(
			new BoardItem(Long.MIN_VALUE, "Document", BoardItem.ROOT_PAGE_TYPE),
			new ArrayList<DefaultTreeNode<BoardItem>>()
		);
		pageRoot.add(
			new DefaultTreeNode<BoardItem>(
				new BoardItem(Long.MIN_VALUE, "About", BoardItem.ABOUT_PAGE_TYPE)
			)
		);
		pageRoot.add(
			new DefaultTreeNode<BoardItem>(
				new BoardItem(Long.MIN_VALUE, "Release Log", BoardItem.LOG_PAGE_TYPE)
			)
		);		
		root.add(pageRoot);
		boardModel.addOpenObject(pageRoot);
		////
		
		// build items of Workspace / Milestone
		workspaces = db.getWorkspaces();
		for (Workspace ws : workspaces) {
			DefaultTreeNode<BoardItem> wsNode = new DefaultTreeNode<BoardItem>(
				new BoardItem(ws.getId(), ws.getTitle(), BoardItem.WORKSPACE_TYPE),
				new ArrayList<DefaultTreeNode<BoardItem>>()
			);
			for (Milestone ms : ws.getMilestones()) {
				wsNode.add(
					new DefaultTreeNode<BoardItem>(
						new BoardItem(ms.getId(), ms.getTitle(), BoardItem.MILESTONE_TYPE)
					)
				);
			}
			root.add(wsNode);
			boardModel.addOpenObject(wsNode);
		}
		////
	}
	
	private Workspace getWorkspaceById(Long id) {
		for (Workspace ws : workspaces) {
			if (ws.getId().equals(id)) {
				return ws;
			}
		}
		return null;
	}
}