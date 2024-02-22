package com.te.doctormgntsystem.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.te.doctormgntsystem.dto.AppointmentLists;
import com.te.doctormgntsystem.dto.BookAppointmentPojo;
import com.te.doctormgntsystem.dto.DoctorPojo;
import com.te.doctormgntsystem.dto.LeaveRequestPojo;
import com.te.doctormgntsystem.entity.Appointment;
import com.te.doctormgntsystem.entity.Doctor;
import com.te.doctormgntsystem.entity.LeaveRequest;
import com.te.doctormgntsystem.entity.LeaveStatus;
import com.te.doctormgntsystem.exception.DataNotFoundException;
import com.te.doctormgntsystem.repository.DoctorRepository;
import com.te.doctormgntsystem.repository.LeaveRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

	private final DoctorRepository doctorRepository;
	private final LeaveRepository leaveRepository;

	@Override
	public DoctorPojo addDoctor(DoctorPojo doctorPojo) {
		try {
			if (doctorPojo.getDoctorId() != null) {
				doctorRepository.findByDoctorId(doctorPojo.getDoctorId()).map(doctor -> {

					BeanUtils.copyProperties(doctorPojo, doctor);
					doctor.setDoctorName("Dr." + doctorPojo.getDoctorName());
					doctor.setDoctorPhoneNumber("+91 " + doctorPojo.getDoctorPhoneNumber());
					BeanUtils.copyProperties(doctorRepository.save(doctor), doctorPojo);
					return doctorPojo;
				});
				return doctorPojo;
			}
			Doctor doctor = new Doctor();

			BeanUtils.copyProperties(doctorPojo, doctor);
			doctor.setDoctorName("Dr." + doctorPojo.getDoctorName());
			doctor.setDoctorPhoneNumber("+91 " + doctorPojo.getDoctorPhoneNumber());
			BeanUtils.copyProperties(doctorRepository.save(doctor), doctorPojo);
			return doctorPojo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException(e.getMessage());
		}
	}

	@Override
	public Map<Boolean, List<BookAppointmentPojo>> getAppointments(AppointmentLists appointmentLists) {
		try {

			Map<Boolean, List<Appointment>> orElseGet = doctorRepository.findByDoctorId(appointmentLists.getDoctorId())
					.map(doctor -> {

						return doctor.getAppointments().stream().collect(Collectors.partitioningBy(
								appointment -> appointment.getBookingDateTime().toLocalDate().equals(LocalDate.now())));

					}).orElseGet(HashMap::new);

			Map<Boolean, List<BookAppointmentPojo>> pojolist = new HashMap<>();

			for (Map.Entry<Boolean, List<Appointment>> entry : orElseGet.entrySet()) {
				Boolean key = entry.getKey();
				List<Appointment> appoint = entry.getValue();
				List<BookAppointmentPojo> appointPojo = appoint.stream().map(appo -> {
					BookAppointmentPojo appointmentPojo = new BookAppointmentPojo();
					appointmentPojo.setUserId(appo.getUser().getUserId());
					appointmentPojo.setDoctorId(appo.getDoctor().getDoctorId());
					BeanUtils.copyProperties(appo, appointmentPojo);
					return appointmentPojo;
				}).collect(Collectors.toList());

				pojolist.put(key, appointPojo);
			}
			return pojolist;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException(e.getMessage());
		}
	}

	@Override
	public LeaveRequestPojo sendRequest(LeaveRequestPojo leaveRequestPojo) {
		try {
			LeaveRequest leaveRequest = new LeaveRequest();
			BeanUtils.copyProperties(leaveRequestPojo, leaveRequest);
			doctorRepository.findByDoctorId(leaveRequestPojo.getDoctorId()).ifPresent(doctor->{
				leaveRequest.setDoctor(doctor);
				doctor.setLeaveStatus(LeaveStatus.PENDING);
			});
			BeanUtils.copyProperties(leaveRepository.save(leaveRequest), leaveRequestPojo);
			return leaveRequestPojo;
		} catch (Exception e) {
			throw new DataNotFoundException(e.getMessage());
		}
		
	}

}

//List<Appointment> todaysList  = new ArrayList<>(); 
//List<Appointment> tommarowsList  = new ArrayList<>(); 
//List<Appointment>  appointments= new ArrayList<>();
//
//Stream<List<Appointment>> streamlist = appointmentRepository.findAll().stream().map(appointment->{
//
//	if(appointment.getBookingDateTime().equals(LocalDate.now())) {
//		todaysList.add(appointment);
//	}else {
//		tommarowsList.add(appointment);
//	}
//	appointments.addAll(todaysList);
//	appointments.addAll(tommarowsList);
//	return appointments;
//});
//map
//List<BookAppointmentPojo> todaysPojosList = BeanCopy.copy(todaysList, BookAppointmentPojo.class);
//System.err.println(todaysPojosList);
//List<BookAppointmentPojo> tmrwPojosList = BeanCopy.copy(tommarowList, BookAppointmentPojo.class);
//System.err.println(tmrwPojosList);
//Map<String, List<Appointment>> todays = new HashMap<>();
//System.err.println(todays);
//Map<String, List<Appointment>> tommarows = new HashMap<>();
//System.err.println(tommarows);
//
//appointmentRepository.findAll().stream().forEach(appointment -> {
//
//	if (appointment.getBookingDateTime().toLocalDate().isEqual(today)) {
//		System.err.println(appointment.getBookingDateTime());
//		todaysList.add(appointment);
//		todays.put("TodaysList", todaysList);
//		
//	}
//	tommarowList.add(appointment);
//	tommarows.put("TommarowList", tommarowList);
//});
//List<Appointment> appointmentList = new ArrayList<>();
//appointmentList.addAll(todaysList);
//appointmentList.addAll(tommarowList);
//Map<Map<String, List<Appointment>>, Map<String, List<Appointment>>> appointmentsList = new HashMap<>();
//appointmentsList.merge(todays, tommarows, null);
//return appointmentsList;

//Map<String, List<BookAppointmentPojo>> appointmentsListPjo = new HashMap<>();
//for (Entry<String, List<Appointment>> entry : appointmentsList.entrySet()) {
//	String key = entry.getKey();
//	System.err.println(key);
//	List<Appointment> val = entry.getValue();
//	List<BookAppointmentPojo> pojolist = BeanCopy.copy(val, BookAppointmentPojo.class);
//	appointmentsListPjo.put(key, pojolist);
//}

//return appointmentsList;
