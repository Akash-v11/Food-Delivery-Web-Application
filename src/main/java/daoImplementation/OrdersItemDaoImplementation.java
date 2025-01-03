package daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import daoInterface.OrdersItemDaoInterface;
import databaseutil.MyConnection;
import model.OrdersHistory;
import model.OrdersItem;

public class OrdersItemDaoImplementation implements OrdersItemDaoInterface 
{

    private List<OrdersItem> ordersItemList = new ArrayList<>();

    private final String INSERT_QUERY = "INSERT INTO ordersitem (ordersId, menuId, quantity, ItemTotal) VALUES (?, ?, ?, ?)";
    private final String FETCHALL_QUERY = "SELECT * FROM ordersitem";
    private final String FETCH_UNIQUE_QUERY = "SELECT * FROM ordersitem WHERE OrdersItemId = ?";
    private final String UPDATE_QUERY = "UPDATE ordersitem SET ordersId = ?, menuId = ?, quantity = ?, ItemTotal = ? WHERE OrdersItemId = ?";
    private final String DELETE_QUERY = "DELETE FROM ordersitem WHERE OrdersItemId = ?";

    private Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;
    private OrdersItem ordersItem;
    private int status;

    public OrdersItemDaoImplementation() {
        try {
            con = MyConnection.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insert(OrdersItem oi) {
        try {
            pstmt = con.prepareStatement(INSERT_QUERY);
            pstmt.setInt(1, oi.getOrdersId());
            pstmt.setInt(2, oi.getMenuId());
            pstmt.setInt(3, oi.getQuantity());
            pstmt.setDouble(4, oi.getItemTotal());

            status = pstmt.executeUpdate();
            System.out.println(status != 0 ? "Insertion successful" : "Insertion failed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<OrdersItem> fetchAll() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(FETCHALL_QUERY);
            ordersItemList = extractionOrdersItemList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersItemList;
    }

    private List<OrdersItem> extractionOrdersItemList(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                ordersItemList.add(new OrdersItem(
                        resultSet.getInt("OrdersItemId"),
                        resultSet.getInt("ordersId"),
                        resultSet.getInt("menuId"),
                        resultSet.getInt("quantity"),
                        resultSet.getFloat("ItemTotal")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersItemList;
    }

    @Override
    public OrdersItem fetchSpecific(int id) {
        try {
            pstmt = con.prepareStatement(FETCH_UNIQUE_QUERY);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();

            ordersItemList = extractionOrdersItemList(resultSet);

            if (!ordersItemList.isEmpty()) 
            {
                ordersItem = ordersItemList.get(0);
            } else {
                System.out.println("No Records Found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersItem;
    }

    @Override
    public int update(OrdersItem oi) {
        try {
            pstmt = con.prepareStatement(UPDATE_QUERY);
            pstmt.setInt(1, oi.getOrdersId());
            pstmt.setInt(2, oi.getMenuId());
            pstmt.setInt(3, oi.getQuantity());
            pstmt.setDouble(4, oi.getItemTotal());
            pstmt.setInt(5, oi.getOrdersItemId());

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
   	public List<OrdersItem> fetchUserId(int UserId) {
   		try {
               pstmt = con.prepareStatement("SELECT * FROM ordersitem WHERE OrdersId = ?");
               pstmt.setInt(1, UserId);
               resultSet = pstmt.executeQuery();
               ordersItemList = extractionOrdersItemList(resultSet);
               
               if (!ordersItemList.isEmpty()) {
                   ordersItem = ordersItemList.get(0);
               } else {
                   System.out.println("No Records Found");
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
   		return ordersItemList;
   	}
}
