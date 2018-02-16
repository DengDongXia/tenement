package model;

public class Order{
	private int id;
	private String userId;
	private int houseId;
	private int confirm;

	public void setId(int id){
		this.id = id;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}
	public void setHouseId(int houseId){
		this.houseId = houseId;
	}
	public void setConfirm(int confirm){
		this.confirm = confirm;
	}

	public int getId(){
		return this.id;
	}
	public String getUserId(){
		return this.userId;
	}
	public int getHouseId(){
		return this.houseId;
	}
	public int getConfirm(){
		return this.confirm;
	}

}
