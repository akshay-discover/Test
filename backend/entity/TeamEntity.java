package com.discover.codeorange.backend.entity;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Column;

@Entity(name = "teams")
//@Table(name = "teams", schema = "RRTDashboard")
public class TeamEntity implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -3202126971906155270L;

	@Id
	@GeneratedValue
	private int teamID;

	@Column(nullable = false)
	private String teamName;

	@Column(nullable = false)
	private String departmentName;
	
	@ManyToOne
	@JoinColumn(name="users_username")
	private UserEntity userDetails;

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

	public UserEntity getUserEntity() {
		return userDetails;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userDetails = userEntity;
	}

}