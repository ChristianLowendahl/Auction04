package fourth_bid.applications;

import fourth_bid.console.Login;
import fourth_bid.console.Menu;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Q5 {
    public void finishedAuctions() throws IOException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Start date: ");
        String startDate = scanner.nextLine();
        System.out.println("End date: ");
        String endDate = scanner.nextLine();

        try {
            Login database = new Login();
            database.login();

            con = database.conn;

            stm = con.prepareStatement("SELECT Auction.ID, Auction.StartDate, Auction.EndDate, Product.Name, Product.Provision " +
                    "FROM Auction INNER JOIN Product ON Auction.ProductID = Product.ID " +
                    "WHERE StartDate > ? AND EndDate < ?");

            stm.setString(1, startDate);
            stm.setString(2, endDate);
            rs = stm.executeQuery();

            while(rs.next()) {
                String id = rs.getString("ID");
                String sDate = rs.getString("StartDate");
                String eDate = rs.getString("EndDate");
                String productName = rs.getString("Name");
                String provision = rs.getString("Provision");

                System.out.println("ID: " + id + " Start Date: " + sDate + " End Date: " + eDate +
                        " Product: " + productName + " Commission " + provision);

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
