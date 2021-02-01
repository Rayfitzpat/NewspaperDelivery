import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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


    public static void main(String[] args) {
        init_db();  // open the connection to the database

        int menuChoice = 0; // variable used to store main menu choice
        final int STOP_APP = 7; //value from menu that is used to quit the application

        while (menuChoice != STOP_APP) {
            displayMainMenu(); //display the primary menu
            if (in.hasNextInt()) {
                //get the menu choice from the user
                menuChoice = in.nextInt();

                switch (menuChoice) {
                    case 1:
                        displayAllDeliveryPerson(); //The code for this method is already done for you below
                        break;
//                    case 2:
//                        displayDeliveryPerson(); //You need to code this method below
//                        break;
                    case 3:
                        addNewDeliveryPerson(); //You need to code this method below
                        break;
//                    case 4:
//                        editDeliveryPerson(); //You need to code this method below
//                        break;
                    case 5:
                        deleteDeliveryPerson(); //You need to code this method below
                        break;
                    case 4:
                        System.out.println("Program is closing...");
                        cleanup_resources();  // close the connection to the database when finished program
                        break;
                    default:
                        System.out.println("You entered an valid choice, please try again...");
                }
            } else {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an valid choice, please try again...");
            }
        }
    }

    }



