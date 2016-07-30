package bg.unisofia.fmi.dao.impl;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bg.unisofia.fmi.dao.UserDAO;
import bg.unisofia.fmi.models.User;

@Singleton
public class UserDAOImpl implements UserDAO {

	@PersistenceContext(unitName = "barDatabase")
	EntityManager em;

	/* (non-Javadoc)
	 * @see bg.unisofia.fmi.dao.impl.UserDao#addUser(bg.unisofia.fmi.models.User)
	 */
	@Override
	public void addUser(User user) {
		em.persist(user);
	}

	/* (non-Javadoc)
	 * @see bg.unisofia.fmi.dao.impl.UserDao#findByUsername(java.lang.String)
	 */
	@Override
	public User findByUsername(String userName) {
		String txtQuery = "SELECT u FROM User u WHERE u.userName=:userName";
		TypedQuery<User> getUserQuery = em.createQuery(txtQuery, User.class);
		getUserQuery.setParameter("userName", userName);
		return findUser(getUserQuery);
	}
	
	/* (non-Javadoc)
	 * @see bg.unisofia.fmi.dao.impl.UserDao#authenticateUser(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean authenticateUser(String userName, String password) throws Exception {
		User user = findByUsername(userName);
		if (user == null) {
			//TO DO: MAKE CUSTOM EXCEPTION
			throw new Exception("Invalid userName");
		}
		//IMPLEMTATION LATER - kkoruev
		return false;
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
