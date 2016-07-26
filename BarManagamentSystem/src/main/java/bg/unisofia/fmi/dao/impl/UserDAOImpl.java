package bg.unisofia.fmi.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bg.unisofia.fmi.dao.UserDAO;
import bg.unisofia.fmi.models.User;

@Stateless(name = "userDAO")
public class UserDAOImpl implements UserDAO {

	@PersistenceContext(unitName = "barDatabase")
	EntityManager em;

	@Override
	public void addUser(User user) {
		em.persist(user);
	}

	public User findByUsername(String userName) {
		String txtQuery = "SELECT u FROM User u WHERE u.userName=:userName";
		TypedQuery<User> getUserQuery = em.createQuery(txtQuery, User.class);
		getUserQuery.setParameter("userName", userName);
		return findUser(getUserQuery);
	}
	
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
