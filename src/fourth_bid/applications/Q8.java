package fourth_bid.applications;

import fourth_bid.console.Login;
import fourth_bid.console.Menu;
import fourth_bid.models.Auction;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Q8 {

    Scanner sc = new Scanner(System.in);
    private String input;

    private ArrayList<Auction> auctions = new ArrayList<>();

    private int indexAuction = 0;

    public void run() throws IOException, SQLException {
        listAuctions();
    }


    private void listAuctions() throws IOException, SQLException {

        loadAllActions();

        System.out.println("\n***************************************\n");

        int i = 0;
        for (Auction a : auctions) {
            i++;
            System.out.println("[" + i + "]\t\t" + "Auctions final date: " +  a.getEndDate());
        }

        System.out.print("\nChoose a number between [1] and [" + auctions.size() + "]\n" +
                "to select a auction: ");

        input = sc.nextLine();
        indexAuction = Integer.parseInt(input);

        showBidHistoryByAuction();
    }



    private void loadAllActions() throws IOException, SQLException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            Login database = new Login();
            database.login();
            con = database.conn;

            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Auction;");

            while (rs.next())
                auctions.add(new Auction(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6)));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stm != null)
                    stm.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    private void showBidHistoryByAuction() throws IOException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            Login database = new Login();
            database.login();
            con = database.conn;

            stm = con.prepareStatement("SELECT BidDate AS Date, BidTime AS Time, Price, Customer.FirstName AS 'First name', Customer.LastName AS 'Last name' FROM Bid\n" +
                    "  INNER JOIN Customer ON Bid.CustomerID = Customer.ID\n" +
                    "WHERE AuctionID = ?;");

            stm.setInt(1, auctions.get(indexAuction-1).getId());

            rs = stm.executeQuery();

            while(rs.next()) {
                String date = rs.getString(1);
                String time = rs.getString(2);
                double price = rs.getDouble(3);
                String  firstName = rs.getString(4);
                String lastName = rs.getString(5);

                System.out.println("\n***************************************\n");
                System.out.println("Customer: " + firstName + " " + lastName + "\n" +
                                    date + " " + time + "\t" + price);

                }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stm != null)
                    stm.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Menu menu = new Menu();
            menu.goBackToMenu();
        }

    }
}