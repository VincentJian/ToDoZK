package org.zkoss.todoZK.vo;

import java.util.ArrayList;
import java.util.List;

public class Workspace {
	private Long id;
	private String title;
	private List<Milestone> milestones;

	public Workspace() {
		milestones = new ArrayList<Milestone>();
	}
	
	public List<Milestone> getMilestones() {
		return milestones;
	}

	public void addMilestone(Milestone ms) {
		milestones.add(ms);
	}
	
	public void removeMilestone(Milestone ms) {
		milestones.remove(ms);
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
	
	public int getTotalTaskAmount() {
		int result = 0;
		for (Milestone ms : milestones) {
			result += ms.getTotalTaskAmount();
		}
		return result;
	}
	
	public int getFinishedTask() {
		int result = 0;
		for (Milestone ms : milestones) {
			result += ms.getFinishedTask();
		}
		return result;
	}
}