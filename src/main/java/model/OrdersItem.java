package model; 

public class OrdersItem 
{
	private int ordersItemId;
	private int ordersId;
	private int menuId;
	private int quantity;
	private float ItemTotal;
	public int getOrdersItemId() {
		return ordersItemId;
	}
	public void setOrdersItemId(int ordersItemId) {
		this.ordersItemId = ordersItemId;
	}
	public int getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getItemTotal() {
		return ItemTotal;
	}
	public void setItemTotal(float itemTotal) {
		ItemTotal = itemTotal;
	}
	public OrdersItem(int ordersItemId, int ordersId, int menuId, int quantity, float itemTotal) {
		super();
		this.ordersItemId = ordersItemId;
		this.ordersId = ordersId;
		this.menuId = menuId;
		this.quantity = quantity;
		ItemTotal = itemTotal;
	}
	public OrdersItem(int ordersId, int menuId, int quantity, float itemTotal) {
		super();
		this.ordersId = ordersId;
		this.menuId = menuId;
		this.quantity = quantity;
		ItemTotal = itemTotal;
	}
	public OrdersItem() {
		super();
	}
	@Override
	public String toString() {
		return "OrdersItem [ordersItemId=" + ordersItemId + ", ordersId=" + ordersId + ", menuId=" + menuId
				+ ", quantity=" + quantity + ", ItemTotal=" + ItemTotal + "]";
	}
	
 	 
	
}
