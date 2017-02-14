package fourth_bid.console;


import java.io.IOException;
import java.sql.SQLException;

public class Program implements Runnable {

    public void start() throws SQLException, IOException {

        Login database = new Login();
        database.login();

        UserVerifier verifier = new UserVerifier();
        verifier.run();

    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.currentThread().setName("Program");

                Login database = new Login();
                database.login();

                Loader loader = new Loader();
                loader.loadAllData();

                Menu menu = new Menu();
                menu.welcome();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
