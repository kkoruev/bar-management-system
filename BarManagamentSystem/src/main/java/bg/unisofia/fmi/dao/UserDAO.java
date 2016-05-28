package bg.unisofia.fmi.dao;

import javax.persistence.EntityManager;
import bg.unisofia.fmi.models.User;

public class UserDAO {

	
	public static void main(String[] args) {
		UserDAO userDao = new UserDAO(em);
		
	}
	
    private EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }
    
    public void addUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
}
