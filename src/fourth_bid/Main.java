package fourth_bid;

import java.io.IOException;
import java.sql.SQLException;

public class Main  {

    public static void main(String[] args) throws IOException, SQLException {
        System.out.println("Hello Fourth Bid!");

        Loader loader = new Loader();
        loader.loadAllData();

        // HEJ

    }
}
