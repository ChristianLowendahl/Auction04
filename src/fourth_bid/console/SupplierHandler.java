package fourth_bid.console;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

//------------  Lägga in en ny leverantör  -------------------

public class SupplierHandler {

    public void addSupplier() throws IOException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("Write supplier's name: ");
        String name = sc.nextLine();
        System.out.print("Write supplier's email: ");
        String email = sc.nextLine();
        System.out.print("Write supplier's address: ");
        String address = sc.nextLine();
        System.out.print("Write supplier's city: ");
        String city = sc.nextLine();

        try {

            Login database = new Login();
            database.login();

            con = database.conn;

            stm = con.prepareStatement("{CALL AddSupplier(?, ?, ?, ?)}");
            stm.setString(1, name);
            stm.setString(2, email);
            stm.setString(3, address);
            stm.setString(4, city);

            int rows = stm.executeUpdate();
            if (rows == 1)
                System.out.println("Supplier successfully added!");
            else
                System.out.println("Supplier not added!");


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
