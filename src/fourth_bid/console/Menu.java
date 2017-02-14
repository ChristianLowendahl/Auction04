package fourth_bid.console;

import java.sql.SQLException;
import fourth_bid.applications.*;

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
                    System.exit(0);
                    break;
                case 3:
                    System.exit(0);
                    break;
                case 4:
                    Q4 q4 = new Q4();
                    q4.run();
                    break;
                case 5:
                    System.exit(0);
                    break;
                case 6:
                    System.exit(0);
                    break;
                case 7:
                    System.exit(0);
                    break;
                case 8:
                    System.exit(0);
                    break;
                case 9:
                    System.exit(0);
                    break;
                case 10:
                    System.exit(0);
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
        System.out.println("[2] Question 2.");
        System.out.println("[3] Question 3.");
        System.out.println("[4] List bids.");
        System.out.println("[5] Question 5.");
        System.out.println("[6] Question 6.");
        System.out.println("[7] Question 7.");
        System.out.println("[8] Question 8.");
        System.out.println("[9] Question 9.");
        System.out.println("[10] Question 10.");


        System.out.print("\nChoose a [number]: ");
    }

    private void printGoBackMenu(){
        System.out.println("\n***************************************\n");

        System.out.println("Write 'exit' to finish.");
        System.out.println("Write 'menu' for top menu.");
        System.out.print("Write here: ");
    }

}