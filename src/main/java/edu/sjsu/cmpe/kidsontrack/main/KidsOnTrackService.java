package edu.sjsu.cmpe.kidsontrack.main;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.*;

import edu.sjsu.cmpe.kidsontrack.api.resources.CourseResource;
import edu.sjsu.cmpe.kidsontrack.api.resources.RootResource;
import edu.sjsu.cmpe.kidsontrack.api.resources.StudentResource;
import edu.sjsu.cmpe.kidsontrack.api.resources.TeacherResource;
import edu.sjsu.cmpe.kidsontrack.config.KidsOnTrackServiceConfiguration;
import edu.sjsu.cmpe.kidsontrack.dao.StudentMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.StudentMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.ui.resources.HomepageResource;
import edu.sjsu.cmpe.kidsontrack.ui.resources.LoginResource;
import edu.sjsu.cmpe.kidsontrack.ui.resources.RegisterResource;
import edu.sjsu.cmpe.kidsontrack.ui.resources.StudentsResource;
import edu.sjsu.cmpe.kidsontrack.ui.resources.TeachersResource;

public class KidsOnTrackService extends
		Service<KidsOnTrackServiceConfiguration> {

	public static void main(String[] args) throws Exception {
		new KidsOnTrackService().run(args);
	}

	public void initialize(Bootstrap<KidsOnTrackServiceConfiguration> bootstrap) {
		bootstrap.setName("kidsontrack-service");
		bootstrap.addBundle(new ViewBundle());
		bootstrap.addBundle(new AssetsBundle());
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
		TeacherMgntDaoInterface teacherMgntDao = new TeacherMgntDao();
		StudentMgntDaoInterface studentMgntDao = new StudentMgntDao();
		
		environment.addResource(LoginResource.class);
		environment.addResource(HomepageResource.class);
		environment.addResource(RegisterResource.class);
		environment.addResource(new StudentsResource(studentMgntDao));
		environment.addResource(new TeachersResource(teacherMgntDao));
	}
}
