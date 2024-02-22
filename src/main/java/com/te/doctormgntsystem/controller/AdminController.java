package com.te.doctormgntsystem.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.doctormgntsystem.dto.AdminPojo;
import com.te.doctormgntsystem.dto.AdminResponsePojo;
import com.te.doctormgntsystem.dto.Response;
import com.te.doctormgntsystem.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController("admin_controller")
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
@CrossOrigin("*")
public class AdminController {

	private final AdminService adminService;

	@PostMapping("add")
	public ResponseEntity<Response> addAdmin(@Valid @RequestBody AdminPojo adminPojo) {
		return ResponseEntity.accepted()
				.body(Response.builder().error(false)
						.message(adminPojo.getAdminId() != null ? "Updated Successfully" : "Registered Successfully")
						.data(adminService.addAdmin(adminPojo)).build());
	}

	@GetMapping("get/doctors")
	public ResponseEntity<Response> getDoctors() {
		return ResponseEntity.accepted().body(Response.builder().error(false).message("Doctors Fetched Successfully")
				.data(adminService.getAllDoctors()).build());
	}
	
	@GetMapping("get/leave/requests")
	public ResponseEntity<Response> getLeaveRequests(){
		return ResponseEntity.accepted().body(Response.builder().error(false)
				.message("Requests Fetched Successfully").data(adminService.getAllRequests()).build());
	}
	
	@PostMapping("get/request")
	public ResponseEntity<Response> getRequestDoctor(@RequestBody AdminResponsePojo adminResponsePojo){
		return ResponseEntity.accepted().body(Response.builder().error(false)
				.data(adminService.adminResponse(adminResponsePojo)).build());
	}
}
