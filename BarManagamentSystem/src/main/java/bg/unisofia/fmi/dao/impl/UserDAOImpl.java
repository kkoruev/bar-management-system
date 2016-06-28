package bg.unisofia.fmi.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.unisofia.fmi.dao.UserDAO;
import bg.unisofia.fmi.models.User;

@Stateless(name="userDAO")
public class UserDAOImpl implements UserDAO{
	
	@PersistenceContext(unitName="barDatabase")
	EntityManager em;

	@Override
	public void addUser(User user) {
		em.persist(user);
	}
	
	
	
}
