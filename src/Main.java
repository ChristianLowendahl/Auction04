import fourth_bid.console.Login;
import fourth_bid.console.UserVerifier;

import java.io.IOException;
import java.sql.SQLException;

public class Main  {

    public static void main(String[] args) throws IOException, SQLException {
        System.out.println("Hello Fourth Bid!");

        Login database = new Login();
        database.login();

        UserVerifier verifier = new UserVerifier();
        verifier.run();

        // HEJ

    }
}
