package utils;

import daoImpl.AdminDaoImpl;
import daoImpl.HouseDaoImpl;
import daoImpl.NoticeDaoImpl;
import daoImpl.OrderDaoImpl;
import daoImpl.UserDaoImpl;

public class InstanceUtil {
	public static AdminDaoImpl adi = new AdminDaoImpl();
	public static HouseDaoImpl hdi = new HouseDaoImpl();
	public static NoticeDaoImpl ndi = new NoticeDaoImpl();
	public static OrderDaoImpl odi = new OrderDaoImpl();
	public static UserDaoImpl udi = new UserDaoImpl();
}
