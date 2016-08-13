package bg.unisofia.fmi.dao.impl;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bg.unisofia.fmi.dao.UserDAO;
import bg.unisofia.fmi.dto.UserDTO;
import bg.unisofia.fmi.models.User;
import bg.unisofia.fmi.utils.EncryptionUtils;
import bg.unisofia.fmi.enums.Role;
import bg.unisofia.fmi.exceptions.InvalidRoleException;

@Singleton
public class UserDAOImpl implements UserDAO {

	@PersistenceContext(unitName = "barDatabase")
	EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * bg.unisofia.fmi.dao.impl.UserDao#registerUser(bg.unisofia.fmi.models.
	 * User)
	 */
	@Override
	public void registerUser(UserDTO userDTO) throws Exception{
		if (findByUsername(userDTO.getUsername()) != null){
			throw new Exception("User with name " + userDTO.getUsername() + " exists");
		}
		if(!Role.contains(userDTO.getRole())) {
			throw new InvalidRoleException();
		}
		String salt = EncryptionUtils.generateSalt();
		String hashedPassword = EncryptionUtils.getHashedPassword(userDTO.getUsername(), salt);
		User user = new User();
		user.setRole(userDTO.getRole());
		user.setUsername(userDTO.getUsername());
		user.setName(userDTO.getName());
		user.setPasswordHash(hashedPassword);
		user.setPasswordSalt(salt);
		em.persist(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see bg.unisofia.fmi.dao.impl.UserDao#findByUsername(java.lang.String)
	 */
	@Override
	public User findByUsername(String username) {
		String txtQuery = "SELECT u FROM User u WHERE u.username = :username";
		TypedQuery<User> getUserQuery = em.createQuery(txtQuery, User.class);
		getUserQuery.setParameter("username", username);
		return findUser(getUserQuery);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see bg.unisofia.fmi.dao.impl.UserDao#authenticateUser(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean authenticateUser(String userName, String password) throws Exception {
		User user = findByUsername(userName);
		return user != null;
	}

	private User findUser(TypedQuery<User> query) {
		try {
			User result = query.getSingleResult();
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
