package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImplementation.OrdersHistoryDaoImplementation;
import model.OrdersHistory;

 
@WebServlet("/ViewOrdersHistory")
public class ViewOrdersHistory extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int userId = Integer.parseInt(req.getParameter("uid"));
		System.out.println("\n\nfetched userId "+ userId + "\n\n");
		
		OrdersHistoryDaoImplementation ohdaoi = new OrdersHistoryDaoImplementation();
		List<OrdersHistory> ordersHistoryList = (List<OrdersHistory>) ohdaoi.fetchUserId(userId); 

 		
		System.out.println("\n\n fetched all ordersHistoryList details " + ordersHistoryList + "\n\n");
  

        // Set attribute and forward
        req.getSession().setAttribute("fetchedordersHistory", ordersHistoryList);
        req.getRequestDispatcher("ordershistory.jsp").forward(req, resp);
	 
		
		
	}
}
