package edu.sjsu.cmpe.kidsontrack.dto.usermgnt;

import java.io.Serializable;

import edu.sjsu.cmpe.kidsontrack.model.usermgnt.User;

/**
 * @author Lei Zhang
 * 
 */
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;

	private Integer authResult;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getAuthResult() {
		return authResult;
	}

	public void setAuthResult(Integer authResult) {
		this.authResult = authResult;
	}

}
