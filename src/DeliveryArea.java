import java.sql.*;
import java.util.Scanner;

public class DeliveryArea {

    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    static Scanner in = new Scanner(System.in);

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

    public static void main(String[] args) {
        init_db();
        DisplayAllAreas();
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
