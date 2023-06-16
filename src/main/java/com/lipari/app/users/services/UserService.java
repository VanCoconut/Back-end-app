package com.lipari.app.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.app.commons.exception.utils.AlreadyExistsException;
import com.lipari.app.commons.exception.utils.AuthException;
import com.lipari.app.commons.exception.utils.InvalidDataException;
import com.lipari.app.commons.exception.utils.NotFoundException;
import com.lipari.app.commons.exception.utils.ValidationException;
import com.lipari.app.commons.validations.GeneralValidation;
import com.lipari.app.users.entities.Address;
import com.lipari.app.users.entities.Role;
import com.lipari.app.users.entities.User;
import com.lipari.app.users.repositories.AddressRepo;
import com.lipari.app.users.repositories.RoleRepo;
import com.lipari.app.users.repositories.UserRepo;
import com.lipari.app.users.validations.ChangePasswordValidation;
import com.lipari.app.users.validations.SignInValidation;
import com.lipari.app.users.validations.SignUpValidation;

@Service
public class UserService {

	private UserRepo userDao;
	private AddressRepo addressDao;
	private RoleRepo roleDao;

	@Autowired
	private SignInValidation signInValidation;

	@Autowired
	private SignUpValidation signUpUpValidation;

	@Autowired
	private GeneralValidation generalValidation;

	@Autowired
	private ChangePasswordValidation changePasswordValidation;
	

	@Autowired
	public UserService(RoleRepo roleDao, UserRepo userDao, AddressRepo addressDao) {
		this.userDao = userDao;
		this.addressDao = addressDao;
		this.roleDao = roleDao;
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
			user = userDao.findById(user.getId()).orElseThrow(() -> new NotFoundException("user not found"));
			userDao.updateUser(user.getId(), user.getNome(), user.getCognome(), user.getUsername(), user.getPassword(),
					user.getEmail(), user.getRole());
			return userDao.findById(user.getId()).get();
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}

	}

	public User changePassword(int id, String oldPsw, String newPsw, String confPsw) {
		try {
			changePasswordValidation.validation(oldPsw, newPsw, confPsw);
			User user = userDao.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
			user.setPassword(newPsw);
			userDao.updateUser(user.getId(), user.getNome(), user.getCognome(), user.getUsername(),
					user.getPassword(), user.getEmail(), user.getRole());
			return user;
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}

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
			if (addressDao.addressAlreadyExist(userId,newAddress)!=null) throw new AlreadyExistsException("id already exist");
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

	public void cancelAddress(int id) {
		try {
			generalValidation.positiveInt(id);
			Address a = addressDao.getAddressById(id).orElseThrow(() -> new NotFoundException("address not found"));
			// Address a = addressDao.findById(id).orElseThrow(() -> new
			// NotFoundException("address not found"));
			addressDao.delete(a);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//ROLE
	
	public List<Role> findAllRole() {
		return roleDao.findAll();
	}

	public Role findRoleById(int id) {
		try {
			generalValidation.positiveInt(id);
			return roleDao.findById(id).orElseThrow(() -> new NotFoundException("address not found"));
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}
	
	public Role findRoleByDescription(String d) {
		try {
			generalValidation.stringNotBlank(d);
			return roleDao.getRoleByDescription(d).orElseThrow(() -> new NotFoundException("description not found"));
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}
	
	public Role addRole(Role role) {
		try {
			generalValidation.positiveInt(role.getId());
			generalValidation.stringNotBlank(role.getDescrizione());
			if (roleDao.roleAlreadyExist(role.getId(),role.getDescrizione())!=null) throw new AlreadyExistsException("id o description already exist");
			Role r = new Role(role.getId(), role.getDescrizione());
			return roleDao.save(r);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}
	
	public Role updateRole(int oldId, Role role) {
		try {
			generalValidation.positiveInt(role.getId());
			generalValidation.positiveInt(oldId);
			generalValidation.stringNotBlank(role.getDescrizione());
			roleDao.findById(oldId).orElseThrow(() -> new NotFoundException("id not found"));
			if (roleDao.roleAlreadyExist(role.getId(),role.getDescrizione())!=null) throw new AlreadyExistsException("id o description already exist");
			roleDao.updateRole(oldId, role.getId(), role.getDescrizione());
			return roleDao.findById(role.getId()).get();
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}

	}
	
	public void cancelRoleById(int id) {
		try {
			generalValidation.positiveInt(id);
			Role r = roleDao.findById(id).orElseThrow(() -> new NotFoundException("id not found"));
			roleDao.delete(r);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void cancelRoleByDescription(String descr) {
		try {
			generalValidation.stringNotBlank(descr);
			Role r = roleDao.getRoleByDescription(descr).orElseThrow(() -> new NotFoundException("description not found"));
			roleDao.delete(r);;
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

}
