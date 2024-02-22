package com.te.doctormgntsystem.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.te.doctormgntsystem.dto.BookAppointmentPojo;
import com.te.doctormgntsystem.dto.DoctorOPojo;
import com.te.doctormgntsystem.dto.DoctorRatingPojo;
import com.te.doctormgntsystem.dto.UserOPojo;
import com.te.doctormgntsystem.dto.UserPojo;

public interface UserService {

	public UserPojo addUser(UserPojo userPojo);
	
	public DoctorOPojo getDoctor(String doctorName);
	
	public List<DoctorOPojo> getDoctorsBySpecialization(String doctorSpecialization);

	//get data by doctorName and doctorSpecialization
	public Object getDoctors(UserOPojo userOPojo);

	public BookAppointmentPojo bookingAppointment(BookAppointmentPojo bookAppointmentPojo);
	
	DoctorRatingPojo addRatings(@RequestBody DoctorRatingPojo doctorRatingPojo);
}
