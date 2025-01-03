package controller; 

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoImplementation.CartDaoImplementation;
import daoImplementation.MenuDaoImplementation;
import model.CartItem;
import model.Menu;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet 
{
	private Map<Integer, CartItem> cart;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		HttpSession session = req.getSession();
		CartDaoImplementation cart = (CartDaoImplementation) session.getAttribute("cart");
		
 		
		if(cart == null)
		{
			cart = new CartDaoImplementation();
			session.setAttribute("cart", cart);
		}
		
		String action = req.getParameter("act");
		if("add".equals(action))
		{
			addItemToCart(req,cart);
		}
		else if("remove".equals(action))
		{
			removeItemFromCart(req,cart);
		}
		else if("update".equals(action)) 
		{
			updateItemToCart(req,cart);
		}
		else if("clearcart".equals(action))
		{
			clearCart(req,cart);
		}
		
		   session.setAttribute("cart",cart);
		   System.out.println("\n\n cart session details from CartServlet \n\n\n"+cart);
		   resp.sendRedirect("cart.jsp");
 
	}

	private void clearCart(HttpServletRequest req, CartDaoImplementation cart)
	{
		cart.clear();
 	}

	private void updateItemToCart(HttpServletRequest req, CartDaoImplementation cart) 
	{
 		int menuId = Integer.parseInt(req.getParameter("MenuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		cart.updateItem(menuId, quantity);
	}

	private void removeItemFromCart(HttpServletRequest req, CartDaoImplementation cart) 
	{
		int menuId = Integer.parseInt(req.getParameter("MenuId"));
		cart.removeItem(menuId);
 	}

	private void addItemToCart(HttpServletRequest req, CartDaoImplementation cart) 
	{
		int menuid = Integer.parseInt(req.getParameter("MenuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		MenuDaoImplementation mdaoi = new MenuDaoImplementation();
		Menu menu = mdaoi.FecthMenuId(menuid);
		System.out.println("fetched menuid \n");
 
		  req.getSession().setAttribute("RestaurantId", menu.getRestaurantId());
		
		  if(menu!=null)
		  {
			  CartItem item = new CartItem(menu.getMenuId(), 
						menu.getRestaurantId(), 
						menu.getName(), 
						quantity, 
						menu.getPrice(),
						quantity*menu.getPrice()
				 	    );
			  
			  cart.addItem(item);
		  }
	}
	
	 
}
