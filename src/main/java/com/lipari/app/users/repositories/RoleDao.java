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
public interface RoleDao extends JpaRepository<Role, Integer> {

	@Query(value = "SELECT * FROM t_role WHERE descrizione= :descr", nativeQuery = true)
	Optional<Role> getRoleByDescription(@Param("descr") String descrizione);

	@Transactional(rollbackFor = DataException.class)
	@Modifying
	@Query(value = "UPDATE t_role SET id= :newId, descrizione= :descr WHERE id= :oldId", nativeQuery = true)
	void updateRole(@Param("oldId") int oldId, @Param("newId") int newId, @Param("descr") String descrizione);

	@Query(value = "SELECT * FROM t_role WHERE id = :id OR descrizione = :d LIMIT 1", nativeQuery = true)
	Role roleAlreadyExist(@Param("id") int id, @Param("d") String d);
}
