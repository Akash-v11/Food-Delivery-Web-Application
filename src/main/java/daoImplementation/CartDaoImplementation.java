package daoImplementation;

import java.util.LinkedHashMap;
import java.util.Map;

import model.CartItem;

/**
 * interacting with session object
 */
public class CartDaoImplementation 
{

	private Map<Integer, CartItem> items;
	
	public CartDaoImplementation() 
	{
		this.items = new LinkedHashMap<Integer, CartItem>();
	}
	
	public void addItem(CartItem item) 
	{
		int menuId = item.getMenuId();
		if(items.containsKey(menuId))
		{
			CartItem existingMenu = items.get(menuId);
			existingMenu.setQuantity(existingMenu.getQuantity() + item.getQuantity());
			existingMenu.setSubTotal(existingMenu.getQuantity() * existingMenu.getPrice());
		}
		else 
		{
			items.put(menuId, item);
		}
 	}
	
	public void updateItem(int menuId, int quantity) 
	{
		if(items.containsKey(menuId))
		{
			if(quantity <= 0)
			{
				items.remove(menuId);
			}
			else 
			{
				CartItem cartItem = items.get(menuId);
				cartItem.setQuantity(quantity);
				cartItem.setSubTotal((quantity * cartItem.getPrice()));
			}
		}
 	}
	
	public void removeItem(int menuId) {
		items.remove(menuId);
	}
	
	public Map<Integer, CartItem> getItems() {
		return items;
	}
	
	public void clear() {
		items.clear();
	}
	
	
	
	
	
	
	
	// Override the toString() method to display cart details
    @Override
    public String toString() {
        if (items.isEmpty()) {
            return "Cart is empty.";
        }
        StringBuilder sb = new StringBuilder("Cart Items:\n");
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            sb.append("Menu ID: ").append(entry.getKey())
              .append(", Item Details: ").append(entry.getValue())
              .append("\n");
        }
        return sb.toString();
    }
    
    
}
