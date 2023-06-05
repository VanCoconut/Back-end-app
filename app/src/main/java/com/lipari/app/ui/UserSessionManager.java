package com.lipari.app.ui;

import com.lipari.app.model.vo.User;

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
