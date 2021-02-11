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
        validatePublicationName(newPublication_Name);
        if (validName == true) {


            System.out.println("Please enter a frequency for the new publication, either Daily or Weekly\n");
            String newPublication_Frequency = in.nextLine();
            validatePublicationFrequency(newPublication_Frequency);
            if (validFrequency == true) {

                System.out.println("Please enter a cost for the new publication\n");
                double newPublication_Cost = in.nextDouble();
                validatePublicationCost(newPublication_Cost);
                if (validCost == true) {
                    //asks the user to enter a stock level for the new publication");
                    System.out.println("Please enter the stock level for the new publication\n");
                    int newPublication_Stock_Level = in.nextInt();
                    validatePublicationStockLevel(newPublication_Stock_Level);
                    if (validStockLevel == true) {

                        Statement addNew = publicationMain.con.createStatement();
                        addNew.executeUpdate("INSERT INTO publication VALUES (null, '" + newPublication_Name + "','" + newPublication_Frequency + "','" + newPublication_Cost + "','" + newPublication_Stock_Level + "')");
                        displayAllPublication(stmt);
                    }
                }
            }
        } else if (!validStockLevel) {
            System.out.println("The Stock level you have entered is invalid, please enter a valid stock level and try again");
            addNewPublication(stmt);
        } else if (!validCost) {
            System.out.println("Please enter a valid number\n");
            addNewPublication(stmt);
        } else if (!validFrequency) {
            System.out.println("The frequency you have entered is invalid, please choose either 'Daily' or 'Weekly' and try again\n");
            addNewPublication(stmt);
        } else if (!validName) {
            System.out.println("The String you have entered is incorrect as it contains a number or other illegal character, please enter a correct string and try again\n");
            addNewPublication(stmt);
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
        validatePublicationName(newPublication_Name);
        if (validName == true) {
            Statement editpublication_name = publicationMain.con.createStatement();
            editpublication_name.executeUpdate("Update publication SET publication_name = '" + newPublication_Name + "' where publication_id = '" + editId + "'");

        } else if (!validName)
            System.out.println("The String you have entered is incorrect as it contains a number or other illegal character, please enter a correct string and try again");
    }

    public void editpublication_frequency() throws SQLException {
        System.out.println("Please enter a new frequency for the publication, either daily or weekly");
        String newPublication_Frequency = in.next();
        boolean validFrequency = validatePublicationFrequency(newPublication_Frequency);
        if (validFrequency == true) {
            Statement editpublication_frequency = publicationMain.con.createStatement();
            editpublication_frequency.executeUpdate("Update publication SET publication_frequency = '" + newPublication_Frequency + "' where publication_id = '" + editId + "'");
        } else
            System.out.println("You have entered an illegal character, please enter only 'Daily' or 'Weekly' and try again ");
    }

    public void editpublication_cost() throws SQLException {
        System.out.println("Please enter a new cost for the publication");
        String newPublication_Cost = in.next();
       boolean validNumberedString= validatePublicationId(newPublication_Cost);
        if (validNumberedString == true) {
            Statement editpublication_cost = publicationMain.con.createStatement();
            editpublication_cost.executeUpdate("Update publication SET publication_cost = '" + newPublication_Cost + "' where publication_id = '" + editId + "'");
        } else
            System.out.println("You have entered an invalid price, please enter a proper numeric value and try again");
    }

    public void editpublication_stock_level() throws SQLException {
        System.out.println("Please enter a new stock level");
        int newPublication_Stock_Level = in.nextInt();
        validatePublicationStockLevel(newPublication_Stock_Level);
        if (validStockLevel == true) {
            Statement editpublication_stock_level = publicationMain.con.createStatement();
            editpublication_stock_level.executeUpdate("Update publication SET publication_stock_level = '" + newPublication_Stock_Level + "' where publication_id = '" + editId + "'");
        } else if (!validStockLevel) {
            System.out.println("You have entered and invalid stock level, please enter a different stock level and try again");
        }
    }

    public void deletePublication(Statement stmt) throws SQLException {
        displayAllPublication(stmt);


        System.out.println("Please enter the ID of the publication you would like to delete\n");


        String deleteId = in.next();
      boolean validString =  validatePublicationId(deleteId);
        if (validString == true)
        {
            System.out.println("Are you sure you want to delete this publication, all orders, invoices and deliveries associated with this publication will also be deleted.");
            Statement deletePublication = publicationMain.con.createStatement();
            deletePublication.executeUpdate("DELETE from publication where publication_id = " + deleteId );
        } else
            System.out.println("You have entered an incorrect ID, please check the list of IDs and try again.\n");
        deletePublication(stmt);
    }

    public boolean validatePublicationId(String publicationId) {
        boolean validNumberedString = true;
        for (int i = 0; i < publicationId.length() + 1; i++) {

            if (publicationId.charAt(i) >= '0'
                    && publicationId.charAt(i) <= '9') {
                validNumberedString = true;
                return true;
            } else {
                validNumberedString = false;
                return false;
            }
        }
        validNumberedString = false;
        return false;
    }

    //TODO Make a method to validate stock level, implement it into add and edit, move these validate methods to pulication.java
    public boolean validatePublicationName(String newPublication_Name) {
        if (newPublication_Name.matches("[a-zA-Z\\s\'\"]+")) {
            validName = true;
            return true;
        } else
            validName = false;
        return false;

    }

    public boolean validatePublicationFrequency(String newPublication_Frequency) {
        if (newPublication_Frequency.matches("[a-zA-Z]+")) {

            return true;
        } else

        return false;

    }

    public boolean validatePublicationCost(double newPublication_Cost) {
        if (newPublication_Cost >= 0.5 && newPublication_Cost <= 5.0) {
            validCost = true;
            return true;
        } else
            validCost = false;
        return false;
    }

    public boolean validatePublicationStockLevel(double newPublication_Stock_Level) {
        if (newPublication_Stock_Level >= 0 && newPublication_Stock_Level <= 10000)
        {
            validStockLevel = true;
            return true;
        } else
            validStockLevel = false;
        return false;
    }

}



