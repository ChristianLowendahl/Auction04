package fourth_bid.applications;

import fourth_bid.console.Login;

import java.sql.*;

public class Q6 {
    //En kundlista på alla kunder som köpt något, samt vad deras totala ordervärde är.


    public void listCustomerValue() {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            Login database = new Login();
            database.login();

            con = database.conn;

            stm = con.createStatement();
            stm.executeQuery("SELECT * FROM CustomersValue");
            rs = stm.getResultSet();

            while(rs.next()) {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String totalvalue = rs.getString("TotalValue");
                System.out.println(firstName + lastName + totalvalue);
            }
        }catch (SQLException e){

        }

    }
}
