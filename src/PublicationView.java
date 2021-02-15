import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class PublicationView {

    //imports scanner
    Scanner in = new Scanner(System.in);
    String editId = "";
    //creates an object from publication main and publication class for use in this class..
    PublicationMain publicationMain = new PublicationMain();
    Publication p = new Publication();


    // ******************************************************************************************************
    // Beginning of display all publications
    // ******************************************************************************************************
    //Prints out the publication table
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
            System.out.println("Error: failed to display all Publications.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
    }


    // ******************************************************************************************************
    // Beginning of display a certain publication with entered ID.
    // ******************************************************************************************************

    public void displayPublication(Statement stmt) {
        System.out.println("Please enter the ID of the publication you want to display");

        String id = in.next();
        //checks if the entered id is a whole number.
        boolean validId = p.validateAWholeNumber(id);
        if (validId) {
            //checks if the id entered is a valid ID in the list of publications, if it is, print out the associated data with that entry.
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
                System.out.println("Error: failed to display all Publications.");
                System.out.println(str);
            }
        } else {
            //if the valid ID check fails, the below is printed.
            System.out.println("You have entered an invalid ID, please try again");
        }


    }

    // ******************************************************************************************************
    // Beginning of the add a new publication method
    // ******************************************************************************************************

    public void addNewPublication(Statement stmt) throws SQLException {


        PublicationMain publicationMain = new PublicationMain();

        //prompts the user to enter a new publication name.
        System.out.println("\nPlease Enter a name for the new Publication\n");
        String newPublication_Name = in.nextLine();
        //checks if the name is valid, if it is, the code moves on to the next step
        boolean validName = p.validatePublicationName(newPublication_Name);
        if (validName == true) {

            //prompts the user to enter a new publication frequency
            System.out.println("Please enter a frequency for the new publication, either Daily or Weekly\n");
            String newPublication_Frequency = in.nextLine();
           newPublication_Frequency = newPublication_Frequency.toLowerCase();
            //checks to see if the frequency is valid, if is it the code moves on to the next step
            boolean validFrequency = p.validatePublicationFrequency(newPublication_Frequency);
            if (validFrequency == true) {

                //prompts the user to enter a new publication cost
                System.out.println("Please enter a cost for the new publication\n");
                String newPublication_Cost = in.next();
                //checks to see if the cost is valid, if is it the code moves on to the next step
                boolean validCost = p.validateANumber(newPublication_Cost);
                if (validCost == true) {


                    //prompts the user to enter a new publication stock level
                    System.out.println("Please enter the stock level for the new publication\n");
                    String newPublication_Stock_Level = in.next();
                    //checks to see if the stock level is valid, if is it the code applies the update
                    boolean validStockLevel = p.validateAWholeNumber(newPublication_Stock_Level);
                    if (validStockLevel == true) {

                        Statement addNew = publicationMain.con.createStatement();
                        addNew.executeUpdate("INSERT INTO publication VALUES (null, '" + newPublication_Name + "','" + newPublication_Frequency + "','" + newPublication_Cost + "','" + newPublication_Stock_Level + "')");
                        //displays all publications to show you the new update.
                        displayAllPublication(stmt);
                        // --------------------------------
                        //error handling.
                        //-------------------------------
                    } else {
                        System.out.println("The number you entered is invalid, please try again using a whole number.");
                        addNewPublication(stmt);
                    }
                } else {
                    System.out.println("The number you entered is invalid, please try again using a valid number.");
                    addNewPublication(stmt);
                }
            } else {
                addNewPublication(stmt);
            }
        } else {
            addNewPublication(stmt);
        }
    }
    // ******************************************************************************************************
    // Beginning of the edit publication method.
    // ******************************************************************************************************

    public void editPublication(Statement stmt) throws SQLException {

        //displays all publications so the user can see which publication they want to update.
        displayAllPublication(stmt);


        String str = "";
        int count;
        //prompts the user to enter the ID of the publication they want to edit.
        System.out.println("Please enter the ID of the publication you would like to edit");
        editId = in.next();
        //checks if the entery is a valid whole number. if it is, move on.
        boolean validNumberedString = p.validateAWholeNumber(editId);
        if (validNumberedString == true) {
            //checks to see if the entered number is a real entry on the database. if it is, move on.
            str = "select count(*) as total from publication where publication_id = " + editId;
            ResultSet rs = stmt.executeQuery(str);
            count = 0;
            while (rs.next()) {
                count = rs.getInt("total");
            }
            if (count > 0) {

//            -----------------------------------------------
//                    start of the edit menu
//            -----------------------------------------------

                int menuChoiceEditPublication = 0; // variable used to store Edit menu choice
                int stopEdit = 5; //value from menu that is used to close the Edit Student process

                while (menuChoiceEditPublication != stopEdit) {
                    editPublicationMenu(); //display the primary menu
                    if (in.hasNextInt()) {
                        //get the menu choice from the user
                        menuChoiceEditPublication = in.nextInt();
                        //switch statement to ask the user which option they want.
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
                        //error handling
                        System.out.println("You entered an invalid choice, please try again...");
                    }
                }


            }
        } else if (!validNumberedString) {
            System.out.println("you have entered an incorrect ID, please enter a correct ID and try again");
        }
    }

    // ******************************************************************************************************
    // Edit Publication name method
    // ******************************************************************************************************
    public void editpublication_name() throws SQLException {

        System.out.println("Please enter a new name for the publication");
        in.nextLine();
        String newPublication_Name = in.nextLine();
        //validates the entry by the user, if it is valid, executes the update
        boolean validName = p.validatePublicationName(newPublication_Name);
        if (validName) {
            Statement editpublication_name = publicationMain.con.createStatement();
            editpublication_name.executeUpdate("Update publication SET publication_name = '" + newPublication_Name + "' where publication_id = '" + editId + "'");
            System.out.println("Successfully updated the publication name to " + newPublication_Name + "\n\nReturning to edit menu....");
        }
    }

    //  ------------------------------------------------------------------------------------------------------
    // Edit Publication frequency method
    //  ------------------------------------------------------------------------------------------------------
    public void editpublication_frequency() throws SQLException {
        System.out.println("Please enter a new frequency for the publication, either Daily or Weekly");
        String newPublication_Frequency = in.next();
        //validates the entry by the user, if it is valid, executes the update
        boolean validFrequency = p.validatePublicationFrequency(newPublication_Frequency);
        if (validFrequency) {
           newPublication_Frequency = newPublication_Frequency.toLowerCase();
            Statement editpublication_frequency = publicationMain.con.createStatement();
            editpublication_frequency.executeUpdate("Update publication SET publication_frequency = '" + newPublication_Frequency + "' where publication_id = '" + editId + "'");
            System.out.println("Successfully update the frequency to " + newPublication_Frequency + "\n\nReturning to edit menu....");
        }
    }

    //  ------------------------------------------------------------------------------------------------------
    // Edit Publication cost method
    //  ------------------------------------------------------------------------------------------------------
    public void editpublication_cost() throws SQLException {
        System.out.println("Please enter a new cost for the publication");
        String newPublication_Cost = in.next();
        //validates the entry by the user, if it is valid, executes the update
        boolean validNumberedString = p.validateANumber(newPublication_Cost);
        if (validNumberedString) {
            Statement editpublication_cost = publicationMain.con.createStatement();
            editpublication_cost.executeUpdate("Update publication SET publication_cost = '" + newPublication_Cost + "' where publication_id = '" + editId + "'");
            System.out.println("Successfully update the price to " + newPublication_Cost + "\n\nReturning to edit menu....");
        } else System.out.println("Your entry is not a valid number, please try again using a valid number.");
    }

    //  ------------------------------------------------------------------------------------------------------
    // Edit Publication stock level method
    // ------------------------------------------------------------------------------------------------------
    public void editpublication_stock_level() throws SQLException {
        String newPublication_Stock_Level = in.next();
        //validates the entry by the user, if it is valid, executes the update
        boolean validStock = p.validateANumber(newPublication_Stock_Level);
        if (validStock) {
            Statement editpublication_stock_level = publicationMain.con.createStatement();
            editpublication_stock_level.executeUpdate("Update publication SET publication_stock_level = '" + newPublication_Stock_Level + "' where publication_id = '" + editId + "'");
            System.out.println("Successfully update the frequency to " + newPublication_Stock_Level + "\n\nReturning to edit menu....");
        } else System.out.println("Your entry is not a valid number, please try again using a valid whole number.");
    }


    // ******************************************************************************************************
    // Beginning of the delete publication method.
    // ******************************************************************************************************
    public void deletePublication(Statement stmt) throws SQLException {

        PublicationMain pm = new PublicationMain();
        int deleteCount;
        String str;
        ResultSet rs;
        displayAllPublication(stmt);

        System.out.println("Please enter the ID of the publication you would like to delete\n");
        String id = in.next();

        boolean validString = p.validateAWholeNumber(id);
        if (validString) {

            //validates that the chosen entry is in the database.
            str = "select count(*) as total from publication where publication_id = " + id;
            rs = stmt.executeQuery(str);
            deleteCount = 0;
            while (rs.next()) {
                deleteCount = rs.getInt("total");
            }

            if (deleteCount > 0) {
                //asks the user if they are sure, and want to delete the entry from the database.
                boolean confirmDelete = p.askUserYesOrNo("Are you sure want to delete this publication. All deliveries, Daily Summaries and orders associated with this publication will also be deleted(yes/no)");
                if (confirmDelete) {
                    //if the user choses yes, the entry is deleted.
                    Statement deletePublication = publicationMain.con.createStatement();
                    deletePublication.executeUpdate("DELETE from publication where publication_id = " + id);
                    System.out.println("The Publication with the id of " + id + " has been deleted...... \n\nReturning to menu......");
                } else {
                    //if the user choses no, they are returned to the main menu
                    System.out.println("Returning to Main Menu...");
                }
            }

        } else {
            //Tells the user the ID they have entered is invalid, brings them to the start of the method
            System.out.println("You have entered an incorrect ID, please check the list of IDs and try again.\n");
            deletePublication(stmt);
        }


    }


    // ******************************************************************************************************
    //Menu for the edit publicatinon method
    // ******************************************************************************************************
    public static void editPublicationMenu() {
        System.out.println("\nEdit Publication Menu");
        System.out.println("1: Edit Publication name");
        System.out.println("2: Edit Publication frequency");
        System.out.println("3: Edit Publication cost");
        System.out.println("4: Edit Publication stock level");
        System.out.println("5: Exit to Main Menu\n");
        System.out.print("Enter your choice: ");
    }

    // ******************************************************************************************************
    // Beginning of main menu for the main application
    // ******************************************************************************************************
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
}



