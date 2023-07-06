package com.lipari.app.users.repositories;

import com.lipari.app.users.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Address repo.
 */
@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

    /**
     * Gets all user address.
     *
     * @param userId the user id
     * @return the all user address
     */
    @Query(value = "SELECT * FROM t_address WHERE user_id = :userId", nativeQuery = true)
	List<String> getAllUserAddress(@Param("userId") Long userId);


    /**
     * Gets address by id.
     *
     * @param id the id
     * @return the address by id
     */
    @Query(value = "SELECT * FROM t_address WHERE address_id = :id", nativeQuery = true)
	Optional<Address> getAddressById(@Param("id") Long id);

    /**
     * Address already exist address.
     *
     * @param id        the id
     * @param indirizzo the indirizzo
     * @return the address
     */
    @Query(value = "SELECT * FROM t_address WHERE user_id = :user_id AND address = :i", nativeQuery = true)
	Address addressAlreadyExist(@Param("user_id") Long id, @Param("i") String indirizzo);
	

}
