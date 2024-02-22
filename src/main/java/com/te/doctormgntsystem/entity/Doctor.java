package com.te.doctormgntsystem.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.te.doctormgntsystem.generator.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "doctor_table")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "doctorId")
//can instruct the JSON serializer to use an identifier (usually a unique ID) for each instance of the 
//annotated class. 
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq")
	@GenericGenerator(name = "doctor_seq", strategy = "com.te.doctormgntsystem.generator.SequenceGenerator", parameters = {
			@Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = SequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") })  
	private String doctorId;
	private String doctorName;
	private String password;
	private Double averageRating;
	private String doctorEmail;
	private String doctorPhoneNumber;
	private String doctorImageUrl;
	
    @Enumerated(EnumType.STRING)
    private LeaveStatus leaveStatus;
	private String doctorSpecialization;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "doctor",fetch = FetchType.LAZY)
	private List<Appointment> appointments;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "doctor",fetch = FetchType.LAZY)
	private List<DoctorRating> doctorRatings;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "doctor",fetch = FetchType.LAZY)
	private List<LeaveRequest> leaveRequests;
}
