package com.lipari.app.users.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lipari.app.commons.exception.utils.AuthException;
import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.commons.exception.utils.NotFoundException;
import com.lipari.app.commons.repositories.BaseDao;
import com.lipari.app.users.entities.User;
import com.lipari.app.utils.DbConnection;

import jakarta.transaction.Transactional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	@Query(value = "SELECT * FROM t_user WHERE username = :usr AND password= :psw", nativeQuery = true)
	User getUserByCredential(@Param("usr") String username, @Param("psw") String password);

	@Transactional
	@Modifying
	@Query(value = "UPDATE t_user SET nome= :nome,cognome= :cog,username= :usr,password= :psw,email= :email,role= :role WHERE id= :id", nativeQuery = true)
	int updateUser(@Param("id") int currentUserId, @Param("nome") String nome, @Param("cog") String cognome,
			@Param("usr") String username, @Param("psw") String password, @Param("email") String email,
			@Param("role") int role);


}
