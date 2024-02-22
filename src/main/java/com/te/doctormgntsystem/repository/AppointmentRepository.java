package com.te.doctormgntsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.doctormgntsystem.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	
	Optional<Appointment> findByAppointmentId(Integer appointmentId);

}
