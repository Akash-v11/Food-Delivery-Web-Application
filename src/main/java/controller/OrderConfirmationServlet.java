package controller; 

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImplementation.OrdersHistoryDaoImplementation;
import model.OrdersHistory;

@WebServlet("/orderConfirmationServlet")
public class OrderConfirmationServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try 
        {
            // Fetch orderId from the request
            int maxOrdersId = Integer.parseInt(req.getParameter("maxOrdersId"));
            System.out.println("\n fetched orderId from OrderConfirmationServlet "+maxOrdersId+"\n");
 			req.getSession().setAttribute("orderId",maxOrdersId);
 			
            // Fetch the order history for the specific orderId
            OrdersHistoryDaoImplementation ordersHistoryDao = new OrdersHistoryDaoImplementation();
            OrdersHistory orderHistory = ordersHistoryDao.fetchSpecific(maxOrdersId);
            System.out.println("\n fetched orderHistory from OrderConfirmationServlet "+orderHistory+"\n");


            if (orderHistory != null) {
                // Store the order history in the request scope
                req.setAttribute("orderHistory", orderHistory);

                // Forward the request to the confirmation JSP page
                req.getRequestDispatcher("/orderConfirmation.jsp").forward(req, resp);
                System.out.println("\n\n\n Redirecting to orderConfirmation.jsp with orderHistory \n");

            } else {
                // If no order found, redirect to failure page
                resp.sendRedirect("failure.jsp?message=Order not found");
                System.out.println("\n\n\n NOT Redirecting to orderConfirmation.jsp \n");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("failure.jsp?message=" + e.getMessage());
        }
    }
}
