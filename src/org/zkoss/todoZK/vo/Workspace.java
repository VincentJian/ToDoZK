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
	public List<Milestone> getMilestones() {
		return milestones;
	}
	public void addMilestone(Milestone milestone) {
		milestones.add(milestone);
	}
	public void removeMilestone(Milestone milestone) {
		milestones.remove(milestone);
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Workspace [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append("\n");
		for(Milestone ms : milestones){
			builder.append("\tMilestone [id=");
			builder.append(ms.getId());
			builder.append(", title=");
			builder.append(ms.getTitle());
			builder.append("\n");
			for(Task task : ms.getTasks()){
				builder.append("\t\t"+task+"\n");				
			}
			builder.append("\n\t]\n");
		}
		builder.append("]\n");
		return builder.toString();
	}

}
