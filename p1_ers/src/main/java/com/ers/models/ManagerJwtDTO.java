package com.revature.models;

import java.util.Objects;

public class ManagerJwtDTO {
	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	
	public ManagerJwtDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ManagerJwtDTO(String username, String firstname, String lastname, String email) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	public ManagerJwtDTO(int id, String username, String firstname, String lastname, String email) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	@Override
	public String toString() {
		return "ManagerJwtDTO [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + "]";
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstname, id, lastname, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManagerJwtDTO other = (ManagerJwtDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstname, other.firstname) && id == other.id
				&& Objects.equals(lastname, other.lastname) && Objects.equals(username, other.username);
	}
}
