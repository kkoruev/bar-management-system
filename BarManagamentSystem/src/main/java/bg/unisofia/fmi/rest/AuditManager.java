package bg.unisofia.fmi.rest;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import bg.unisofia.fmi.dao.OrderDAO;
import bg.unisofia.fmi.enums.Status;
import bg.unisofia.fmi.models.Category;
import bg.unisofia.fmi.models.Item;
import bg.unisofia.fmi.models.Order;

@Path("/audit")
public class AuditManager {

	@EJB
	OrderDAO orderDAO;
	
	@GET
	@Path("/income/month")
	public Double getIncomeForMonth(Date month) {
		List<Order> ordersForMonth = orderDAO.getOrdersByDate(month);
		Double price = 0.0;
		for (Order order : ordersForMonth) {
			for (Item item : order.getItems()) {
				price = price + item.getPrice();
			}
		}
		return price;
	}

	public Double getIncomeForItem(Item item) {
		return 1.0;
	}

	public Integer getCountOfLateOrders(Date month) {
		int count = 0;
		List<Order> ordersForMonth = orderDAO.getOrdersByDate(month);
		for (Order order : ordersForMonth) {
			if (order.getStatus().equals(Status.OVERDUE)) {
				count++;
			}
		}
		return count;
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
