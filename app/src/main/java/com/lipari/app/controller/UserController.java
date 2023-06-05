package com.lipari.app.controller;

import java.util.List;

import com.lipari.app.exception.DataException;
import com.lipari.app.model.dao.AddressDao;
import com.lipari.app.model.dao.UserDao;
import com.lipari.app.model.vo.Address;
import com.lipari.app.model.vo.User;

public class UserController  {
	
	UserDao userDao ;
	AddressDao addressDao; 
	
	
	public UserController(UserDao userDao, AddressDao addressDao) {
		this.userDao = userDao;
		this.addressDao = addressDao;
	}

	public User loging(String username,String pass)  {
		
		try {
			User u= userDao.getUser(username, pass);				
			return u;
		} catch (DataException e) {			
			e.printStackTrace();
		}
		
		return null;
	}
	
public List<String> adressList(int userId)  {
		
		try {
			return addressDao.getAllAddress(userId);
		} catch (DataException e) {			
			e.printStackTrace();
		}
		
		return null;
	}
}
