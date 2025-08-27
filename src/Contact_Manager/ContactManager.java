import java.sql.*;
import java.util.Scanner;

public class ContactManager {

    // Connect to Oracle Database
    private static Connection connectDB() {
        try {
            String url = "jdbc:oracle:thin:@192.168.1.3:1521:orcl";
            String user = "scott";        // 🔑 your Oracle username
            String password = "tiger";    // 🔑 your Oracle password
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("X Database connection failed.");
            return null;
        }
    }

    // Save Contact
    private static void saveContact(String name, String email, String phone) {
        try (Connection conn = connectDB()) {
            if (conn == null) return;
            String sql = "INSERT INTO contacts (name, email, phone) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.executeUpdate();
            System.out.println("Contact saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("X Error saving contact.");
        }
    }

    // Delete Contact
    private static void deleteContact(String nameToDelete) {
        try (Connection conn = connectDB()) {
            if (conn == null) return;
            String sql = "DELETE FROM contacts WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nameToDelete);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Contact deleted successfully!");
            } else {
                System.out.println("Contact not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("X Error deleting contact.");
        }
    }

    // View Contacts
    private static void viewContacts() {
        try (Connection conn = connectDB()) {
            if (conn == null) return;
            String sql = "SELECT * FROM contacts";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            boolean hasData = false;
            System.out.println("\n Contact List:");
            while (rs.next()) {
                hasData = true;
                System.out.println(
                    "Name: " + rs.getString("name") +
                    " | Email: " + rs.getString("email") +
                    " | Phone: " + rs.getString("phone")
                );
            }
            if (!hasData) {
                System.out.println(" No contacts available.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("X Error retrieving contacts.");
        }
    }

    // Main Menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Contact Manager =====");
            System.out.println("1. Save Contact");
            System.out.println("2. Delete Contact");
            System.out.println("3. View Contacts");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();
                    saveContact(name, email, phone);
                    break;

                case 2:
                    System.out.print("Enter Name to Delete: ");
                    String delName = sc.nextLine();
                    deleteContact(delName);
                    break;

                case 3:
                    viewContacts();
                    break;

                case 4:
                    System.out.println("Exiting Contact Manager...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
