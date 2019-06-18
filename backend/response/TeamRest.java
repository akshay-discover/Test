package com.discover.codeorange.backend.response;

//Returning Information -- don't use sensitive data here such as password
public class TeamRest {
	private int teamID;
	private String teamName;
	private String departmentName;

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}