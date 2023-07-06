package com.lipari.app.users.repositories;

import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.users.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * The interface Role repo.
 */
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    Optional<Role> findByName(String name);

    /**
     * Gets role by description.
     *
     * @param descrizione the descrizione
     * @return the role by description
     */
    @Query(value = "SELECT * FROM t_role WHERE description= :descr", nativeQuery = true)
	Optional<Role> getRoleByDescription(@Param("descr") String descrizione);

    /**
     * Update role.
     *
     * @param oldId       the old id
     * @param newId       the new id
     * @param descrizione the descrizione
     */
    @Transactional(rollbackFor = DataException.class)
	@Modifying
	@Query(value = "UPDATE t_role SET role_id= :newId, description= :descr WHERE role_id= :oldId", nativeQuery = true)
	void updateRole(@Param("oldId") Long oldId, @Param("newId") Long newId, @Param("descr") String descrizione);

    /**
     * Role already exist role.
     *
     * @param id the id
     * @param d  the d
     * @return the role
     */
    @Query(value = "SELECT * FROM t_role WHERE role_id = :id OR description = :d LIMIT 1", nativeQuery = true)
	Role roleAlreadyExist(@Param("id") Long id, @Param("d") String d);
}
