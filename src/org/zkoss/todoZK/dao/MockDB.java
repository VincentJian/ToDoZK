package org.zkoss.todoZK.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.zkoss.todoZK.vo.Milestone;
import org.zkoss.todoZK.vo.Task;
import org.zkoss.todoZK.vo.Workspace;

class MockDB extends AbstractDB {
	private static List<Workspace> workspaces;
	private static Long longId = 0L;
	private static final Random random = new Random();
	private static final Date standardDate = new Date();

	public MockDB() {
		if (workspaces != null) {
			return;
		}

		workspaces = new ArrayList<Workspace>();
		Workspace ws1 = new Workspace();
		ws1.setId(genNextLong());
		ws1.setTitle("ZK Workspace");

		Workspace ws2 = new Workspace();
		ws2.setId(genNextLong());
		ws2.setTitle("Personal");
		
		genMilestone(ws1, random.nextInt(3)+2);
		genMilestone(ws2, random.nextInt(3)+2);
		
		workspaces.add(ws1);
		workspaces.add(ws2);
	}

	@Override
	public List<Workspace> getWorkspaces() {
		return workspaces;
	}
	
	@Override
	public Workspace getWorkspaceById(Long id) {
		for (Workspace ws : workspaces) {
			if (ws.getClass().equals(id)) {
				return ws;
			}
		}
		return null;
	}
	
	private void genMilestone(Workspace ws, int number) {
		for (int i=0; i<number; i++) {
			Milestone ms = new Milestone();
			ms.setId(genNextLong());
			ms.setTitle("Milestone "+ms.getId());
			genTask(ms, random.nextInt(8)+1);
			ws.addMilestone(ms);
		}
	}
	
	private void genTask(Milestone ms, int number) {
		for (int i=0; i<number; i++) {
			Task task = new Task();
			task.setId(genNextLong());
			task.setTitle("Task "+task.getId());
			task.setPriority(random.nextInt(5));
			task.setCreateDate(genDate(true));
			task.setDueDate(genDate(false));
			ms.addTask(task);
		}
	}

	private Date genDate(boolean before) {
		long diff = random.nextInt(10) * 86400L
				+ random.nextInt(24) * 3600L 
				+ random.nextInt(60) * 60L
				+ random.nextInt(60) * 1L;
		if (before) {
			diff *= -1L;
		}
		return new Date(standardDate.getTime() + diff * 1000);
	}

	private static synchronized Long genNextLong() {
		longId++;
		return longId;
	}
}