package bg.unisofia.fmi.dao;

import java.util.List;

import bg.unisofia.fmi.dto.ItemDTO;
import bg.unisofia.fmi.models.Item;

public interface ItemDAO {
	
	public void addItem(ItemDTO itemDTO);
	
	public List<Item> getItems();
	
}
