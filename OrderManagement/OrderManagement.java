
import java.sql.*;
import java.util.Scanner;

public class OrderManagement {

    static final String URL = "jdbc:mysql://localhost:3306/ecommerce_db";
    static final String USER = "root";
    static final String PASSWORD = "Mysql@2025";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Database Connected Successfully!");

            int choice;

            do {
                System.out.println("\n===== ORDER MANAGEMENT SYSTEM =====");
                System.out.println("1. Insert Three Orders");
                System.out.println("2. Search Orders by Status");
                System.out.println("3. Increase Order Quantity");
                System.out.println("4. Update Order Status");
                System.out.println("5. Total Order Value and Delete Cancelled Order");
                System.out.println("0. Exit");

                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {

                    // A) Insert three orders
                    case 1:

                        String insertQuery = "INSERT INTO Orders VALUES (?, ?, ?, ?, ?, ?)";

                        PreparedStatement ps1 = con.prepareStatement(insertQuery);

                        for (int i = 1; i <= 3; i++) {

                            System.out.println("\nEnter Order " + i + " Details:");

                            System.out.print("Order ID: ");
                            int id = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Customer Name: ");
                            String customer = sc.nextLine();

                            System.out.print("Product Name: ");
                            String product = sc.nextLine();

                            System.out.print("Quantity: ");
                            int quantity = sc.nextInt();

                            System.out.print("Unit Price: ");
                            double price = sc.nextDouble();
                            sc.nextLine();

                            System.out.print("Status: ");
                            String status = sc.nextLine();

                            Order order = new Order(id, customer, product,
                                    quantity, price, status);

                            ps1.setInt(1, order.getOrderId());
                            ps1.setString(2, order.getCustomerName());
                            ps1.setString(3, order.getProductName());
                            ps1.setInt(4, order.getQuantity());
                            ps1.setDouble(5, order.getUnitPrice());
                            ps1.setString(6, order.getStatus());

                            try {
                                ps1.executeUpdate();
                                System.out.println("Order inserted successfully!");
                            } catch (SQLException e) {
                                if ("23000".equals(e.getSQLState())) {
                                    System.out.println("Duplicate Order ID! Cannot insert.");
                                } else {
                                    System.out.println("Error: " + e.getMessage());
                                }
                            }
                        }

                        ps1.close();
                        break;

                    // B) Search by status and quantity > 2
                    case 2:

                        sc.nextLine();

                        System.out.print("Enter Status: ");
                        String searchStatus = sc.nextLine();

                        String searchQuery = "SELECT * FROM Orders WHERE status = ? " +
                                "AND quantity > 2 ORDER BY unit_price DESC";

                        PreparedStatement ps2 = con.prepareStatement(searchQuery);
                        ps2.setString(1, searchStatus);

                        ResultSet rs2 = ps2.executeQuery();

                        boolean found = false;

                        while (rs2.next()) {

                            found = true;

                            Order order = new Order(
                                    rs2.getInt("order_id"),
                                    rs2.getString("customer_name"),
                                    rs2.getString("product_name"),
                                    rs2.getInt("quantity"),
                                    rs2.getDouble("unit_price"),
                                    rs2.getString("status"));

                            System.out.println("\n--------------------");
                            order.displayOrder();
                        }

                        if (!found) {
                            System.out.println("No matching orders found.");
                        }

                        rs2.close();
                        ps2.close();
                        break;

                    // C) Increase quantity
                    case 3:

                        System.out.print("Enter Order ID: ");
                        int orderId = sc.nextInt();

                        System.out.print("Enter Additional Quantity: ");
                        int additionalQty = sc.nextInt();

                        if (additionalQty <= 0) {
                            System.out.println("Quantity must be positive.");
                            break;
                        }

                        String quantityQuery = "UPDATE Orders SET quantity = quantity + ? WHERE order_id = ?";

                        PreparedStatement ps3 = con.prepareStatement(quantityQuery);

                        ps3.setInt(1, additionalQty);
                        ps3.setInt(2, orderId);

                        int rows = ps3.executeUpdate();

                        if (rows > 0) {
                            System.out.println("Quantity updated successfully!");
                        } else {
                            System.out.println("Order does not exist.");
                        }

                        ps3.close();
                        break;

                    // D) Update valid status
                    case 4:

                        System.out.print("Enter Order ID: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter New Status: ");
                        String newStatus = sc.nextLine();

                        if (!newStatus.equalsIgnoreCase("Pending") &&
                                !newStatus.equalsIgnoreCase("Shipped") &&
                                !newStatus.equalsIgnoreCase("Delivered")) {

                            System.out.println("Invalid Status!");
                            break;
                        }

                        String checkQuery = "SELECT * FROM Orders WHERE order_id = ?";

                        PreparedStatement ps4 = con.prepareStatement(checkQuery);
                        ps4.setInt(1, updateId);

                        ResultSet rs4 = ps4.executeQuery();

                        if (rs4.next()) {

                            rs4.close();
                            ps4.close();

                            String updateQuery = "UPDATE Orders SET status = ? WHERE order_id = ?";

                            PreparedStatement ps5 = con.prepareStatement(updateQuery);

                            ps5.setString(1, newStatus);
                            ps5.setInt(2, updateId);
                            ps5.executeUpdate();

                            ps5.close();

                            String displayQuery = "SELECT * FROM Orders WHERE order_id = ?";

                            PreparedStatement ps6 = con.prepareStatement(displayQuery);
                            ps6.setInt(1, updateId);

                            ResultSet rs5 = ps6.executeQuery();

                            if (rs5.next()) {

                                Order order = new Order(
                                        rs5.getInt("order_id"),
                                        rs5.getString("customer_name"),
                                        rs5.getString("product_name"),
                                        rs5.getInt("quantity"),
                                        rs5.getDouble("unit_price"),
                                        rs5.getString("status"));

                                System.out.println("\nUpdated Order Details:");
                                order.displayOrder();
                            }

                            rs5.close();
                            ps6.close();

                        } else {
                            System.out.println("Order does not exist.");
                            rs4.close();
                            ps4.close();
                        }

                        break;

                    // E) Total value and delete cancelled order
                    case 5:

                        String totalQuery = "SELECT SUM(quantity * unit_price) AS total_value FROM Orders";

                        PreparedStatement ps7 = con.prepareStatement(totalQuery);
                        ResultSet rs6 = ps7.executeQuery();

                        if (rs6.next()) {
                            System.out.println("Total Order Value: " +
                                    rs6.getDouble("total_value"));
                        }

                        rs6.close();
                        ps7.close();

                        System.out.print("Enter Order ID to delete: ");
                        int deleteId = sc.nextInt();

                        String deleteQuery = "DELETE FROM Orders WHERE order_id = ? AND status = 'Cancelled'";

                        PreparedStatement ps8 = con.prepareStatement(deleteQuery);
                        ps8.setInt(1, deleteId);

                        int deleted = ps8.executeUpdate();

                        if (deleted > 0) {
                            System.out.println("Cancelled order deleted successfully!");
                        } else {
                            System.out.println("Order cannot be deleted. " +
                                    "It does not exist or status is not Cancelled.");
                        }

                        ps8.close();
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                }

            } while (choice != 0);

            con.close();
            sc.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
