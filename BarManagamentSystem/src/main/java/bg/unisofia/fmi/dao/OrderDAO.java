package bg.unisofia.fmi.dao;

import java.util.Date;
import java.util.List;

import bg.unisofia.fmi.models.Item;
import bg.unisofia.fmi.models.Order;
import bg.unisofia.fmi.models.User;

public interface OrderDAO {
	
	public List<Order> getOrdersByDate(Date date);

	public void createNewOrder(String name);

	public void addItems(String orderName, List<Item> items);

	public void receiveOrder(User bartender);

	public void completeOrders(String name);
	
	public List<Order> getAllOrders();
	
}
