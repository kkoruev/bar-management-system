package bg.unisofia.fmi.dao.impl;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.unisofia.fmi.dao.CategoryDAO;
import bg.unisofia.fmi.models.Category;


@Singleton
public class CategoryDAOImpl implements CategoryDAO{

	@PersistenceContext
	EntityManager em;
	
	@Override
	public void addCategory(Category category) {
		em.persist(category);
	}

}
