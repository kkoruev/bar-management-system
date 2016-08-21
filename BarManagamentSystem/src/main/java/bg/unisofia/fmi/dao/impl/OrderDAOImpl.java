package bg.unisofia.fmi.dao.impl;

import java.util.Date;
import java.util.List;

import bg.unisofia.fmi.dao.OrderDAO;
import bg.unisofia.fmi.models.Item;
import bg.unisofia.fmi.models.Order;
import bg.unisofia.fmi.models.User;


public class OrderDAOImpl implements OrderDAO{

	@Override
	public List<Order> getOrdersByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
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
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
