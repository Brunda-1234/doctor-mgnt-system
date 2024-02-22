package com.te.doctormgntsystem.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Convert;
import javax.persistence.Converter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.te.doctormgntsystem.deserializer.LocalTimeDeserializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@SuppressWarnings("serial")
public class BookAppointmentPojo implements Serializable {

	private Integer appointmentId;

	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy HH:mm:ss",timezone = "Asia/Kolkata")
	private LocalDateTime bookingDateTime;

//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
//	private LocalDate bookingDate;
//
//	@JsonDeserialize(using = LocalTimeDeserializer.class)
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Kolkata")
//	private LocalTime bookingTime;

	private String userId;
	private String doctorId;

}
