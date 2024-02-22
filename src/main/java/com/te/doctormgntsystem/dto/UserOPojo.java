package com.te.doctormgntsystem.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@SuppressWarnings("serial")
public class UserOPojo implements Serializable{
	
	private String userId;
	private String userName;

	private Integer userAge;
	private String userEmail;
	private String userMobileNumber;
	private String userSex;

	private String userLocation;
	private String docName;
	private String specialization;
}
