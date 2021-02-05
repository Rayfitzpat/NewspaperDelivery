import java.sql.Connection;
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



     public void displayPublication(Statement stmt)
     {
         System.out.println("Please enter the ID of the publication you want to display");
         String id = in.next();
         String str = "Select * from publication where publicationId = " + id;

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


     public void addNewPublication(Statement stmt) throws SQLException {

    String newPublicationName = "";
        Publication publication = new Publication();

        PublicationMain publicationMain = new PublicationMain();
         System.out.println("Please Enter a name for the new Publication");
         if (in.hasNextLine()) {
             //asks the user to enter a name for the new publication

              newPublicationName = in.nextLine();
             publication.setPublicationName(newPublicationName);

             if (newPublicationName.length() < 2 || newPublicationName.length() > 25)
             {
                 System.out.println("The first name must contain between 2 and 25 characters");
                 addNewPublication(stmt);
             }
             else if (!validateString(newPublicationName))
             {
                 System.out.println("Names cannot contain numbers");
                 addNewPublication(stmt);
             }
         }








         //asks the user to enter a frequency for the new publication, daily or weekly
         System.out.println("Please enter a frequency for the new publication, either Daily or Weekly");
         String newPublicationFrequency = in.nextLine();

         //asks the user to enter a cost for the new publication
         System.out.println("Please enter a cost for the new publication");
         double newPublicationCost = in.nextDouble();

         //asks the user to enter a stock level for the new publication");
         System.out.println("Please enter the stock level for the new publication");
         int newPublicationStockLevel = in.nextInt();

         Statement addNew = publicationMain.con.createStatement();
         addNew.executeUpdate("INSERT INTO publication VALUES (null, '"+newPublicationName+"','"+newPublicationFrequency+"','"+newPublicationCost+"','"+newPublicationStockLevel+"')");






     }

    public static boolean validateString (String name){
        name = name.toLowerCase();
        char[] nameArray = name.toCharArray();
        for (int i = 0; i < nameArray.length; i++) {
            char ch = nameArray[i];
            if (ch >= 'a' && ch <= 'z') {
                return true;
            }
        }
        return false;
    }


    }



