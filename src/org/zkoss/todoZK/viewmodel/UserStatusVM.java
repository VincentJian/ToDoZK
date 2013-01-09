package org.zkoss.todoZK.viewmodel;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.todoZK.dao.AbstractDB;
import org.zkoss.todoZK.dao.DBProvider;
import org.zkoss.todoZK.vo.Workspace;

public class UserStatusVM {
	private static AbstractDB db = DBProvider.getInstance();
	private List<Workspace> workspaces;	//Refactory Maybe not a good idea in product
	private String currentPath;
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
		return currentPath;
	}
	
	public int getFinishedAmount() {
		return finishedTask;
	}
	
	public int getUnfinishedAmount() {
		return totalTaskAmount - finishedTask;
	}
	
	@GlobalCommand @NotifyChange({"currentPath","finishedAmount", "unfinishedAmount"})
	public void updateUserStatus(@BindingParam("path") String path,
			@BindingParam("finished") int finished,
			@BindingParam("total") int total) {
		currentPath = path;
		finishedTask = finished;
		totalTaskAmount = total;
	}
	
	private void gatherAllStat() {
		workspaces = db.getWorkspaces();
		currentPath = "All Workspace";
		totalTaskAmount = 0;
		finishedTask = 0;
		for (Workspace ws : workspaces) {
			totalTaskAmount += ws.getTotalTaskAmount();
			finishedTask += ws.getFinishedTask();
		}
	}
}