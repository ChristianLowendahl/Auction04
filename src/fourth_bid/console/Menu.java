package fourth_bid.console;

import java.sql.SQLException;
import fourth_bid.applications.*;
import fourth_bid.applications.Q8;

public class Menu {

    public void welcome() throws java.io.IOException, SQLException {

        while (true) {

            printTopMenu();

            int i = In.inInt();
            switch (i) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    Q1 q1 = new Q1();
                    q1.addSupplier();
                    break;
                case 2:
                    Q2 q2 = new Q2();
                    q2.addCustomer();
                    break;
                case 3:
                    Q3 q3 = new Q3();
                    q3.run();
                    break;
                case 4:
                    Q4 q4 = new Q4();
                    q4.run();
                    break;
                case 5:
                    Q5 q5 = new Q5();
                    q5.finishedAuctions();
                    break;
                case 6:
                    Q6 q6 = new Q6();
                    q6.listCustomerValue();
                    break;
                case 7:
                    Q7 q7 = new Q7();
                    q7.totalComissionPerMonth();
                    break;
                case 8:
                    Q8 q8 = new Q8();
                    q8.run();
                    break;
                case 10:
                    UserVerifier verifier = new UserVerifier();
                    verifier.addUser();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public void goBackToMenu() throws java.io.IOException, SQLException {
        while (true) {

            printGoBackMenu();

            String  str = In.inText();
            switch (str) {
                case "exit":
                    System.exit(0);
                    break;
                case "menu":
                    welcome();
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    private void printTopMenu(){

        System.out.println("[0] To exit.");
        System.out.println("[1] Add new supplier.");
        System.out.println("[2] Add new customer.");
        System.out.println("[3] Add suppliers auction.");
        System.out.println("[4] List bids.");
        System.out.println("[5] Choose auctions by date.");
        System.out.println("[6] Active customers.");
        System.out.println("[7] Commissions per month.");
        System.out.println("[8] Bidding History by auction.");
        System.out.println("[10] Add new user.");

        System.out.print("\nChoose a [number]: ");
    }

    private void printGoBackMenu(){
        System.out.println("\n***************************************\n");

        System.out.println("Write 'exit' to finish.");
        System.out.println("Write 'menu' for top menu.");
        System.out.print("Write here: ");
    }
}