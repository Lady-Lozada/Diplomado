package com.developers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developers.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByFirstNameAndLastName(String name, String lastName);
	
	public User findByUserName(String userName);
}
