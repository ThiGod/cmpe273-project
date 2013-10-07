package edu.sjsu.cmpe.kidsontrack.model.usermgnt;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

/**
 * This is the user bean.
 * 
 * @author: Lei Zhang 
 * 
 * Creation date: (09/30/2013)
 */
@Document
@XmlRootElement(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * This is the unique user id.
	 */
	@Id
	private String userId;

	/**
	 * This is the user first name.
	 */
	private String firstName;

	/**
	 * This is the user last name.
	 */
	private String lastName;

	/**
	 * This is the user email.
	 */
	private String email;

	/**
	 * This is the user password.
	 */
	private String password;

	/**
	 * This is the user role as teacher or parent.
	 */
	private String role;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
