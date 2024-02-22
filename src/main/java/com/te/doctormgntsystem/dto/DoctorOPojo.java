package com.te.doctormgntsystem.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.te.doctormgntsystem.entity.LeaveStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@SuppressWarnings("serial")
public class DoctorOPojo implements Serializable{

	private String doctorId;
	private String doctorName;
	private String doctorEmail;
	private String doctorPhoneNumber;
	private String doctorSpecialization;
	private String doctorImageUrl;
	private Double averageRating;
	
	@Enumerated(EnumType.STRING)
    private LeaveStatus leaveStatus;
}
