package com.te.doctormgntsystem.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuppressWarnings("serial")
public class DoctorRatingPojo implements Serializable {

	private Double doctorRatings;
	private String doctorId;
	private String userId;
}
