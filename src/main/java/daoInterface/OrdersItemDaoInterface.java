package daoInterface; 

import java.util.List;

import model.OrdersItem;

public interface OrdersItemDaoInterface  {
	// Method to insert an OrdersItem into the database
    int insert(OrdersItem oi);

    // Method to fetch all OrdersItems from the database
    List<OrdersItem> fetchAll();

    // Method to fetch a specific OrdersItem by its OrdersItemId
    OrdersItem fetchSpecific(int id);

    // Method to update an existing OrdersItem in the database
    int update(OrdersItem oi);

    // Method to delete an OrdersItem by its OrdersItemId
    int delete(int id);

	List<OrdersItem> fetchUserId(int UserId);
}
