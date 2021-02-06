import java.sql.*;
import java.util.ArrayList;

public class CustomerView {

    /**
     * The class will be handling the outputs into the console and fetching data with the database
     * <p>
     * To be finished if I have time:
     * - actual deleting of the customers
     */

    private ArrayList<Customer> customers; // local copy of all customers in the db

    // constructor
    public CustomerView(Statement stmt) {
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
     *
     * @param stmt Connection statement
     * @return an ArrayList of Customer objects from the database
     */
    public ArrayList<Customer> fetchCustomers(Statement stmt) {
        // array list for saving all the objects of the Customer class
        ArrayList<Customer> customersList = new ArrayList<>();

        String query = "Select * from customer";
        ResultSet rs;
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {

                int id = rs.getInt("customer_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int address1 = rs.getInt("address1");
                String address2 = rs.getString("address2");
                String town = rs.getString("town");
                String eircode = rs.getString("eircode");
                String phonenumber = rs.getString("phone_number");
                String holidayStartDate = rs.getString("holiday_start_date");
                String holidayEndDate = rs.getString("holiday_end_date");
                boolean status = rs.getBoolean("customer_status");
                int deliveryAreaId = rs.getInt("delivery_area_id");

                customersList.add(new Customer(
                        id,
                        firstName,
                        lastName,
                        address1,
                        address2,
                        town,
                        eircode,
                        phonenumber,
                        holidayStartDate,
                        holidayEndDate,
                        status,
                        deliveryAreaId)
                );
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);
            System.out.println("Error: failed to read all customers.");
        } catch (CustomerExceptionHandler customerException) {
            System.out.println(customerException.getMessage());
        }

        // saving customers locally
        customers = customersList;

        return customersList;
    }


    /**
     * Method is inserting new customer into the db
     *
     * @param customer object containing all the data about the customer.
     *                 This data is accessed through getters and setters
     *                 and inserted into the db
     * @param con      Conncetion object to establish connection to db and perform the insert
     */
    public boolean insertCustomer(Customer customer, Connection con) {

        boolean isQuerySuccessfull = false; // setting default result flag

        // checking if the new record is not duplicate
        if (!ifCustomerExists(customer)) { // if false

            // sql query
            String insertQuery = "INSERT INTO customer VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try {
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
                pstmt.setString(10, customer.getStatus() + "");
                pstmt.setInt(11, customer.getDeliveryAreaId());

                int rows = pstmt.executeUpdate();

                isQuerySuccessfull = true;
                System.out.println("Adding new customer record was successful");
            } catch (SQLException sqle) {
                System.out.println("Error: failed to add a customer record");
                System.out.println(sqle.getMessage());
                System.out.println(insertQuery);
                isQuerySuccessfull = false;
            }
        } else {
            // outputting message about duplicate
            System.out.println(customer.getFirstName() + " " + customer.getLastName() + " record already exists in the database");
            isQuerySuccessfull = false;
        }

        return isQuerySuccessfull;
    }

    /**
     * Method is setting customer status to "inactive" or false
     *
     * @param customerId the if of customer that has to be deactivated
     * @param stmt       Statement object to access the db
     */
    public void deactivateCustomer(int customerId, Statement stmt) throws CustomerExceptionHandler {

        // checking if customer with Id customerId exists in the db
        if (ifCustomerExists(customerId)) {
            String updateQuery = "UPDATE customer\n" +
                    "SET customer_status = false " +
                    "WHERE customer_id = " + customerId + ";";
            try {
                stmt.executeUpdate(updateQuery);
                System.out.println("Customer with Id " + customerId + " was successfully deactivated");
            } catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
                System.out.println(updateQuery);
                throw new CustomerExceptionHandler("Failed to deactivate customer record");
            }
        } else {
            throw new CustomerExceptionHandler("There is no customer with id " + customerId + " in the database");
        }
    }

    /**
     * Method updates customer information
     * @param customerId the id of the customer record that must be updated
     * @param c the customer object with containing data for update
     * @param stmt Statement object to access the db
     * @throws CustomerExceptionHandler
     */
    public void updateCustomer(int customerId, Customer c,  Statement stmt) throws CustomerExceptionHandler {

        // checking if customer with Id customerId exists in the db
        if (ifCustomerExists(customerId)) {

            // if customer exists, then update is possible
            String updateQuery = "UPDATE customer " +
                    "SET first_name = \"" + c.getFirstName() +
                    "\", last_name = \"" + c.getLastName() +
                    "\", address1 = " + c.getAddress1() +
                    ", address2 = \"" + c.getAddress2() +
                    "\", town = \"" + c.getTown() +
                    "\", eircode = \"" + c.getEircode() +
                    "\", phone_number = \"" + c.getPhoneNumber() +
                    "\", holiday_start_date = " + c.getHolidayStartDate() +
                    ", holiday_end_date = " + c.getHolidayEndDate() +
                    ", customer_status = \"" + c.getStatus() +
                    "\", delivery_area_id = " + c.getDeliveryAreaId() +
                    " WHERE customer_id = " + customerId + ";";
            try {
                stmt.executeUpdate(updateQuery);
                System.out.println("Customer with Id " + customerId + " was successfully updated");
            } catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
                System.out.println(updateQuery);
                throw new CustomerExceptionHandler("Failed to update customer record");
            }
        } else {
            throw new CustomerExceptionHandler("There is no customer with id " + customerId + " in the database");
        }
    }

    /**
     * Method is checking if such customer already exists. The check is performed by checking name and address
     *
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
     *
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
     *
     * @param customers collection of objects that will be printed to console
     */
    public void printCustomers(ArrayList<Customer> customers) {
        System.out.printf("\n%-5s %-25s %-45s %-15s %-10s %-10s\n", "ID", "Name", "Address", "Phone", "Status", "Delivery Area ID");
        for (int i = 0; i < customers.size(); i++) {
            System.out.printf("%-5d %-25s %-45s %-15s %-10s %-10d\n", customers.get(i).getCustomerId(), customers.get(i).getFirstName() + " " + customers.get(i).getLastName(), (customers.get(i).getAddress1() + " " + customers.get(i).getAddress2() + ", " + customers.get(i).getTown()), customers.get(i).getPhoneNumber(), customers.get(i).getStatus(), customers.get(i).getDeliveryAreaId());
        }
    }

    /**
     * Method for printing all customer objects out into console window
     */
    public void printAllActiveCustomers() {
        System.out.printf("\n%-5s %-25s %-45s %-15s %-10s %-10s\n", "ID", "Name", "Address", "Phone", "Status", "Delivery Area ID");
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getStatus() == true) {
                String customerStatus = "active";
                System.out.printf("%-5d %-25s %-45s %-15s %-10s %-10d\n", customers.get(i).getCustomerId(), customers.get(i).getFirstName() + " " + customers.get(i).getLastName(), (customers.get(i).getAddress1() + " " + customers.get(i).getAddress2() + ", " + customers.get(i).getTown()), customers.get(i).getPhoneNumber(), customerStatus, customers.get(i).getDeliveryAreaId());
            }

        }
    }

    /**
     * Method for printing customer objects out into console window
     */
    public void printCustomers() {
        System.out.printf("\n%-5s %-25s %-45s %-15s %-10s %-10s\n", "ID", "Name", "Address", "Phone", "Status", "Delivery Area ID");
        for (int i = 0; i < customers.size(); i++) {
            String status = customers.get(i).getStatus() ? "active" : "inactive";

            System.out.printf("%-5d %-25s %-45s %-15s %-10s %-10d\n", customers.get(i).getCustomerId(), customers.get(i).getFirstName() + " " + customers.get(i).getLastName(), (customers.get(i).getAddress1() + " " + customers.get(i).getAddress2() + ", " + customers.get(i).getTown()), customers.get(i).getPhoneNumber(), status, customers.get(i).getDeliveryAreaId());
        }
    }

//    /**
//     * Method for printing customer object out by id
//     */
//    public void printCustomerById(int id) {
//        System.out.printf("\n%-5s %-25s %-45s %-15s %-10s %-10s\n", "ID", "Name", "Address", "Phone", "Status", "Delivery Area ID");
//        for (int i = 0; i < customers.size(); i++) {
//            String status = customers.get(i).getStatus() ? "active" : "inactive";
//
//            System.out.printf("%-5d %-25s %-45s %-15s %-10s %-10d\n", customers.get(i).getCustomerId(), customers.get(i).getFirstName() + " " + customers.get(i).getLastName(), (customers.get(i).getAddress1() + " " + customers.get(i).getAddress2() + ", " + customers.get(i).getTown()), customers.get(i).getPhoneNumber(), status, customers.get(i).getDeliveryAreaId());
//        }
//    }
}
