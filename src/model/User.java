package model;

public class User{
	private String id;
	private String email;
	private String userName;
	private String password;
	private String head;
	private boolean isLandlord;
	private String phone;


	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}

	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return this.userName;
	}

	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return this.password;
	}

	public void setHead(String head){
		this.head = head;
	}
	public String getHead(){
		return this.head;
	}
	public void setIsLandlord(boolean isLandlord){
		this.isLandlord = isLandlord;
	}
	public boolean getIsLandlord(){
		return this.isLandlord;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
}
