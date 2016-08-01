package bg.unisofia.fmi.rest;

import java.util.Date;

import javax.ws.rs.Path;

import bg.unisofia.fmi.dto.CategoryDTO;
import bg.unisofia.fmi.dto.ItemDTO;

@Path("/audit")
public class AuditManager {

	public Double getIncomeForMonth(Date month) {
		return 1.0;
	}

	public Double getIncomeForItem(ItemDTO item) {
		return 1.0;
	}

	public Integer getCountOfLateOrders() {
		return 1;
	}

	public Integer getBestDayInMonth() {
		return 5;	
	}

	public ItemDTO getMostSellingItemForMonth(Date month){
		return new ItemDTO();
	}
	
	public CategoryDTO getMostSellingCategoryForMonth(Date month){
		return new CategoryDTO();
	}
	
}
