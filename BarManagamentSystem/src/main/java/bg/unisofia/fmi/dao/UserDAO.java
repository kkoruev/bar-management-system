package bg.unisofia.fmi.dao;

import bg.unisofia.fmi.dto.UserDTO;
import bg.unisofia.fmi.models.User;

public interface UserDAO {

	void registerUser(UserDTO userDTO) throws Exception;

	User findByUsername(String userName);

	boolean authenticateUser(String userName, String password) throws Exception;
	
}
