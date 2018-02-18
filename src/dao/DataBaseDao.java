package dao;
import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;
public class DataBaseDao {
	public Connection getConn()throws ClassNotFoundException,SQLException{
		Connection conn=null;
		try{
			Context ic =new InitialContext();
			DataSource source=(DataSource)ic.lookup("java:comp/env/jdbc/tenement");
			conn=source.getConnection();
		}catch(SQLException exception){
			exception.printStackTrace();
		}catch(NamingException namingException){
			namingException.printStackTrace();
		}
		return conn;
	}
	public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs){
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try{
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	public int executeSQL(String preparedSql,String[] param){
		Connection conn=null;
		PreparedStatement pstmt=null;
		int num=0;
		try{
			conn=getConn();
			pstmt=conn.prepareStatement(preparedSql);
			if(param!=null){
				for(int i=0;i<param.length;i++){
					pstmt.setString(i+1,param[i]);
				}
			}
			num=pstmt.executeUpdate();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(conn,pstmt,null);
		}
		return num;
	}
}
