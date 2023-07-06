package com.lipari.app.users.services;

import com.lipari.app.commons.exception.utils.*;
import com.lipari.app.commons.validations.GeneralValidation;
import com.lipari.app.users.dto.RegisterDto;
import com.lipari.app.users.entities.Address;
import com.lipari.app.users.entities.AppUser;
import com.lipari.app.users.entities.Role;
import com.lipari.app.users.repositories.AddressRepo;
import com.lipari.app.users.repositories.RoleRepo;
import com.lipari.app.users.repositories.UserRepo;
import com.lipari.app.users.validations.ChangePasswordValidation;
import com.lipari.app.users.validations.SignInValidation;
import com.lipari.app.users.validations.SignUpValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * The type User service.
 */
@RequiredArgsConstructor
@Service
public class UserService {

    /**
     * The User repo.
     */
    private final UserRepo userRepo;
    /**
     * The Address repo.
     */
    private final AddressRepo addressRepo;
    /**
     * The Role repo.
     */
    private final RoleRepo roleRepo;
    /**
     * The Password encoder.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * The Sign in validation.
     */
    @Autowired
	private SignInValidation signInValidation;

    /**
     * The Sign up up validation.
     */
    @Autowired
	private SignUpValidation signUpUpValidation;

    /**
     * The General validation.
     */
    @Autowired
	private GeneralValidation generalValidation;

    /**
     * The Change password validation.
     */
    @Autowired
	private ChangePasswordValidation changePasswordValidation;

    /**
     * Find user by id app user.
     *
     * @param id the id
     * @return the app user
     */
// USER
	public AppUser findUserById(Long id) {

		try {
			generalValidation.positiveLong(id);
			AppUser u = userRepo.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
			return u;
		} catch (InvalidDataException e) {
			throw new AuthException("Accesso non autorizzato " + e.getMessage());
		}
	}

    /**
     * Loging app user.
     *
     * @param username the username
     * @param pass     the pass
     * @return the app user
     */
    public AppUser loging(String username, String pass) {

		try {
			signInValidation.validation(username, pass);
			AppUser u = userRepo.getUserByCredential(username, pass);
			if (u == null)
				throw new AuthException("Accesso non autorizzato password o username errati");
			return u;
		} catch (InvalidDataException e) {
			throw new InvalidDataException("Accesso non autorizzato " + e.getMessage());
		}
	}

    /**
     * Create user app user.
     *
     * @param registerDto the register dto
     * @return the app user
     */
    @Transactional(rollbackFor = DataException.class)
	public AppUser createUser(RegisterDto registerDto) {
		try {
			//signUpUpValidation.validation(registerDto);
			AppUser user = new AppUser();
			user.setNome(registerDto.getFirstname());
			user.setCognome(registerDto.getLastname());
			user.setUsername(registerDto.getUsername());
			user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
			user.getRoles().add(
					roleRepo.findByName(registerDto.getRole()).orElseThrow(()->new NotFoundException("role name not found")));
			return userRepo.save(user);
		} catch (InvalidDataException e) {
			throw new ValidationException(e.getMessage());
		}
	}

    /**
     * Change user app user.
     *
     * @param id      the id
     * @param appUser the app user
     * @return the app user
     */
    @Transactional(rollbackFor = DataException.class)
	public AppUser changeUser(Long id, AppUser appUser) {
		try {
			signUpUpValidation.validation(appUser);
			AppUser u = userRepo.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
			// userRepo.updateUser(id, user.getNome(), user.getCognome(),
			// user.getUsername(), user.getPassword(),
			// user.getEmail(), user.getRoles());
			appUser.setId(id);
			if (appUser.getBasket() == null)
				appUser.setBasket(u.getBasket());
			if (appUser.getRoles() == null)
				appUser.setRoles(u.getRoles());
			if (appUser.getAddressList() == null)
				appUser.setAddressList(u.getAddressList());
			return userRepo.save(appUser);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}

	}

    /**
     * Change password app user.
     *
     * @param id      the id
     * @param oldPsw  the old psw
     * @param newPsw  the new psw
     * @param confPsw the conf psw
     * @return the app user
     */
    @Transactional(rollbackFor = DataException.class)
	public AppUser changePassword(Long id, String oldPsw, String newPsw, String confPsw) {
		try {
			changePasswordValidation.validation(oldPsw, newPsw, confPsw);
			AppUser appUser = userRepo.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
			appUser.setPassword(newPsw);
			return userRepo.save(appUser);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}

    /**
     * Cancel user.
     *
     * @param userId the user id
     */
    @Transactional(rollbackFor = DataException.class)
	public void cancelUser(Long userId) {
		try {
			generalValidation.positiveLong(userId);
			if (userRepo.getReferenceById(userId) == null)
				throw new NotFoundException("user not found");
			userRepo.deleteById(userId);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}

	}

	// ADDRESS

    /**
     * Adress list list.
     *
     * @param userId the user id
     * @return the list
     */
    public List<String> adressList(Long userId) {
		return addressRepo.getAllUserAddress(userId);
	}

    /**
     * Add address app user.
     *
     * @param userId     the user id
     * @param newAddress the new address
     * @return the app user
     */
    @Transactional(rollbackFor = DataException.class)
	public AppUser addAddress(Long userId, String newAddress) {
		try {
			generalValidation.positiveLong(userId);
			generalValidation.stringNotBlank(newAddress);
			if (addressRepo.addressAlreadyExist(userId, newAddress) != null)
				throw new AlreadyExistsException("address' id already exist");
			AppUser appUser = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
			Address a = new Address(newAddress);
			List<Address> l = new ArrayList<>();
			l.add(a);
			appUser.getAddressList().addAll(l);
			return userRepo.save(appUser);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}

    /**
     * Gets address by id.
     *
     * @param id the id
     * @return the address by id
     */
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

    /**
     * Cancel address.
     *
     * @param id the id
     */
    @Transactional(rollbackFor = DataException.class)
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

	// ROLE

    /**
     * Find all role list.
     *
     * @return the list
     */
    public List<Role> findAllRole() {
		return roleRepo.findAll();
	}

    /**
     * Find role by id role.
     *
     * @param id the id
     * @return the role
     */
    public Role findRoleById(Long id) {
		try {
			generalValidation.positiveLong(id);
			return roleRepo.findById(id).orElseThrow(() -> new NotFoundException("role not found"));
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}

    /**
     * Find role by description role.
     *
     * @param d the d
     * @return the role
     */
    public Role findRoleByDescription(String d) {
		try {
			generalValidation.stringNotBlank(d);
			return roleRepo.getRoleByDescription(d).orElseThrow(() -> new NotFoundException("description not found"));
		} catch (InvalidDataException e) {
			throw new ValidationException("Operazione negata " + e.getMessage());
		}
	}

    /**
     * Add role role.
     *
     * @param name the name
     * @return the role
     */
    @Transactional(rollbackFor = DataException.class)
	public Role addRole(String name) {
		try {
			generalValidation.stringNotBlank(name);
			//if (roleRepo.roleAlreadyExist(role.getId(), role.getName()) != null)
			//	throw new AlreadyExistsException("id and/or description already exist");
			Role r = new Role(null, name);
			return roleRepo.save(r);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}
	}

    /**
     * Update role role.
     *
     * @param oldId the old id
     * @param role  the role
     * @return the role
     */
    @Transactional(rollbackFor = DataException.class)
	public Role updateRole(Long oldId, Role role) {
		try {
			generalValidation.positiveLong(role.getId());
			generalValidation.positiveLong(oldId);
			generalValidation.stringNotBlank(role.getName());
			roleRepo.findById(oldId).orElseThrow(() -> new NotFoundException("id not found"));
			if (roleRepo.roleAlreadyExist(role.getId(), role.getName()) != null)
				throw new AlreadyExistsException("id and/or description already exist");
			roleRepo.updateRole(oldId, role.getId(), role.getName());
			return roleRepo.findById(role.getId()).get();
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		}

	}

    /**
     * Cancel role by id.
     *
     * @param id the id
     */
    @Transactional(rollbackFor = DataException.class)
	public void cancelRoleById(Long id) {
		try {
			generalValidation.positiveLong(id);
			Role r = roleRepo.findById(id).orElseThrow(() -> new NotFoundException("id not found"));
			List<AppUser> appUserList = userRepo.findAll();

//			for (User user : userList) {
//				if (user.getRoles().getId() == id) {
//					user.setRole(null);
//				}
//			}

			roleRepo.delete(r);
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

    /**
     * Cancel role by description.
     *
     * @param descr the descr
     */
    @Transactional(rollbackFor = DataException.class)
	public void cancelRoleByDescription(String descr) {
		try {
			generalValidation.stringNotBlank(descr);
			Role r = roleRepo.getRoleByDescription(descr)
					.orElseThrow(() -> new NotFoundException("description not found"));
			List<AppUser> appUserList = userRepo.findAll();

//			for (User user : userList) {
//				if (user.getRoles().getName().equals(descr)) {
//					user.setRole(null);
//				}
//			}
			roleRepo.delete(r);
			;
		} catch (InvalidDataException e) {
			throw new ValidationException("Operzione negata " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
