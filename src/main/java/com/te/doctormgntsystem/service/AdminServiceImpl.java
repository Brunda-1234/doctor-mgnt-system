package com.te.doctormgntsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.te.doctormgntsystem.dto.AdminPojo;
import com.te.doctormgntsystem.dto.AdminResponsePojo;
import com.te.doctormgntsystem.dto.DoctorOPojo;
import com.te.doctormgntsystem.dto.DoctorPojo;
import com.te.doctormgntsystem.dto.LeaveRequestPojo;
import com.te.doctormgntsystem.entity.Admin;
import com.te.doctormgntsystem.entity.Doctor;
import com.te.doctormgntsystem.entity.LeaveRequest;
import com.te.doctormgntsystem.entity.LeaveStatus;
import com.te.doctormgntsystem.exception.DataNotFoundException;
import com.te.doctormgntsystem.generator.BeanCopy;
import com.te.doctormgntsystem.repository.AdminRepository;
import com.te.doctormgntsystem.repository.DoctorRepository;
import com.te.doctormgntsystem.repository.LeaveRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final AdminRepository adminRepository;
	private final DoctorRepository doctorRepository;
	private final LeaveRepository leaveRepository;

	@Override
	public AdminPojo addAdmin(AdminPojo adminPojo) {
		try {
			if (adminPojo.getAdminId() != null) {
				adminRepository.findByAdminId(adminPojo.getAdminId()).map(admin -> {
					BeanUtils.copyProperties(adminPojo, admin);
					admin.setAdminPhoneNumber("+91 " + adminPojo.getAdminPhoneNumber());
					BeanUtils.copyProperties(adminRepository.save(admin), adminPojo);
					return adminPojo;
				});
				return adminPojo;
			}
			Admin admin = new Admin();
			BeanUtils.copyProperties(adminPojo, admin);
			admin.setAdminPhoneNumber("+91 " + adminPojo.getAdminPhoneNumber());
			BeanUtils.copyProperties(adminRepository.save(admin), adminPojo);
			return adminPojo;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException(e.getMessage());
		}

	}

	@Override
	public List<DoctorOPojo> getAllDoctors() {
		try {
			List<DoctorOPojo> doctorpojolist = BeanCopy.copy(doctorRepository.findAll(), DoctorOPojo.class);
			return doctorpojolist;
		} catch (Exception e) {
			throw new DataNotFoundException(e.getMessage());
		}

	}

	@Override
	public List<LeaveRequestPojo> getAllRequests() {
		try {
			return leaveRepository.findAll().stream().map(request -> {

				LeaveRequestPojo leaveRequestPojo = new LeaveRequestPojo();
				BeanUtils.copyProperties(request, leaveRequestPojo);
				leaveRequestPojo.setDoctorId(request.getDoctor().getDoctorId());
				return leaveRequestPojo;

			}).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException(e.getMessage());
		}

	}

	@Transactional
	@Override
	public String adminResponse(AdminResponsePojo adminResponsePojo) {
		try {
//			leaveRepository.findByLeaveId(adminResponsePojo.getLeaveId()).map(leaveRequest -> {
//                
//				doctorRepository.findByDoctorId(leaveRequest.getDoctor().getDoctorId()).map(doctor -> {
//					if (adminResponsePojo.getLeaveStatus().equals(LeaveStatus.APPROVED)) {
//						doctor.setLeaveStatus(LeaveStatus.APPROVED);
//						return "Request Approved Successfully";
//					}
//					doctor.setLeaveStatus(LeaveStatus.REJECTED);
//					return "Request Rejected Successfully";
//
//				}).orElse(null);
//               
//			}).orElse(null);

			Optional<LeaveRequest> leaveOp = leaveRepository.findById(adminResponsePojo.getLeaveId());
			if(leaveOp.isPresent()) {
				LeaveRequest leaveRequest = leaveOp.get();
				return doctorRepository.findByDoctorId(leaveRequest.getDoctor().getDoctorId()).map(doctor->{
					
				if(adminResponsePojo.getLeaveStatus().equals(LeaveStatus.APPROVED)) {
					doctor.setLeaveStatus(adminResponsePojo.getLeaveStatus());
					return "Request Approved Successfully";
				}
				doctor.setLeaveStatus(adminResponsePojo.getLeaveStatus());
				return "Request Rejected Successfully";
			}).orElse(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException(e.getMessage());
		}
		return null;
	}

}
