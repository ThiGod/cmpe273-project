package edu.sjsu.cmpe.kidsontrack.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.kidsontrack.domain.UserCredential;

@JsonPropertyOrder(alphabetic = true)
public class UserCredentialDto extends LinksDto {
	private UserCredential userCredential;
	
	private Boolean authenticationResult;

	public UserCredential getUserCredential() {
		return userCredential;
	}

	public void setUserCredential(UserCredential userCredential) {
		this.userCredential = userCredential;
	}

	public Boolean getAuthenticationResult() {
		return authenticationResult;
	}

	public void setAuthenticationResult(Boolean authenticationResult) {
		this.authenticationResult = authenticationResult;
	}
	


}
