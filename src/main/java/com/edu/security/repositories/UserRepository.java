package com.edu.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.security.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsernameAndStatus(String username, Integer status);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}