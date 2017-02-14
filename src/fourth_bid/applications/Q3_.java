package fourth_bid.applications;

import fourth_bid.console.Login;
import fourth_bid.console.Menu;
import fourth_bid.console.In;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Q3_ {

    public void run() throws IOException, SQLException {
        Menu menu = new Menu();
        System.out.println("\nAdd auction\n");
        loadSuppliers();
        loadProducts(In.inText());
        addAuction(In.inInt());
        menu.goBackToMenu();
    }

    public void addAuction(int productId) throws SQLException, IOException {
        PreparedStatement stm = null;
        Connection con = null;

        System.out.println("Starting Bid: ");
        int startingBid = In.inInt();
        System.out.println("Accept Offer: ");
        int acceptOffer = In.inInt();
        System.out.println("Start Date(yyyy-mm-dd):");
        String startDate = In.inText();
        System.out.println("End Date(yyyy-mm-dd):");
        String endDate = In.inText();

        try{

            Login database = new Login();
            database.login();
            con = database.conn;

            stm = con.prepareStatement("{CALL AddAuction(?, ?, ?, ?, ?)}");
            stm.setInt(1,startingBid);
            stm.setInt(2,acceptOffer);
            stm.setString(3,startDate);
            stm.setString(4,endDate);
            stm.setInt(5,productId);

            int rows = stm.executeUpdate();
            if (rows == 1)
                System.out.println("Auction successfully added!");
            else
                System.out.println("Auction not added!");

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(stm != null) {
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }

    public void loadSuppliers()throws SQLException{
        Statement stm = null;
        ResultSet rs = null;
        Connection con = null;

        try{
            Login database = new Login();
            database.login();
            con = database.conn;
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT Name FROM supplier");

            System.out.println("Enter one of the available suppliers:");

            int counter = 1;
            while(rs.next()) {

                String name = rs.getString("Name");

                System.out.println(counter + ": " + name);
                counter++;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(rs != null) {
                rs.close();
            }
            if(stm != null) {
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }

    public void loadProducts(String input) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Connection con = null;

        try{

            Login database = new Login();
            database.login();
            con = database.conn;

            stm = con.prepareStatement("select product.id, product.Name from product\n" +
                    "inner join supplier on product.supplierID = supplier.id\n" +
                    "where supplier.name LIKE (?)");
            stm.setString(1, "%" + input + "%");
            rs = stm.executeQuery();

            System.out.println("Enter the ID of the product you would like to add to the auction:");

            int counter = 1;
            while(rs.next()) {

                String id = rs.getString("ID");
                String name = rs.getString("Name");

                System.out.println("ID: " + id + " Name: " + name);
                counter++;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(rs != null) {
                rs.close();
            }
            if(stm != null) {
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }
}
