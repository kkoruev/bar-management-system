package bg.unisofia.fmi.rest;

import java.util.Date;

import javax.ws.rs.Path;

import bg.unisofia.fmi.models.Category;
import bg.unisofia.fmi.models.Item;

@Path("/audit")
public class AuditManager {

	public Double getIncomeForMonth(Date month) {
		return 1.0;
	}

	public Double getIncomeForItem(Item item) {
		return 1.0;
	}

	public Integer getCountOfLateOrders(Date month) {
		return 1;
	}

	public Integer getBestDayInMonth(Date month) {
		return 5;	
	}

	public Item getMostSellingItemForMonth(Date month){
		return new Item();
	}
	
	public Category getMostSellingCategoryForMonth(Date month){
		return new Category();
	}
	
}
