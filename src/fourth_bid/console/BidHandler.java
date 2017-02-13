package fourth_bid.console;


import java.io.IOException;
import java.sql.*;

public class BidHandler {

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

        System.out.println("Write 'all' to show all bids.");
        System.out.println("Write 'higher' to show only the higher bids.");
        System.out.println("Write 'menu' to go back to menu.");
        System.out.print("Skriv här: ");

    }

    private void listAllBid() throws IOException, SQLException {

        Statement stm = null;
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Auction?useSSL=false", "root", "nack"))
        {

            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM AllBid;");

            while (rs.next()){
                int id = rs.getInt("ID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String product = rs.getString("Product");
                String higherBid = rs.getString("HigherBid");

                System.out.println(firstName + "\t" + lastName + "\t\t\t" + product  + "\t\t\t" + higherBid + "\n");
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

            Menu menu = new Menu();
            menu.goBackToMenu();
        }
    }


    private void listHigherBid() throws IOException, SQLException {

        Statement stm = null;
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Auction?useSSL=false", "root", "nack"))
        {

            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM HigherBid");

            while (rs.next()){
                int id = rs.getInt("ID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String product = rs.getString("Product");
                String higherBid = rs.getString("HigherBid");

                System.out.println(firstName + "\t" + lastName + "\t\t\t" + product  + "\t\t\t" + higherBid + "\n");
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

            Menu menu = new Menu();
            menu.goBackToMenu();
        }
    }
}
