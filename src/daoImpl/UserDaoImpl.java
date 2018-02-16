package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.DataBaseDao;
import dao.UserDao;
import model.User;
import model.Users;

public class UserDaoImpl extends DataBaseDao implements UserDao{

	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	@Override
	public User login(String email, String psw) {
		String sql = "select * from user where email=? and psw=?";
		User u = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, psw);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				u = new User();
				u.setId(rs.getString("id"));
				u.setEmail(rs.getString("email"));
				u.setHead(rs.getString("head"));
				u.setIsLandlord(rs.getBoolean("isLandlord"));
				u.setPassword(rs.getString("psw"));
				u.setPhone(rs.getString("phone"));
				u.setUserName(rs.getString("userName"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		return u;
	}

	@Override
	public boolean regist(User user) {
		String sql="insert into user(email, psw, head, phone, userName,isLandlord) values(?,?,?,?,?,?)";
		String[] params = {user.getEmail(), user.getPassword(), user.getHead(), user.getPhone(), user.getUserName(), ""+user.getIsLandlord()};
		return this.executeSQL(sql, params)==1;
	}

	@Override
	public User[] getAll() {
		String sql = "select * from user order by id";
		ArrayList<User> list = new ArrayList<User>();
		User u = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				u = new User();
				u.setEmail(rs.getString("email"));
				u.setHead(rs.getString("head"));
				u.setId(rs.getString("id"));
				u.setIsLandlord(rs.getBoolean("isLandlord"));
				u.setPassword(rs.getString("psw"));
				u.setPhone(rs.getString("phone"));
				u.setUserName(rs.getString("userName"));
				list.add(u);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		User[] arr = new User[list.size()];
		return list.toArray(arr);
	}

	@Override
	public User getUserWithId(String id) {
		String sql = "select * from user where id=?";
		User u = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				u = new User();
				u.setId(rs.getString("id"));
				u.setEmail(rs.getString("email"));
				u.setHead(rs.getString("head"));
				u.setIsLandlord(rs.getBoolean("isLandlord"));
				u.setPassword(rs.getString("psw"));
				u.setPhone(rs.getString("phone"));
				u.setUserName(rs.getString("userName"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		return u;
	}
	public boolean hasUser(String id){
		String sql = "select * from users where id=?";
		User user = null;
		try{
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				user = new User();
				user.setId(rs.getString("id"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("psw"));
				user.setHead(rs.getString("head"));
				user.setId(rs.getString("id"));
				user.setPhone(rs.getString("phone"));
				user.setIsLandlord(rs.getBoolean("isLandlord"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		return user!=null;
	}
	public boolean hasUserWithEmail(String email){
		String sql = "select * from users where email=?";
		User user = null;
		try{
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while(rs.next()){
				user = new User();
				user.setId(rs.getString("id"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("psw"));
				user.setHead(rs.getString("head"));
				user.setId(rs.getString("id"));
				user.setPhone(rs.getString("phone"));
				user.setIsLandlord(rs.getBoolean("isLandlord"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		return user!=null;
	}

}
