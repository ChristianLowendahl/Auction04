package fourth_bid.applications;

import fourth_bid.console.Login;
import fourth_bid.console.Menu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Q2 {

    public void addCustomer() throws IOException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("First name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Phone: ");
        String phone = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Address: ");
        String address = scanner.nextLine();
        System.out.println("City: ");
        String city = scanner.nextLine();

        try {

            Login database = new Login();
            database.login();

            con = database.conn;

            stm = con.prepareStatement("{CALL AddCustomer(?, ?, ?, ?, ?, ?)}");
            stm.setString(1, firstName);
            stm.setString(2, lastName);
            stm.setString(3, phone);
            stm.setString(4, email);
            stm.setString(5, address);
            stm.setString(6, city);

            int rows = stm.executeUpdate();
            if (rows == 1)
                System.out.println("Customer successfully added!");
            else
                System.out.println("Customer not added!");


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (stm != null)
                try {
                    stm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if( con != null)
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            Menu menu = new Menu();
            menu.goBackToMenu();
        }
    }
}