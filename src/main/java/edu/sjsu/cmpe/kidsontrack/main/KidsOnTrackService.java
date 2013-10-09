package edu.sjsu.cmpe.kidsontrack.main;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.*;

import edu.sjsu.cmpe.kidsontrack.config.KidsOnTrackServiceConfiguration;
import edu.sjsu.cmpe.kidsontrack.controller.usermgnt.LoginController;
import edu.sjsu.cmpe.kidsontrack.controller.usermgnt.MainpageController;
import edu.sjsu.cmpe.kidsontrack.controller.usermgnt.SignupController;
import edu.sjsu.cmpe.kidsontrack.resources.ExampleResource;

public class KidsOnTrackService extends Service<KidsOnTrackServiceConfiguration> {

    public static void main(String[] args) throws Exception {
	new KidsOnTrackService().run(args);
    }


    public void initialize(Bootstrap<KidsOnTrackServiceConfiguration> bootstrap) {
    	bootstrap.setName("kidsontrack-service");
    	bootstrap.addBundle(new ViewBundle());
    }

  
    public void run(KidsOnTrackServiceConfiguration configuration,
	    Environment environment) throws Exception {
	/** Root API */
	environment.addResource(LoginController.class);
	environment.addResource(MainpageController.class);
	environment.addResource(SignupController.class);
	//environment.addResource(ExampleResource.class);

    }
}
