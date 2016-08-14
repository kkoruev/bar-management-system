package bg.unisofia.fmi.dao;

import bg.unisofia.fmi.models.Category;

public interface CategoryDAO {
	
	public Category getCategory(String categoryName);
	
	public void addCategory(Category category);
	
}	
