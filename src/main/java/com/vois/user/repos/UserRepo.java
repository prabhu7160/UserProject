package com.vois.user.repos;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.vois.user.entities.UsersProject;

public interface UserRepo extends JpaRepository<UsersProject, Integer> {

	Optional<UsersProject> findByEmail(String email);
	
	@Query("select u from UsersProject u where u.email = : email ")
	public UsersProject getUserByUserName(@Param("email") String email);

}