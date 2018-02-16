package model;

public class Admin{
	private String id;
	private String userName;
	private String password;

	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
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
}

