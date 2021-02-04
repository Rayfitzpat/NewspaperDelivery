import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryPersonMain {

    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    static Scanner in = new Scanner(System.in);

    public static void init_db() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/databaseGroupProject?useTimezone=true&serverTimezone=UTC";
            con = DriverManager.getConnection(url, "root", "admin");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error: Failed to connect to database\n" + e.getMessage());
        }
    }


    public static void main(String[] args) throws SQLException {
        init_db();  // open the connection to the database
        DeliveryPersonView dpv = new DeliveryPersonView();
        int menuChoice = 0; // variable used to store main menu choice
        final int STOP_APP = 7; //value from menu that is used to quit the application

//        dpv.displayAllDeliveryPerson(stmt);
//        dpv.displayDeliveryPerson(stmt);
//        dpv.deleteDeliveryPerson(stmt);
        //dpv.addNewDeliveryPerson(stmt);

       while (menuChoice != STOP_APP) {
            dpv.displayMainMenu(); //display the primary menu
            if (in.hasNextInt()) {
                //get the menu choice from the user
                menuChoice = in.nextInt();

                switch (menuChoice) {
                    case 1:
                        dpv.displayAllDeliveryPerson(stmt); //The code for this method is already done for you below
                        break;
                    case 2:
                        dpv.displayDeliveryPerson(stmt); //You need to code this method below
                        break;
                    case 3:
                        dpv.addNewDeliveryPerson(stmt); //You need to code this method below
                        break;
                   case 4:
                        dpv.editDeliveryPerson(stmt); //You need to code this method below
                        break;
                    case 5:
                        dpv.deleteDeliveryPerson(stmt); //You need to code this method below
                       break;
                    case 6:
                        System.out.println("Program is closing...");
                        dpv.cleanup_resources();  // close the connection to the database when finished program
                        break;
                    default:
                        System.out.println("You entered an invalid choice, please try again...");
               }
            } else {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an invalid choice, please try again...");
            }
        }
    }


}



