import java.sql.*;
import java.util.ArrayList;

public class CustomerView {
    /**
     * The class will be handling the outputs into the console and fetching data with the database
     */



    /**
     * Method is retrieving the data about the customers and returns ArrayList of Customer objects
     * @param stmt Connection statement
     * @return an ArrayList of Customer objects from the database
     */
    public ArrayList<Customer> getAllCustomers(Statement stmt)
    {
        // array list for saving all the objects of the Customer class
        ArrayList<Customer> customersList = new ArrayList<>();
        int id;
        String firstName;
        String lastName;
        int address1;
        String address2;
        String town;
        String eircode;
        String phonenumber;
        String holidayStartDate;
        String holidayEndDate;
        boolean status;
        int deliveryAreaId;

        String query = "Select * from customer";
        ResultSet rs;
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {

                id = rs.getInt("customer_id");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                address1 = rs.getInt("address1");
                address2 = rs.getString("address2");
                town = rs.getString("town");
                eircode = rs.getString("eircode");
                phonenumber = rs.getString("phone_number");
                holidayStartDate = rs.getString("holiday_start_date");
                holidayEndDate = rs.getString("holiday_end_date");
                status = rs.getBoolean("customer_status");
                deliveryAreaId = rs.getInt("delivery_area_id");

                customersList.add(new Customer(id, firstName, lastName, address1, address2, town, eircode, phonenumber, holidayStartDate, holidayEndDate, status, deliveryAreaId));
            }

        } catch (SQLException sqle) {
            System.out.println("Error: failed to read all customers.");
            System.out.println(sqle.getMessage());
            System.out.println(query);
        }
        catch (CustomerExceptionHandler customerException) {
            System.out.println(customerException.getMessage());
        }

        return customersList;
    }

    public void printCustomers(ArrayList<Customer> customers) {
        System.out.printf("\n%-5s %-25s %-45s %-15s %-10s %-10s\n", "ID", "Name", "Address", "Phone", "Status", "Delivery Area ID");
        for (int i = 0; i < customers.size(); i++)
        {
            System.out.printf("%-5d %-25s %-45s %-15s %-10s %-10d\n", customers.get(i).getCustomerId(), customers.get(i).getFirstName() + " " + customers.get(i).getLastName(), (customers.get(i).getAddress1() + " " + customers.get(i).getAddress2() + ", " + customers.get(i).getTown()), customers.get(i).getPhoneNumber() , customers.get(i).getStatus(), customers.get(i).getDeliveryAreaId());
        }
    }

    // need to work on check of duplicates
    public boolean insertCustomer(Customer customer, Connection con) {

        // setting resulting flag to default true value
        boolean isSuccess = true;

        // sql query
        String insertQuery = "INSERT INTO customer VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement pstmt = con.prepareStatement(insertQuery);
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setInt(3, customer.getAddress1());
            pstmt.setString(4, customer.getAddress2());
            pstmt.setString(5, customer.getTown());
            pstmt.setString(6, customer.getEircode());
            pstmt.setString(7, customer.getPhoneNumber());
            pstmt.setString(8, customer.getHolidayStartDate());
            pstmt.setString(9, customer.getHolidayEndDate());
            pstmt.setString(10, customer.getStatus()+"");
            pstmt.setInt(11, customer.getDeliveryAreaId());

            int rows = pstmt.executeUpdate();

            System.out.println("Adding new customer record was successful");
        }
        catch (SQLException sqle)
        {
            System.out.println("Error: failed to add a customer record");
            System.out.println(sqle.getMessage());
            System.out.println(insertQuery);

            // resetting the success flag
            isSuccess = false;
        }

        return isSuccess;
    }
}
