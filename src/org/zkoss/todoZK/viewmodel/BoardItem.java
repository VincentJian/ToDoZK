package org.zkoss.todoZK.viewmodel;


public class BoardItem {
	public static final int WORKSPACE_TYPE = -1;
	public static final int MILESTONE_TYPE = -2;
	
	private String title;
	private Long id;
	private int type;
	
	public BoardItem(Long id, String title, int type) {
		setId(id);
		setTitle(title);
		setType(type);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}
	public boolean isWorkspace() {
		return type == WORKSPACE_TYPE;
	}
	public boolean isMilestone() {
		return type == MILESTONE_TYPE;
	}
}