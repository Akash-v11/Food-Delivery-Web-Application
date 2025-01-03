package controller; 


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImplementation.RestaurantDaoImplementation;
import model.Restaurant;

@WebServlet("/GetRestaurant")
public class GetRestaurant extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestaurantDaoImplementation rdaoi = new RestaurantDaoImplementation(); 
        
        // Fetch all restaurants
//        List<Restaurant> restaurantList = rdaoi.fetchAll();
        
        List<Restaurant> restaurantList = rdaoi.fetchAll();
        if (restaurantList == null) {
            System.out.println("No restaurants were fetched from the database.");
        } else {
            System.out.println("Successfully fetched " + restaurantList.size() + " restaurants.");
        }

        
        // Store the entire list in the session
//        req.getSession().setAttribute("restaurantDisplay", restaurantList);

        if (restaurantList != null) 
        {
            req.getSession().setAttribute("restaurantDisplay", restaurantList);
        } 
        else 
        {
            System.out.println("Error: Restaurant list is null!");
        }
        
        System.out.println("successfully fetched restaurantDisplay from GetRestaurant ");
        
        
        req.getRequestDispatcher("home.jsp").forward(req, resp); 
//        resp.sendRedirect("home.jsp"); 
        


    }
}
