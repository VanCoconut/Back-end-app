package com.lipari.app.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lipari.app.commons.exception.utils.AuthException;
import com.lipari.app.commons.exception.utils.InvalidDataException;
import com.lipari.app.commons.exception.utils.NotFoundException;
import com.lipari.app.commons.exception.utils.ValidationException;
import com.lipari.app.commons.validations.GeneralValidation;
import com.lipari.app.users.entities.Address;
import com.lipari.app.users.entities.User;
import com.lipari.app.users.repositories.AddressDao;
import com.lipari.app.users.repositories.UserDao;
import com.lipari.app.users.validations.ChangePasswordValidation;
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
	private ChangePasswordValidation changePasswordValidation;

	@Autowired
	public UserService(UserDao userDao, AddressDao addressDao) {
		this.userDao = userDao;
		this.addressDao = addressDao;
	}

	// USER

	public User findUserById(int id) {

		try {
			generalValidation.positiveInt(id);
			User u = userDao.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
			return u;
		} catch (InvalidDataException e) {
			throw new AuthException("Accesso non autorizzato " + e.getMessage());
		}
	}

	public User loging(String username, String pass) {

		try {
			signInValidation.validation(username, pass);
			User u = userDao.getUserByCredential(username, pass);
			if (u == null)
				throw new AuthException("Accesso non autorizzato password o username errati");
			return u;
		} catch (InvalidDataException e) {
			throw new InvalidDataException("Accesso non autorizzato " + e.getMessage());
		}
	}

	public User createUser(User user) {
		try {
			signUpUpValidation.validation(user);
			return userDao.save(user);
		} catch (InvalidDataException e) {
			throw new ValidationException(e.getMessage());
		}
	}

	public User changeUser(User user) {
		try {
			signUpUpValidation.validation(user);
			userDao.updateUser(user.getId(), user.getNome(), user.getCognome(), user.getUsername(), user.getPassword(),
					user.getEmail(), user.getRole());
			return userDao.findById(user.getId()).get();
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}

	}

	/*
	 * public boolean changePassword(User user, String oldPsw, String newPsw, String
	 * confPsw) { try { changePasswordValidation.validation(oldPsw,newPsw,confPsw);
	 * user.setPassword(newPsw); return userDao.updateUser(user.getId(),
	 * user.getNome(), user.getCognome(), user.getUsername(), user.getPassword(),
	 * user.getEmail(), user.getRole()); } catch (InvalidDataException e) { throw
	 * new ValidationException("Operzione negata " + e.getMessage()); }
	 * 
	 * }
	 */

	public void cancelUser(int userId) {
		try {
			generalValidation.positiveInt(userId);
			if (userDao.getReferenceById(userId) != null)
				throw new NotFoundException("user not found");
			userDao.deleteById(userId);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}

	}

	// ADDRESS

	public List<String> adressList(int userId) {
		return addressDao.getAllUserAddress(userId);
	}

	public Address addAddress(int userId, String newAddress) {
		try {
			generalValidation.positiveInt(userId);
			generalValidation.stringNotBlank(newAddress);
			Address a = new Address(userId, newAddress);
			return addressDao.save(a);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}
	
	public Address getAddressById(int id) {
		try {
			generalValidation.positiveInt(id);
		 Address a = addressDao.getAddressById(id).orElseThrow(() -> new NotFoundException("address not found"));
		 return a;
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Transactional
	public void cancelAddress(int id) {
		try {
			generalValidation.positiveInt(id);
			Address a = addressDao.getAddressById(id).orElseThrow(() -> new NotFoundException("address not found"));
			//Address a = addressDao.findById(id).orElseThrow(() -> new NotFoundException("address not found"));
			addressDao.delete(a);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
