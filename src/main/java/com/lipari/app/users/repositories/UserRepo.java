package com.lipari.app.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.users.entities.Role;
import com.lipari.app.users.entities.User;



@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	
	@Query(value = "SELECT * FROM t_user WHERE username = :usr AND password= :psw", nativeQuery = true)
	User getUserByCredential(@Param("usr") String username, @Param("psw") String password);

	
	@Modifying
	@Query(value = "UPDATE t_user SET name= :nome,surname= :cog,username= :usr,password= :psw,email= :email,role= :role WHERE user_id= :id", nativeQuery = true)
	int updateUser(@Param("id") Long userId, @Param("nome") String nome, @Param("cog") String cognome,
			@Param("usr") String username, @Param("psw") String password, @Param("email") String email,
			@Param("role") Role role);




}
