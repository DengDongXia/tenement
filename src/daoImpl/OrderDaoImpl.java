package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.DataBaseDao;
import dao.OrderDao;
import model.House;
import model.OrderModel;
import model.User;

public class OrderDaoImpl extends DataBaseDao implements OrderDao{

	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	@Override
	public User publishOrder(House house, User user) {
	
		String sql1 = "insert into orders(userId,houseId) values(?,?)";
		String sql2 = "select * from user where id=?";
		String[] params = {user.getId(), ""+house.getId()};
		if(this.executeSQL(sql1, params)==1) {
			User u = null;
			try {
				conn = this.getConn();
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, house.getPublisher());
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
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				this.closeAll(conn, pstmt, rs);
			}
			return u;
		}else {
			return null;
		}
	}

	@Override
	public OrderModel[] getOrder() {
		String sql = "select t1.id, t1.userName, t1.phone, "
				+ " t2.id, t2.userName, t2.phone, "
				+ " t3.id, t3.title, t3.price, t3.address, t3.comment, "
				+ " t4.confirm, t4.id "
				+ " from orders t4 "
				+ " inner join user t1 on t4.userId=t1.id "
				+ " inner join house t3 on t4.houseId=t3.id "
				+ " inner join user t2 on t3.publisher=t2.id"
				+ " order by t4.id DESC";
		ArrayList<OrderModel> list = new ArrayList<OrderModel>();
		OrderModel om = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				om = new OrderModel();
				om.setUserId(rs.getString(1));
				om.setUserName(rs.getString(2));
				om.setUserPhone(rs.getString(3));
				om.setPublisherId(rs.getString(4));
				om.setPublisherName(rs.getString(5));
				om.setPublisherPhone(rs.getString(6));
				om.setHouseId(rs.getInt(7));
				om.setHouseTitle(rs.getString(8));
				om.setHousePrice(rs.getDouble(9));
				om.setHouseAddress(rs.getString(10));
				om.setHouseComment(rs.getString(11));
				om.setConfirm(rs.getInt(12));
				om.setId(rs.getInt(13));
				list.add(om);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		OrderModel[] arr = new OrderModel[list.size()];
		return list.toArray(arr);
		
	}

	@Override
	public OrderModel[] getOrderUnconfirm() {
		String sql = "select t1.id, t1.userName, t1.phone, "
				+ " t2.id, t2.userName, t2.phone, "
				+ " t3.id, t3.title, t3.price, t3.address, t3.comment, "
				+ " t4.confirm, t4.id "
				+ " from orders t4 "
				+ " inner join user t1 on t4.userId=t1.id "
				+ " inner join house t3 on t4.houseId=t3.id "
				+ " inner join user t2 on t3.publisher=t2.id"
				+ " where t4.confirm=2"
				+ " order by t4.id DESC";
		ArrayList<OrderModel> list = new ArrayList<OrderModel>();
		OrderModel om = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				om = new OrderModel();
				om.setUserId(rs.getString(1));
				om.setUserName(rs.getString(2));
				om.setUserPhone(rs.getString(3));
				om.setPublisherId(rs.getString(4));
				om.setPublisherName(rs.getString(5));
				om.setPublisherPhone(rs.getString(6));
				om.setHouseId(rs.getInt(7));
				om.setHouseTitle(rs.getString(8));
				om.setHousePrice(rs.getDouble(9));
				om.setHouseAddress(rs.getString(10));
				om.setHouseComment(rs.getString(11));
				om.setConfirm(rs.getInt(12));
				om.setId(rs.getInt(13));
				list.add(om);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		OrderModel[] arr = new OrderModel[list.size()];
		return list.toArray(arr);
	}

	@Override
	public OrderModel[] getOrderWithId(String id) {
		String sql = "select t1.id, t1.userName, t1.phone, "
				+ " t2.id, t2.userName, t2.phone, "
				+ " t3.id, t3.title, t3.price, t3.address, t3.comment, "
				+ " t4.confirm, t4.id "
				+ " from orders t4 "
				+ " inner join user t1 on t4.userId=t1.id "
				+ " inner join house t3 on t4.houseId=t3.id "
				+ " inner join user t2 on t3.publisher=t2.id"
				+ " where t4.id=?"
				+ " order by t4.id DESC";
		ArrayList<OrderModel> list = new ArrayList<OrderModel>();
		OrderModel om = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				om = new OrderModel();
				om.setUserId(rs.getString(1));
				om.setUserName(rs.getString(2));
				om.setUserPhone(rs.getString(3));
				om.setPublisherId(rs.getString(4));
				om.setPublisherName(rs.getString(5));
				om.setPublisherPhone(rs.getString(6));
				om.setHouseId(rs.getInt(7));
				om.setHouseTitle(rs.getString(8));
				om.setHousePrice(rs.getDouble(9));
				om.setHouseAddress(rs.getString(10));
				om.setHouseComment(rs.getString(11));
				om.setConfirm(rs.getInt(12));
				om.setId(rs.getInt(13));
				list.add(om);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		OrderModel[] arr = new OrderModel[list.size()];
		assert arr.length<=1;
		return list.toArray(arr);
	}

	@Override
	public OrderModel[] getOrderWithUserId(String userId) {
		String sql = "select t1.id, t1.userName, t1.phone, "
				+ " t2.id, t2.userName, t2.phone, "
				+ " t3.id, t3.title, t3.price, t3.address, t3.comment, "
				+ " t4.confirm, t4.id "
				+ " from orders t4 "
				+ " inner join user t1 on t4.userId=t1.id "
				+ " inner join house t3 on t4.houseId=t3.id "
				+ " inner join user t2 on t3.publisher=t2.id"
				+ " where t1.id=?"
				+ " order by t4.id DESC";
		ArrayList<OrderModel> list = new ArrayList<OrderModel>();
		OrderModel om = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(userId));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				om = new OrderModel();
				om.setUserId(rs.getString(1));
				om.setUserName(rs.getString(2));
				om.setUserPhone(rs.getString(3));
				om.setPublisherId(rs.getString(4));
				om.setPublisherName(rs.getString(5));
				om.setPublisherPhone(rs.getString(6));
				om.setHouseId(rs.getInt(7));
				om.setHouseTitle(rs.getString(8));
				om.setHousePrice(rs.getDouble(9));
				om.setHouseAddress(rs.getString(10));
				om.setHouseComment(rs.getString(11));
				om.setConfirm(rs.getInt(12));
				om.setId(rs.getInt(13));
				list.add(om);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		OrderModel[] arr = new OrderModel[list.size()];
		return list.toArray(arr);
	}

	@Override
	public OrderModel[] getOrderWithPublisher(String publisherId) {
		String sql = "select t1.id, t1.userName, t1.phone, "
				+ " t2.id, t2.userName, t2.phone, "
				+ " t3.id, t3.title, t3.price, t3.address, t3.comment, "
				+ " t4.confirm, t4.id "
				+ " from orders t4 "
				+ " inner join user t1 on t4.userId=t1.id "
				+ " inner join house t3 on t4.houseId=t3.id "
				+ " inner join user t2 on t3.publisher=t2.id"
				+ " where t2.id=?"
				+ " order by t4.id DESC";
		ArrayList<OrderModel> list = new ArrayList<OrderModel>();
		OrderModel om = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(publisherId));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				om = new OrderModel();
				om.setUserId(rs.getString(1));
				om.setUserName(rs.getString(2));
				om.setUserPhone(rs.getString(3));
				om.setPublisherId(rs.getString(4));
				om.setPublisherName(rs.getString(5));
				om.setPublisherPhone(rs.getString(6));
				om.setHouseId(rs.getInt(7));
				om.setHouseTitle(rs.getString(8));
				om.setHousePrice(rs.getDouble(9));
				om.setHouseAddress(rs.getString(10));
				om.setHouseComment(rs.getString(11));
				om.setConfirm(rs.getInt(12));
				om.setId(rs.getInt(13));
				list.add(om);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		OrderModel[] arr = new OrderModel[list.size()];
		return list.toArray(arr);
	}

	@Override
	public OrderModel[] getOrderWithPublisherUnconfirm(String publisherId) {
		String sql = "select t1.id, t1.userName, t1.phone, "
				+ " t2.id, t2.userName, t2.phone, "
				+ " t3.id, t3.title, t3.price, t3.address, t3.comment, "
				+ " t4.confirm, t4.id "
				+ " from orders t4 "
				+ " inner join user t1 on t4.userId=t1.id "
				+ " inner join house t3 on t4.houseId=t3.id "
				+ " inner join user t2 on t3.publisher=t2.id"
				+ " where t2.id=? and t4.confirm=0"
				+ " order by t4.id DESC";
		ArrayList<OrderModel> list = new ArrayList<OrderModel>();
		OrderModel om = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(publisherId));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				om = new OrderModel();
				om.setUserId(rs.getString(1));
				om.setUserName(rs.getString(2));
				om.setUserPhone(rs.getString(3));
				om.setPublisherId(rs.getString(4));
				om.setPublisherName(rs.getString(5));
				om.setPublisherPhone(rs.getString(6));
				om.setHouseId(rs.getInt(7));
				om.setHouseTitle(rs.getString(8));
				om.setHousePrice(rs.getDouble(9));
				om.setHouseAddress(rs.getString(10));
				om.setHouseComment(rs.getString(11));
				om.setConfirm(rs.getInt(12));
				om.setId(rs.getInt(13));
				list.add(om);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		OrderModel[] arr = new OrderModel[list.size()];
		return list.toArray(arr);
	}

	@Override
	public boolean auditOrder(String orderId, int action) {
		String sql = "update orders set confirm=? where id=?";
		String[] params = {""+action, orderId};
		return this.executeSQL(sql, params)==1;
	}

	@Override
	public boolean confirmOrder(String id) {
		String sql = "update orders set confirm=2 where id=?";
		String[] params = {id};
		return this.executeSQL(sql, params)==1;
	}



}
