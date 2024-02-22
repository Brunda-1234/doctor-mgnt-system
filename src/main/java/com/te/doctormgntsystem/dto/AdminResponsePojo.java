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
public class AdminResponsePojo implements Serializable{
	
	private Integer leaveId;
	@Enumerated(EnumType.STRING)
	private LeaveStatus leaveStatus;
	
	private String reason;

}
