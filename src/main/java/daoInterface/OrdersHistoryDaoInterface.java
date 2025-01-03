package daoInterface; 

import java.util.List;

import model.OrdersHistory;

public interface OrdersHistoryDaoInterface {

    // Method to insert an OrdersHistory into the database
    int insert(OrdersHistory oh);

    // Method to fetch all OrdersHistory records from the database
    List<OrdersHistory> fetchAll();

    // Method to fetch a specific OrdersHistory by its ordersHistoryId
    OrdersHistory fetchSpecific(int id);

    // Method to update an existing OrdersHistory in the database
    int update(OrdersHistory oh);

    // Method to delete an OrdersHistory by its ordersHistoryId
    int delete(int id);

    List<OrdersHistory> fetchUserId(int UserId);
 }
