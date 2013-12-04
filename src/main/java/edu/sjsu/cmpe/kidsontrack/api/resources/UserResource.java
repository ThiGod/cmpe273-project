package edu.sjsu.cmpe.kidsontrack.api.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.kidsontrack.dao.StudentMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.StudentMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.domain.UserCredential;
import edu.sjsu.cmpe.kidsontrack.dto.UserCredentialDto;




@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
	
	private StudentMgntDaoInterface studentMgntDao = new StudentMgntDao();

	private TeacherMgntDaoInterface teacherMgntDao = new TeacherMgntDao();

    public UserResource() {
	// do nothing
    }

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "verify-user")
	public UserCredentialDto authorizeUser(@Valid UserCredential userCredential) throws Exception {

        String email = userCredential.getEmail();
        String password = userCredential.getPassword();

		Boolean result = false;
		
		if (studentMgntDao.isFound(email, password)){
			result = true;
		} else if (teacherMgntDao.isFound(email, password)){
			result = true;
		}

		UserCredentialDto response = new UserCredentialDto();
		response.setUserCredential(userCredential);
		response.setAuthenticationResult(result);
		
		return response;

	}
}

