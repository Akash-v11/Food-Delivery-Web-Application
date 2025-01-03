package model; 

 
import java.time.LocalDateTime;

public class Orders
{
	private int ordersId;
	private int uid;
	private int restaurantid;
	private int menuid;
	private int quantity;
	private Float totalAmount;
	private String paymentType;
	private LocalDateTime dateAndtime;
	private String status;
	 
	public Orders() {
		super();
	}
 
	public Orders(int ordersId, int uid, int restaurantid, int menuid, int quantity, Float totalAmount,
			String paymentType, LocalDateTime dateAndtime, String status) {
		super();
		this.ordersId = ordersId;
		this.uid = uid;
		this.restaurantid = restaurantid;
		this.menuid = menuid;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.paymentType = paymentType;
		this.dateAndtime = dateAndtime;
		this.status = status;
	}
	
 
	public Orders(int uid, int restaurantid, int menuid, int quantity, Float totalAmount, String paymentType,
			LocalDateTime dateAndtime, String status) {
		super();
		this.uid = uid;
		this.restaurantid = restaurantid;
		this.menuid = menuid;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.paymentType = paymentType;
		this.dateAndtime = dateAndtime;
		this.status = status;
	}

	 

	public int getOrdersId() {
		return ordersId;
	}


	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public int getRestaurantid() {
		return restaurantid;
	}


	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}


	public int getMenuid() {
		return menuid;
	}


	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Float getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getPaymentType() {
		return paymentType;
	}


	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}


	public LocalDateTime getDateAndtime() {
		return dateAndtime;
	}


	public void setDateAndtime(LocalDateTime dateAndtime) {
		this.dateAndtime = dateAndtime;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Orders [ordersId=" + ordersId + ", uid=" + uid + ", restaurantid=" + restaurantid + ", menuid=" + menuid
				+ ", quantity=" + quantity + ", totalAmount=" + totalAmount + ", paymentType=" + paymentType
				+ ", dateAndtime=" + dateAndtime + ", status=" + status + "]";
	}


	 
	
	
	
	 
}
