package fourth_bid.console;

import fourth_bid.models.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


public class Loader {

    public void loadAllData() throws IOException, SQLException {


        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Auction> auctions = new ArrayList<>();
        ArrayList<Bid> bids = new ArrayList<>();
        ArrayList<AuctionHistory> auctionHistories = new ArrayList<>();

        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;

        try {

            Login database = new Login();
            database.login();


            con = database.conn;

            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Customer;");

            while (rs.next())
                customers.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(7)));

            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Supplier;");

            while (rs.next())
                suppliers.add(new Supplier(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Product;");

            while (rs.next())
                products.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));

            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Auction;");

            while (rs.next())
                auctions.add(new Auction(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6)));

            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Bid;");

            while (rs.next())
                bids.add(new Bid(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6)));

            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM AuctionHistory;");

            while (rs.next())
                auctionHistories.add(new AuctionHistory(rs.getInt(1), rs.getDouble(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7)));

            // SKAPA RELATIONER:
            //System.out.println("**********************");

            for (Customer i : customers){
                //System.out.println(i);
            }

            for (Supplier i : suppliers){
                //System.out.println(i);
            }

            for (Product i : products){
                i.setRelation(suppliers);
                //System.out.println(i);
            }

            for (Auction i : auctions){
                i.setRelation(products);
                //System.out.println(i);
            }

            for (Bid i : bids){
                i.setRelation(auctions, customers);
                //System.out.println(i);
            }

            for (AuctionHistory i : auctionHistories){
                i.setRelation(auctions, products, customers);
                //System.out.println(i);
            }

            System.out.println("**********************");

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
}