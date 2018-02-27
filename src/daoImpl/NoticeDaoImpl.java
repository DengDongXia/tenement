package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.DataBaseDao;
import dao.NoticeDao;
import model.Notice;

public class NoticeDaoImpl extends DataBaseDao implements NoticeDao{

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	@Override
	public Notice[] getAll(String userId) {
		String sql = "select * from notice where userTo=? order by id DESC";
		Notice n = null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				n = new Notice();
				n.setComment(rs.getString("comment"));
				n.setId(rs.getInt("id"));
				n.setIsRead(rs.getBoolean("isRead"));
				n.setUserTo(rs.getString("userTo"));
				list.add(n);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		Notice[] arr = new Notice[list.size()];
		return list.toArray(arr);
	}

	@Override
	public Notice[] getUnread(String userId) {
		String sql = "select * from notice where userTo=? and isRead=false order by id DESC";
		Notice n = null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				n = new Notice();
				n.setComment(rs.getString("comment"));
				n.setId(rs.getInt("id"));
				n.setIsRead(rs.getBoolean("isRead"));
				n.setUserTo(rs.getString("userTo"));
				list.add(n);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		Notice[] arr = new Notice[list.size()];
		return list.toArray(arr);
	}

	@Override
	public boolean send(Notice notice) {
		String sql = "insert into notice(userTo, comment) values(?,?)";
		String[] params = {notice.getUserTo(), notice.getComment()};
		return this.executeSQL(sql, params)==1;
	}

	@Override
	public boolean read(Notice[] arr) {
		String sql = "update notice set isRead=true where id=?";
		int count = 0;
		for(Notice n:arr) {
			String[] params = {""+n.getId()};
			count += this.executeSQL(sql, params);
		}
		return arr.length==count;
	}

}
