package daoInterface; 

import java.util.List;

import model.Orders;

public interface OrdersDaoInterface {

    // Method to insert a new order
    int insert(Orders o);

    // Method to fetch all orders
    List<Orders> fetchAll();

    // Method to fetch a specific order by its ID
    Orders fetchSpecific(int id);

    // Method to update an existing order
    int update(Orders o);

    // Method to delete an order by its ID
    int delete(int id);

 
	List<Orders> FETCHMAX(int orderId);

	List<Orders> fetchUserId(int UserId);
}
