package fourth_bid.console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Login {

    private static String user = "root";
    private static String pass = "nack";
    private static String host = "localhost";
    private static String database = "Auction";
    public static Connection conn;

    public void login() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load the driver");
        }

         conn = DriverManager.getConnection
                ("jdbc:mysql://" + host + ":3306/" + database +"?useSSL=false", user, pass);
    }
}