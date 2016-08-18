package bg.unisofia.fmi.dao;

import java.util.List;

import bg.unisofia.fmi.models.Item;

public interface ItemDAO {
	
	public void addItem(Item item);
	
	public List<Item> getItems();
	
}
