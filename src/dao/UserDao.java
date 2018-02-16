package dao;

import model.User;

public interface UserDao {
	public User login(String email, String psw);
	public boolean regist(User user);
	public User[] getAll();
	
	
	public User getUserWithId(String id);
}
