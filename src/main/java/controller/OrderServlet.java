package controller; 
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoImplementation.CartDaoImplementation;
import daoImplementation.OrdersDaoImplementation;
import daoImplementation.OrdersHistoryDaoImplementation;
import daoImplementation.OrdersItemDaoImplementation;
import model.CartItem;
import model.Menu;
import model.Orders;
import model.OrdersHistory;
import model.OrdersItem;
import model.Restaurant;
import model.User;

@WebServlet("/orderservlet")
public class OrderServlet extends HttpServlet {

	int maxOrdersId;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        		HttpSession session = req.getSession();
        		
//          Restaurant restaurant = (Restaurant) session.getAttribute("restaurantDisplay");
            Restaurant restaurant = (Restaurant) session.getAttribute("restaurantID");
             User user = (User) session.getAttribute("LoggedInUser");
             ArrayList<Menu> menus = (ArrayList<Menu>) session.getAttribute("MenuFetchOne");
  
            System.out.println("\n\n\n restaurant details \n\n\n"+restaurant);
            System.out.println("\n\n user details \n\n\n"+user);                  
            System.out.println("\n\n menu details \n\n\n"+menus);

            if (menus != null && !menus.isEmpty()) {
                Menu menu = menus.get(0);  // Assuming you want the first menu

         		CartDaoImplementation cart = (CartDaoImplementation) session.getAttribute("cart");
                System.out.println("\n\ncart details \n\n"+cart);

                // Checkout Form parameters
                String address = req.getParameter("address");
                String paymentMethod = req.getParameter("payment-method");
                float grandTotal = Float.parseFloat(req.getParameter("grandTotal"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                
                session.setAttribute("address",address);
                session.setAttribute("paymentMethod",paymentMethod);
                session.setAttribute("grandTotal",grandTotal);
 

                
                // Check if quantity is valid
                if (quantity <= 0) {
                    resp.sendRedirect("failure.jsp?message=Invalid quantity");
                    return;
                }

                // Debug: Logging checkout data
                System.out.println("Order Details-Address: " + address +  ", Payment Method: " + paymentMethod 
                                   + ", Grand Total: " + grandTotal + ", Quantity: " + quantity);

                
                
                int userId = user.getUid();
                int restaurantId = restaurant.getRestaurantid();
                int menuId = menu.getMenuId();
                
                System.out.println("\n" +userId + restaurantId + menuId + "\n");
                 

                if (restaurant == null || user == null) {
                    resp.sendRedirect("failure.jsp");
                    return;
                }

                if (cart != null && cart.getItems() != null) {
                    Map<Integer, CartItem> cartItems = cart.getItems();

                    OrdersDaoImplementation ordersDao = new OrdersDaoImplementation();
                    OrdersItemDaoImplementation ordersItemDao = new OrdersItemDaoImplementation();
                    OrdersHistoryDaoImplementation ordersHistoryDao = new OrdersHistoryDaoImplementation();

                    // Step 1: Insert into the orders table
                    Orders orderTable = new Orders(userId, restaurantId, menuId, quantity, grandTotal, paymentMethod, LocalDateTime.now(), "Pending");
                    int orderId = ordersDao.insert(orderTable);  // Inserting the order
                   
                    
                    // Step 2: Fetch the max orderId
                    List<Orders> fetchmax = ordersDao.FETCHMAX(orderId);
                    maxOrdersId = fetchmax.get(0).getOrdersId();
                    
                    session.setAttribute("maxOrdersId",maxOrdersId);

                    System.err.println("maxOrdersIdmaxxx " + maxOrdersId);// Getting the latest orderId inserted

                    // Step 3: Insert into OrdersItem table
                    for (CartItem item : cartItems.values()) 
                    {
                        int itemQuantity = item.getQuantity();
                        Float itemTotal = (float) item.getSubTotal();

                        OrdersItem orderItem = new OrdersItem(maxOrdersId, menuId, itemQuantity, itemTotal);
                        ordersItemDao.insert(orderItem);
                        System.out.println("\n\n\n SUCCESSFULLY INSERTED orderItem "+ orderItem +"\n\n\n");

                    }

                    // Step 4: Insert into OrdersHistory table
                    OrdersHistory ordersHistory = new OrdersHistory(maxOrdersId, userId, restaurantId, grandTotal, "Delivered");
                    ordersHistoryDao.insert(ordersHistory);
 
                    System.out.println("\n\n\n SUCCESSFULLY INSERTED ordersHistory " + ordersHistory +"\n\n\n");

                    
                    
                    // Redirect to the order confirmation page
                    resp.sendRedirect("orderConfirmationServlet?maxOrdersId=" + maxOrdersId);
                    System.out.println("\n\n\n Redirecting to confirmation page with maxOrdersId: " + maxOrdersId +"\n\n\n");
                } else {
                    resp.sendRedirect("failure.jsp");
                }
            } else {
                resp.sendRedirect("failure.jsp?message=Menu list is empty");
                System.out.println("\n\n\n NOT Redirecting to confirmation page with maxOrdersId: \n\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("failure.jsp?message=" + e.getMessage());
        }
    }
}
