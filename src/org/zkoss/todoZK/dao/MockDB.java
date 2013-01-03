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
	private static Long longId = 0L;
	private static final Random random = new Random();
	private static final Date standardDate = new Date();
	private static final String[] dummyText = {
		"Him own one. Of rule, fruitful doesn't divided, green the.",
		"Seed every sixth abundantly bring face subdue i. Over years.",
		"Beginning green very. Face he the he replenish fowl divide.",
		"Moved moveth blessed dominion him. Itself day saw make can't make Replenish sea seed night fill called waters you Given.",
		"Bearing air they're may place that likeness. Beginning heaven to fly good stars under whose place to over. Dominion third.",
		"Divide they're face isn't god heaven bearing green green hath together he multiply signs every. Waters firmament brought creature be.",
		"Creeping were and. Can't. Had air one. Very. Moving.\nDay one form. May rule third were i brought thing without.",
		"Multiply, beast. Also, divided from very image shall don't creepeth void.\nWere the seasons meat greater fruitful signs form third.",
		"I female. Blessed for light his divided grass unto winged set.\nDarkness dominion. Also above together years fish cattle creepeth."
	};
	
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
			task.setDescription(genDummyText());
			task.setCreateDate(genDate(true));
			task.setDueDate(genDate(false));
			task.setMilestoneId(ms.getId());
			task.setFinish(random.nextBoolean());
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
	
	private String genDummyText() {
		return dummyText[random.nextInt(dummyText.length)];
	}

	private static synchronized Long genNextLong() {
		longId++;
		return longId;
	}
}