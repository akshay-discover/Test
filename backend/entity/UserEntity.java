package com.discover.codeorange.backend.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity(name = "users")
//@Table(name = "users", schema = "RRTDashboard")
public class UserEntity implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -3202126971906155270L;

	@Id
	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String encryptedPassword;

	private String emailVerificationToken;

	@Column(nullable = false)
	private Boolean getEmailVerificationStatus = false;
	
	@OneToMany(mappedBy="userDetails", fetch = FetchType.EAGER, cascade=CascadeType.ALL) //Add , cascade=CascadeType.ALL after " if you want persistence
	private List<TeamEntity> teams;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public Boolean getGetEmailVerificationStatus() {
		return getEmailVerificationStatus;
	}

	public void setGetEmailVerificationStatus(Boolean getEmailVerificationStatus) {
		this.getEmailVerificationStatus = getEmailVerificationStatus;
	}
	
	public List<TeamEntity> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamEntity> teams) {
		this.teams = teams;
	}
	
}