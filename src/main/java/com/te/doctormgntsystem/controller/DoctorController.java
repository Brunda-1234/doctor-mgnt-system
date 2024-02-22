package com.te.doctormgntsystem.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.doctormgntsystem.dto.AppointmentLists;
import com.te.doctormgntsystem.dto.DoctorPojo;
import com.te.doctormgntsystem.dto.LeaveRequestPojo;
import com.te.doctormgntsystem.dto.Response;
import com.te.doctormgntsystem.service.DoctorService;

import lombok.RequiredArgsConstructor;

@RestController("doctor_controller")
@RequestMapping("api/v1/doctor")
@CrossOrigin("*")
@RequiredArgsConstructor
public class DoctorController {

	private final DoctorService doctorService;

	@PostMapping("add/doctor")
	public ResponseEntity<Response> addDoctor(@Valid @RequestBody DoctorPojo doctorPojo) {
		return ResponseEntity.accepted()
				.body(Response.builder().error(false)
						.message(doctorPojo.getDoctorId() != null ? "Updated Successfully" : "Registered Successfully")
						.data(doctorService.addDoctor(doctorPojo)).build());
	}

	@PostMapping("get/appointments")
	public ResponseEntity<Response> getAllAppointments(@RequestBody AppointmentLists appointmentLists){
		return ResponseEntity.accepted().body(Response.builder().error(false)
				.message("Appointments Festched Successfully")
				.data(doctorService.getAppointments(appointmentLists)).build());
				
	}
	
	@PostMapping("leave/request")
	public ResponseEntity<Response> sendLeave(@RequestBody LeaveRequestPojo leaveRequestPojo){
		return ResponseEntity.accepted().body(Response.builder().error(false).message("Leave Request Sent Successfully").data(doctorService.sendRequest(leaveRequestPojo)).build());
	}

}
