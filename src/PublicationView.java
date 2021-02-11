import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class PublicationView {


    String newPublication_Name = "";
    boolean validName = true;
    boolean validFrequency = true;
    boolean validCost = true;
    boolean validStockLevel = true;

    Scanner in = new Scanner(System.in);
    String editId = "";
    PublicationMain publicationMain = new PublicationMain();
    Publication publication = new Publication();

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
        System.out.println("5: Delete Publication");
        System.out.println("6: Exit Programme\n");
        System.out.print("Enter your choice: ");
    }

    public void displayAllPublication(Statement stmt) {
        String str = "Select * from publication";


        try {
            ResultSet rs = stmt.executeQuery(str);

            System.out.printf("\n%-12s %-25s %-15s %-10s %-10s \n", "Pub ID", "Publication Name", "Frequency", "Cost", "Stock Level");
            while (rs.next()) {
                int publication_id = rs.getInt("publication_id");
                String publication_name = rs.getString("publication_name");
                String publication_frequency = rs.getString("publication_frequency");
                double publication_cost = rs.getDouble("publication_cost");
                int publication_stock_level = rs.getInt("publication_stock_level");


                System.out.printf("%-12s %-25s %-15s %-10s %-10s \n", publication_id, publication_name, publication_frequency, publication_cost, publication_stock_level);
            }

        } catch (SQLException sqle) {
            System.out.println("Error: failed to display all Delivery People.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
    }

    public void displayPublication(Statement stmt) {
        System.out.println("Please enter the ID of the publication you want to display");
        String id = in.next();
        String str = "Select * from publication where publication_id = " + id;

        try {
            ResultSet rs = stmt.executeQuery(str);

            System.out.printf("\n%-12s %-25s %-15s %-10s %-10s \n", "Pub ID", "Publication Name", "Frequency", "Cost", "Stock Level");
            while (rs.next()) {
                int publication_id = rs.getInt("publication_id");
                String publication_name = rs.getString("publication_name");
                String publication_frequency = rs.getString("publication_frequency");
                double publication_cost = rs.getDouble("publication_cost");
                int publication_stock_level = rs.getInt("publication_stock_level");


                System.out.printf("%-12s %-25s %-15s %-10s %-10s \n", publication_id, publication_name, publication_frequency, publication_cost, publication_stock_level);
            }

        } catch (SQLException sqle) {
            System.out.println("Error: failed to display all Delivery People.");
            System.out.println(str);
        }


    }

    // ******************************************************************************************************
//    // Beginning of the Edit Person Method Section
//    // ******************************************************************************************************

    public void addNewPublication(Statement stmt) throws SQLException {


        PublicationMain publicationMain = new PublicationMain();
        displayAllPublication(stmt);
        newPublication_Name = "";


        System.out.println("Please Enter a name for the new Publication\n");
        newPublication_Name = in.nextLine();
        boolean validName = validatePublicationName(newPublication_Name);
        if (validName == true) {


            System.out.println("Please enter a frequency for the new publication, either Daily or Weekly\n");
            String newPublication_Frequency = in.nextLine();
           boolean validFrequency = validatePublicationFrequency(newPublication_Frequency);
            if (validFrequency == true) {

                System.out.println("Please enter a cost for the new publication\n");
                String newPublication_Cost = in.next();
               boolean validCost = validateANumber(newPublication_Cost);
                if (validCost == true) {
                    //asks the user to enter a stock level for the new publication");
                    System.out.println("Please enter the stock level for the new publication\n");
                    String newPublication_Stock_Level = in.next();
                  boolean validStockLevel =  validateANumber(newPublication_Stock_Level);
                    if (validStockLevel == true) {

                        Statement addNew = publicationMain.con.createStatement();
                        addNew.executeUpdate("INSERT INTO publication VALUES (null, '" + newPublication_Name + "','" + newPublication_Frequency + "','" + newPublication_Cost + "','" + newPublication_Stock_Level + "')");
                        displayAllPublication(stmt);
                    }
                }
            }
        }
    }


    public void editPublication(Statement stmt) throws SQLException {

        displayAllPublication(stmt);


        String str = "";
        int count;
        System.out.println("Please enter the ID of the publication you would like to edit");
        editId = in.next();
       boolean validNumberedString= validatePublicationId(editId);
        if (validNumberedString == true) {
            str = "select count(*) as total from publication where publication_id = " + editId;
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
                                editpublication_name(); //The code for this method is already done for you below
                                break;
                            case 2:
                                editpublication_frequency(); //You need to code this method below
                                break;
                            case 3:
                                editpublication_cost(); //You need to code this method below
                                break;
                            case 4:
                                editpublication_stock_level(); //You need to code this method below
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
        } else if (!validNumberedString) {
            System.out.println("you have entered an incorrect ID, please enter a correct ID and try again");
        }
    }

    public void editpublication_name() throws SQLException {

        System.out.println("Please enter a new name for the publication");
        in.nextLine();
        newPublication_Name = in.nextLine();
      boolean validName =  validatePublicationName(newPublication_Name);
        if (validName) {
            Statement editpublication_name = publicationMain.con.createStatement();
            editpublication_name.executeUpdate("Update publication SET publication_name = '" + newPublication_Name + "' where publication_id = '" + editId + "'");
            System.out.println("Successfully updated the publication name to " + newPublication_Name+"\n\nReturning to edit menu....");
        }
    }

    public void editpublication_frequency() throws SQLException {
        System.out.println("Please enter a new frequency for the publication, either Daily or Weekly");
        String newPublication_Frequency = in.next();
        boolean validFrequency = validatePublicationFrequency(newPublication_Frequency);
        if (validFrequency)
        {
            Statement editpublication_frequency = publicationMain.con.createStatement();
            editpublication_frequency.executeUpdate("Update publication SET publication_frequency = '" + newPublication_Frequency + "' where publication_id = '" + editId + "'");
            System.out.println("Successfully update the frequency to " + newPublication_Frequency +"\n\nReturning to edit menu....");
        }
    }

    public void editpublication_cost() throws SQLException {
        System.out.println("Please enter a new cost for the publication");
        String newPublication_Cost = in.next();
       boolean validNumberedString= validateANumber(newPublication_Cost);
        if (validNumberedString) {
            Statement editpublication_cost = publicationMain.con.createStatement();
            editpublication_cost.executeUpdate("Update publication SET publication_cost = '" + newPublication_Cost + "' where publication_id = '" + editId + "'");
            System.out.println("Successfully update the price to " + newPublication_Cost +"\n\nReturning to edit menu....");
        }
    }


    public void editpublication_stock_level() throws SQLException
    {
        String newPublication_Stock_Level = in.next();
        boolean validStock = validateANumber(newPublication_Stock_Level);
                if(validStock) {
                    Statement editpublication_stock_level = publicationMain.con.createStatement();
                    editpublication_stock_level.executeUpdate("Update publication SET publication_stock_level = '" + newPublication_Stock_Level + "' where publication_id = '" + editId + "'");
                    System.out.println("Successfully update the frequency to " + newPublication_Stock_Level + "\n\nReturning to edit menu....");
                }
    }






    public void deletePublication(Statement stmt) throws SQLException
    {
        displayAllPublication(stmt);

        System.out.println("Please enter the ID of the publication you would like to delete\n");
        String deleteId = in.next();

      boolean validString =  validatePublicationId(deleteId);
        if (validString == true)
        {

    boolean confirmDelete = askUserYesOrNo( "Are you sure want to delete this publication. All deliveries, Daily Summaries and orders associated with this publication will also be deleted(yes/no)");
        if(confirmDelete)
            {
            Statement deletePublication = publicationMain.con.createStatement();
            deletePublication.executeUpdate("DELETE from publication where publication_id = " + deleteId);
            }

        }    else
            {
            System.out.println("You have entered an incorrect ID, please check the list of IDs and try again.\n");
            deletePublication(stmt);
            }
        System.out.println("Returning to Main Menu...");
    }




    public boolean askUserYesOrNo(String question){
        String answer;
        boolean inputValid = false;
        boolean confirm = false;

        while (!inputValid)
        {
            System.out.println(question);
            if (in.hasNextLine())
            {
                // if customer reply is "yes" or "no", save it and exit the loo0
                in.nextLine();
                answer = in.nextLine();
                if (answer.equals("yes") || answer.equals("Yes")) {
                    inputValid = true;
                    confirm = true;

                }
                else if(answer.equals("no") || answer.equals("No")) {
                    inputValid = true;
                    confirm = false;
                }
                else {
                    System.out.println("You entered an invalid answer, please use \"yes\" or \"no\"...");
                }
            }
            else
            {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an invalid answer, please use \"yes\" or \"no\"...");
            }
        }
        return confirm;
    }

    public boolean validatePublicationId(String publicationId) {

        for (int i = 0; i < publicationId.length() + 1; i++) {

            if (publicationId.charAt(i) >= '0'
                    && publicationId.charAt(i) <= '9') {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    //TODO Make a method to validate stock level, implement it into add and edit, move these validate methods to pulication.java
    public boolean validatePublicationName(String newPublication_Name) {
        if (newPublication_Name.matches("[a-zA-Z\\s\'\"]+"))
        {

            return true;
        } else
            System.out.println("You have entered an invalid character(s). Please try again only using valid characters");

        return false;

    }

    public boolean validatePublicationFrequency(String newPublication_Frequency) {
        if (newPublication_Frequency.matches("[a-zA-Z]+"))
        {
            if(newPublication_Frequency.matches("Daily") || newPublication_Frequency.matches("Weekly"))
            {
              return true;
            }
           else if(newPublication_Frequency.matches("daily") || newPublication_Frequency.matches("weekly"))

                System.out.println("Publication NOT updated. Please use capitilization, thank you :)");
            return false;
        }
        else
            System.out.println("Please only enter the words, 'Daily' or 'Weekly'");
        return false;

    }

    public boolean validateANumber(String publicationCost)
    {

        for (int i = 0; i < publicationCost.length() + 1; i++) {

            if (publicationCost.charAt(i) >= '0'
                    && publicationCost.charAt(i) <= '9') {
                return true;
            } else {
                System.out.println("A character you entered is not a valid number, please try again using only valid numbers.");
                return false;
            }
        }
        System.out.println("A character you entered is not a valid number, please try again using only valid numbers.");
        return false;

    }


}



