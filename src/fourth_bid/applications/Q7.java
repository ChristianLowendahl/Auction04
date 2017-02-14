package fourth_bid.applications;

import fourth_bid.console.Login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Q7 {
    //Vad den totala provisionen är per månad.

    public void totalComissionPerMonth() {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            Login database = new Login();
            database.login();

            con = database.conn;

            stm = con.createStatement();
            stm.executeQuery("SELECT * FROM TotalComissionPerMonth");
            rs = stm.getResultSet();

            while (rs.next()) {
                String month = rs.getString("Month");
                String comission = rs.getString("TotalProvision");
                System.out.println(month + comission);

            }
        } catch (SQLException e) {

        }
    }
}
