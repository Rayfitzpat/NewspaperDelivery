import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.function.ToDoubleBiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeliveryAreaDB
{
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

    public ArrayList <DeliveryArea> getAllAreas(Statement stmt)
    {
        //1: Query the database for all areas
        //2: Display the result set in an appropriate manner
        String str = "Select * from delivery_area";
        ArrayList <DeliveryArea> deliveryAreas = new ArrayList<>() ;

        try {
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                int delivery_person_id = rs.getInt("delivery_person_id");
                int delivery_area_id = rs.getInt("delivery_area_id");
                String name = rs.getString("name");
                String description = rs.getString("description");

                // create an object of DeliveryArea and add it into the array list
                DeliveryArea area = new DeliveryArea(delivery_area_id, name, description, delivery_person_id);
                deliveryAreas.add(area);
            }
        } catch (SQLException sqle) {
            System.out.println("Error: failed to get areas.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
        catch (DeliveryAreaExceptionHandler e) {
            System.out.println(e.getMessage());
        }
        return deliveryAreas;
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

        Statement addNewArea = DBconnection.con.createStatement();
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
            Statement deletePerson = DBconnection.con.createStatement();
            deletePerson.executeUpdate("delete from delivery_area where delivery_area_id ="+deleteId+"");
            System.out.println("Delivery Area with id: "+deleteId+" has been deleted.");
        }
    }

//    public void editDeliveryAreas(Statement stmt) throws SQLException
//    {
//        String str;
//        ResultSet rs;
//        Scanner in = new Scanner(System.in);
//        DeliveryAreaMain dam = new DeliveryAreaMain();
//        displayAllAreas(stmt);
//        System.out.println("Please enter the id of the Delivery Area that you would like to edit: ");
//        int editId = in.nextInt();
//
//        if (editId < 0)
//        {
//
//        }
//    }

    public void editDeliveryArea(Statement stmt) throws SQLException, DeliveryAreaExceptionHandler {
        DeliveryArea da = new DeliveryArea();
        String str;
        int count;
        Scanner in = new Scanner(System.in);
        displayAllAreas(stmt);
        System.out.println("Please enter the id of the person you would like to edit");
        editId = in.next();
        if (da.validateEntry(editId))
        {
            str = "select count(*) as total from delivery_area where delivery_area_id = " + editId;
            ResultSet rs = stmt.executeQuery(str);
            count = 0;
            while (rs.next())
            {
                count = rs.getInt("total");
            }
            if (count > 0)
            {
                System.out.println("Please enter the id corresponding to the attribute you would like to edit");
                int deliveryAreaEditMenu = 0; // variable used to store Edit menu choice
                int stopEdit = 4; //value from menu that is used to close the Edit process

                while (deliveryAreaEditMenu != stopEdit)
                {
                    editDeliveryAreaMenu(); //display the primary menu
                    if (in.hasNextInt())
                    {
                        //get the menu choice from the user
                        deliveryAreaEditMenu = in.nextInt();

                        switch (deliveryAreaEditMenu)
                        {
                            case 1:
                                editDeliveryAreaName(); //The code for this method is already done for you below
                                break;
                            case 2:
                                editDeliveryAreaDescription(); //You need to code this method below
                                break;
                            case 3:
                                editDeliveryPersonId(); //You need to code this method below
                                break;
                            case 4:
                                System.out.println("Returning to main Delivery Menu......");
                                displayAllAreas(stmt);
                                break;
                            default:
                                System.out.println("You entered an invalid choice, please try again...");
                        }
                    }
                    else
                    {
                        //clear the input buffer and start again
                        in.nextLine();
                        System.out.println("You entered an invalid choice, please try again...");
                    }
                }
            }
        }
    }

    // ******************************************************************************************************
    // Beginning of the Edit Area Method Section
    // ******************************************************************************************************

    public void editDeliveryAreaName() throws SQLException, DeliveryAreaExceptionHandler
    {
        DeliveryArea da = new DeliveryArea();
        System.out.println("Please enter a new Delivery Area Name: ");
        Scanner in = new Scanner(System.in);
        String newName = in.nextLine();
        if (!da.validateString(newName))
        {
            System.out.println("Names cannot contain numbers and must be between 1 to 20 characters");
            da.validName = false;
        }
        else
        {
            da.validName = true;
            Statement editPerson = DBconnection.con.createStatement();
            editPerson.executeUpdate("Update delivery_area SET name = '" + newName + "' where delivery_area_id = '" + editId + "'");
        }
    }

    public void editDeliveryAreaDescription() throws SQLException, DeliveryAreaExceptionHandler
    {
        DeliveryArea da = new DeliveryArea();
        System.out.println("Please enter a new Delivery Area Description: ");
        Scanner in = new Scanner(System.in);
        String newDesc = in.nextLine();
        if (!da.validateDesc(newDesc))
        {
            System.out.println("Descriptions cannot contain numbers and must be between 1 to 14 characters");
            da.validDesc = false;
        }
        else
        {
            da.validDesc = true;
            Statement editPerson = DBconnection.con.createStatement();
            editPerson.executeUpdate("Update delivery_area SET description = '" + newDesc + "' where delivery_area_id = '" + editId + "'");
        }
    }


    public void editDeliveryPersonId() throws SQLException
    {
        DeliveryArea da = new DeliveryArea();
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the new Delivery Person id: ");
        String replacementId = in.nextLine();
        if (!da.validateEntry(replacementId))
        {
            System.out.println("Names cannot contain numbers and must be between 1 to 20 characters");
            da.validName = false;
        }
        else
        {
            da.validName = true;
            Statement editPerson = DBconnection.con.createStatement();
            editPerson.executeUpdate("Update delivery_area SET delivery_person_id = '" + replacementId + "' where delivery_area_id = '" + editId + "'");
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

    public static void editDeliveryAreaMenu() {
        System.out.println("\nEdit Delivery Area Menu");
        System.out.println("1: Edit Delivery Area Name: ");
        System.out.println("2: Edit Delivery Area Descriptions: ");
        System.out.println("3: Edit Delivery Area, Delivery Person: ");
    }
}