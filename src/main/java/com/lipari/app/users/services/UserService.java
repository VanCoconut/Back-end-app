package com.lipari.app.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.app.commons.exception.utils.AuthException;
import com.lipari.app.commons.exception.utils.InvalidDataException;
import com.lipari.app.commons.exception.utils.ValidationException;
import com.lipari.app.commons.validations.GeneralValidation;
import com.lipari.app.users.entities.User;
import com.lipari.app.users.repositories.AddressDao;
import com.lipari.app.users.repositories.UserDao;
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

	// USER

	public User findUserById(int id) {

		try {
			generalValidation.positiveInt(id);
			User u = userDao.getUserById(id);
			return u;
		} catch (InvalidDataException e) {
			throw new AuthException("Accesso non autorizzato " + e.getMessage());
		}
	}

	public User loging(String username, String pass) {

		try {
			signInValidation.validation(username, pass);
			User u = userDao.getUser(username, pass);
			return u;
		} catch (InvalidDataException e) {
			throw new AuthException("Accesso non autorizzato " + e.getMessage());
		}
	}

	public boolean createUser(User user) {
		try {
			signUpUpValidation.validation(user);
			return userDao.setUser(user.getNome(), user.getCognome(), user.getUsername(), user.getPassword(),
					user.getEmail(), user.getRole());
		} catch (InvalidDataException e) {
			throw new ValidationException(e.getMessage());
		}
	}

	public boolean changeUser(User user) {
		try {
			signUpUpValidation.validation(user);
			return userDao.updateUser(user.getId(), user.getNome(), user.getCognome(), user.getUsername(),
					user.getPassword(), user.getEmail(), user.getRole());
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}

	}

	public boolean cancelUser(int userId) {
		try {
			generalValidation.positiveInt(userId);
			return userDao.deleteUser(userId);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}

	}

	// ADDRESS

	public List<String> adressList(int userId) {
		return addressDao.getAllAddress(userId);
	}

	public boolean addAddress(int userId, String newAddress) {
		try {
			generalValidation.positiveInt(userId);
			generalValidation.stringNotBlank(newAddress);
			return addressDao.setAddress(userId, newAddress);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}

	public boolean cancelAddress(int id) {
		try {
			generalValidation.positiveInt(id);
			return addressDao.deleteAddress(id);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}

}
