/**
 * 
 */
package edu.sjsu.cmpe.kidsontrack.service;

import edu.sjsu.cmpe.kidsontrack.dao.UserMgntDao;
import edu.sjsu.cmpe.kidsontrack.domain.User;
import edu.sjsu.cmpe.kidsontrack.dto.UserDto;
import edu.sjsu.cmpe.kidsontrack.exception.UserManagmentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lei Zhang
 * 
 */
@Service
public class UserSecurityServiceImpl implements UserMgntService {

	@Autowired
	private UserMgntDao userMgntDao;

	public UserMgntDao getUserRoleDao() {
		return userMgntDao;
	}

	public void setUserRoleDao(UserMgntDao userRoleDao) {
		this.userMgntDao = userRoleDao;
	}

	public User getUserById(String userId) throws UserManagmentException {
		User user = null;
		user = userMgntDao.getUserById(userId);
		return user;
	}

	public User getUserByEmail(String email) throws UserManagmentException {
		User user = null;
		user = userMgntDao.getUserByEmail(email);
		return user;
	}

	public List<User> getAllUsers() throws UserManagmentException {
		List<User> users = userMgntDao.getAllUser();
		return users;
	}

	public UserDto addUser(User user) throws UserManagmentException {
		return userMgntDao.saveOrUpdateUser(user);
	}

	public boolean deleteUser(String userId) throws UserManagmentException {
		return userMgntDao.deleteUser(userId);
	}

	public UserDto updateUser(User user) throws UserManagmentException {
		return userMgntDao.saveOrUpdateUser(user);
	}

	public UserDto authenticate(String email, String password)
			throws UserManagmentException {

		return userMgntDao.authenticate(email, password);
	}

}
