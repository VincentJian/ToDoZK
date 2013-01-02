package org.zkoss.todoZK.dao;

import java.util.List;

import org.zkoss.todoZK.vo.Milestone;
import org.zkoss.todoZK.vo.Task;
import org.zkoss.todoZK.vo.Workspace;

public abstract class AbstractDB {
	public abstract List<Workspace> getWorkspaces();
	public abstract Workspace getWorkspaceById(Long id);
	public abstract List<Milestone> getMilestonesByWorkspace(Long workspaceId);
	public abstract List<Task> getTasksByMilestone(Long milestoneId);
	public abstract void addWorkspace(Workspace ws);
	public abstract void addMilestone(Milestone ms);
	public abstract void addTask(Task task);
}