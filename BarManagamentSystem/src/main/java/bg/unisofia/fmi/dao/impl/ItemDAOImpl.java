package bg.unisofia.fmi.dao.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.unisofia.fmi.dao.CategoryDAO;
import bg.unisofia.fmi.dao.ItemDAO;
import bg.unisofia.fmi.models.Category;
import bg.unisofia.fmi.models.Item;

@Singleton
public class ItemDAOImpl implements ItemDAO {

	@PersistenceContext
	EntityManager em;

	@EJB
	CategoryDAO categoryDAO;

	@Override
	public void addItem(Item item) {
		Category category = categoryDAO.getCategory(item.getCategory()
				.getName());
		item.setCategory(category);
		em.persist(item);
	}

	@Override
	public List<Item> getItems() {
		return em.createNamedQuery("Item.findAll", Item.class).getResultList();
	}

}
