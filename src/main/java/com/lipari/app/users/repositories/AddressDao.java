package com.lipari.app.users.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lipari.app.users.entities.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {

	@Query(value = "SELECT * FROM t_address WHERE user_id = :userId", nativeQuery = true)
	List<String> getAllUserAddress(@Param("userId") int userId);
	
	
	@Query(value = "SELECT * FROM t_address WHERE id = :id", nativeQuery = true)
	Optional<Address> getAddressById(@Param("id") int id);
	
	@Query(value = "SELECT * FROM t_address WHERE user_id = :user_id AND idnirizzo = :i", nativeQuery = true)
	Address addressAlreadyExist(@Param("user_id") int id, @Param("i") String indirizzo);
	

}
