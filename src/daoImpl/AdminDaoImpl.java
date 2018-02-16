package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.AdminDao;
import dao.DataBaseDao;
import model.Admin;

public class AdminDaoImpl extends DataBaseDao implements AdminDao{
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	@Override
	public Admin login(String userName, String psw) {
		String sql = "select * from admin where userName=? and psw=?";
		Admin admin = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, psw);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				admin = new Admin();
				admin.setId(rs.getString("id"));
				admin.setUserName(rs.getString("userName"));
				admin.setPassword(rs.getString("psw"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		return admin;
	}
	

}
