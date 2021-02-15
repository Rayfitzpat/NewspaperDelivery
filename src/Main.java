import java.sql.*;
import java.util.Scanner;

public class Main {
    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    static Scanner in = new Scanner(System.in);



    public static void main(String[] args) throws SQLException {

        DBconnection.init_db();  // open the connection to the database

        int menuChoice = 0; // variable used to store main menu choice
        final int STOP_APP = 6; //value from menu that is used to quit the application
        DeliveryPersonDB dpm = new DeliveryPersonDB();
//        dpm.deliveryPersonMainPage();

        while (menuChoice != STOP_APP) {
            displayMainMenu(); //display the primary menu
            if (in.hasNextInt()) {
                //get the menu choice from the user
                menuChoice = in.nextInt();

                switch (menuChoice) {
//                    case 1:
//                        customer(stmt); //The code for this method is already done for you below
//                        break;
                    case 2:
                        dpm.deliveryPersonMainPage();  //You need to code this method below
                        break;
//                    case 3:
//                        deliveryArea(stmt); //You need to code this method below
//                        break;
//                    case 4:
//                        publications(stmt); //You need to code this method below
//                        break;
//                    case 5:
//                        order(stmt); //You need to code this method below
//                        break;
//                    case 6:
//                        invoice(stmt); //You need to code this method below
//                        break;
//                    case 7:
//                        customerSupport(stmt); //You need to code this method below
//                        break;
//                    case 8:
//                        System.out.println("Program is closing...");
//                        cleanup_resources();  // close the connection to the database when finished program
//                        break;
//                    default:
//                        System.out.println("You entered an invalid choice, please try again...");
                }
            } else {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an invalid choice, please try again...");
            }
        }
    }
                    public static void displayMainMenu()
                    {
                        System.out.println("\nMain Menu");
                        System.out.println("1: Customers");
                        System.out.println("2: Delivery Person");
                        System.out.println("3: Delivery Area");
                        System.out.println("4: Publications ");
                        System.out.println("5: Order");
                        System.out.println("6: Invoice");
                        System.out.println("7: Customer Support");
                        System.out.println("8: Exit Application\n");
                        System.out.print("Enter your choice: ");
                    }
                }
