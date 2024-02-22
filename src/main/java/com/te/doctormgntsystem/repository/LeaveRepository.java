package com.te.doctormgntsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.doctormgntsystem.entity.LeaveRequest;

public interface LeaveRepository extends JpaRepository<LeaveRequest, Integer>{

	Optional<LeaveRequest> findByLeaveId(Integer leaveId);
}
