package controller; 


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImplementation.MenuDaoImplementation;
import daoImplementation.RestaurantDaoImplementation;
import daoInterface.RestaurantDaoInterface;
import model.Menu;
import model.Restaurant;

@WebServlet("/ShowMenu")
public class ShowMenu extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		 int restaurantid = Integer.parseInt(req.getParameter("Restaurantid").trim());
		 
		 MenuDaoImplementation mdaoi = new MenuDaoImplementation();
		 List<Menu> menu = mdaoi.fetchSpecificRestaurantid(restaurantid);
		 
		 System.out.println("fetched menu arraylist from ShowMenu \n" + menu);
		 
		 RestaurantDaoInterface rdaoi = new RestaurantDaoImplementation();
		 Restaurant restaurantID  =  rdaoi.fetchSpecific(restaurantid);
		 
		 
		 req.getSession().setAttribute("MenuFetchOne", menu);
  		 req.getSession().setAttribute("restaurantID", restaurantID);
  		 
  		 System.out.println("MenuFetchOne and restaurantID stored in session \n\n fetched from ShowMenu \n");
 
 
 		 resp.sendRedirect("menu.jsp");
	}
}
  
