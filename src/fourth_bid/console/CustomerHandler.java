package fourth_bid.console;

import java.sql.*;
import java.util.Scanner;

public class CustomerHandler {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public void addCustomer() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("First name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Phone: ");
        String phone = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Address: ");
        String address = scanner.nextLine();
        System.out.println("City: ");
        String city = scanner.nextLine();

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Auction004?useSSL=false","root", "lpko");

        preparedStatement = connection.prepareStatement("INSERT INTO Customer (FirstName, LastName, Phone, Email, Address, City) VALUES (?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, phone);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, address);
        preparedStatement.setString(6, city);

        int rows = preparedStatement.executeUpdate();

        if (rows == 1)
            System.out.println("Customer added!");
        else
            System.out.println("Customer could not be added!");

    }
}
