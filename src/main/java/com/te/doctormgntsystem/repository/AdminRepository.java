package com.te.doctormgntsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.doctormgntsystem.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{

	Optional<Admin> findByAdminId(String adminId);
}
