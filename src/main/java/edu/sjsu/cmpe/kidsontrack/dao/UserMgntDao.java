package edu.sjsu.cmpe.kidsontrack.dao;

import java.util.List;

import edu.sjsu.cmpe.kidsontrack.dto.usermgnt.UserDto;
import edu.sjsu.cmpe.kidsontrack.exception.UserManagmentException;
import edu.sjsu.cmpe.kidsontrack.model.usermgnt.User;

/**
 * @author Lei Zhang
 *
 */
public interface UserMgntDao {

	public User getUserById(String id) throws UserManagmentException;	
	public User getUserByEmail(String email) throws UserManagmentException;
	public List<User> getAllUser() throws UserManagmentException;
	public UserDto saveOrUpdateUser(User user) throws UserManagmentException;
	public boolean deleteUser(String Id) throws UserManagmentException;	
	
	public UserDto authenticate(String email, String password) throws UserManagmentException;
	
}


