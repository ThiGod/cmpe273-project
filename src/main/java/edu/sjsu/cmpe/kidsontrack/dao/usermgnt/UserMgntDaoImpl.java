/**
 * 
 */
package edu.sjsu.cmpe.kidsontrack.dao.usermgnt;

import edu.sjsu.cmpe.kidsontrack.dto.usermgnt.UserDto;
import edu.sjsu.cmpe.kidsontrack.exception.UserManagmentException;
import edu.sjsu.cmpe.kidsontrack.model.usermgnt.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lei Zhang
 * 
 */
@Repository
public class UserMgntDaoImpl implements UserMgntDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public User getUserById(String id) throws UserManagmentException {

		User user = mongoTemplate.findById(id, User.class);
		return user;
	}
	
	public User getUserByEmail(String email) throws UserManagmentException {
		User user = null;
		try {
			Query searchUserQuery = new Query(Criteria.where("email").is(email));
			user = mongoTemplate.findOne(searchUserQuery, User.class);
		}
		catch (DataAccessException e) {
			throw new UserManagmentException(e);
		}
		return user;
	}

	public UserDto saveOrUpdateUser(User user) throws UserManagmentException {
		UserDto httpUser = new UserDto();

		try {
			if (!mongoTemplate.collectionExists(User.class)) {
				mongoTemplate.createCollection(User.class);
			}

			Query query = new Query();
			query.addCriteria(Criteria.where("email").is(user.getEmail()));

			Update update = new Update();
			update.set("email", user.getEmail());
			update.set("password", user.getPassword());
			update.set("firstName", user.getFirstName());
			update.set("lastName", user.getLastName());
			update.set("role", user.getRole());
			mongoTemplate.upsert(query, update, User.class);

			httpUser.setUser(user);
			httpUser.setAuthResult(1);
			return httpUser;
		} catch (DataAccessException e) {
			throw new UserManagmentException(e);
		}
	}

	public List<User> getAllUser() throws UserManagmentException {
		List<User> users = null;
		try {
			users = (List<User>) mongoTemplate.findAll(User.class);
		} catch (DataAccessException e) {
			throw new UserManagmentException(e);
		}
		return users;
	}

	public boolean deleteUser(String email) throws UserManagmentException {
		try {
			Query searchQuery = new Query(Criteria.where("email").is(email));
			mongoTemplate.remove(searchQuery, User.class);
			return true;
		} catch (DataAccessException e) {
			throw new UserManagmentException(e);
		}
	}

	@Override
	public UserDto authenticate(String email, String password)
			throws UserManagmentException {
		UserDto httpUser = new UserDto();
		User user = getUserByEmail(email);
		httpUser.setUser(user);

		if (user == null) {
			httpUser.setAuthResult(-1);
		} else {
			if (!user.getPassword().equalsIgnoreCase(password)) {
				httpUser.setAuthResult(0);
			} else {
				httpUser.setAuthResult(1);
			}
		}
		return httpUser;
	}
	


}
