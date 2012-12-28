package org.zkoss.todoZK.vo;

import java.util.Date;

public class Task {
	private Long id;
	private String title;
	private String description;
	private Date createDate;
	private Date dueDate;
	private int priority;
	private boolean finish;
	//private List<User> member;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Task [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", description=");
		builder.append(description);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", dueDate=");
		builder.append(dueDate);
		builder.append(", priority=");
		builder.append(priority);
		builder.append(", finish=");
		builder.append(finish);
		builder.append("]");
		return builder.toString();
	}
}