package fourth_bid;

import fourth_bid.console.*;

import java.io.IOException;
import java.sql.SQLException;

public class Main  {

    public static void main(String[] args) throws IOException, SQLException {
        System.out.println("Hello Fourth Bid!");

        Program program = new Program();
        program.start();


    }
}
