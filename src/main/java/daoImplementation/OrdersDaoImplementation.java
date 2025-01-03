package daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import daoInterface.OrdersDaoInterface;
import databaseutil.MyConnection;
import model.Orders;
import model.OrdersHistory;

public class OrdersDaoImplementation implements OrdersDaoInterface 
{
    private List<Orders> ordersList = new ArrayList<>();
 
    
    private final String INSERT_QUERY = "INSERT INTO Orders (uid, restaurantid, menuid, quantity, totalAmount, paymentType, dateAndtime, status) "
            + "VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?)";
    private final String FETCHALL_QUERY = "SELECT * FROM Orders";
    private final String FETCH_UNIQUE_QUERY = "SELECT * FROM Orders WHERE ordersId = ?";
    private final String UPDATE_QUERY = "UPDATE Orders SET uid = ?, restaurantid = ?, menuid = ?, quantity = ?, totalAmount = ?, paymentType = ?, status = ? WHERE ordersId = ?";
    private final String DELETE_QUERY = "DELETE FROM Orders WHERE ordersId = ?";

    private Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;
    
    private Orders order;
    private int status;

    public OrdersDaoImplementation() {
        try {
            con = MyConnection.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      
    @Override
    public int insert(Orders o) {
        try {
            pstmt = con.prepareStatement(INSERT_QUERY);
            pstmt.setInt(1, o.getUid());
            pstmt.setInt(2, o.getRestaurantid());
            pstmt.setInt(3, o.getMenuid());
            pstmt.setInt(4, o.getQuantity());
            pstmt.setFloat(5, o.getTotalAmount());
            pstmt.setString(6, o.getPaymentType());
            pstmt.setString(7, o.getStatus()); // Fixed index

            status = pstmt.executeUpdate();
            System.out.println(status != 0 ? "Insertion successful" : "Insertion failed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    
    
    
    private final String FETCHMAX = "SELECT * FROM Orders WHERE ordersId = (SELECT MAX(ordersId) FROM Orders)";
    @Override
    public List<Orders> FETCHMAX(int orderId) 
    {
        try
        {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(FETCHMAX);
            ordersList = extractionOrdersList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }


    
    
    @Override
    public List<Orders> fetchAll() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(FETCHALL_QUERY);
            ordersList = extractionOrdersList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    private List<Orders> extractionOrdersList(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                ordersList.add(new Orders(
                        resultSet.getInt("ordersId"),
                        resultSet.getInt("uid"),
                        resultSet.getInt("restaurantid"),
                        resultSet.getInt("menuid"),
                        resultSet.getInt("quantity"),
                        resultSet.getFloat("totalAmount"),
                        resultSet.getString("paymentType"),
                        resultSet.getTimestamp("dateAndtime").toLocalDateTime(),
                        resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    @Override
    public Orders fetchSpecific(int id) {
        try {
            pstmt = con.prepareStatement(FETCH_UNIQUE_QUERY);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();

            ordersList = extractionOrdersList(resultSet);

            if (!ordersList.isEmpty()) {
                order = ordersList.get(0);
            } else {
                System.out.println("No Records Found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public int update(Orders o) {
        try {
            pstmt = con.prepareStatement(UPDATE_QUERY);
            pstmt.setInt(1, o.getUid());
            pstmt.setInt(2, o.getRestaurantid());
            pstmt.setInt(3, o.getMenuid());
            pstmt.setInt(4, o.getQuantity());
            pstmt.setFloat(5, o.getTotalAmount());
            pstmt.setString(6, o.getPaymentType());
            pstmt.setString(7, o.getStatus());
            pstmt.setInt(8, o.getOrdersId());

            status = pstmt.executeUpdate();
            System.out.println(status != 0 ? "Update successful" : "Update failed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

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
	public List<Orders> fetchUserId(int UserId) {
		try {
            pstmt = con.prepareStatement("SELECT * FROM Orders WHERE uid = ?");
            pstmt.setInt(1, UserId);
            resultSet = pstmt.executeQuery();
            ordersList= extractionOrdersList(resultSet);
            
            if (!ordersList.isEmpty()) {
            	 order = ordersList.get(0);
            } else {
                System.out.println("No Records Found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return ordersList;
	}
}
 

