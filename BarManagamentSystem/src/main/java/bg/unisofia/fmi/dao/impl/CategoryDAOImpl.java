package bg.unisofia.fmi.dao.impl;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bg.unisofia.fmi.dao.CategoryDAO;
import bg.unisofia.fmi.models.Category;


@Singleton
public class CategoryDAOImpl implements CategoryDAO{

	@PersistenceContext
	EntityManager em;
	
	
	@Override
	public Category getCategory(String categoryName) {
		String txtQuery = "SELECT c FROM Category c WHERE c.name = :name";
		TypedQuery<Category> getUserQuery = em.createQuery(txtQuery, Category.class);
		getUserQuery.setParameter("name", categoryName);
		return getUserQuery.getSingleResult();
	}
	
	@Override
	public void addCategory(Category category) {
		em.persist(category);
	}
	
	@Override
	public List<Category> getCategories() {
		return em.createNamedQuery("Category.findAll", Category.class).getResultList();
	}


}
