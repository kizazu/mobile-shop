package com.edu.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SignupRequest {

	public String username;

	public String email;
	
	public String password;
	
	public String repassword;
	
	public String role;
	
	

}
