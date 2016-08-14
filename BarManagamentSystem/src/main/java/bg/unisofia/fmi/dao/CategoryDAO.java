package bg.unisofia.fmi.dao;

import java.util.List;

import bg.unisofia.fmi.models.Category;

public interface CategoryDAO {
	
	public Category getCategory(String categoryName);
	
	public void addCategory(Category category);

	List<Category> getCategories();
	
}	
