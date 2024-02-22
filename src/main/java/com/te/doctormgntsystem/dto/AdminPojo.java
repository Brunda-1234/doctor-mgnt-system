package com.te.doctormgntsystem.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuppressWarnings("serial")
public class AdminPojo implements Serializable{

	private String adminId;
	@NotBlank(message = "Name not be Empty")
	@Pattern(regexp = "^[a-zA-z\\s]{3,}",message = "Name should not contains digits and special characters ")
	private String adminName;
	@Email(message = "Email should be in format of abc@gmail.com")
	@NotBlank(message = "Email not to be Empty")
	private String adminEmail;
	@Pattern(regexp = "^[0-9]{10}$",message = "PhoneNumber should contains digits")
	@NotBlank(message = "PhoneNumber not to be Empty")
	private String adminPhoneNumber;

	@NotBlank(message = "password not be empty")
	@Size(min = 5, max = 10)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", 
     message = "Password must contain alphabets, numbers, and special characters.")
	private String password;
}
