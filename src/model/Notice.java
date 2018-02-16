package model;

public class Notice{
	private int id;
	private String userTo;
	private String comment;
	private boolean isRead;

	public int getId(){
		return this.id;
	}
	public String getUserTo(){
		return this.userTo;
	}
	public String getComment(){
		return this.comment;
	}
	public boolean getIsRead(){
		return this.isRead;
	}

	public void setId(int id){
		this.id = id;
	}
	public void setUserTo(String userTo){
		this.userTo = userTo;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
	public void setIsRead(boolean isRead){
		this.isRead = isRead;
	}
}