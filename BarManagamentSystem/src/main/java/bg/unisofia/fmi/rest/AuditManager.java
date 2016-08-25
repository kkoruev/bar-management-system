package bg.unisofia.fmi.rest;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import bg.unisofia.fmi.dao.OrderDAO;
import bg.unisofia.fmi.enums.Status;
import bg.unisofia.fmi.models.Category;
import bg.unisofia.fmi.models.Item;
import bg.unisofia.fmi.models.OrderUnit;

@Path("/audit")
public class AuditManager {

	@EJB
	OrderDAO orderDAO;
	
	@GET
	@Path("/income")
	@Consumes(MediaType.APPLICATION_JSON)
	public Double[] getIncomeFosrMonth(@QueryParam("year") String year) {
		Double[] monthsIncome = new Double[12];
		
		for(int month = 1; month <= 12; month++ ){
			List<OrderUnit> ordersForMonth = orderDAO.getOrdersForMonth(month);
			Double price = 0.0;
			for(OrderUnit order : ordersForMonth) {
				for(Item item : order.getItems()) {
					price = price + item.getPrice();
				}
			}
			monthsIncome[month] = price;
		}
		
		return monthsIncome;
	}

	public Double getIncomeForItem(String itemName) {
		List<OrderUnit> allOrders = orderDAO.getAllOrders();
		Double income = 0.0;
		for (OrderUnit order : allOrders) {
			for (Item item : order.getItems()) {
				if (item.getName().equals(itemName)) {
					income = income + item.getPrice();
				}
			}
		}
		return income;
	}

	public Integer getCountOfLateOrders(Integer year, Integer monthNumber) {
		Date month = new Date();
		int count = 0;
		List<OrderUnit> ordersForMonth = orderDAO.getOrdersByDate(month);
		for (OrderUnit order : ordersForMonth) {
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
