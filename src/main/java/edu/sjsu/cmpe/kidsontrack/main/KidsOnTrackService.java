package edu.sjsu.cmpe.kidsontrack.main;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.*;

import edu.sjsu.cmpe.kidsontrack.api.resources.CourseResource;
import edu.sjsu.cmpe.kidsontrack.api.resources.RootResource;
import edu.sjsu.cmpe.kidsontrack.api.resources.StudentResource;
import edu.sjsu.cmpe.kidsontrack.api.resources.TeacherResource;
import edu.sjsu.cmpe.kidsontrack.config.KidsOnTrackServiceConfiguration;
import edu.sjsu.cmpe.kidsontrack.resources.ExampleResource;
import edu.sjsu.cmpe.kidsontrack.resources.WelcomeResource;

public class KidsOnTrackService extends
		Service<KidsOnTrackServiceConfiguration> {

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
		environment.addResource(RootResource.class);
		/** Teacher API */
		environment.addResource(TeacherResource.class);
		/** Student API */
		environment.addResource(StudentResource.class);
		/** Course API */
		environment.addResource(CourseResource.class);

		/** GUI Side */
		environment.addResource(ExampleResource.class);
		environment.addResource(WelcomeResource.class);
	}
}
