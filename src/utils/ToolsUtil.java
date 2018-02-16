package utils;



import model.House;
import model.HouseModel;
import model.OrderModel;

public class ToolsUtil {
	
	public static HouseModel[] split(HouseModel[] arr, int page, int count) {
		HouseModel[] ret;
		if(getPages(arr, count)==0) {
			return arr;
		}
		if(getPages(arr, count)!=page || arr.length%count==0) {
			ret = new HouseModel[count];
			for(int i=(page-1)*count,j=0;i<page*count;i++,j++) {
				ret[j] = arr[i];
			}
		}else {
			ret = new HouseModel[arr.length%count];
			for(int i=(page-1)*count,j=0;i<(page-1)*count+arr.length%count;i++,j++) {
				ret[j] = arr[i];
			}
		}
		return ret;
	}
	public static OrderModel[] split(OrderModel[] arr, int page, int count) {
		OrderModel[] ret;
		if(getPages(arr, count)==0) {
			return arr;
		}
		if(getPages(arr, count)!=page || arr.length%count==0) {
			ret = new OrderModel[count];
			for(int i=(page-1)*count,j=0;i<page*count;i++,j++) {
				ret[j] = arr[i];
			}
		}else {
			ret = new OrderModel[arr.length%count];
			for(int i=(page-1)*count,j=0;i<(page-1)*count+arr.length%count;i++,j++) {
				ret[j] = arr[i];
			}
		}
		return ret;
	}
	public static <T> int getPages(T[] arr,int count) {
		return arr.length%count==0?arr.length/count:arr.length/count+1;
	}
	
	public static House HouseModelToHouse(HouseModel hm) {
		House house = new House();
		house.setAddress(hm.getAddress());
		house.setCity(hm.getCity());
		house.setComment(hm.getComment());
		house.setCount(hm.getCount());
		house.setId(hm.getId());
		house.setPrice(hm.getPrice());
		house.setProvince(hm.getProvince());
		house.setPublisher(hm.getPublisher());
		house.setRegion(hm.getRegion());
		house.setTitle(hm.getTitle());
		return house;
	}
	
	public static String generateUserId() {
		int random = (int)(89999999*Math.random()+10000000);
		return ""+random;
	}
	
}
