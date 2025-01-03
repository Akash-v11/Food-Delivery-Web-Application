package daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import daoInterface.OrdersHistoryDaoInterface;
import databaseutil.MyConnection;
import model.OrdersHistory;

public class OrdersHistoryDaoImplementation implements OrdersHistoryDaoInterface {

    // SQL Queries
    private final String INSERT_QUERY = "INSERT INTO ordershistory (ordersId, uid, restaurantid, total, status) VALUES (?, ?, ?, ?, ?)";
    private final String FETCHALL_QUERY = "SELECT * FROM  ordershistory";
    private final String FETCH_UNIQUE_QUERY = "SELECT * FROM ordershistory WHERE ordersHistoryId = ?";
    private final String UPDATE_QUERY = "UPDATE OrdersHistory SET ordersId = ?, uid = ?, restaurantid = ?, total = ?, status = ? WHERE ordersHistoryId = ?";
    private final String DELETE_QUERY = "DELETE FROM OrdersHistory WHERE ordersHistoryId = ?";

    // Database connection and statement objects
    private Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;

    // List to hold OrdersHistory objects
    private List<OrdersHistory> ordersHistoryList = new ArrayList<>();
    private OrdersHistory ordersHistory;
    private int status;

    // Constructor to establish database connection
    public OrdersHistoryDaoImplementation() {
        try {
            con = MyConnection.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Insert method to add an OrdersHistory to the database
    @Override
    public int insert(OrdersHistory oh) {
        try {
            pstmt = con.prepareStatement(INSERT_QUERY);
            pstmt.setInt(1, oh.getOrdersId());
            pstmt.setInt(2, oh.getUid());
            pstmt.setInt(3, oh.getRestaurantid());
            pstmt.setFloat(4, oh.getTotal());
            pstmt.setString(5, oh.getStatus());
            
            status = pstmt.executeUpdate();
            System.out.println(status != 0 ? "Insertion successful" : "Insertion failed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    // Fetch all OrdersHistory records from the database
    @Override
    public List<OrdersHistory> fetchAll() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(FETCHALL_QUERY);
            ordersHistoryList = extractOrdersHistoryList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersHistoryList;
    }

    // Helper method to map result set to a list of OrdersHistory objects
    private List<OrdersHistory> extractOrdersHistoryList(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                ordersHistoryList.add(new OrdersHistory(
                        resultSet.getInt("ordersHistoryId"),
                        resultSet.getInt("ordersId"),
                        resultSet.getInt("uid"),
                        resultSet.getInt("restaurantid"),
                        resultSet.getFloat("total"),
                        resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersHistoryList;
    }

    // Fetch a specific OrdersHistory record by its ID
    @Override
    public OrdersHistory fetchSpecific(int id) {
        try {
            pstmt = con.prepareStatement(FETCH_UNIQUE_QUERY);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            ordersHistoryList = extractOrdersHistoryList(resultSet);
            
            if (!ordersHistoryList.isEmpty()) {
                ordersHistory = ordersHistoryList.get(0);
            } else {
                System.out.println("No Records Found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersHistory;
     }
    
     
    // Update an existing OrdersHistory record in the database
    @Override
    public int update(OrdersHistory oh) {
        try {
            pstmt = con.prepareStatement(UPDATE_QUERY);
            pstmt.setInt(1, oh.getOrdersId());
            pstmt.setInt(2, oh.getUid());
            pstmt.setInt(3, oh.getRestaurantid());
            pstmt.setFloat(4, oh.getTotal());
            pstmt.setString(5, oh.getStatus());
            pstmt.setInt(6, oh.getOrdersHistoryId());

            status = pstmt.executeUpdate();
            System.out.println(status != 0 ? "Update successful" : "Update failed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    // Delete a specific OrdersHistory record by its ID
    @Override
    public int delete(int id) {
        try {
            pstmt = con.prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, id);
            status = pstmt.executeUpdate();
            System.out.println(status != 0 ? "Delete successful" : "Delete failed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    
    
    
	@Override
	public List<OrdersHistory> fetchUserId(int UserId) {
		try {
            pstmt = con.prepareStatement("SELECT * FROM ordershistory WHERE uid = ?");
            pstmt.setInt(1, UserId);
            resultSet = pstmt.executeQuery();
            ordersHistoryList = extractOrdersHistoryList(resultSet);
            
            if (!ordersHistoryList.isEmpty()) {
                ordersHistory = ordersHistoryList.get(0);
            } else {
                System.out.println("No Records Found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return ordersHistoryList;
	}
}
