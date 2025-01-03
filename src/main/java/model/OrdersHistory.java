package model; 

public class OrdersHistory
{
    private int ordersHistoryId;
    private int ordersId;
    private int uid;
    private int restaurantid;
    private Float total;
    private String status;

    // Default constructor
    public OrdersHistory() {
        super();
    }

    
    // Constructor with all fields
    public OrdersHistory(int ordersHistoryId, int ordersId, int uid, int restaurantid, Float total, String status) {
        super();
        this.ordersHistoryId = ordersHistoryId;
        this.ordersId = ordersId;
        this.uid = uid;
        this.restaurantid = restaurantid;
        this.total = total;
        this.status = status;
    }

    // Constructor without ordersHistoryId (for insertions)
    public OrdersHistory(int ordersId, int uid, int restaurantid, Float total, String status) {
        super();
        this.ordersId = ordersId;
        this.uid = uid;
        this.restaurantid = restaurantid;
        this.total = total;
        this.status = status;
    }

    // Getters and Setters
    public int getOrdersHistoryId() {
        return ordersHistoryId;
    }

    public void setOrdersHistoryId(int ordersHistoryId) {
        this.ordersHistoryId = ordersHistoryId;
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

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrdersHistory [ordersHistoryId=" + ordersHistoryId + ", ordersId=" + ordersId + ", uid=" + uid
                + ", restaurantid=" + restaurantid + ", total=" + total + ", status=" + status + "]";
    }
}
