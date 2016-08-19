package bg.unisofia.fmi.dao.impl;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bg.unisofia.fmi.dao.BillDAO;
import bg.unisofia.fmi.exceptions.InvalidUserException;
import bg.unisofia.fmi.models.Bill;
import bg.unisofia.fmi.models.Order;
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
	public void startBill(Bill bill, User user) {
		if (user == null) { 
			throw new InvalidUserException();
		}
		bill.setUser(user);
		em.persist(bill);
	}
	
	@Override
	public List<Order> getOrders(int billId) {
		String txtQuery = "SELECT b FROM Bill b WHERE b.billId = :billId";
		TypedQuery<Bill> getBillsQuery = em.createQuery(txtQuery, Bill.class);
		getBillsQuery.setParameter("billId", billId);
		return getBillsQuery.getSingleResult().getOrders(); 
	}

	@Override
	public void addOrder(Bill bill) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float endBill() {
		// TODO Auto-generated method stub
		return 0;
	}


}
