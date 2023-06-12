package com.lipari.app.main;

import com.lipari.app.users.entities.User;

public class UserSessionManager {

	private static UserSessionManager _instance;
	private User user;
	
	private UserSessionManager() {
		
	}
	
	public static UserSessionManager getInstance() {
		if(_instance==null) {
			_instance= new UserSessionManager();
		}
		return _instance;
	}
	
	public void storeUser(User user) {
		this.user=user;
	}
	public User getUser() {
		return this.user;
	}

}
