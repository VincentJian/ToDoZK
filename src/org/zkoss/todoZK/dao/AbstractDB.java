package org.zkoss.todoZK.dao;

import java.util.List;

import org.zkoss.todoZK.exception.MilestoneNotExist;
import org.zkoss.todoZK.exception.WorkspaceNotExist;
import org.zkoss.todoZK.vo.Milestone;
import org.zkoss.todoZK.vo.Task;
import org.zkoss.todoZK.vo.Workspace;

public abstract class AbstractDB {
	public abstract List<Workspace> getWorkspaces();
	public abstract Workspace getWorkspaceById(Long id) throws WorkspaceNotExist;
	public abstract Milestone getMilestoneById(Long id) throws MilestoneNotExist;
	public abstract List<Milestone> getMilestonesByWorkspace(Long workspaceId) throws WorkspaceNotExist;
	public abstract List<Task> getTasksByMilestone(Long milestoneId) throws MilestoneNotExist;
	public abstract void addWorkspace(Workspace ws);
	public abstract void addMilestone(Milestone ms) throws WorkspaceNotExist;
	public abstract void addTask(Task task) throws MilestoneNotExist;
}