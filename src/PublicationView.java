import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class PublicationView {

    Scanner in = new Scanner(System.in);
    public void displayAllPublication(Statement stmt)
    {
        String str = "Select * from publication";


        try {
            ResultSet rs = stmt.executeQuery(str);

            System.out.printf("\n%-12s %-25s %-15s %-10s %-10s \n", "Pub ID", "Publication Name", "Frequency", "Cost", "Stock Level");
            while (rs.next()) {
                int publicationId = rs.getInt("publicationId");
                String publicationName = rs.getString("publicationName");
                String publicationFrequency = rs.getString("publicationFrequency");
                int publicationCost = rs.getInt("publicationCost");
                int publicationStockLevel = rs.getInt("publicationStockLevel");


                System.out.printf("%-12s %-25s %-15s %-10s %-10s \n", publicationId, publicationName, publicationFrequency, publicationCost, publicationStockLevel);
            }

        } catch (SQLException sqle) {
            System.out.println("Error: failed to display all Delivery People.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
     }
    }



