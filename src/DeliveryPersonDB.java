import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryPersonDB {

    static Scanner in = new Scanner(System.in);




//    public static void main(String[] args) throws SQLException {
    public void deliveryPersonMainPage() throws SQLException{
        //DBconnection.init_db();  // open the connection to the database
        DeliveryPersonView dpv = new DeliveryPersonView();
        Main main = new Main();
        int menuChoice = 0; // variable used to store main menu choice
        final int STOP_APP = 6; //value from menu that is used to quit the application

//        dpv.displayAllDeliveryPerson(DBconnection.stmt);
//        dpv.displayDeliveryPerson(DBconnection.stmt);
//        dpv.deleteDeliveryPerson(DBconnection.stmt);
        //dpv.addNewDeliveryPerson(DBconnection.stmt);

       while (menuChoice != STOP_APP) {
            dpv.displayMainMenu(); //display the primary menu
            if (in.hasNextInt()) {
                //get the menu choice from the user
                menuChoice = in.nextInt();

                switch (menuChoice) {
                    case 1:
                        dpv.displayAllDeliveryPerson(DBconnection.stmt); //The code for this method is already done for you below
                        break;
                    case 2:
                        dpv.displayDeliveryPerson(DBconnection.stmt); //You need to code this method below
                        break;
                    case 3:
                        dpv.addNewDeliveryPerson(DBconnection.stmt); //You need to code this method below
                        break;
                   case 4:
                        dpv.editDeliveryPerson(DBconnection.stmt); //You need to code this method below
                        break;
                    case 5:
                        dpv.deleteDeliveryPerson(DBconnection.stmt); //You need to code this method below
                       break;
                    case 6:
                        return;
//                        main.displayMainMenu(); // close the connection to the database when finished program
//                        break;
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



