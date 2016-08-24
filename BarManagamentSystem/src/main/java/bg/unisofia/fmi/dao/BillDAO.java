package bg.unisofia.fmi.dao;

import java.util.List;

import bg.unisofia.fmi.models.Bill;
import bg.unisofia.fmi.models.OrderUnit;
import bg.unisofia.fmi.models.User;

public interface BillDAO {
	
	List<Bill> getOpenBillsByUser(User user);
	
	void startBill(Bill bill, User user);
	
	float endBill();

	List<OrderUnit> getOrders(int billId);

	void addOrder(OrderUnit order);


	
}
