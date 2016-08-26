package bg.unisofia.fmi.dao;

import java.util.List;

import bg.unisofia.fmi.dto.ItemDTO;
import bg.unisofia.fmi.models.Bill;
import bg.unisofia.fmi.models.OrderUnit;
import bg.unisofia.fmi.models.User;

public interface BillDAO {
	
	List<Bill> getOpenBillsByUser(User user);
	
	Bill startBill(Bill bill, User user);
	
	void endBill(int billId);

	List<OrderUnit> getOrders(int billId);

	OrderUnit addOrder(int billId, OrderUnit order);
	
	public List<Bill> getAllBillsForLastWeek();

	List<ItemDTO> getOrderedItems(List<OrderUnit> orders);

}
