package fourth_bid.applications;


import fourth_bid.models.Auction;
import fourth_bid.models.Product;
import fourth_bid.models.Supplier;
import fourth_bid.console.Login;
import fourth_bid.console.Menu;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Q3 {

    Scanner sc = new Scanner(System.in);
    private String input;

    private ArrayList<Supplier> suppliers = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();

    private int indexSupplier = 0;
    private int indexProduct = 0;

    private Auction newAuction;

    public void run() throws IOException, SQLException {
        listSuppliers();
    }


    private void listSuppliers() throws IOException, SQLException {

        loadAllSuppliers();

        System.out.println("\n***************************************\n");

        int i = 0;
        for (Supplier s : suppliers) {
            i++;
            System.out.println("[" + i + "]\t\t" + s.getName());
        }

        System.out.print("\nChose a number between [1] and [" + suppliers.size() + "]\n" +
                "to select a supplier: ");

        input = sc.nextLine();
        indexSupplier = Integer.parseInt(input);

        listProductsBySupplier();
    }

    private void listProductsBySupplier() throws IOException, SQLException {

        loadProductsBySupplier();

        int j = 0;
        for (Product p : products) {
            j++;
            System.out.println("[" + j + "]\t\t" + p.getName());
        }

        System.out.print("\nChose a number between [1] and [" + products.size() + "]\n" +
                "to select a product from supplier " + suppliers.get(indexSupplier-1).getName() + ": ");

        input = sc.nextLine();
        indexProduct = Integer.parseInt(input);

        prepareAuction();

    }

    private void prepareAuction() throws IOException, SQLException {

        System.out.print("\nStarting Bid: ");
        input = sc.nextLine();
        int startingBid = Integer.parseInt(input);

        System.out.print("Accept Offer: ");
        input = sc.nextLine();
        int acceptOffer = Integer.parseInt(input);

        System.out.print("Start Date(yyyy-mm-dd): ");
        String startDate = sc.nextLine();

        System.out.print("End Date(yyyy-mm-dd): ");
        String endDate = sc.nextLine();

        newAuction = new Auction(startingBid, acceptOffer, startDate, endDate, products.get(indexProduct-1).getId());


        addAuction();

    }

    private void addAuction() throws IOException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        //

        try {
            Login database = new Login();
            database.login();
            con = database.conn;

            stm = con.prepareStatement("{CALL AddAuction(?, ?, ?, ?, ?)}");
            stm.setInt(1, newAuction.getStartingBid());
            stm.setInt(2, newAuction.getAcceptOffer());
            stm.setString(3, newAuction.getStartDate());
            stm.setString(4, newAuction.getEndDate());
            stm.setInt(5, products.get(indexProduct-1).getId());

            int rows = stm.executeUpdate();
            if (rows == 1)
                System.out.println("Auction successfully added!");
            else
                System.out.println("Auction not added!");


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


    private void loadAllSuppliers() throws IOException, SQLException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            Login database = new Login();
            database.login();
            con = database.conn;

            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM Supplier;");

            while (rs.next())

                suppliers.add(new Supplier(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));


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

    private void loadProductsBySupplier() throws IOException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            Login database = new Login();
            database.login();
            con = database.conn;

            stm = con.prepareStatement("SELECT * FROM Product WHERE SupplierID = ?");

            stm.setInt(1, suppliers.get(indexSupplier-1).getId());

            rs = stm.executeQuery();

            while(rs.next()) {
                int productId = rs.getInt("ID");
                String productName = rs.getString("Name");
                String productDescription = rs.getString("Description");
                int provision = rs.getInt("Provision");
                int supplierID = rs.getInt("SupplierID");

                products.add(new Product(productId, productName, productDescription, provision, supplierID));
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

        }

    }
}