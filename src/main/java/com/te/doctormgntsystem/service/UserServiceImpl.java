package com.te.doctormgntsystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.te.doctormgntsystem.dto.BookAppointmentPojo;
import com.te.doctormgntsystem.dto.DoctorOPojo;
import com.te.doctormgntsystem.dto.DoctorRatingPojo;
import com.te.doctormgntsystem.dto.UserOPojo;
import com.te.doctormgntsystem.dto.UserPojo;
import com.te.doctormgntsystem.entity.Appointment;
import com.te.doctormgntsystem.entity.DoctorRating;
import com.te.doctormgntsystem.entity.User;
import com.te.doctormgntsystem.exception.DataNotFoundException;
import com.te.doctormgntsystem.exception.TimeSlotException;
import com.te.doctormgntsystem.repository.AppointmentRepository;
import com.te.doctormgntsystem.repository.DoctorRatingRepository;
import com.te.doctormgntsystem.repository.DoctorRepository;
import com.te.doctormgntsystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final DoctorRepository doctorRepository;
	private final AppointmentRepository appointmentRepository;
	private final DoctorRatingRepository doctorRatingRepository;

	@Override
	public UserPojo addUser(UserPojo userPojo) {
		try {
			if (userPojo.getUserId() != null) {
				userRepository.findByUserId(userPojo.getUserId()).map(user -> {
					BeanUtils.copyProperties(userPojo, user);
					user.setUsertMobileNumber("+91 " + userPojo.getUserMobileNumber());
					BeanUtils.copyProperties(userRepository.save(user), userPojo);
					return userPojo;

				});
				return userPojo;
			}
			User user = new User();
			BeanUtils.copyProperties(userPojo, user);
			user.setUsertMobileNumber("+91 " + userPojo.getUserMobileNumber());
			BeanUtils.copyProperties(userRepository.save(user), userPojo);
			return userPojo;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException(e.getMessage());
		}

	}

	@Override
	public DoctorOPojo getDoctor(String doctorName) {
		try {
			return doctorRepository.findByDoctorName(doctorName).map(doctor -> {
				DoctorOPojo doctorOPojo = new DoctorOPojo();
				BeanUtils.copyProperties(doctor, doctorOPojo);
				return doctorOPojo;
			}).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException(e.getMessage());
		}

	}

	@Override
	public List<DoctorOPojo> getDoctorsBySpecialization(String doctorSpecialization) {
		try {
			return doctorRepository.findByDoctorSpecialization(doctorSpecialization).map(doctorlist -> {
				return doctorlist.stream().map(doctor -> {
					DoctorOPojo doctorOPojo = new DoctorOPojo();
					BeanUtils.copyProperties(doctor, doctorOPojo);
					return doctorOPojo;
				}).collect(Collectors.toList());
			}).orElseGet(ArrayList::new);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException(e.getMessage());
		}

	}

	// get data by doctorName and doctorSpecialization
	@Override
	public Object getDoctors(UserOPojo userOPojo) {
		try {
			return doctorRepository
					.findByDoctorSpecializationOrDoctorName(userOPojo.getSpecialization(), userOPojo.getDocName())
					.map(doctorlist -> {
						return doctorlist.stream().map(doctor -> {
							DoctorOPojo doctorOPojo = new DoctorOPojo();
							BeanUtils.copyProperties(doctor, doctorOPojo);
							return doctorOPojo;
						}).collect(Collectors.toList());
					}).orElseGet(ArrayList::new);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException(e.getMessage());
		}
	}

	@Override
	public BookAppointmentPojo bookingAppointment(BookAppointmentPojo bookAppointmentPojo) {
		try {
			Appointment appointment = new Appointment();
			BeanUtils.copyProperties(bookAppointmentPojo, appointment);

			doctorRepository.findByDoctorId(bookAppointmentPojo.getDoctorId()).ifPresent(doctor -> {
				appointment.setDoctor(doctor);
			});
			doctorRepository.findByDoctorId(bookAppointmentPojo.getDoctorId()).ifPresent(doctor -> {

				doctor.getAppointments().stream().forEach(appoint -> {
					if (appoint.getBookingDateTime().equals(bookAppointmentPojo.getBookingDateTime())) {
						throw new TimeSlotException("This Appointment Slot is already booked");
					}
				});
				appointment.setDoctor(doctor);
			});

			userRepository.findByUserId(bookAppointmentPojo.getUserId()).ifPresent(user -> {
				appointment.setUser(user);
			});
			BeanUtils.copyProperties(appointmentRepository.save(appointment), bookAppointmentPojo);
			System.err.println(bookAppointmentPojo);
			return bookAppointmentPojo;
		} catch (TimeSlotException ex) {
			throw ex;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException(e.getMessage());
		}

	}

	@Override
	public DoctorRatingPojo addRatings(DoctorRatingPojo doctorRatingPojo) {
		try {
			DoctorRating doctorRating = new DoctorRating();
		
			doctorRepository.findByDoctorId(doctorRatingPojo.getDoctorId()).ifPresent(doctor->{
				if(doctor.getDoctorId() == doctorRatingPojo.getDoctorId()) {
					List<DoctorRating> doctorRatings = doctor.getDoctorRatings();
					Double collect = doctorRatings.stream().collect(Collectors.averagingDouble(DoctorRating::getDoctorRatings));
					doctor.setAverageRating(collect);
					
				}
			});
			BeanUtils.copyProperties(doctorRatingPojo, doctorRating);
			BeanUtils.copyProperties(doctorRatingRepository.save(doctorRating), doctorRatingPojo);
			return doctorRatingPojo;			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException(e.getMessage());
		}
	
	
	}

}
