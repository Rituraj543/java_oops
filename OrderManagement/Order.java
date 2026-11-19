
public class Order {

    private int orderId;
    private String customerName;
    private String productName;
    private int quantity;
    private double unitPrice;
    private String status;

    public Order(int orderId, String customerName,
            String productName, int quantity,
            double unitPrice, String status) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getStatus() {
        return status;
    }

    public void displayOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Product Name: " + productName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Unit Price: " + unitPrice);
        System.out.println("Status: " + status);
    }
}
