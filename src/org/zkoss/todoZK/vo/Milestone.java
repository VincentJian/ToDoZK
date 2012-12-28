package org.zkoss.todoZK.vo;

import java.util.ArrayList;
import java.util.List;

public class Milestone {
	private Long id;
	private String title;
	private List<Task> tasks;
	public Milestone() {
		tasks = new ArrayList<Task>();
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
	public List<Task> getTasks() {
		return tasks;
	}
	public void addTask(Task task) {
		tasks.add(task);
	}
	public void removeTask(Task task) {
		tasks.remove(task);
	}	
}
