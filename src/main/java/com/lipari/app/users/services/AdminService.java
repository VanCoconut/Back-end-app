package com.lipari.app.users.services;

import java.util.ArrayList;
import java.util.List;

import com.lipari.app.commons.exception.utils.*;
import com.lipari.app.commons.validations.GeneralValidation;
import com.lipari.app.users.entities.Role;
import com.lipari.app.users.entities.RoleEnum;
import com.lipari.app.users.entities.User;
import com.lipari.app.users.repositories.AddressRepo;
import com.lipari.app.users.repositories.RoleRepo;
import com.lipari.app.users.repositories.UserRepo;
import com.lipari.app.users.validations.ChangePasswordValidation;
import com.lipari.app.users.validations.SignInValidation;
import com.lipari.app.users.validations.SignUpValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lipari.app.orders.entities.Order;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

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
	public AdminService(RoleRepo roleDao, UserRepo userDao, AddressRepo addressDao) {
		this.userRepo = userDao;
		this.addressRepo = addressDao;
		this.roleRepo = roleDao;
	}

	/*@Transactional(rollbackFor = DataException.class)
	public User addRoleForUser(Long userId) {
		try {

			User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
			user.setRole(RoleEnum.USER);
			return userRepo.save(user);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}*/

	public List<User> findAllUser() {
		return userRepo.findAll();
	}

	/*@Transactional(rollbackFor = DataException.class)
	public Role addRole(String roleName) {
		try {
			generalValidation.stringNotBlank(roleName);
			if (roleRepo.findByName(roleName.toUpperCase()).isPresent())
				throw new AlreadyExistsException("role name already exist");
			Role r = new Role(roleName.toUpperCase());
			return roleRepo.save(r);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}*/
}
