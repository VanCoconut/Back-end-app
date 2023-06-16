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

	private UserRepo userRepo;
	private AddressRepo addressRepo;
	private RoleRepo roleRepo;

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
		this.userRepo = userDao;
		this.addressRepo = addressDao;
		this.roleRepo = roleDao;
	}

	// USER

	public User findUserById(Long id) {

		try {
			generalValidation.positiveLong(id);
			User u = userRepo.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
			return u;
		} catch (InvalidDataException e) {
			throw new AuthException("Accesso non autorizzato " + e.getMessage());
		}
	}

	public User loging(String username, String pass) {

		try {
			signInValidation.validation(username, pass);
			User u = userRepo.getUserByCredential(username, pass);
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
			return userRepo.save(user);
		} catch (InvalidDataException e) {
			throw new ValidationException(e.getMessage());
		}
	}

	public User changeUser(User user) {
		try {
			signUpUpValidation.validation(user);
			user = userRepo.findById(user.getId()).orElseThrow(() -> new NotFoundException("user not found"));
			userRepo.updateUser(user.getId(), user.getNome(), user.getCognome(), user.getUsername(), user.getPassword(),
					user.getEmail(), user.getRole());
			return userRepo.findById(user.getId()).get();
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}

	}

	public User changePassword(Long id, String oldPsw, String newPsw, String confPsw) {
		try {
			changePasswordValidation.validation(oldPsw, newPsw, confPsw);
			User user = userRepo.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
			user.setPassword(newPsw);
			userRepo.updateUser(user.getId(), user.getNome(), user.getCognome(), user.getUsername(),
					user.getPassword(), user.getEmail(), user.getRole());
			return user;
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}

	public void cancelUser(Long userId) {
		try {
			generalValidation.positiveLong(userId);
			if (userRepo.getReferenceById(userId) != null)
				throw new NotFoundException("user not found");
			userRepo.deleteById(userId);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}

	}

	// ADDRESS

	public List<String> adressList(Long userId) {
		return addressRepo.getAllUserAddress(userId);
	}

	public User addAddress(Long userId, String newAddress) {
		try {
			generalValidation.positiveLong(userId);
			generalValidation.stringNotBlank(newAddress);
			if (addressRepo.addressAlreadyExist(userId,newAddress)!=null) throw new AlreadyExistsException("id already exist");
			User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException(""));
			Address a = new Address(newAddress);
			user.addAddress(a);
			return userRepo.save(user);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}

	public Address getAddressById(Long id) {
		try {
			generalValidation.positiveLong(id);
			Address a = addressRepo.getAddressById(id).orElseThrow(() -> new NotFoundException("address not found"));
			return a;
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void cancelAddress(Long id) {
		try {
			generalValidation.positiveLong(id);
			Address a = addressRepo.getAddressById(id).orElseThrow(() -> new NotFoundException("address not found"));
			addressRepo.delete(a);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//ROLE
	
	public List<Role> findAllRole() {
		return roleRepo.findAll();
	}

	public Role findRoleById(Long id) {
		try {
			generalValidation.positiveLong(id);
			return roleRepo.findById(id).orElseThrow(() -> new NotFoundException("address not found"));
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}
	
	public Role findRoleByDescription(String d) {
		try {
			generalValidation.stringNotBlank(d);
			return roleRepo.getRoleByDescription(d).orElseThrow(() -> new NotFoundException("description not found"));
		} catch (InvalidDataException e) {
			throw new ValidationException("Operazione negata " + e.getMessage());
		}
	}
	
	public Role addRole(Role role) {
		try {
			generalValidation.positiveLong(role.getId());
			generalValidation.stringNotBlank(role.getDescrizione());
			if (roleRepo.roleAlreadyExist(role.getId(),role.getDescrizione())!=null) throw new AlreadyExistsException("id o description already exist");
			Role r = new Role(role.getDescrizione());
			return roleRepo.save(r);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}
	
	public Role updateRole(Long oldId, Role role) {
		try {
			generalValidation.positiveLong(role.getId());
			generalValidation.positiveLong(oldId);
			generalValidation.stringNotBlank(role.getDescrizione());
			roleRepo.findById(oldId).orElseThrow(() -> new NotFoundException("id not found"));
			if (roleRepo.roleAlreadyExist(role.getId(),role.getDescrizione())!=null) throw new AlreadyExistsException("id o description already exist");
			roleRepo.updateRole(oldId, role.getId(), role.getDescrizione());
			return roleRepo.findById(role.getId()).get();
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}

	}
	
	public void cancelRoleById(Long id) {
		try {
			generalValidation.positiveLong(id);
			Role r = roleRepo.findById(id).orElseThrow(() -> new NotFoundException("id not found"));
			roleRepo.delete(r);
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
			Role r = roleRepo.getRoleByDescription(descr).orElseThrow(() -> new NotFoundException("description not found"));
			roleRepo.delete(r);;
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

}
