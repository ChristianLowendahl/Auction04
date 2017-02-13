package fourth_bid.console;


import java.sql.SQLException;

public class Menu {

    public void welcome() throws java.io.IOException, SQLException {

        while (true) {

            printTopMenu();

            int tal = In.inInt();
            switch (tal) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    SupplierHandler handler = new SupplierHandler();
                    handler.addSupplier();
                    break;
                case 2:
                    System.exit(0);
                    break;
                case 3:
                    System.exit(0);
                    break;
                case 4:
                    System.exit(0);
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
                    System.out.println("Felaktigt menyval");
            }
        }

    }


    public void goBackToMenu() throws java.io.IOException, SQLException {
        while (true) {

            printGoBackMenu();

            String  str = In.inText();
            switch (str) {
                case "avsluta":
                    System.exit(0);
                    break;
                case "menu":
                    welcome();
                    break;
                default:
                    System.out.println("Felaktigt menyval");
            }
        }
    }

    private void printTopMenu(){

        System.out.println("[1] Add new supplier.\t\t\t\t[7] fråga 1.");
        System.out.println("[2] fråga 2.\t\t\t\t[8] fråga 2.");
        System.out.println("[3] fråga 3.\t\t\t\t[9] fråga 3.");
        System.out.println("[4] fråga 4.\t\t\t\t[10] fråga 4.");
        System.out.println("[5] fråga 5.");
        System.out.println("[6] fråga 6.\t\t\t\t[0] Avsluta programmet.");

        System.out.print("\nVälj ett [tal]: ");
    }

    private void printGoBackMenu(){
        System.out.println("\n***************************************\n");

        System.out.println("Skriv 'avsluta' för avsluta programmet.");
        System.out.println("Skriv 'menu' för top menu.");
        System.out.print("Skriv här: ");
    }

}