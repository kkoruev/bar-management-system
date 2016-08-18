package bg.unisofia.fmi.dao;

import bg.unisofia.fmi.models.User;

public interface UserDAO {

	void registerUser(User user) throws Exception;

	User findByUsername(String userName);

	boolean authenticateUser(String userName, String password) throws Exception;
	
}
