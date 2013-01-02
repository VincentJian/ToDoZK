package org.zkoss.todoZK.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.zkoss.todoZK.exception.MilestoneNotExist;
import org.zkoss.todoZK.exception.WorkspaceNotExist;
import org.zkoss.todoZK.vo.Milestone;
import org.zkoss.todoZK.vo.Task;
import org.zkoss.todoZK.vo.Workspace;

class MockDB extends AbstractDB {
	//simulate table in Database
	private static List<Workspace> workspaces = new ArrayList<Workspace>();
	private static List<Milestone> milestones = new ArrayList<Milestone>();
	private static List<Task> tasks = new ArrayList<Task>();
	//
	
	private static Long longId = 0L;
	private static final Random random = new Random();
	private static final Date standardDate = new Date();

	public MockDB() {
		genMockData();
	}

	@Override
	public List<Workspace> getWorkspaces() {
		return workspaces;
	}
		
	@Override
	public Workspace getWorkspaceById(Long id) throws WorkspaceNotExist {
		for (Workspace ws : workspaces) {
			if (ws.getId().equals(id)) {
				return ws;
			}
		}
		throw new WorkspaceNotExist();
	}
	
	@Override
	public Milestone getMilestoneById(Long id) throws MilestoneNotExist {
		for (Milestone ms : milestones) {
			if (ms.getId().equals(id)) {
				return ms;
			}
		}
		throw new MilestoneNotExist();
	}
	
	@Override
	public List<Milestone> getMilestonesByWorkspace(Long workspaceId) throws WorkspaceNotExist {
		return getWorkspaceById(workspaceId).getMilestones();
	}

	@Override
	public List<Task> getTasksByMilestone(Long milestoneId) throws MilestoneNotExist {
		return getMilestoneById(milestoneId).getTasks();
	}
	
	@Override
	public void addWorkspace(Workspace ws) {
		workspaces.add(ws);
	}

	@Override
	public void addMilestone(Milestone ms) throws WorkspaceNotExist {
		Workspace ws = getWorkspaceById(ms.getWorkspaceId());
		ws.addMilestone(ms);
		milestones.add(ms);
	}

	@Override
	public void addTask(Task task) throws MilestoneNotExist {
		Milestone ms = getMilestoneById(task.getMilestoneId());
		ms.addTask(task);
		tasks.add(task);
	}

	////////////////////////////////////////////////////////////////////
	private void genMockData() {
		Workspace ws1 = new Workspace();
		ws1.setId(genNextLong());
		ws1.setTitle("ZK Workspace");
		addWorkspace(ws1);
		genMilestone(ws1, random.nextInt(3)+2);

		Workspace ws2 = new Workspace();
		ws2.setId(genNextLong());
		ws2.setTitle("Personal");
		addWorkspace(ws2);
		genMilestone(ws2, random.nextInt(3)+2);
	}

	private void genMilestone(Workspace ws, int number) {
		for (int i=0; i<number; i++) {
			Milestone ms = new Milestone();
			ms.setId(genNextLong());
			ms.setTitle("Milestone "+ms.getId());
			ms.setWorkspaceId(ws.getId());
			try {
				addMilestone(ms);
			} catch (WorkspaceNotExist e) {
				//never happen
			}
			genTask(ms, random.nextInt(8)+1);
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
			task.setMilestoneId(ms.getId());
			try {
				addTask(task);
			} catch (MilestoneNotExist e) {
				//never happen
			}
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