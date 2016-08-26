package bg.unisofia.fmi.rest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bg.unisofia.fmi.dao.BillDAO;
import bg.unisofia.fmi.dao.OrderDAO;
import bg.unisofia.fmi.dto.MonthIncomeDTO;
import bg.unisofia.fmi.dto.WaiterStatisticsDTO;
import bg.unisofia.fmi.enums.Status;
import bg.unisofia.fmi.models.Bill;
import bg.unisofia.fmi.models.Category;
import bg.unisofia.fmi.models.Item;
import bg.unisofia.fmi.models.OrderUnit;

@Path("/audit")
public class AuditManager {

	@EJB
	OrderDAO orderDAO;
	
	@EJB
	BillDAO billDAO;
	
	@Path("/income/week")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<WaiterStatisticsDTO> getWaiterBillsForLastWeek() {		
		Map<String, WaiterStatisticsDTO> waiterBills = new HashMap<>();		
		List<Bill> allBillsForWeek = billDAO.getAllBillsForLastWeek();
		
		for(Bill bill : allBillsForWeek) {
			WaiterStatisticsDTO waiterStatDTO = null;
			if(waiterBills.containsKey(bill.getUser().getName())) {
				waiterStatDTO = waiterBills.get(bill.getUser().getName());
				waiterStatDTO.setCountBills(waiterStatDTO.getCountBills() + 1);
			} else {
				waiterStatDTO = new WaiterStatisticsDTO();
				waiterStatDTO.setUsername(bill.getUser().getName());
				waiterStatDTO.setCountBills(1);
				waiterBills.put(bill.getUser().getName(), waiterStatDTO);
			}
		}
		
		return new ArrayList<WaiterStatisticsDTO>(waiterBills.values());
		
	}
	
	@GET
	@Path("/income/year")
	@Produces(MediaType.APPLICATION_JSON)
	public MonthIncomeDTO getIncomeForYear() throws ParseException {
		List<Double> monthsIncome = new ArrayList<>();
		
		for(int month = 1; month <= 12; month++ ){
			List<OrderUnit> ordersForMonth = orderDAO.getOrdersForMonth(month);
			Double price = 0.0;
			for(OrderUnit order : ordersForMonth) {
				for(Item item : order.getItems()) {
					price = price + item.getPrice();
				}
			}
			monthsIncome.add(price);
		}
		MonthIncomeDTO monthIncomDTO = new MonthIncomeDTO();
		monthIncomDTO.setIncome(monthsIncome);
		return monthIncomDTO;
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
	
}
