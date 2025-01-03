package daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import daoInterface.MenuDaoInterface;
import databaseutil.MyConnection;
import model.Menu;

public class MenuDaoImplementation implements MenuDaoInterface
{
    List<Menu> menuArrayList = new ArrayList<Menu>();

    private final String INSERT_QUERY = "INSERT INTO menu (restaurantid, name, description, price, rating, isAvailable, image) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String FETCHALL_QUERY = "SELECT * FROM menu";
    private final String FETCH_UNIQUE_QUERY = "SELECT * FROM menu WHERE restaurantid = ?";
    private final String UPDATE_QUERY = "UPDATE menu SET name = ?, description = ?, price = ?, rating = ?, isAvailable = ?, image = ? WHERE menuid = ?";
    private final String DELETE_QUERY = "DELETE FROM menu WHERE menuid = ?";

    private Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;
    private int status;

 
    public MenuDaoImplementation() {
        try {
            con = MyConnection.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Menu m) {
        try {
            pstmt = con.prepareStatement(INSERT_QUERY);
            pstmt.setInt(1, m.getRestaurantId());
            pstmt.setString(2, m.getName());
            pstmt.setString(3, m.getDescription());
            pstmt.setFloat(4, m.getPrice());
            pstmt.setFloat(5, m.getRating());
            pstmt.setInt(6, m.getIsAvailable());
            pstmt.setString(7, m.getImage());

            status = pstmt.executeUpdate();
            System.out.println(status != 0 ? "Insertion successful" : "Insertion failed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Menu> fetchAll() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(FETCHALL_QUERY);

            menuArrayList = extractionMenuArrayList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuArrayList;
    }

    public List<Menu> extractionMenuArrayList(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                menuArrayList.add(new Menu(
                        resultSet.getInt("menuid"),
                        resultSet.getInt("restaurantid"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getFloat("price"),
                        resultSet.getFloat("rating"),
                        resultSet.getInt("isAvailable"),
                        resultSet.getString("image")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuArrayList;
    }
 
    @Override
    public List<Menu> fetchSpecificRestaurantid(int id)//restaurantid
    {
        try {
            pstmt = con.prepareStatement(FETCH_UNIQUE_QUERY);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();

            menuArrayList = extractionMenuArrayList(resultSet);

            if (!menuArrayList.isEmpty()) 
            {
            	 menuArrayList.get(0);
            } 
            else
            {
                System.out.println("No Records");
                System.exit(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuArrayList;
    }
    
 
    private final String FETCH_MENU = "SELECT * FROM menu WHERE menuid = ?";
 	private Menu menu;
    @Override 
    public Menu FecthMenuId(int id) //menuid
    {
        try {
            pstmt = con.prepareStatement(FETCH_MENU);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();

            menuArrayList = extractionMenuArrayList(resultSet);

            if (!menuArrayList.isEmpty()) 
            {
            	menu = menuArrayList.get(0);
            } 
            else
            {
                System.out.println("No Records");
                System.exit(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }
    
    
    
    

    @Override
    public int update(Menu m) {
        try {
            pstmt = con.prepareStatement(UPDATE_QUERY);
            pstmt.setString(1, m.getName());
            pstmt.setString(2, m.getDescription());
            pstmt.setFloat(3, m.getPrice());
            pstmt.setFloat(4, m.getRating());
            pstmt.setInt(5, m.getIsAvailable());
            pstmt.setString(6, m.getImage());
            pstmt.setInt(7, m.getMenuId());

            status = pstmt.executeUpdate();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
