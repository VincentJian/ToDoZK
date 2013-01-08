package org.zkoss.todoZK.vo;

import java.util.ArrayList;
import java.util.List;

public class Milestone {
	private Long id;
	private Long workspaceId;
	private String title;
	private List<Task> tasks;
	
	public Milestone() {
		tasks = new ArrayList<Task>();
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void addTask(Task task) {
		tasks.add(task);
	}
	
	public void removeTask(Task task) {
		tasks.remove(task);
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Long getWorkspaceId() {
		return workspaceId;
	}
	
	public void setWorkspaceId(Long workspaceId) {
		this.workspaceId = workspaceId;
	}

	public int getTotalTaskAmount() {
		return tasks.size();
	}

	public int getFinishedTask() {
		int result = 0;
		for (Task task : tasks) {
			if (task.isFinish()) {
				result++;
			}
		}
		return result;
	}
}