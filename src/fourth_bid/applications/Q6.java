package fourth_bid.applications;

import fourth_bid.console.Login;
import fourth_bid.console.Menu;

import java.io.IOException;
import java.sql.*;

public class Q6 {
    //En kundlista på alla kunder som köpt något, samt vad deras totala ordervärde är.

    public void listCustomerValue() throws IOException, SQLException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            Login database = new Login();
            database.login();

            con = database.conn;

            stm = con.createStatement();
            stm.executeQuery("SELECT * FROM CustomersValue ");
            rs = stm.getResultSet();

            while (rs.next()) {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String totalvalue = rs.getString("TotalValue");

                System.out.println("First name: " + firstName + " Last name: " + lastName + " Total value: " + totalvalue);
            }

        }catch (SQLException e){
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