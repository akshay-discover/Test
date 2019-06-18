package com.discover.codeorange.backend.dto;

import java.io.Serializable;
import java.util.List;

//Data Transfer Object

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 2L;

	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String encryptedPassword;
	private String emailVarificationToken;
	private boolean emailVarificationStatus = false;
	private List<TeamDTO> teams;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailVarificationToken() {
		return emailVarificationToken;
	}

	public void setEmailVarificationToken(String emailVarificationToken) {
		this.emailVarificationToken = emailVarificationToken;
	}

	public boolean getEmailVarificationStatus() {
		return emailVarificationStatus;
	}

	public void setEmailVarificationStatus(boolean emailVarificationStatus) {
		this.emailVarificationStatus = emailVarificationStatus;
	}

	public List<TeamDTO> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamDTO> teams) {
		this.teams = teams;
	}
}