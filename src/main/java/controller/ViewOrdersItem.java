package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImplementation.OrdersItemDaoImplementation;
import model.OrdersItem;


@WebServlet("/ViewOrdersItem")
public class ViewOrdersItem extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int userId = Integer.parseInt(req.getParameter("uid"));
		System.out.println("\n\nfetched userId "+ userId + "\n\n");
		
		OrdersItemDaoImplementation odaoi = new OrdersItemDaoImplementation();
		List<OrdersItem> ordersItemList = (List<OrdersItem>) odaoi.fetchUserId(userId); 

 		
		System.out.println("\n\n fetched all ordersItemList details " + ordersItemList + "\n\n");
  

        // Set attribute and forward
        req.getSession().setAttribute("fetchedordersItem", ordersItemList);
        req.getRequestDispatcher("ordersItem.jsp").forward(req, resp);
	 
		
		
	}
	
}
