package com.lipari.app.users.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.users.entities.Role;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(String name);

	@Transactional(rollbackFor = DataException.class)
	@Modifying
	@Query(value = "UPDATE t_role SET role_id= :newId, name= :name WHERE role_id= :oldId", nativeQuery = true)
	void updateRole(@Param("oldId") Long oldId, @Param("newId") Long newId, @Param("name") String descrizione);

	//@Query(value = "SELECT * FROM t_role WHERE role_id = :id OR name = :n LIMIT 1", nativeQuery = true)
	//Role roleAlreadyExist(@Param("id") Long id, @Param("d") String n);
}
