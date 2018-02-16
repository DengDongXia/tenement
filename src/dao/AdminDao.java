package dao;

import model.Admin;

public interface AdminDao {
	public Admin login(String userName, String psw);
}
