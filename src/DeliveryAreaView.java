import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.function.ToDoubleBiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeliveryAreaView
{
    DeliveryAreaMain dam = new DeliveryAreaMain();
    String editId = "";

    public void displayAllAreas(Statement stmt)
    {
        //1: Query the database for all areas
        //2: Display the result set in an appropriate manner
        String str = "Select * from delivery_area";

        try {
            ResultSet rs = stmt.executeQuery(str);
            System.out.printf("\n%-12s %-15s %-20s\n", "delivery_area_id", "name", "description");
            while (rs.next()) {
                int delivery_area_id = rs.getInt("delivery_area_id");
                String name = rs.getString("name");
                String description = rs.getString("description");

                System.out.printf("%-12d %-15s %-20s\n", delivery_area_id, name, description);
            }
        } catch (SQLException sqle) {
            System.out.println("Error: failed to areas.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
    }


    public void displayAllCustomers(Statement stmt)
    {
        //1: Query the database for all areas
        //2: Display the result set in an appropriate manner
        String str = "Select * from customer order by address2";

        try {
            ResultSet rs = stmt.executeQuery(str);
            System.out.printf("\n%-12s %-15s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-15s %-12s\n", "customer_id", "first_name", "last_name", "address1", "address2", "town", "eircode", "phone_number", "holiday_start_date", "holiday_end_date", "customer_status");
            while (rs.next()) {
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

                System.out.printf("%-12s %-15s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-15s %-12s\n", customer_id, first_name, last_name, address1, address2, town, eircode, phone_number, holiday_start_date, holiday_end_date, customer_status);
            }
        } catch (SQLException sqle) {
            System.out.println("Error: failed to areas.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
    }

    public void createNewDeliveryArea(Statement stmt) throws SQLException //NEEDS FINISHING
    {
        DeliveryAreaMain dam = new DeliveryAreaMain();
        DeliveryArea da = new DeliveryArea();
        String dAreaName, description;
        int id, deliverypersonid;
        Scanner in = new Scanner(System.in);


        System.out.println("Please enter Delivery Area name: ");   //Needs Validation
        dAreaName = in.nextLine();
        da.setDAreaName(dAreaName);

        System.out.println("Please enter an Area Description: ");   //Needs Validation
        description = in.nextLine();
        da.setDescription(description);

        System.out.println("Please enter the delivery person's id: ");
        deliverypersonid = in.nextInt();
        da.setDeliveryPersonId(deliverypersonid);

        Statement addNewArea = dam.con.createStatement();
        addNewArea.executeUpdate("insert into delivery_area values (null ,'" + da.getDAreaName() + "','" + da.getDescription() + "','" + da.getDeliveryPersonId() + "')");
    }

    public void deleteDeliveryArea(Statement stmt) throws SQLException // NEEDS VALIDATION
    {
        String str;
        ResultSet rs;
        Scanner in = new Scanner(System.in);
        DeliveryAreaMain dam = new DeliveryAreaMain();
        displayAllAreas(stmt);
        System.out.println("Please enter the id of the Delivery Area you would like to delete: ");
        int deleteId = in.nextInt();

        if (deleteId < 0)
        {
            System.out.println("Please Input a valid ID");
        }
        if (deleteId > 0)
        {
            str = "Select count(*) as total from delivery_area where delivery_area_id = " + deleteId;
            Statement deletePerson = dam.con.createStatement();
            deletePerson.executeUpdate("delete from delivery_area where delivery_area_id ="+deleteId+"");
            System.out.println("Delivery Area with id: "+deleteId+" has been deleted.");
        }
    }

    public void editDeliveryAreas(Statement stmt) throws SQLException
    {
        String str;
        ResultSet rs;
        Scanner in = new Scanner(System.in);
        DeliveryAreaMain dam = new DeliveryAreaMain();
        displayAllAreas(stmt);
        System.out.println("Please enter the id of the Delivery Area that you would like to edit: ");
        int editId = in.nextInt();

        if (editId < 0)
        {

        }
    }



    public static void displayMainMenu()
    {
        System.out.println("\n Main Menu ");
        System.out.println("1: Display all Customers");
        System.out.println("2: Display all Delivery Areas");
        System.out.println("3: Create New Delivery Area");
        System.out.println("4: Update Delivery Area Name");
        System.out.println("5: Delete a Delivery Area");
        System.out.println("6: Close the Application");
    }


}
