package bg.unisofia.fmi.dao;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.unisofia.fmi.models.Role;

@Singleton
public class RoleDAO {
	
	@PersistenceContext(unitName="barDatabase")
	private EntityManager em;
	
	public RoleDAO() {
	}

	public void addRole(Role role) {
		em.persist(role);
	}
	
}
