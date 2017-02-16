package fourth_bid.applications;


import fourth_bid.console.In;
import fourth_bid.console.Login;
import fourth_bid.console.Menu;

import java.io.IOException;
import java.sql.*;

public class Q4 {

    public void run() throws IOException, SQLException {
        subMenu();
    }

    public void subMenu() throws IOException, SQLException {
        printSubMenu();

        String  str = In.inText();
        switch (str) {
            case "all":
                listAllBid();
                break;
            case "higher":
                listHigherBid();
                break;
            case "menu":
                Menu menu = new Menu();
                menu.welcome();
                break;
            default:
                System.out.println("Invalid command!");
        }

    }


    private void printSubMenu(){
        System.out.println("\n***************************************\n");
        System.out.println("Write 'all' to show all bids.");
        System.out.println("Write 'higher' to show only the higher bids.");
        System.out.println("Write 'menu' to go back to menu.");
        System.out.print("Write here: ");

    }

    private void listAllBid() throws IOException, SQLException {

        System.out.println("\n***************************************\n");

        Statement stm = null;
        ResultSet rs = null;

        Login database = new Login();
        database.login();

        try (Connection con =  database.conn)
        {

            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM AllBid");

            while (rs.next()){
                //int id = rs.getInt("ID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String product = rs.getString("Product");
                String higherBid = rs.getString("HigherBid");



                System.out.println( "Customer: \t"  +  firstName + "\t" + lastName + "\n" +
                        "Product: \t" + product  + "\n" +
                        "Bid: \t" + higherBid + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            subMenu();
        }
    }


    private void listHigherBid() throws IOException, SQLException {

        System.out.println("\n***************************************\n");

        Statement stm = null;
        ResultSet rs = null;

        Login database = new Login();
        database.login();

        try (Connection con = database.conn)
        {

            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM HigherBid");

            while (rs.next()){
                int id = rs.getInt("ID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String product = rs.getString("Product");
                String higherBid = rs.getString("HigherBid");

                System.out.println( "Customer: \t"  +  firstName + "\t" + lastName + "\n" +
                        "Product: \t" + product  + "\n" +
                        "Higher Bid: \t" + higherBid + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            subMenu();
        }
    }
}