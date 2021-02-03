import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerView {
    /**
     * The class will be handling the outputs into the console and fetching data with the database
     */

    public ArrayList<Customer> getAllCustomers(Statement stmt)
    {
        // array list for saving all the objects of the Customer class
        ArrayList<Customer> customersList = new ArrayList<>();

        String query = "Select * from customer";
        ResultSet rs;
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int spublicationId = rs.getInt("publicationId");
                String publicationName = rs.getString("publicationName");
                String publicationFrequency = rs.getString("publicationFrequency");
                float publicationCost = rs.getFloat("publicationCost");
                int stockLevel = rs.getInt("publicationStockLevel");

                customersList.add(new Customer());
            }

        } catch (SQLException sqle) {
            System.out.println("Error: failed to read all customers.");
            System.out.println(sqle.getMessage());
            System.out.println(query);
        }

        return customersList;
    }
}
