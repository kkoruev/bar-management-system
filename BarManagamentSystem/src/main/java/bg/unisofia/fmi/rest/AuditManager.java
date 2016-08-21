package bg.unisofia.fmi.rest;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
	@Path("/income")
	@Consumes(MediaType.APPLICATION_JSON)
	public Double getIncomeFosrMonth(@QueryParam("year") String year, @QueryParam("month") String monthNumber) {
		Date month  = new Date();
		List<Order> ordersForMonth = orderDAO.getOrdersByDate(month);
		Double price = 0.0;
		for (Order order : ordersForMonth) {
			for (Item item : order.getItems()) {
				price = price + item.getPrice();
			}
		}
		return price;
	}

	public Double getIncomeForItem(String itemName) {
		List<Order> allOrders = orderDAO.getAllOrders();
		Double income = 0.0;
		for (Order order : allOrders) {
			for (Item item : order.getItems()) {
				if (item.getName().equals(itemName)) {
					income = income + item.getPrice();
				}
			}
		}
		return income;
	}

	public Integer getCountOfLateOrders(Integer year,Integer monthNumber) {
		Date month = new Date();
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
