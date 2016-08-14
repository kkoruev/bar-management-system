package bg.unisofia.fmi.dao;

import bg.unisofia.fmi.dto.BillDTO;
import bg.unisofia.fmi.models.Order;
import bg.unisofia.fmi.models.User;

public interface BillDAO {
	
	void startBill(BillDTO billDTO, User user);
	
	void addOrder(Order order);
	
	float endBill();


	
}
