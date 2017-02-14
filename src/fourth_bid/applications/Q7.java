package fourth_bid.applications;

import fourth_bid.console.Login;
import fourth_bid.console.Menu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Q7 {
    //Vad den totala provisionen är per månad.

    public void totalComissionPerMonth() throws IOException, SQLException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            Login database = new Login();
            database.login();

            con = database.conn;

            stm = con.createStatement();
            stm.executeQuery("SELECT * FROM TotalCommissionPerMonth");
            rs = stm.getResultSet();

            while (rs.next()) {
                String month = rs.getString("Month");
                String commission = rs.getString("TotalProvision");

                switch(month) {
                    case "1":
                        month = "January";
                        break;
                    case "2":
                        month = "Febuary";
                        break;
                    case "3":
                        month = "March";
                        break;
                    case "4":
                        month = "April";
                        break;
                    case "5":
                        month = "May";
                        break;
                    case "6":
                        month = "June";
                        break;
                    case "7":
                        month = "July";
                        break;
                    case "8":
                        month = "August";
                        break;
                    case "9":
                        month = "September";
                        break;
                    case "10":
                        month = "October";
                        break;
                    case "11":
                        month = "November";
                        break;
                    case "12":
                        month = "December";
                        break;
                }

                System.out.println("For " + month + " the total commission were: " + commission);

            }
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