package com.lipari.app.users.services;

import com.lipari.app.commons.exception.utils.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional(rollbackFor = DataException.class)
    public User createUser(User user) {
        try {
            signUpUpValidation.validation(user);
            return userRepo.save(user);
        } catch (InvalidDataException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = DataException.class)
    public User changeUser(Long id, User user) {
        try {
            signUpUpValidation.validation(user);
            User u = userRepo.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
            // userRepo.updateUser(id, user.getNome(), user.getCognome(),
            // user.getUsername(), user.getPassword(),
            // user.getEmail(), user.getRole());
            user.setId(id);
            if (user.getBasket() == null)
                user.setBasket(u.getBasket());
            //if (user.getRoles() == null)
            //	user.setRoles(u.getRoles());
            if (user.getAddressList() == null)
                user.setAddressList(u.getAddressList());
            return userRepo.save(user);
        } catch (InvalidDataException e) {
            throw new ValidationException("Operzione negata " + e.getMessage());
        }

    }

    @Transactional(rollbackFor = DataException.class)
    public User changePassword(Long id, String oldPsw, String newPsw, String confPsw) {
        try {
            changePasswordValidation.validation(oldPsw, newPsw, confPsw);
            User user = userRepo.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
            user.setPassword(newPsw);
            return userRepo.save(user);
        } catch (InvalidDataException e) {
            throw new ValidationException("Operzione negata " + e.getMessage());
        }
    }

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

    public List<String> adressList(Long userId) {
        return addressRepo.getAllUserAddress(userId);
    }

    @Transactional(rollbackFor = DataException.class)
    public User addAddress(Long userId, String newAddress) {
        try {
            generalValidation.positiveLong(userId);
            generalValidation.stringNotBlank(newAddress);
            if (addressRepo.addressAlreadyExist(userId, newAddress) != null)
                throw new AlreadyExistsException("address' id already exist");
            User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
            Address a = new Address(newAddress);
            List<Address> l = new ArrayList<>();
            l.add(a);
            user.getAddressList().addAll(l);
            // user.addAddress(a);
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

    public List<Role> findAllRole() {
        return roleRepo.findAll();
    }

    public Role findRoleById(Long id) {
        try {
            generalValidation.positiveLong(id);
            return roleRepo.findById(id).orElseThrow(() -> new NotFoundException("role not found"));
        } catch (InvalidDataException e) {
            throw new ValidationException("Operzione negata " + e.getMessage());
        }
    }

    public Role findRoleByDescription(String n) {
        try {
            generalValidation.stringNotBlank(n);
            return roleRepo.findByName(n.toUpperCase()).orElseThrow(() -> new NotFoundException("name not found"));
        } catch (InvalidDataException e) {
            throw new ValidationException("Operazione negata " + e.getMessage());
        }
    }

    @Transactional(rollbackFor = DataException.class)
    public Role addRole(String roleName) {
        try {
            generalValidation.stringNotBlank(roleName);
            if (!roleRepo.findByName(roleName.toUpperCase()).isEmpty())
                throw new AlreadyExistsException("role name already exist");
            Role r = new Role(roleName.toUpperCase());
            return roleRepo.save(r);
        } catch (InvalidDataException e) {
            throw new ValidationException("Operzione negata " + e.getMessage());
        }
    }

    @Transactional(rollbackFor = DataException.class)
    public User addRoleForUser(Long userId, String roleName) {
        try {
            generalValidation.positiveLong(userId);
            generalValidation.stringNotBlank(roleName);
            roleRepo.findByName(roleName.toUpperCase()).orElseThrow(() -> new NotFoundException("role not found"));
            Role r = new Role(roleName);
            User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
            List<Role> l = new ArrayList<>();
            l.add(r);
            //user.getRoles().addAll(l);
            return userRepo.save(user);
        } catch (InvalidDataException e) {
            throw new ValidationException("Operzione negata " + e.getMessage());
        }
    }

    @Transactional(rollbackFor = DataException.class)
    public Role updateRole(Long oldId, Role role) {
        try {
            generalValidation.positiveLong(role.getId());
            generalValidation.positiveLong(oldId);
            generalValidation.stringNotBlank(role.getName());
            roleRepo.findById(oldId).orElseThrow(() -> new NotFoundException("id not found"));
            //if (roleRepo.roleAlreadyExist(role.getId(), role.getName()) != null)
            //	throw new AlreadyExistsException("id and/or description already exist");
            roleRepo.updateRole(oldId, role.getId(), role.getName());
            return roleRepo.findById(role.getId()).get();
        } catch (InvalidDataException e) {
            throw new ValidationException("Operzione negata " + e.getMessage());
        }

    }

    @Transactional(rollbackFor = DataException.class)
    public void cancelRoleById(Long id) {
        try {
            generalValidation.positiveLong(id);
            Role r = roleRepo.findById(id).orElseThrow(() -> new NotFoundException("id not found"));
            List<User> userList = userRepo.findAll();
            //userList.forEach(u -> u.getRoles().stream().filter(e -> e.getId()!=id));
            roleRepo.delete(r);
        } catch (InvalidDataException e) {
            throw new ValidationException("Operzione negata " + e.getMessage());
        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Transactional(rollbackFor = DataException.class)
    public void cancelRoleByDescription(String descr) {
        try {
            generalValidation.stringNotBlank(descr);
            Role r = roleRepo.findByName(descr).orElseThrow(() -> new NotFoundException("description not found"));
            List<User> userList = userRepo.findAll();
            //userList.forEach(u -> u.getRoles().stream().filter(e -> !e.getName().equals(descr)));
            roleRepo.delete(r);
        } catch (InvalidDataException e) {
            throw new ValidationException("Operzione negata " + e.getMessage());
        } catch (Exception e) {

        }
    }

}
