package org.zkoss.todoZK.viewmodel;

import java.util.List;

import org.zkoss.todoZK.dao.AbstractDB;
import org.zkoss.todoZK.dao.DBProvider;
import org.zkoss.todoZK.vo.Milestone;
import org.zkoss.todoZK.vo.Workspace;

public class UserStatusVM {
	private static AbstractDB db = DBProvider.getInstance();
	private List<Workspace> workspaces;	//Refactory Maybe not a good idea in product
	private Workspace nowWorkspace;
	private Milestone nowMilestone;
	private int finishedTask;
	private int totalTaskAmount;
	
	/**
	 * Only fetch DB at initialize, 
	 * follow up operation will use the value passed by EventQueue.
	 */
	public UserStatusVM() {
		gatherAllStat();
	}
	
	public String getCurrentPath() {
		StringBuffer result = new StringBuffer();
		result.append(nowWorkspace == null ? "All Workspace" : nowWorkspace.getTitle());
		result.append(nowMilestone == null ? "" : " > " + nowMilestone.getTitle());
		return result.toString();
	}
	
	public int getFinishedAmount() {
		return finishedTask;
	}
	
	public int getUnfinishedAmount() {
		return totalTaskAmount - finishedTask;
	}
	
	private void gatherAllStat() {
		workspaces = db.getWorkspaces();
		nowWorkspace = null;
		nowMilestone = null;
		totalTaskAmount = 0;
		finishedTask = 0;
		for (Workspace ws : workspaces) {
			totalTaskAmount += ws.getTotalTaskAmount();
			finishedTask += ws.getFinishedTask();
		}
	}
}