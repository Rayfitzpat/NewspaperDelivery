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
                double publicationCost = rs.getDouble("publicationCost");
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
                 double publicationCost = rs.getDouble("publicationCost");
                 int publicationStockLevel = rs.getInt("publicationStockLevel");


                 System.out.printf("%-12s %-25s %-15s %-10s %-10s \n", publicationId, publicationName, publicationFrequency, publicationCost, publicationStockLevel);
             }

         } catch (SQLException sqle) {
             System.out.println("Error: failed to display all Delivery People.");
             System.out.println(sqle.getMessage());
             System.out.println(str);
         }


     }
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //problem with this method, when a new entry is created it loops around to asking for frequency and cost at the same time.
    // --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

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




    String editId = "";

     public void editPublication(Statement stmt) throws SQLException
     {



         String str = "";
         int count;
         displayAllPublication(stmt);
         System.out.println("Please enter the id corresponding to the attribute you would like to edit");
         editId = in.next();
         if (validateEntry(editId)) {
             str = "select count(*) as total from publication where publicationiD = " + editId;
             ResultSet rs = stmt.executeQuery(str);
             count = 0;
             while (rs.next()) {
                 count = rs.getInt("total");
             }
             if (count > 0) {


                 int menuChoiceEditPublication = 0; // variable used to store Edit menu choice
                 int stopEdit = 5; //value from menu that is used to close the Edit Student process

                 while (menuChoiceEditPublication != stopEdit) {
                     editPublicationMenu(); //display the primary menu
                     if (in.hasNextInt()) {
                         //get the menu choice from the user
                         menuChoiceEditPublication = in.nextInt();

                         switch (menuChoiceEditPublication) {
                             case 1:
                                 editPublicationName(); //The code for this method is already done for you below
                                 break;
                             case 2:
                                 editPublicationFrequency(); //You need to code this method below
                                 break;
                             case 3:
                                 editPublicationCost(); //You need to code this method below
                                 break;
                             case 4:
                                 editPublicationStockLevel(); //You need to code this method below
                                 break;
                             case 5:
                                 System.out.println("Program is closing...");
                                 displayAllPublication(stmt);  // close the connection to the database when finished program
                                 break;
                             default:
                                 System.out.println("You entered an invalid choice, please try again...");
                         }
                     } else {
                         //clear the input buffer and start again
                         in.nextLine();
                         System.out.println("You entered an invalid choice, please try again...");
                     }
                 }


             }
         }
     }

    PublicationMain publicationMain = new PublicationMain();
    Publication publication = new Publication();

         // ******************************************************************************************************
//    // Beginning of the Edit Person Method Section
//    // ******************************************************************************************************

         public void editPublicationName() throws SQLException {

         System.out.println("Please enter a new name for the publication");
             //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
             //error here, i need this to be in.nextline but if i do that it puts me in a loop of the menu choice popping up and asking me what option I want, I can never edit it.
             //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

             String newPublicationName = in.next();
         Statement editPublicationName = publicationMain.con.createStatement();
         editPublicationName.executeUpdate("Update publication SET publicationName = '" + newPublicationName + "' where publicationId = '"+editId+"'");
     }

         public void editPublicationFrequency() throws SQLException {
         System.out.println("Please enter a new frequency for the publication, either daily or weekly");
         String newPublicationFrequency = in.next();
         Statement editPublicationFrequency = publicationMain.con.createStatement();
         editPublicationFrequency.executeUpdate("Update publication SET publicationFrequency = '" + newPublicationFrequency + "' where publicationId = '" +editId+"'");
     }

         public void editPublicationCost() throws SQLException {
         System.out.println("Please enter a new cost for the publication");
         double newPublicationCost = in.nextDouble();
         Statement editPublicationCost = publicationMain.con.createStatement();
         editPublicationCost.executeUpdate("Update publication SET publicationCost = '"+newPublicationCost+"' where publicationId = '"+editId+"'");
     }

         public void editPublicationStockLevel() throws SQLException {
         System.out.println("Please enter a new stock level");
         int newPublicationStockLevel = in.nextInt();
         Statement editPublicationStockLevel = publicationMain.con.createStatement();
         editPublicationStockLevel.executeUpdate("Update publication SET publicationStockLevel = '" + newPublicationStockLevel + "' where publicationId = '" +editId+"'");
     }




     public void deletePublication(Statement stmt) throws SQLException
     {



         int deleteCount;
         String str;
         ResultSet rs;
         displayAllPublication(stmt);
         System.out.println("Please enter the id of the publication that you want to delete");

         String id = in.next();
         if (validateEntry(id)) {
             str = "select count(*) as total from publication where publicationId = " + id;

             rs = stmt.executeQuery(str);
             deleteCount = 0;
             while (rs.next()) {
                 deleteCount = rs.getInt("total");
             }

             if (deleteCount > 0) {

                 try {
                     Statement deletePublication = publicationMain.con.createStatement();
                     deletePublication.executeUpdate("delete from publication where publicationId =" + id + "");
                     System.out.println("Publication with id: " + id + " has been deleted");
                 } catch (Exception e) {
                     System.out.println("unable to delete the publication");
                 }
             } else {
                 System.out.println("That publication does not exist, please try again");
             }
         }
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


    public static boolean validateEntry (String id){
        if (id.length() > 2) {
            System.out.println("invalid entry, you must enter no more than 2 numbers");
            return false;
        } else {
            try {
                int tempId = Integer.parseInt(id);
            } catch (Exception e) {
                System.out.println("invalid Text entered, please enter a number");
                return false;
            }
        }
        return true;
    }

    public static void editPublicationMenu() {
        System.out.println("\nEdit Publication Menu");
        System.out.println("1: Edit Publication name");
        System.out.println("2: Edit Publication frequency");
        System.out.println("3: Edit Publication cost");
        System.out.println("4: Edit Publication stock level");
        System.out.println("5: Exit to Main Menu\n");
        System.out.print("Enter your choice: ");
    }
    public static void displayMainMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1: Display all publications");
        System.out.println("2: Display a publication with ID ");
        System.out.println("3: Add new publication");
        System.out.println("4: Edit Publication");
        System.out.println("5: Exit Programme\n");
        System.out.print("Enter your choice: ");
    }

    }



