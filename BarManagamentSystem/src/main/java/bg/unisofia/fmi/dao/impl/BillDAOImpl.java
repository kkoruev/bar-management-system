package bg.unisofia.fmi.dao.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bg.unisofia.fmi.dao.BillDAO;
import bg.unisofia.fmi.dto.BillDTO;
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
		TypedQuery<Bill> getUserQuery = em.createQuery(txtQuery, Bill.class);
		getUserQuery.setParameter("user", user);
		return getUserQuery.getResultList();
	}
	
	@Override
	public void startBill(BillDTO billDTO, User user) {
		if (user == null) { 
			throw new InvalidUserException();
		}
		Bill bill = new Bill();
		bill.setTableName(billDTO.getTableName());
		bill.setUser(user);
		bill.setCreatedAt(new Date());
		em.persist(bill);
	}

	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float endBill() {
		// TODO Auto-generated method stub
		return 0;
	}


}
