package org.zkoss.todoZK.viewmodel;

import java.util.List;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.todoZK.dao.AbstractDB;
import org.zkoss.todoZK.dao.DBProvider;
import org.zkoss.todoZK.exception.WorkspaceNotExist;
import org.zkoss.todoZK.vo.Milestone;
import org.zkoss.zul.Div;

public class CardViewVM {
	
	private AbstractDB db = DBProvider.getInstance();
	private List<Milestone> milestones;
	private Converter<String, Integer, Div> priorityConverter = new PriorityConverter();

	public CardViewVM() {
		try {
			milestones = db.getMilestonesByWorkspace(new Long(1));
		} catch (WorkspaceNotExist e) {
			e.printStackTrace();
		}
	}
	
	public List<Milestone> getMilestones() {
		return milestones;
	}
	
	@GlobalCommand @NotifyChange("milestones")
	public void changeWorkspace(long id) {
		//TODO: sidebar should trigger global command
	}
	
	public Converter<String, Integer, Div> getPriorityConverter() {
		return priorityConverter;
	}
	
	public class PriorityConverter implements Converter<String, Integer, Div> {
		public Integer coerceToBean(String sclass, Div div, BindContext ctx) {
			return null;
		}
		
		public String coerceToUi(Integer priority, Div div, BindContext ctx) {
			switch (priority) {
			case 0:
				return "priority routine";
			case 1:
				return "priority essential";
			case 2:
				return "priority vital";
			case 3:
				return "priority important";
			case 4:
				return "priority urgent";
			default:
				return null;
			}
		}
	}
}
