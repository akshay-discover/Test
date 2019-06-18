package com.discover.codeorange.backend.dto;

import java.io.Serializable;

//Data Transfer Object

public class TeamDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int teamID;
	private String teamName;
	private String departmentName;
	private UserDTO userDetails;

	public int getTeamID() {
		return teamID;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public UserDTO getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDTO userDetails) {
		this.userDetails = userDetails;
	}

}