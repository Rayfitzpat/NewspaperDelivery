import java.sql.*;
import java.util.Scanner;

public class DeliveryAreaMain
{
    static Scanner in = new Scanner(System.in);



    public static void main(String[] args) throws SQLException
    {
        DBconnection.init_db(); //Opens the connection to the database.
        DeliveryAreaDB dav = new DeliveryAreaDB();

        // Variable used to store menu choice.
        int menuChoice = 0;

        // value that wil close the application.
        final int STOP_APP = 5;

        while (menuChoice != STOP_APP)
        {
            dav.displayMainMenu();
            if (in.hasNextInt())
            {
                menuChoice = in.nextInt();

                switch(menuChoice)
                {
                    case 1:
                        dav.displayAllCustomers(DBconnection.stmt);
                        break;

                    case 2:
                        dav.displayAllAreas(DBconnection.stmt);
                        break;

                    case 3:
                        dav.createNewDeliveryArea(DBconnection.stmt);

                    case 4:
                        System.out.println("Update Delivery Area Name");

                    case 5:
                        dav.deleteDeliveryArea(DBconnection.stmt);
                }

            }
        }

    }
}

