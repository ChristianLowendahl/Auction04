package fourth_bid.console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Login {

    public  Connection conn;

    public void login() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load the driver!");
        }

        String user = "root";
        String pass = "ilu";
        String host = "localhost";
        String database = "Auction";

         conn = DriverManager.getConnection
                ("jdbc:mysql://" + host + ":3306/" + database +"?useSSL=false", user, pass);
    }
}