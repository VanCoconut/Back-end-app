package com.lipari.app.users.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lipari.app.users.entities.Role;

import jakarta.transaction.Transactional;



@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
	
	@Query(value = "SELECT * FROM t_role WHERE descrizione = :descr", nativeQuery = true)
	int getRoleByDescription(@Param("descr") String descrizione);
	

	@Transactional
	@Modifying
	@Query(value = "UPDATE t_role SET id= :newId, descrizione= :descr WHERE id= :oldId", nativeQuery = true)
	void addDeletedColumn(@Param("oldId") int oldId,@Param("newId") int newId,@Param("descr") String descrizione);
	
}

