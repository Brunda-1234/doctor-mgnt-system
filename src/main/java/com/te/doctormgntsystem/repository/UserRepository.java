package com.te.doctormgntsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.doctormgntsystem.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUserId(String UserId);
}
