package org.zkoss.todoZK.dao;

public class DBProvider {
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
	
	private static int findIndex(String[] array, String value){
		for(int i=0; i<array.length; i++){
			if(array[i].equals(value)){
				return i;
			}
		}
		return -1;
	}
}
