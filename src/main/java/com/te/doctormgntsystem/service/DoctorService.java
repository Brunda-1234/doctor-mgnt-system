package com.te.doctormgntsystem.service;

import java.util.List;
import java.util.Map;

import com.te.doctormgntsystem.dto.AppointmentLists;
import com.te.doctormgntsystem.dto.BookAppointmentPojo;
import com.te.doctormgntsystem.dto.DoctorPojo;
import com.te.doctormgntsystem.dto.LeaveRequestPojo;
import com.te.doctormgntsystem.entity.Appointment;

public interface DoctorService {

	public DoctorPojo addDoctor(DoctorPojo doctorPojo);
	
	public Map<Boolean, List<BookAppointmentPojo>> getAppointments(AppointmentLists appointmentLists);
	
	LeaveRequestPojo sendRequest(LeaveRequestPojo leaveRequestPojo);
}
