package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImplementation.OrdersDaoImplementation;
import model.Orders;
 
@WebServlet("/ViewOrders")
public class ViewOrders extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int userId = Integer.parseInt(req.getParameter("uid"));
		System.out.println("\n\nfetched userId "+ userId + "\n\n");
		
		OrdersDaoImplementation ohdaoi = new OrdersDaoImplementation();
		List<Orders> ordersList = (List<Orders>) ohdaoi.fetchUserId(userId); 

 		
		System.out.println("\n\n fetched all ordersList details " + ordersList + "\n\n");
  

        // Set attribute and forward
        req.getSession().setAttribute("fetchedorders", ordersList);
        req.getRequestDispatcher("orders.jsp").forward(req, resp);
	}
}
