import java.sql.*;
import java.util.ArrayList;

public class CustomerView {

    /**
     * The class will be handling the outputs into the console and fetching data with the database
     */

    private ArrayList<Customer> customers; // local copy of all customers in the db

    public CustomerView(Statement stmt) throws CustomerExceptionHandler {
        fetchCustomers(stmt);
    }


    // getters and setters
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }


    /**
     * Method is retrieving the data about the customers and returns ArrayList of Customer objects
     * @param stmt Connection statement
     * @return an ArrayList of Customer objects from the database
     */
    public ArrayList<Customer> fetchCustomers(Statement stmt)  throws CustomerExceptionHandler  {
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
            System.out.println(sqle.getMessage());
            System.out.println(query);
            throw new CustomerExceptionHandler("Error: failed to read all customers.");
        }
        catch (CustomerExceptionHandler customerException) {
            System.out.println(customerException.getMessage());
        }

        // saving customers locally
        customers = customersList;

        return customersList;
    }




    /**
     * Method is inserting new customer into the db
     * @param customer object containing all the data about the customer.
     *                 This data is accessed through getters and setters
     *                 and inserted into the db
     * @param con Conncetion object to establish connection to db and perform the insert
     */
    public void insertCustomer(Customer customer, Connection con) throws CustomerExceptionHandler {

        // checking if the new record is not duplicate
        if (!ifCustomerExists(customer)) { // if false

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
                throw new CustomerExceptionHandler("Error: failed to read all customers.");
            }
        }
        else {
            // outputting message about duplicate
            throw new CustomerExceptionHandler(customer.getFirstName() + " " +customer.getLastName() + " record already exists in the database");
        }
    }

    /**
     * Method is setting customer status to "inactive" or false
     * @param customerId the if of customer that has to be deactivated
     * @param stmt Statement object to access the db
     * @return true if customer status was changed to "inactive" or false if not
     */
    public void deactivateCustomer(int customerId, Statement stmt) throws CustomerExceptionHandler  {

        // checking if customer with Id customerId exists in the db
        if (ifCustomerExists(customerId)) {
            String updateQuery = "UPDATE customer\n" +
                    "SET customer_status = false " +
                    "WHERE customer_id = " + customerId + ";";
            try {
                stmt.executeUpdate(updateQuery);
                throw new CustomerExceptionHandler("Customer with Id "+ customerId + " was successfully deactivated");
            }
            catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
                System.out.println(updateQuery);
                throw new CustomerExceptionHandler("Failed to deactivate customer record");
            }
        }
        else {
            throw new CustomerExceptionHandler("There is no customer with id " + customerId + " in the database");
        }
    }

    /**
     * Method is checking if such customer already exists. The check is performed by checking name and address
     * @param newCustomer the customer object that needs to be checked if duplicate
     * @return true if this customer already exists in the db, false if not
     */
    public boolean ifCustomerExists(Customer newCustomer) {

        // accessing data of new customer
        String firstName = newCustomer.getFirstName();
        String lastName = newCustomer.getLastName();
        int address = newCustomer.getAddress1();

        for (Customer c : customers) {
            if (c.getFirstName().equals(firstName) && c.getLastName().equals(lastName) && c.getAddress1() == address) {
                return true;
            }
        }

        // if return didn't happen in the foreach loop, then this is not duplicate
        return false;
    }

    /**
     * Method is checking if customer with customerId  already exists
     * @param customerId customer if from the db
     * @return true if this customer already exists in the db, false if not
     */
    public boolean ifCustomerExists(int customerId) {

        for (Customer c : customers) {
            if (c.getCustomerId() == customerId) {
                return true;
            }
        }

        // if return didn't happen in the foreach loop, then this is not duplicate
        return false;
    }

    /**
     * Method for printing customer objects out into console window
     * @param customers collection of objects that will be printed to console
     */
    public void printCustomers(ArrayList<Customer> customers) {
        System.out.printf("\n%-5s %-25s %-45s %-15s %-10s %-10s\n", "ID", "Name", "Address", "Phone", "Status", "Delivery Area ID");
        for (int i = 0; i < customers.size(); i++)
        {
            System.out.printf("%-5d %-25s %-45s %-15s %-10s %-10d\n", customers.get(i).getCustomerId(), customers.get(i).getFirstName() + " " + customers.get(i).getLastName(), (customers.get(i).getAddress1() + " " + customers.get(i).getAddress2() + ", " + customers.get(i).getTown()), customers.get(i).getPhoneNumber() , customers.get(i).getStatus(), customers.get(i).getDeliveryAreaId());
        }
    }


    /**
     * Method for printing customer objects out into console window
     */
    public void printCustomers() {
        System.out.printf("\n%-5s %-25s %-45s %-15s %-10s %-10s\n", "ID", "Name", "Address", "Phone", "Status", "Delivery Area ID");
        for (int i = 0; i < customers.size(); i++)
        {
            System.out.printf("%-5d %-25s %-45s %-15s %-10s %-10d\n", customers.get(i).getCustomerId(), customers.get(i).getFirstName() + " " + customers.get(i).getLastName(), (customers.get(i).getAddress1() + " " + customers.get(i).getAddress2() + ", " + customers.get(i).getTown()), customers.get(i).getPhoneNumber() , customers.get(i).getStatus(), customers.get(i).getDeliveryAreaId());
        }
    }
}
