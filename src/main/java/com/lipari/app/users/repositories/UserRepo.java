package com.lipari.app.users.repositories;

import com.lipari.app.users.entities.AppUser;
import com.lipari.app.users.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * The interface User repo.
 */
@Repository
public interface UserRepo extends JpaRepository<AppUser, Long> {

    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<AppUser> findByUsername(String username);

    /**
     * Gets user by credential.
     *
     * @param username the username
     * @param password the password
     * @return the user by credential
     */
    @Query(value = "SELECT * FROM t_user WHERE username = :usr AND password= :psw", nativeQuery = true)
    AppUser getUserByCredential(@Param("usr") String username, @Param("psw") String password);

    /**
     * Update user int.
     *
     * @param userId   the user id
     * @param nome     the nome
     * @param cognome  the cognome
     * @param username the username
     * @param password the password
     * @param email    the email
     * @param role     the role
     * @return the int
     */
    @Modifying
	@Query(value = "UPDATE t_user SET name= :nome,surname= :cog,username= :usr,password= :psw,email= :email,role= :role WHERE user_id= :id", nativeQuery = true)
	int updateUser(@Param("id") Long userId, @Param("nome") String nome, @Param("cog") String cognome,
				   @Param("usr") String username, @Param("psw") String password, @Param("email") String email,
				   @Param("role") Role role);


}
