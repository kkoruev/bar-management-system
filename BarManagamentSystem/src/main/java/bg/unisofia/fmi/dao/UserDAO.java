package bg.unisofia.fmi.dao;

import bg.unisofia.fmi.models.User;

public interface UserDAO {

	void addUser(User user);

	User findByUsername(String userName);

	boolean authenticateUser(String userName, String password) throws Exception;
	
}
