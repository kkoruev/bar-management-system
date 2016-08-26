package bg.unisofia.fmi.dao.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bg.unisofia.fmi.dao.OrderDAO;
import bg.unisofia.fmi.models.Item;
import bg.unisofia.fmi.models.OrderUnit;
import bg.unisofia.fmi.models.User;

@Singleton
public class OrderDAOImpl implements OrderDAO{

	@PersistenceContext(unitName = "barDatabase")
	EntityManager em;
	
	@Override
	public List<OrderUnit> getOrdersByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<OrderUnit> getOrdersForMonth(Integer month)
			throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String startMonth = "01-" + month + "-" + currentYear;
		String endMonth = "31-" + month + "-" + currentYear;
		Date startMonthDate = dateFormat.parse(startMonth);
		Date endMonthDate = dateFormat.parse(endMonth);
		String txtQuery = "SELECT o FROM OrderUnit o  WHERE o.completedAt BETWEEN :startDate and :endDate";
		TypedQuery<OrderUnit> getOrdersForMonth = em
				.createQuery(txtQuery, OrderUnit.class)
				.setParameter("startDate", startMonthDate)
				.setParameter("endDate", endMonthDate);

		return getOrdersForMonth.getResultList();
	}

	@Override
	public void createNewOrder(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addItems(String orderName, List<Item> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveOrder(User bartender) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void completeOrders(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrderUnit> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
