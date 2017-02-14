package fourth_bid.console;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserVerifier {

    ArrayList<User> users = new ArrayList<>();

    public void run() throws IOException, SQLException {
        userLogin();
    }

    private void userLogin() throws IOException, SQLException {
        loadUsers();

        Scanner sc = new Scanner(System.in);
        System.out.print("Write your user name: ");
        String userName = sc.nextLine();
        System.out.print("Write your password: ");
        String password = sc.nextLine();

        for (User i : users){
            if (i.getUserName().equals(userName) && i.getPassword().equals(password)){
                System.out.println("Welcome to Fourth Bid!");

                Loader loader = new Loader();
                loader.loadAllData();

                Menu menu = new Menu();
                menu.welcome();
            } else if (!i.getUserName().equals(userName) || !i.getPassword().equals(password)){
                System.out.println("User not found!");
                run();
            }
        }

    }

    public void loadUsers() throws IOException, SQLException {

        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;

        Login database = new Login();
        database.login();

        try {
            con = database.conn;

            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM User;");

            while (rs.next())
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));

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
