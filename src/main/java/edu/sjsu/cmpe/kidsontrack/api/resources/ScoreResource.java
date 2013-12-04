package edu.sjsu.cmpe.kidsontrack.api.resources;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.xerox.amazonws.sns.NotificationService;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.kidsontrack.dao.StudentMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.StudentMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.domain.Grade;
import edu.sjsu.cmpe.kidsontrack.domain.Score;
import edu.sjsu.cmpe.kidsontrack.domain.Teacher;
import edu.sjsu.cmpe.kidsontrack.dto.GradesDto;
import edu.sjsu.cmpe.kidsontrack.dto.LinkDto;
import edu.sjsu.cmpe.kidsontrack.dto.LinksDto;
import edu.sjsu.cmpe.kidsontrack.exception.HTTPClientException;
import edu.sjsu.cmpe.kidsontrack.util.SequenceGenerator;

@Path("/v1/teachers/{teacherId}/students/{studentId}/courses/{courseId}/scores/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScoreResource {

	private StudentMgntDaoInterface studentMgntDao = new StudentMgntDao();

	private TeacherMgntDaoInterface teacherMgntDao = new TeacherMgntDao();
	
	private NotificationService sns;
	private String topicArn;

	public ScoreResource() {
		// do nothing
	}
	
	public ScoreResource(NotificationService sns, String topicArn) {
		this.sns = sns;
		this.topicArn = topicArn;
	}

	@GET
	@Timed(name = "view-scores")
	public GradesDto getAllScores(@PathParam("teacherId") long teacherId,
			@PathParam("studentId") long studentId,
			@PathParam("courseId") long courseId) throws Exception {

		Teacher teacher = teacherMgntDao.findTeacherById(String
				.valueOf(teacherId));

		if (null == teacher) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		List<Grade> grades = studentMgntDao.getAllGrades(String
				.valueOf(studentId));

		GradesDto response = new GradesDto();
		response.setGrades(grades);
		
		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "create-score")
	public Response createScore(@PathParam("teacherId") long teacherId,
			@PathParam("studentId") long studentId,
			@PathParam("courseId") long courseId, @Valid Score score)
			throws Exception {

		Teacher teacher = teacherMgntDao.findTeacherById(String
				.valueOf(teacherId));

		if (null == teacher) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		long id = SequenceGenerator.nextScoreId();
		score.setScoreId(String.valueOf(id));

		studentMgntDao.addScore(String.valueOf(studentId),
				String.valueOf(courseId), score);

		System.out.println("Add score: " + score + " to Student DB");
		
		sns.publish(topicArn, "add score:" + score + " to student:" + studentId, "[SNS] One Score Added");

		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-score", "/teachers/" + teacherId
				+ "/students/" + studentId + "/courses/" + courseId
				+ "/scores/", "GET"));

		return Response.status(201).entity(links).build();

	}



}
