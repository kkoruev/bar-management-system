package bg.unisofia.fmi.dao.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.unisofia.fmi.dao.CategoryDAO;
import bg.unisofia.fmi.dao.ItemDAO;
import bg.unisofia.fmi.dto.ItemDTO;
import bg.unisofia.fmi.models.Category;
import bg.unisofia.fmi.models.Item;

@Singleton
public class ItemDAOImpl implements ItemDAO{

	@PersistenceContext
	EntityManager em;
	
	@EJB
	CategoryDAO categoryDAO;
	
	@Override
	public void addItem(ItemDTO itemDTO) {
		Item item = new Item();
		item.setName(itemDTO.getName());
		item.setPrice(itemDTO.getPrice());
		item.setDescription(itemDTO.getDescription());
		Category category = categoryDAO.getCategory(itemDTO.getCategoryName());
		item.setCategory(category);
		em.persist(item);
	}

	@Override
	public List<Item> getItems() {
		// TODO Auto-generated method stub
		return null;
	}

}
