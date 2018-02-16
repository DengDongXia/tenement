package dao;

import model.House;
import model.OrderModel;
import model.User;

public interface OrderDao {
	public User publishOrder(House house, User user);
	public OrderModel[] getOrder();
	public OrderModel[] getOrderUnconfirm();
	
	public OrderModel[] getOrderWithId(String id);
	
	public OrderModel[] getOrderWithUserId(String userId);
	
	public OrderModel[] getOrderWithPublisher(String publisherId);
	public OrderModel[] getOrderWithPublisherUnconfirm(String publisherId);
	
	public boolean auditOrder(String orderId, int action);
	public boolean confirmOrder(String id);
	
}
