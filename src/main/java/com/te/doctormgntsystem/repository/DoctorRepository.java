package com.te.doctormgntsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.doctormgntsystem.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,String> {

	public Optional<Doctor> findByDoctorId(String doctorId);
	
	Optional<Doctor> findByDoctorName(String doctorName);
	
	Optional<List<Doctor>> findByDoctorSpecialization(String doctorSpecialization);
	
	Optional<List<Doctor>> findByDoctorSpecializationOrDoctorName(String doctorSpecialization,String doctorName);
}
