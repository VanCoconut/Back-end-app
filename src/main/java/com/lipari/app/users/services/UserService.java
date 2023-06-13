package com.lipari.app.users.services;

import java.util.List;

import com.lipari.app.commons.exception.utils.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.app.exception.AuthException;
import com.lipari.app.exception.ValidationException;
import com.lipari.app.users.entities.Address;
import com.lipari.app.users.entities.User;
import com.lipari.app.users.repositories.AddressDao;
import com.lipari.app.users.repositories.UserDao;
import com.lipari.app.commons.validations.GeneralValidation;
import com.lipari.app.users.validations.SignInValidation;
import com.lipari.app.users.validations.SignUpValidation;

@Service
public class UserService {

	private UserDao userDao;
	private AddressDao addressDao;

	@Autowired
	private SignInValidation signInValidation;

	@Autowired
	private SignUpValidation signUpUpValidation;

	@Autowired
	private GeneralValidation generalValidation;

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
			signInValidation.validation(username, pass);
			User u = userDao.getUser(username, pass);
			return u;
		} catch (DataException e) {
			throw new DataException("Accesso non autorizzato " + e.getMessage());
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new AuthException("Accesso non autorizzato " + e.getMessage());
		}
	}

	public List<String> adressList(int userId) {

		try {
			return addressDao.getAllAddress(userId);
		} catch (DataException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean createUser(User user) {
		try {
			signUpUpValidation.validation(user);
			return userDao.setUser(user.getNome(), user.getCognome(), user.getUsername(), user.getPassword(),
					user.getEmail(), user.getRole());
		} catch (DataException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			throw new ValidationException(e.getMessage());
		}
		return false;
	}

	public boolean changeUser(User user) {
		try {
			signUpUpValidation.validation(user);
			return userDao.updateUser(user.getId(), user.getNome(), user.getCognome(), user.getUsername(),
					user.getPassword(), user.getEmail(), user.getRole());
		} catch (DataException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			throw new ValidationException(e.getMessage());
		}
		return false;
	}

	public boolean cancelUser(int userId) {
		try {
			generalValidation.positiveInt(userId);
			return userDao.deleteUser(userId);
		} catch (DataException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			throw new ValidationException(e.getMessage());
		}
		return false;
	}

	public boolean addAddress(int userId, String newAddress) {
		try {
			generalValidation.positiveInt(userId);
			generalValidation.stringNotBlank(newAddress);
			return addressDao.setAddress(userId, newAddress);
		} catch (DataException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			throw new ValidationException(e.getMessage());
		}
		return false;
	}

	public boolean addAddress(Address address) {
		try {
			generalValidation.positiveInt(address.getUserId());
			generalValidation.stringNotBlank(address.getIndirizzo());
			return addressDao.setAddress(address.getUserId(), address.getIndirizzo());
		} catch (DataException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			throw new ValidationException(e.getMessage());
		}
		return false;
	}

	public boolean cancelAddress(int userId, String newAddress) {
		try {
			generalValidation.positiveInt(userId);
			generalValidation.stringNotBlank(newAddress);
			return addressDao.deleteAddress(userId, newAddress);
		} catch (DataException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			throw new ValidationException(e.getMessage());
		}
		return false;
	}

	public boolean changeUserName(User user, String newName) {
		try {
			return userDao.updateUser(user.getId(), newName, user.getCognome(), user.getUsername(), user.getPassword(),
					user.getEmail(), user.getRole());
		} catch (DataException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean changeUserSurname(User user, String newSurname) {
		try {
			return userDao.updateUser(user.getId(), user.getNome(), newSurname, user.getUsername(), user.getPassword(),
					user.getEmail(), user.getRole());
		} catch (DataException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean changeUserEmail(User user, String newEmail) {
		try {
			return userDao.updateUser(user.getId(), user.getNome(), user.getCognome(), user.getUsername(),
					user.getPassword(), newEmail, user.getRole());
		} catch (DataException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean changeUserPassword(User user, String newPsw) {
		try {
			return userDao.updateUser(user.getId(), user.getNome(), user.getCognome(), user.getUsername(), newPsw,
					user.getEmail(), user.getRole());
		} catch (DataException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean changeUserRole(User user, int newRole) {
		try {
			return userDao.updateUser(user.getId(), user.getNome(), user.getCognome(), user.getUsername(),
					user.getPassword(), user.getEmail(), newRole);
		} catch (DataException e) {
			e.printStackTrace();
		}
		return false;
	}

}
