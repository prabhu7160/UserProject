package com.vois.user.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vois.user.entities.UsersProject;

public interface UserRepo extends JpaRepository<UsersProject, Integer> {

	Optional<UsersProject> findByEmail(String email);
}