package edu.sjsu.cmpe.kidsontrack.config;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

public class KidsOnTrackServiceConfiguration extends Configuration {
    @NotEmpty
    @JsonProperty
    private String awsAccessId;

    @NotEmpty
    @JsonProperty
    private String awsSecretKey;

	public String getAwsAccessId() {
		return awsAccessId;
	}

	public void setAwsAccessId(String awsAccessId) {
		this.awsAccessId = awsAccessId;
	}

	public String getAwsSecretKey() {
		return awsSecretKey;
	}

	public void setAwsSecretKey(String awsSecretKey) {
		this.awsSecretKey = awsSecretKey;
	}
    
    
}
