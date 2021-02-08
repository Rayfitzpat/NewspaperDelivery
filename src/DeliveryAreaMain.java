import java.sql.*;
import java.util.Scanner;

public class DeliveryAreaMain
{
    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    static Scanner in = new Scanner(System.in);



    public static void main(String[] args) throws SQLException
    {
        init_db(); //Opens the connection to the database.
        DeliveryAreaView dav = new DeliveryAreaView();

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
                        dav.displayAllCustomers(stmt);
                        break;

                    case 2:
                        dav.displayAllAreas(stmt);
                        break;

                    case 3:
                        dav.createNewDeliveryArea(stmt);

                    case 4:
                        System.out.println("Update Delivery Area Name");

                    case 5:
                        dav.deleteDeliveryArea(stmt);
                }

            }
        }

    }


    public static void init_db()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/databaseGroupProject?useTimezone=true&serverTimezone=UTC";
            con = DriverManager.getConnection(url, "root", "admin");
            stmt = con.createStatement();
        }
        catch(Exception e)
        {
            System.out.println("Error: Failed to connect to database\n" + e.getMessage());
        }
    }
}

