package com.te.doctormgntsystem.service;

import java.util.List;

import com.te.doctormgntsystem.dto.AdminPojo;
import com.te.doctormgntsystem.dto.AdminResponsePojo;
import com.te.doctormgntsystem.dto.DoctorOPojo;
import com.te.doctormgntsystem.dto.LeaveRequestPojo;

public interface AdminService {

	public AdminPojo addAdmin(AdminPojo adminPojo);

	public List<DoctorOPojo> getAllDoctors();
	
	List<LeaveRequestPojo> getAllRequests();
	
	public String adminResponse(AdminResponsePojo adminResponsePojo);
}
