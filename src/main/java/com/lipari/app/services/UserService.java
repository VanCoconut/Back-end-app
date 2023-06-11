package com.lipari.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.app.exception.DataException;
import com.lipari.app.model.dao.AddressDao;
import com.lipari.app.model.dao.UserDao;
import com.lipari.app.model.vo.Address;
import com.lipari.app.model.vo.User;

@Service
public class UserService {

	UserDao userDao;
	AddressDao addressDao;

	@Autowired
	public UserService(UserDao userDao, AddressDao addressDao) {
		this.userDao = userDao;
		this.addressDao = addressDao;
	}

	public String hello() {
		return "hello";
		
	}
	
	public User loging(String username, String pass) {

		try {
			User u = userDao.getUser(username, pass);
			return u;
		} catch (DataException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<String> adressList(int userId) {

		try {
			return addressDao.getAllAddress(userId);
		} catch (DataException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public boolean changeUser(User user) {
		try {
			return userDao.updateUser(user.getId(), user.getNome(), user.getCognome(), user.getUsername(), user.getPassword(),
					user.getEmail(), user.getRole());
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean changeUserName(User user, String newName) {
		try {
			return userDao.updateUser(user.getId(), newName, user.getCognome(), user.getUsername(), user.getPassword(),
					user.getEmail(), user.getRole());
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean changeUserSurname(User user, String newSurname) {
		try {
			return userDao.updateUser(user.getId(), user.getNome(), newSurname, user.getUsername(), user.getPassword(),
					user.getEmail(), user.getRole());
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean changeUserEmail(User user, String newEmail) {
		try {
			return userDao.updateUser(user.getId(), user.getNome(), user.getCognome(), user.getUsername(),
					user.getPassword(), newEmail, user.getRole());
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean changeUserPassword(User user, String newPsw) {
		try {
			return userDao.updateUser(user.getId(), user.getNome(), user.getCognome(), user.getUsername(), newPsw,
					user.getEmail(), user.getRole());
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean changeUserRole(User user, int newRole) {
		try {
			return userDao.updateUser(user.getId(), user.getNome(), user.getCognome(), user.getUsername(),
					user.getPassword(), user.getEmail(), newRole);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean createUser(User user) {
		try {
			return userDao.setUser(user.getNome(), user.getCognome(), user.getUsername(),
					user.getPassword(), user.getEmail(), user.getRole());
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean cancelUser(int userId) {
		try {
			return userDao.deleteUser(userId);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addAddress(int userId, String newAddress) {
		try {
			return addressDao.setAddress(userId, newAddress);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addAddress(Address address) {
		try {
			return addressDao.setAddress(address.getUserId(), address.getIndirizzo());
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean cancelAddress(int userId, String newAddress) {
		try {
			return addressDao.deleteAddress(userId, newAddress);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
