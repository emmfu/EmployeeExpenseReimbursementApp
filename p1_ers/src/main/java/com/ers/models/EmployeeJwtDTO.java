package com.revature.models;

import java.util.Objects;

public class EmployeeJwtDTO {
	private int id;
	private String username;
	// JWTs should not contain sensitive information like passwords
	private String firstName;
	private String lastName;
	private String email;
	
	public EmployeeJwtDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeJwtDTO(String username, String firstName, String lastName, String email) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public EmployeeJwtDTO(int id, String username, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	@Override
	public String toString() {
		return "EmployeeJwtDTO [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeJwtDTO other = (EmployeeJwtDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(username, other.username);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	
	
	

}
