package bg.unisofia.fmi.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import bg.unisofia.fmi.dao.BillDAO;
import bg.unisofia.fmi.dto.ItemDTO;
import bg.unisofia.fmi.enums.Status;
import bg.unisofia.fmi.exceptions.InvalidUserException;
import bg.unisofia.fmi.models.Bill;
import bg.unisofia.fmi.models.Item;
import bg.unisofia.fmi.models.OrderUnit;
import bg.unisofia.fmi.models.User;

@Singleton
public class BillDAOImpl implements BillDAO {

	@PersistenceContext(unitName = "barDatabase")
	EntityManager em;
	
	@Override
	public List<Bill> getOpenBillsByUser(User user) {
		String txtQuery = "SELECT b FROM Bill b WHERE b.user = :user AND b.completedAt IS NULL";
		TypedQuery<Bill> getBillsQuery = em.createQuery(txtQuery, Bill.class);
		getBillsQuery.setParameter("user", user);
		return getBillsQuery.getResultList();
//		List<Bill> bills = getBillsQuery.getResultList();
//		for(Bill bill : bills) {
//			bill.getOrders().size();
//		}
//		return bills;
	}
	
	@Override
	public Bill startBill(Bill bill, User user) {
		if (user == null) { 
			throw new InvalidUserException();
		}
		bill.setCreatedAt(new Date());
		bill.setUser(user);
		em.persist(bill);
		return bill;
	}
	
	@Override
	public List<OrderUnit> getOrders(int billId) {
		Bill bill = em.find(Bill.class, billId);
		return bill.getOrderUnits();
	}
	
	@Override
	public List<ItemDTO> getOrderedItems(List<OrderUnit> orders) {
		Map<String, ItemDTO> counterMap = new HashMap<>();
		ItemDTO itemDTO;
		List<Item> items = new ArrayList<>();
		
		for(OrderUnit order : orders) {
			items.addAll(order.getItems());
		}
		
		for(Item item : items) {
			if(counterMap.containsKey(item.getName())) {
				itemDTO = counterMap.get(item.getName());
				itemDTO.setQuantity(itemDTO.getQuantity() + 1);
			} else {
				itemDTO = new ItemDTO();
				itemDTO.setName(item.getName());
				itemDTO.setQuantity(1);
				itemDTO.setPrice(item.getPrice());
				counterMap.put(item.getName(), itemDTO);
			}
		}
		return new ArrayList<ItemDTO>(counterMap.values());
	}

	@Override
	public OrderUnit addOrder(int billId, OrderUnit order) {
		OrderUnit orderNew = new OrderUnit();
		Bill bill = em.find(Bill.class, billId);
		orderNew.setBill(bill);
		orderNew.setCreatedAt(new Date());
		orderNew.setStatus(Status.PENDING.name());
//		em.persist(orderNew);
		List<Item> items = new ArrayList<>();
		for (Item item : order.getItems()) {
			Item i = em.find(Item.class ,item.getItemId());
			items.add(i);
		}
		orderNew.setItems(items);
		em.persist(orderNew);
		return orderNew;
	}

	@Override
	public void endBill(int billId) {
		Bill bill = em.find(Bill.class, billId);
		bill.setCompletedAt(new Date());
	}

	@Override
	public List<Bill> getAllBillsForLastWeek() {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		Date startDate = cal.getTime();
		System.out.println("Date = "+ cal.getTime());
		
		String txtQuery = "SELECT b FROM Bill b  WHERE b.completedAt BETWEEN :startDate and :endDate";
		TypedQuery<Bill> getAllBillsQuery = em.createQuery(txtQuery, Bill.class)
		.setParameter("startDate", startDate, TemporalType.DATE)
		.setParameter("endDate", new Date(), TemporalType.DATE);
		
		return getAllBillsQuery.getResultList();
		
	}
	
}
