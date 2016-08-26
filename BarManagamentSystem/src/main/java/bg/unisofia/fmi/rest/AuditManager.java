package bg.unisofia.fmi.rest;

import java.text.ParseException;
import java.time.Month;
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
import bg.unisofia.fmi.dto.StatisticsDTO;
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
	public List<StatisticsDTO> getWaiterBillsForLastWeek() {		
		Map<String, StatisticsDTO> waiterBills = new HashMap<>();		
		List<Bill> allBillsForWeek = billDAO.getAllBillsForLastWeek();
		
		for(Bill bill : allBillsForWeek) {
			StatisticsDTO waiterStatDTO = null;
			if(waiterBills.containsKey(bill.getUser().getName())) {
				waiterStatDTO = waiterBills.get(bill.getUser().getName());
				waiterStatDTO.setValue(waiterStatDTO.getValue() + 1);
			} else {
				waiterStatDTO = new StatisticsDTO();
				waiterStatDTO.setLabel(bill.getUser().getName());
				waiterStatDTO.setValue(1.0);
				waiterBills.put(bill.getUser().getName(), waiterStatDTO);
			}
		}
		
		return new ArrayList<StatisticsDTO>(waiterBills.values());
		
	}
	
	@GET
	@Path("/income/year")
	@Produces(MediaType.APPLICATION_JSON)
	public List<StatisticsDTO> getIncomeForYear() throws ParseException {
		List<StatisticsDTO> monthsIncome = new ArrayList<>();
		
		for(Month month : Month.values()){
			List<OrderUnit> ordersForMonth = orderDAO.getOrdersForMonth(month.getValue());
			Double price = 0.0;
			for(OrderUnit order : ordersForMonth) {
				for(Item item : order.getItems()) {
					price = price + item.getPrice();
				}
			}
			StatisticsDTO stat = new StatisticsDTO();
			stat.setLabel(month.toString());
			stat.setValue(price);
			monthsIncome.add(stat);
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
	
}
