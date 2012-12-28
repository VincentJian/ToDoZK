package org.zkoss.todoZK.dao;

import java.util.List;

import org.zkoss.todoZK.dao.MockDB;
import org.zkoss.todoZK.vo.Workspace;

public abstract class AbstractDB {
	public static final String[] ACCEPT_TYPE = {"mock"};
	public static AbstractDB instance;
	public static AbstractDB getInstance(String type){
		if(instance==null){
			switch(findIndex(ACCEPT_TYPE, type)){
			case 0:
				instance = new MockDB();
				break;
			default:
				instance = new MockDB();
			}
		}
		return instance;
	}
	
	public static AbstractDB getInstance() {
		return getInstance(null);
	}
	
	public abstract List<Workspace> getWorkspaces();
	public abstract Workspace getWorkspaceById(Long id);
	
	private static int findIndex(String[] array, String value){
		for(int i=0; i<array.length; i++){
			if(array[i].equals(value)){
				return i;
			}
		}
		return -1;
	}
}