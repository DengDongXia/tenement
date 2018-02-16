package model;

public class OrderModel{
	private int id;
	private String userId;
	private String userName;
	private String userPhone;
	private String publisherId;
	private String publisherName;
	private String publisherPhone;
	private int confirm;
	private int houseId;
	private String houseTitle;
	private double housePrice;
	private String houseAddress;
	private String houseComment;

	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setConfirm(int confirm){
		this.confirm = confirm;
	}
	public int getConfirm(){
		return this.confirm;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}
	public String getUserId(){
		return this.userId;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return this.userName;
	}
	public void setUserPhone(String userPhone){
		this.userPhone = userPhone;
	}
	public String getUserPhone(){
		return this.userPhone;
	}
	public void setPublisherId(String publisherId){
		this.publisherId = publisherId;
	}
	public String getPublihserId(){
		return this.publisherId;
	}
	public void setPublisherName(String publisherName){
		this.publisherName = publisherName;
	}
	public String getPublihserName(){
		return this.publisherName;
	}
	public void setPublisherPhone(String publisherPhone){
		this.publisherPhone = publisherPhone;
	}
	public String getPublihserPhone(){
		return this.publisherPhone;
	}

	public int getHouseId(){
		return this.houseId;
	}
	public void setHouseId(int houseId){
		this.houseId = houseId;
	}
	public String getHouseTitle(){
		return this.houseTitle;
	}
	public void setHouseTitle(String houseTitle){
		this.houseTitle = houseTitle;
	}
	public String getHouseAddress(){
		return this.houseAddress;
	}
	public void setHouseAddress(String houseAddress){
		this.houseAddress = houseAddress;
	}
	public String getHouseComment(){
		return this.houseComment;
	}
	public void setHouseComment(String houseComment){
		this.houseComment = houseComment;
	}
	public double getHousePrice(){
		return this.housePrice;
	}
	public void setHousePrice(double housePrice){
		this.housePrice = housePrice;
	}


}
