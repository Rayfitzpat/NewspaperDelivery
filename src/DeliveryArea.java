import java.sql.*;
import java.util.Scanner;

public class DeliveryArea {

    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args)
    {
        init_db();
        DisplayAllAreas();
        DisplayALlCustomers();
    }





    public static void DisplayAllAreas()
    {
        //1: Query the database for all areas
        //2: Display the result set in an appropriate manner
        String str = "Select * from delivery_area";

        try
        {
            rs = stmt.executeQuery(str);
            System.out.printf("\n%-12s %-15s %-20s\n", "delivery_area_id", "name", "description");
            while (rs.next())
            {
                int delivery_area_id = rs.getInt("delivery_area_id");
                String name = rs.getString("name");
                String description = rs.getString("description");

                System.out.printf("%-12d %-15s %-20s\n", delivery_area_id, name, description);
            }
        }
        catch (SQLException sqle)
        {
            System.out.println("Error: failed to areas.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
    }


    public static void DisplayALlCustomers()
    {
        {
            //1: Query the database for all areas
            //2: Display the result set in an appropriate manner
            String str = "Select * from customer";

            try
            {
                rs = stmt.executeQuery(str);
                System.out.printf("\n%-12s %-15s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-15s %-12s\n", "customer_id", "first_name", "last_name", "address1", "address2", "town", "eircode",  "phone_number", "holiday_start_date", "holiday_end_date", "customer_status");
                while (rs.next())
                {
                    int customer_id = rs.getInt("customer_id");
                    String first_name = rs.getString("first_name");
                    String last_name = rs.getString("last_name");
                    String address1 = rs.getString("address1");
                    String address2 = rs.getString("address2");
                    String town = rs.getString("town");
                    String eircode = rs.getString("eircode");
                    String phone_number = rs.getString("phone_number");
                    String holiday_start_date = rs.getString("holiday_start_date");
                    String holiday_end_date = rs.getString("holiday_end_date");
                    boolean customer_status = rs.getBoolean("customer_status");

                    System.out.printf("%-12s %-15s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-15s %-12s\n", customer_id, first_name, last_name, address1, address2, town, eircode,  phone_number, holiday_start_date, holiday_end_date, customer_status);
                }
            }
            catch (SQLException sqle)
            {
                System.out.println("Error: failed to areas.");
                System.out.println(sqle.getMessage());
                System.out.println(str);
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
