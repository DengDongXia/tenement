package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.DataBaseDao;
import dao.HouseDao;
import model.House;
import model.HouseModel;

public class HouseDaoImpl extends DataBaseDao implements HouseDao{

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	@Override
	public HouseModel[] getHouse() {
		String sql1 = "select * from house order by id DESC";
		String sql2 = "select * from picture where houseId=?";
		ArrayList<HouseModel> list = new ArrayList<>();
		HouseModel[] array = null;
		HouseModel hm = null;
		ArrayList<String> urlList = null;
		String[] urlArray = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				hm = new HouseModel();
				hm.setId(rs.getInt("id"));
				hm.setTitle(rs.getString("title"));
				hm.setProvince(rs.getString("province"));
				hm.setCity(rs.getString("city"));
				hm.setRegion(rs.getString("region"));
				hm.setPrice(rs.getDouble("price"));
				hm.setAddress(rs.getString("address"));
				hm.setComment(rs.getString("comment"));
				hm.setPublisher(rs.getString("publisher"));
				hm.setCount(rs.getInt("count"));
				list.add(hm);
			}
			for(HouseModel h: list) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, h.getId());
				rs = pstmt.executeQuery();
				urlList = new ArrayList<String>();
				while(rs.next()) {
					urlList.add(rs.getString("picPath"));
				}
				urlArray = new String[urlList.size()];
				h.setPic(urlList.toArray(urlArray));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		array = new HouseModel[list.size()];
		return list.toArray(array);
	}

	@Override
	public HouseModel[] getHouseSortByPrice(boolean asc) {
		String sql1;
		if(asc) {
			sql1 = "select * from house order by price ASC";
		}else {
			sql1 = "select * from house order by price DESC";
		}
		 
		String sql2 = "select * from picture where houseId=?";
		ArrayList<HouseModel> list = new ArrayList<>();
		HouseModel[] array = null;
		HouseModel hm = null;
		ArrayList<String> urlList = null;
		String[] urlArray = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				hm = new HouseModel();
				hm.setId(rs.getInt("id"));
				hm.setTitle(rs.getString("title"));
				hm.setProvince(rs.getString("province"));
				hm.setCity(rs.getString("city"));
				hm.setRegion(rs.getString("region"));
				hm.setPrice(rs.getDouble("price"));
				hm.setAddress(rs.getString("address"));
				hm.setComment(rs.getString("comment"));
				hm.setPublisher(rs.getString("publisher"));
				hm.setCount(rs.getInt("count"));
				list.add(hm);
			}
			for(HouseModel h: list) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, h.getId());
				rs = pstmt.executeQuery();
				urlList = new ArrayList<String>();
				while(rs.next()) {
					urlList.add(rs.getString("picPath"));
				}
				urlArray = new String[urlList.size()];
				h.setPic(urlList.toArray(urlArray));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		array = new HouseModel[list.size()];
		return list.toArray(array);
	}

	@Override
	public HouseModel[] getHouseSortByTime(boolean asc) {
		String sql1;
		if(asc) {
			sql1 = "select * from house order by id ASC";
		}else {
			sql1 = "select * from house order by id DESC";
		}
		 
		String sql2 = "select * from picture where houseId=?";
		ArrayList<HouseModel> list = new ArrayList<>();
		HouseModel[] array = null;
		HouseModel hm = null;
		ArrayList<String> urlList = null;
		String[] urlArray = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				hm = new HouseModel();
				hm.setId(rs.getInt("id"));
				hm.setTitle(rs.getString("title"));
				hm.setProvince(rs.getString("province"));
				hm.setCity(rs.getString("city"));
				hm.setRegion(rs.getString("region"));
				hm.setPrice(rs.getDouble("price"));
				hm.setAddress(rs.getString("address"));
				hm.setComment(rs.getString("comment"));
				hm.setPublisher(rs.getString("publisher"));
				hm.setCount(rs.getInt("count"));
				list.add(hm);
			}
			for(HouseModel h: list) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, h.getId());
				rs = pstmt.executeQuery();
				urlList = new ArrayList<String>();
				while(rs.next()) {
					urlList.add(rs.getString("picPath"));
				}
				urlArray = new String[urlList.size()];
				h.setPic(urlList.toArray(urlArray));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		array = new HouseModel[list.size()];
		return list.toArray(array);
	}

	@Override
	public HouseModel[] getHouseWithPrice(double min, double max) {
		String sql1 = "select * from house where price>=? and price<=? order by id ASC";
		String sql2 = "select * from picture where houseId=?";
		ArrayList<HouseModel> list = new ArrayList<>();
		HouseModel[] array = null;
		HouseModel hm = null;
		ArrayList<String> urlList = null;
		String[] urlArray = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setDouble(1, min);
			pstmt.setDouble(2, max);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				hm = new HouseModel();
				hm.setId(rs.getInt("id"));
				hm.setTitle(rs.getString("title"));
				hm.setProvince(rs.getString("province"));
				hm.setCity(rs.getString("city"));
				hm.setRegion(rs.getString("region"));
				hm.setPrice(rs.getDouble("price"));
				hm.setAddress(rs.getString("address"));
				hm.setComment(rs.getString("comment"));
				hm.setPublisher(rs.getString("publisher"));
				hm.setCount(rs.getInt("count"));
				list.add(hm);
			}
			for(HouseModel h: list) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, h.getId());
				rs = pstmt.executeQuery();
				urlList = new ArrayList<String>();
				while(rs.next()) {
					urlList.add(rs.getString("picPath"));
				}
				urlArray = new String[urlList.size()];
				h.setPic(urlList.toArray(urlArray));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		array = new HouseModel[list.size()];
		return list.toArray(array);
	}

	@Override
	public HouseModel[] getHouseWithKeyword(String keyword) {
		String sql1 = "select * from house"
				+ " where title like %"+keyword+"% "
				+ " or province like %"+keyword+"% "
				+ " or city like %"+keyword+"% "
				+ " or region like %"+keyword+"% "
				+ " or address like %"+keyword+"% "
				+ " or comment like %"+keyword+"% "
				+ " order by id ASC";
		String sql2 = "select * from picture where houseId=?";
		ArrayList<HouseModel> list = new ArrayList<>();
		HouseModel[] array = null;
		HouseModel hm = null;
		ArrayList<String> urlList = null;
		String[] urlArray = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				hm = new HouseModel();
				hm.setId(rs.getInt("id"));
				hm.setTitle(rs.getString("title"));
				hm.setProvince(rs.getString("province"));
				hm.setCity(rs.getString("city"));
				hm.setRegion(rs.getString("region"));
				hm.setPrice(rs.getDouble("price"));
				hm.setAddress(rs.getString("address"));
				hm.setComment(rs.getString("comment"));
				hm.setPublisher(rs.getString("publisher"));
				hm.setCount(rs.getInt("count"));
				list.add(hm);
			}
			for(HouseModel h: list) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, h.getId());
				rs = pstmt.executeQuery();
				urlList = new ArrayList<String>();
				while(rs.next()) {
					urlList.add(rs.getString("picPath"));
				}
				urlArray = new String[urlList.size()];
				h.setPic(urlList.toArray(urlArray));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		array = new HouseModel[list.size()];
		return list.toArray(array);
	}

	@Override
	public HouseModel[] getHouseWithPriceSortByPrice(double min, double max, boolean asc) {
		String sql1 ;
		if(asc) {
			sql1 = "select * from house where price>=? and price<=? order by price ASC";
		}else {
			sql1 = "select * from house where price>=? and price<=? order by price DESC";
		}
		String sql2 = "select * from picture where houseId=?";
		ArrayList<HouseModel> list = new ArrayList<>();
		HouseModel[] array = null;
		HouseModel hm = null;
		ArrayList<String> urlList = null;
		String[] urlArray = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setDouble(1, min);
			pstmt.setDouble(2, max);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				hm = new HouseModel();
				hm.setId(rs.getInt("id"));
				hm.setTitle(rs.getString("title"));
				hm.setProvince(rs.getString("province"));
				hm.setCity(rs.getString("city"));
				hm.setRegion(rs.getString("region"));
				hm.setPrice(rs.getDouble("price"));
				hm.setAddress(rs.getString("address"));
				hm.setComment(rs.getString("comment"));
				hm.setPublisher(rs.getString("publisher"));
				hm.setCount(rs.getInt("count"));
				list.add(hm);
			}
			for(HouseModel h: list) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, h.getId());
				rs = pstmt.executeQuery();
				urlList = new ArrayList<String>();
				while(rs.next()) {
					urlList.add(rs.getString("picPath"));
				}
				urlArray = new String[urlList.size()];
				h.setPic(urlList.toArray(urlArray));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		array = new HouseModel[list.size()];
		return list.toArray(array);
	}

	@Override
	public HouseModel[] getHouseWithKeywordSortByPrice(String keyword, boolean asc) {
		String sql1 = "select * from house"
				+ " where title like %"+keyword+"% "
				+ " or province like %"+keyword+"% "
				+ " or city like %"+keyword+"% "
				+ " or region like %"+keyword+"% "
				+ " or address like %"+keyword+"% "
				+ " or comment like %"+keyword+"% ";
		if(asc) {
			sql1 = sql1+" order by price ASC";
		}else {
			sql1 = sql1+" order by price DESC";
		}
		
		String sql2 = "select * from picture where houseId=?";
		ArrayList<HouseModel> list = new ArrayList<>();
		HouseModel[] array = null;
		HouseModel hm = null;
		ArrayList<String> urlList = null;
		String[] urlArray = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				hm = new HouseModel();
				hm.setId(rs.getInt("id"));
				hm.setTitle(rs.getString("title"));
				hm.setProvince(rs.getString("province"));
				hm.setCity(rs.getString("city"));
				hm.setRegion(rs.getString("region"));
				hm.setPrice(rs.getDouble("price"));
				hm.setAddress(rs.getString("address"));
				hm.setComment(rs.getString("comment"));
				hm.setPublisher(rs.getString("publisher"));
				hm.setCount(rs.getInt("count"));
				list.add(hm);
			}
			for(HouseModel h: list) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, h.getId());
				rs = pstmt.executeQuery();
				urlList = new ArrayList<String>();
				while(rs.next()) {
					urlList.add(rs.getString("picPath"));
				}
				urlArray = new String[urlList.size()];
				h.setPic(urlList.toArray(urlArray));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		array = new HouseModel[list.size()];
		return list.toArray(array);
	}

	@Override
	public HouseModel[] getHouseWithPriceSortByTime(double min, double max, boolean asc) {
		String sql1 ;
		if(asc) {
			sql1 = "select * from house where price>=? and price<=? order by id ASC";
		}else {
			sql1 = "select * from house where price>=? and price<=? order by id DESC";
		}
		String sql2 = "select * from picture where houseId=?";
		ArrayList<HouseModel> list = new ArrayList<>();
		HouseModel[] array = null;
		HouseModel hm = null;
		ArrayList<String> urlList = null;
		String[] urlArray = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setDouble(1, min);
			pstmt.setDouble(2, max);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				hm = new HouseModel();
				hm.setId(rs.getInt("id"));
				hm.setTitle(rs.getString("title"));
				hm.setProvince(rs.getString("province"));
				hm.setCity(rs.getString("city"));
				hm.setRegion(rs.getString("region"));
				hm.setPrice(rs.getDouble("price"));
				hm.setAddress(rs.getString("address"));
				hm.setComment(rs.getString("comment"));
				hm.setPublisher(rs.getString("publisher"));
				hm.setCount(rs.getInt("count"));
				list.add(hm);
			}
			for(HouseModel h: list) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, h.getId());
				rs = pstmt.executeQuery();
				urlList = new ArrayList<String>();
				while(rs.next()) {
					urlList.add(rs.getString("picPath"));
				}
				urlArray = new String[urlList.size()];
				h.setPic(urlList.toArray(urlArray));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		array = new HouseModel[list.size()];
		return list.toArray(array);
	}

	@Override
	public HouseModel[] getHouseWithKeywordSortByTime(String keyword, boolean asc) {
		String sql1 = "select * from house"
				+ " where title like '%"+keyword+"%' "
				+ " or province like '%"+keyword+"%' "
				+ " or city like '%"+keyword+"%' "
				+ " or region like '%"+keyword+"%' "
				+ " or address like '%"+keyword+"%' "
				+ " or comment like '%"+keyword+"%' ";
		if(asc) {
			sql1 = sql1+" order by id ASC";
		}else {
			sql1 = sql1+" order by id DESC";
		}
		
		String sql2 = "select * from picture where houseId=?";
		ArrayList<HouseModel> list = new ArrayList<>();
		HouseModel[] array = null;
		HouseModel hm = null;
		ArrayList<String> urlList = null;
		String[] urlArray = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				hm = new HouseModel();
				hm.setId(rs.getInt("id"));
				hm.setTitle(rs.getString("title"));
				hm.setProvince(rs.getString("province"));
				hm.setCity(rs.getString("city"));
				hm.setRegion(rs.getString("region"));
				hm.setPrice(rs.getDouble("price"));
				hm.setAddress(rs.getString("address"));
				hm.setComment(rs.getString("comment"));
				hm.setPublisher(rs.getString("publisher"));
				hm.setCount(rs.getInt("count"));
				list.add(hm);
			}
			for(HouseModel h: list) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, h.getId());
				rs = pstmt.executeQuery();
				urlList = new ArrayList<String>();
				while(rs.next()) {
					urlList.add(rs.getString("picPath"));
				}
				urlArray = new String[urlList.size()];
				h.setPic(urlList.toArray(urlArray));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		array = new HouseModel[list.size()];
		return list.toArray(array);
	}

	@Override
	public boolean publishHouse(House house, String[] picUrl) {
		String sql1 = "insert into house(title, province,city,region,price, address,comment,publisher,count) "
				+ " values(?,?,?,?,?,?,?,?,?)";
		String sql2 = "select max(id) from house;";
		String sql3 = "insert picture(houseId, picPath) "
				+ " values(?,?)";
		String[] params = {
				house.getTitle(), 
				house.getProvince(),
				house.getCity(),
				house.getRegion(),
				""+house.getPrice(),
				house.getAddress(),
				house.getComment(),
				house.getPublisher(),
				""+house.getCount()
				};
		int maxId=-1;
		if(this.executeSQL(sql1, params)==1) {
			try {
				conn = this.getConn();
				pstmt = conn.prepareStatement(sql2);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					maxId = rs.getInt(1);
				}
				if(maxId==-1) {
					return false;
				}
				for(String url:picUrl) {
					String[] param3 = {""+maxId, url};
					this.executeSQL(sql3, param3);
				}
				
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}finally{
				this.closeAll(conn, pstmt, rs);
			}
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean editHouse(House house) {
		String sql = "update house set "
				+ " title=?,"
				+ " province=?,"
				+ " city=?,"
				+ " region=?,"
				+ " price=?,"
				+ " address=?,"
				+ " comment=?,"
				+ " count=?"
				+ " where id=?";
		String[] params = {
				house.getTitle(), 
				house.getProvince(),
				house.getCity(),
				house.getRegion(),
				""+house.getPrice(),
				house.getAddress(),
				house.getComment(),
				""+house.getCount(),
				""+house.getId()
				};
		return this.executeSQL(sql, params)==1;
	}

	@Override
	public boolean deleteHouse(House house) {
		String sql = "delete from house where id=?";
		String[] params = {""+house.getId()};
		return this.executeSQL(sql, params)==1;
	}

	@Override
	public HouseModel getHouseWithId(String id) {
		String sql1 = "select * from house where id=?";
		String sql2 = "select * from picture where houseId=?";
		
		
		HouseModel hm = null;
		ArrayList<String> urlList = null;
		String[] urlArray = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				hm = new HouseModel();
				hm.setId(rs.getInt("id"));
				hm.setTitle(rs.getString("title"));
				hm.setProvince(rs.getString("province"));
				hm.setCity(rs.getString("city"));
				hm.setRegion(rs.getString("region"));
				hm.setPrice(rs.getDouble("price"));
				hm.setAddress(rs.getString("address"));
				hm.setComment(rs.getString("comment"));
				hm.setPublisher(rs.getString("publisher"));
				hm.setCount(rs.getInt("count"));
				
			}
			if(hm==null) {
				return null;
			}
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, hm.getId());
			rs = pstmt.executeQuery();
			urlList = new ArrayList<String>();
			while(rs.next()) {
				urlList.add(rs.getString("picPath"));
			}
			urlArray = new String[urlList.size()];
			hm.setPic(urlList.toArray(urlArray));
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		
		return hm;
	}

	@Override
	public HouseModel[] getHouseWithPublisherId(String publisherId) {
		String sql1 = "select * from house where publisher=? order by id DESC";
		String sql2 = "select * from picture where houseId=?";
		ArrayList<HouseModel> list = new ArrayList<>();
		HouseModel[] array = null;
		HouseModel hm = null;
		ArrayList<String> urlList = null;
		String[] urlArray = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, publisherId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				hm = new HouseModel();
				hm.setId(rs.getInt("id"));
				hm.setTitle(rs.getString("title"));
				hm.setProvince(rs.getString("province"));
				hm.setCity(rs.getString("city"));
				hm.setRegion(rs.getString("region"));
				hm.setPrice(rs.getDouble("price"));
				hm.setAddress(rs.getString("address"));
				hm.setComment(rs.getString("comment"));
				hm.setPublisher(rs.getString("publisher"));
				hm.setCount(rs.getInt("count"));
				list.add(hm);
			}
			for(HouseModel h: list) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, h.getId());
				rs = pstmt.executeQuery();
				urlList = new ArrayList<String>();
				while(rs.next()) {
					urlList.add(rs.getString("picPath"));
				}
				urlArray = new String[urlList.size()];
				h.setPic(urlList.toArray(urlArray));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		array = new HouseModel[list.size()];
		return list.toArray(array);
	}

	@Override
	public HouseModel[] getHouseWithPublisherIdSortByPrice(String publisherId, boolean asc) {
		
		String sql1 = "select * from house where publisher=? ";
		if(asc) {
			sql1 = sql1+" order by price ASC";
		}else {
			sql1 = sql1+" order by price DESC";
		}
		String sql2 = "select * from picture where houseId=?";
		ArrayList<HouseModel> list = new ArrayList<>();
		HouseModel[] array = null;
		HouseModel hm = null;
		ArrayList<String> urlList = null;
		String[] urlArray = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, publisherId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				hm = new HouseModel();
				hm.setId(rs.getInt("id"));
				hm.setTitle(rs.getString("title"));
				hm.setProvince(rs.getString("province"));
				hm.setCity(rs.getString("city"));
				hm.setRegion(rs.getString("region"));
				hm.setPrice(rs.getDouble("price"));
				hm.setAddress(rs.getString("address"));
				hm.setComment(rs.getString("comment"));
				hm.setPublisher(rs.getString("publisher"));
				hm.setCount(rs.getInt("count"));
				list.add(hm);
			}
			for(HouseModel h: list) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, h.getId());
				rs = pstmt.executeQuery();
				urlList = new ArrayList<String>();
				while(rs.next()) {
					urlList.add(rs.getString("picPath"));
				}
				urlArray = new String[urlList.size()];
				h.setPic(urlList.toArray(urlArray));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		array = new HouseModel[list.size()];
		return list.toArray(array);
	}

	@Override
	public HouseModel[] getHouseWithPublisherIdSortByTime(String publisherId, boolean asc) {
		String sql1 = "select * from house where publisher=? ";
		if(asc) {
			sql1 = sql1+" order by id ASC";
		}else {
			sql1 = sql1+" order by id DESC";
		}
		String sql2 = "select * from picture where houseId=?";
		ArrayList<HouseModel> list = new ArrayList<>();
		HouseModel[] array = null;
		HouseModel hm = null;
		ArrayList<String> urlList = null;
		String[] urlArray = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, publisherId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				hm = new HouseModel();
				hm.setId(rs.getInt("id"));
				hm.setTitle(rs.getString("title"));
				hm.setProvince(rs.getString("province"));
				hm.setCity(rs.getString("city"));
				hm.setRegion(rs.getString("region"));
				hm.setPrice(rs.getDouble("price"));
				hm.setAddress(rs.getString("address"));
				hm.setComment(rs.getString("comment"));
				hm.setPublisher(rs.getString("publisher"));
				hm.setCount(rs.getInt("count"));
				list.add(hm);
			}
			for(HouseModel h: list) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, h.getId());
				rs = pstmt.executeQuery();
				urlList = new ArrayList<String>();
				while(rs.next()) {
					urlList.add(rs.getString("picPath"));
				}
				urlArray = new String[urlList.size()];
				h.setPic(urlList.toArray(urlArray));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		array = new HouseModel[list.size()];
		return list.toArray(array);
	}

}
