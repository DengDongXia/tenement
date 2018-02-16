package model;

public class HouseModel {
	private int id;
	private String title;
	private String province;
	private String city;
	private String region;
	private double price;
	private String address;
	private String comment;
	private String publisher;
	private int count;
	private String[] pic;


	public void setId(int id){
		this.id = id;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setProvince(String province){
		this.province = province;
	}
	public void setCity(String city){
		this.city = city;
	}
	public void setRegion(String region){
		this.region = region;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
	public void setPublisher(String publisher){
		this.publisher = publisher;
	}
	public void setCount(int count){
		this.count = count;
	}

	public int getId(){
		return id;
	}
	public String getTitle(){
		return title;
	}
	public String getProvince(){
		return province;
	}
	public String getCity(){
		return city;
	}
	public String getRegion(){
		return region;
	}
	public double getPrice(){
		return price;
	}
	public String getAddress(){
		return address;
	}
	public String getComment(){
		return comment;
	}
	public String getPublisher(){
		return publisher;
	}
	public int getCount(){
		return count;
	}
	public String[] getPic() {
		return this.pic;
	}
	public void setPic(String[] pic) {
		this.pic = pic;
	}
}
