package bg.unisofia.fmi.dao;

import java.util.List;

import bg.unisofia.fmi.models.Bill;
import bg.unisofia.fmi.models.Order;
import bg.unisofia.fmi.models.User;

public interface BillDAO {
	
	List<Bill> getOpenBillsByUser(User user);
	
	void startBill(Bill bill, User user);
	
	void addOrder(Order order);
	
	float endBill();


	
}
