package com.skilldistillery.critique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.critique.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer>{

	// find by first name
	public List<Profile> findByFirstName(String firstName);
	
	// find by last name 
	public List<Profile> findByLastName(String lastName);
	
	// find by first and last name 
	@Query("SELECT p FROM Profile p WHERE p.firstName = :fname AND p.lastName = :lname")
	public List<Profile> findByFirstNameAndLastName(@Param("fname") String fname, @Param("lname") String lname);
	
	// find by city
	@Query("SELECT p from Profile p WHERE p.location.city = :city")
	public List<Profile> queryByCityWithLocation(@Param("city") String city);
	
	// find by state
	@Query("SELECT p from Profile p WHERE p.location.state = :state")
	public List<Profile> queryByStateWithLocation(@Param("state") String state);
	
	// find by country 
	@Query("SELECT p from Profile p WHERE p.location.country = :country")
	public List<Profile> queryByCountryWithLocation(@Param("country") String country);
	
	// find profile by username
	@Query("SELECT p from Profile p WHERE p.user.username = :username")
	public Profile queryByUsernameWithUser(@Param("username") String username);
	
}
