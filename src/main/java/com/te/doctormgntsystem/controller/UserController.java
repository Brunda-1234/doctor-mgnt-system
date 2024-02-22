package com.te.doctormgntsystem.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.doctormgntsystem.dto.BookAppointmentPojo;
import com.te.doctormgntsystem.dto.DoctorRatingPojo;
import com.te.doctormgntsystem.dto.Response;
import com.te.doctormgntsystem.dto.UserOPojo;
import com.te.doctormgntsystem.dto.UserPojo;
import com.te.doctormgntsystem.entity.DoctorRating;
import com.te.doctormgntsystem.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController("user_controller")
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
@CrossOrigin("*")
public class UserController {

	private final UserService userService;

	@PostMapping("add")
	public ResponseEntity<Response> addUSer(@Valid @RequestBody UserPojo userPojo){
		return ResponseEntity.accepted().body(Response.builder().error(false)
				.message(userPojo.getUserId() != null?"Updated Successfully":"Registered Successfully")
				.data(userService.addUser(userPojo)).build());
	}
	
	
	@PostMapping
	public ResponseEntity<Response> getDoctors(@RequestBody UserOPojo userOPojo){
		return ResponseEntity.accepted().body(Response.builder().error(false).message("Doctor Fetched Successfully")
				.data(userService.getDoctors(userOPojo)).build());
		
	}
	
	//fetch the doctor data by using doctorName and doctorSpecialization
    @GetMapping("getAll/{doctorSpecialization}")
    public ResponseEntity<Response> getDoctorsBySpecialization(@PathVariable String doctorSpecialization){
    	return ResponseEntity.accepted().body(Response.builder().error(false).message("Doctors Fetched Successfully")
    			.data(userService.getDoctorsBySpecialization(doctorSpecialization)).build());
    }
    
    @GetMapping("get/{doctorName}")
	public ResponseEntity<Response> getDoctor(@PathVariable String doctorName){
		return ResponseEntity.accepted().body(Response.builder().error(false).message("Doctor Fetched Successfully")
				.data(userService.getDoctor(doctorName)).build());
		
	}
    
    @PostMapping("book/appointment")
    public ResponseEntity<Response> bookAppointment(@RequestBody BookAppointmentPojo bookAppointmentPojo){
    	return ResponseEntity.accepted().body(Response.builder().error(false).message("Appointment Booked Successfully")
    			.data(userService.bookingAppointment(bookAppointmentPojo)).build());
    }
    
    @PostMapping("ratings")
    public ResponseEntity<Response> doctorRating(@RequestBody DoctorRatingPojo doctorRatingPojo){
    	return ResponseEntity.accepted().body(Response.builder().error(false).message("Ratings Uploaded Suceessfully").data(userService.addRatings(doctorRatingPojo)).build());
    }
	
    

}
