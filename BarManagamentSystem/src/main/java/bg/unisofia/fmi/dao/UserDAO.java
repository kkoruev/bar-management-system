package bg.unisofia.fmi.dao;

import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import bg.unisofia.fmi.models.User;

@Singleton
public class UserDAO {

	@PersistenceContext(unitName="barDatabase")
	private EntityManager em;

	public UserDAO() {
	}

	public UserDAO(EntityManager em) {
		this.em = em;
	}

	public void addUser(User user) {
		em.persist(user);
	}
}
