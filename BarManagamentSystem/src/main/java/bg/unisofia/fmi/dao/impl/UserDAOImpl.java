package bg.unisofia.fmi.dao.impl;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bg.unisofia.fmi.dao.UserDAO;
import bg.unisofia.fmi.dto.UserDTO;
import bg.unisofia.fmi.models.Role;
import bg.unisofia.fmi.models.User;
import bg.unisofia.fmi.utils.EncryptionUtils;

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
		String salt = EncryptionUtils.generateSalt();
		String hashedPassword = EncryptionUtils.getHashedPassword(userDTO.getUsername(), salt);
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setName(userDTO.getName());
		user.setPasswordHash(hashedPassword);
		user.setPasswordSalt(salt);
		Role userRole = new Role();
		userRole.setRoleType(userDTO.getUserRole().getRoleType());
		user.setRole(userRole);
		em.persist(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see bg.unisofia.fmi.dao.impl.UserDao#findByUsername(java.lang.String)
	 */
	@Override
	public User findByUsername(String userName) {
		String txtQuery = "SELECT u FROM User u WHERE u.userName=:userName";
		TypedQuery<User> getUserQuery = em.createQuery(txtQuery, User.class);
		getUserQuery.setParameter("userName", userName);
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
