package com.amr.project.service.repository;

import com.amr.project.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.username = ?1")
	User findByUsername(String username);

	@Query("SELECT u FROM User u WHERE u.activationCode = ?1")
	User findByActivationCode(String activationCode);

	@Query("SELECT u FROM User u WHERE u.secret = ?1")
	User findBySecret(String secret);

}

