package dao;

import model.House;
import model.HouseModel;

public interface HouseDao {
	public HouseModel[] getHouse();
	public HouseModel[] getHouseSortByPrice(boolean asc);
	public HouseModel[] getHouseSortByTime(boolean asc);
	
	public HouseModel[] getHouseWithPrice(double min, double max);
	public HouseModel[] getHouseWithKeyword(String keyword);
	public HouseModel[] getHouseWithPriceSortByPrice(double min, double max, boolean asc);
	public HouseModel[] getHouseWithKeywordSortByPrice(String keyword, boolean asc);
	public HouseModel[] getHouseWithPriceSortByTime(double min, double max, boolean asc);
	public HouseModel[] getHouseWithKeywordSortByTime(String keyword, boolean asc);
	
	public HouseModel[] getHouseWithPublisherId(String publisherId);
	public HouseModel[] getHouseWithPublisherIdSortByPrice(String publisherId, boolean asc);
	public HouseModel[] getHouseWithPublisherIdSortByTime(String publisherId, boolean asc);
	
	public boolean publishHouse(House house, String[] picUrl);
	public boolean editHouse(House house);
	public boolean deleteHouse(House house);
	
	public HouseModel getHouseWithId(String id);

}
