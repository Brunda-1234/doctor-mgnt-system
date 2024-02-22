package com.te.doctormgntsystem.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
public class DoctorPojo implements Serializable{

	
	private String doctorId;
	
	@Pattern(regexp = "^[a-zA-Z]{3,}$" ,message = "DoctorName Should Contains Alphabets")
	//{3,} means that the letter 'a' should occur at least three times or more consecutively.
	@NotBlank(message = "Doctorname Should Not be Empty")
	private String doctorName;
	
	@Email(message = "The format of the email Should be abc@gmail.com")
	@NotBlank(message = "Email not to be empty")
	private String doctorEmail;
	
	@NotBlank(message = "password not be empty")
	@Size(min = 5, max = 10)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", 
     message = "Password must contain alphabets, numbers, and special characters.")
//	@JsonIgnore
	private String password;
	
	@Pattern(regexp = "^[0-9]{10}$" ,message = "Doctor PhoneNumber Should Contains Numbers")
	@NotBlank(message = "PhoneNumber not to be empty")
	private String doctorPhoneNumber;
	
	@Enumerated(EnumType.STRING)
    private LeaveStatus leaveStatus;
	
	private String doctorSpecialization;
	
	@NotBlank(message = "Url cannot be Blank")
	private String doctorImageUrl;
	private Double averageRating;
}
