package com.nopcommerce.data;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class UserDataMapper {
	public static UserDataMapper getUserData() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.PROJECT_PATH  + "/resources/UserData.json"), UserDataMapper.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@JsonProperty("fisrtname")
	private String firstName;
	
	@JsonProperty("lastname")
	private String lastName;
	
	@JsonProperty("email")
	private String emailAddress;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("date")
	private String date;
	
	@JsonProperty("month")
	private String month;
	
	@JsonProperty("year")
	private String year;
	
	@JsonProperty("login")
	private Login login;
	
	static class Login {
		@JsonProperty("username")
		private String username;
		
		@JsonProperty("password")
		private String password;
	}
	public String getLoginUserName() {
		return login.username;
	}
	public String getLoginPassword(){
		return login.password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public String getDate() {
		return date;
	}
	public String getMonth() {
		return month;
	}
	public String getYear() {
		return year;
	}
	
	@JsonProperty("subjects")
	private List<Subject> subjects;
	
	public List<Subject> getSubjects(){
		return subjects;
	}
	
	public static class Subject{

		@JsonProperty("name")
		private String name;
		

		@JsonProperty("point")
		private float point;
		
		public String getName() {
			return name;
		}
		
		public float getPoint() {
			return point;
		}
	}

}
