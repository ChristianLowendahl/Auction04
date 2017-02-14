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

    public void addUser() throws IOException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("Write user's name: ");
        String userName = sc.nextLine();
        System.out.print("Write user's email: ");
        String userEmail = sc.nextLine();
        System.out.print("Write a password (4 characters): ");
        String password = sc.nextLine();

        try {

            Login database = new Login();
            database.login();

            con = database.conn;

            stm = con.prepareStatement("{CALL AddUser(?, ?, ?)}");
            stm.setString(1, userName);
            stm.setString(2, userEmail);
            stm.setString(3, password);

            int rows = stm.executeUpdate();
            if (rows == 1)
                System.out.println("User successfully added!");
            else
                System.out.println("User not added!");


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
}
