package com.te.doctormgntsystem.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuppressWarnings("serial")
public class UserPojo implements Serializable{
	

	private String userId;
	
	@Pattern(regexp = "^[a-zA-Z\\s]{3,}$",message = "PatientName should consists Alphabets")
//	@NotBlank(message = "patient name not be empty")
	//\\s doesn't allow the special characters in the name 
	private String userName;
	
	private Integer userAge;
	
//	 @NotBlank(message = "password not be empty")
	 @Size(min = 5, max = 10)
	 @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", 
	      message = "Password must contain alphabets, numbers, and special characters.")
	 /*
	  * ^ = assertion of string starts from here
	  * (?=.*[a-zA-Z]) = accepts atleast one alphabets
	  * (?=.*\\d) = accepts atleast one integer
	  * (?=.*[@$!%*?&]) = atleast one special character
	  * [A-Za-z\\d@$!%*?&] = This part matches the actual characters of the password. 
	  * It ensures that the password contains only alphabets, digits, and the specified special characters.
	  * $ = Matches one or more occurrences of the characters defined in the previous part.
	  * 
	  */
	private String password;
	 
	@Email(message = "The format of the email should be abc@gmail.com")
//	@NotBlank(message = "patient email not be empty")
	private String userEmail;
	
	@Pattern(regexp = "^[0-9]{10}$",message = "Mobile Number should be in numbers")
//	@NotBlank(message = "Mobilenumber not to be empty")
	private String userMobileNumber;
	
	private String userSex;
	
	private String userLocation;

}
