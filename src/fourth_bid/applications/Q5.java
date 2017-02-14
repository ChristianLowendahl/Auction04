package fourth_bid.applications;

import fourth_bid.console.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Q5 {
    //Vilka auktioner avslutas under ett visst datumintervall? Samt vad blir
    //provisionen för varje auktion inom det intervallet?

    public void finishedAuctions() {
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
                    "WHERE StartDate = ? AND EndDate = ?");
            stm.setString(1, startDate);
            stm.setString(2, endDate);
            rs = stm.executeQuery();

            while (rs.next()) {
                String id = rs.getString("ID");
                String sDate = rs.getString("StartDate");
                String eDate = rs.getString("EndDate");
                String productName = rs.getString("Product.Name");
                String provision = rs.getString("Product.Provision");

                System.out.println(id + sDate + eDate + productName + provision);
            }
        }catch (SQLException e){

        }

    }

}
