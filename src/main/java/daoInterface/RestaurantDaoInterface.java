package daoInterface; 

import java.util.List;

import model.Restaurant;
 
public interface RestaurantDaoInterface
{
	void insert(Restaurant r);
	
	List<Restaurant> fetchAll();
	
	Restaurant fetchSpecific(int id); 
	
	int update(Restaurant r);

	int delete(int id);
}
