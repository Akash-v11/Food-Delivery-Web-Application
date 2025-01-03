package daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import daoInterface.RestaurantDaoInterface;
import databaseutil.MyConnection;
import model.Restaurant;
  
public class RestaurantDaoImplementation implements RestaurantDaoInterface
{
	List<Restaurant> restaurantArrayList = new ArrayList<Restaurant>();
	
    private final String INSERT_QUERY = "INSERT INTO Restaurant (name, cuisinetype, address, rating, deliverytime, isactive, image)   VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String FETCHALL_QUERY = "SELECT * FROM Restaurant";
    private final String FETCH_UNIQUE_QUERY = "SELECT * FROM Restaurant where restaurantid = ? ";
    private final String UPDATE_QUERY = "UPDATE `Restaurant` set name = ? , description = ? , price = ? , rating = ? , isavailable = ? , image = ? ";
    private final String DELETE_QUERY = "DELETE from `Restaurant` where `restaurantid` = ? ";
    		
    		 
    private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private Restaurant restaurant;
	private int status;

  
    public RestaurantDaoImplementation()
    {
        try
        {
        	 con = MyConnection.connect();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }

    @Override
    public void insert(Restaurant r) 
    { 	
    	try
        {
    		pstmt = con.prepareStatement(INSERT_QUERY);
            pstmt.setString(1, r.getName());
            pstmt.setString(2, r.getCuisinetype());
            pstmt.setString(3, r.getAddress());
            pstmt.setInt(4, r.getRating());
            pstmt.setString(5, r.getDeliverytime());
            pstmt.setString(6, r.getIsactive());
            pstmt.setString(7, r.getImage());
 
            status = pstmt.executeUpdate();
            System.out.println(status != 0 ? "Insertion successful" : "Insertion failed");

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

	@Override
	public List<Restaurant> fetchAll() 
	{
	 	try 
	 	{
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(FETCHALL_QUERY);
			System.out.println("Total restaurants fetched: " + resultSet.getFetchSize());

			
			restaurantArrayList = extractionRestaurantArrayList(resultSet); 
		}
	 	catch (SQLException e) 
	 	{
 			e.printStackTrace();
		}
		return restaurantArrayList;
	}
	
	public  List<Restaurant> extractionRestaurantArrayList(ResultSet resultSet)
	{
		try 
		{
			while (resultSet.next()) 
			{
				restaurantArrayList.add( new Restaurant(
										                resultSet.getInt("restaurantid"),
										                resultSet.getString("name"),
										                resultSet.getString("cuisinetype"),
										                resultSet.getString("address"),
										                resultSet.getInt("rating"),
										                resultSet.getString("deliverytime"),
										                resultSet.getString("isactive"),
										                resultSet.getString("image") 
														));
			}
			 
		}
		catch (SQLException e)
		{	 
			e.printStackTrace();
		}
		return restaurantArrayList;
	}

	
	@Override
	public Restaurant fetchSpecific(int id) 
	{
		try
		{
			pstmt = con.prepareStatement(FETCH_UNIQUE_QUERY);
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();
			
			restaurantArrayList = extractionRestaurantArrayList(resultSet);
			
			if(! restaurantArrayList.isEmpty())
			{
				restaurant = restaurantArrayList.get(0);
			}
			else
			{
 				System.out.println("No records restaurant found for ID: " + id);
				System.exit(0);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return restaurant;
	}

	@Override
	public int update(Restaurant r)
	{
		try 
		{    
			pstmt = con.prepareStatement(UPDATE_QUERY);
			pstmt.setString(1, " ");
			pstmt.setString(2, " ");
			pstmt.setFloat(3,  4f);
			status = pstmt.executeUpdate();
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public int delete(int id) 
	{
		try
		{
			pstmt = con.prepareStatement(DELETE_QUERY);
			pstmt.setInt(1, id);
			status = pstmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	
	
}
