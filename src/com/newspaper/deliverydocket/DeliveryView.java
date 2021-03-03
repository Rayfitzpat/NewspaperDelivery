package com.newspaper.deliverydocket;

import com.newspaper.customer.CustomerDB;
import com.newspaper.db.DBconnection;

import java.util.Scanner;

public class DeliveryView {


    private Scanner in;
    private Utility utility;

    public DeliveryView() {
        in = new Scanner(System.in);
        utility = new Utility();
    }


    public void deliveryMainPage() {
        int menuChoice = 0; // variable used to store main menu choice
        final int STOP_APP = 9; //value from menu that is used to quit the application


        // initialising view
        try {

            // running the menu
            while (menuChoice != STOP_APP) {
                // display the delivery menu
                displayDeliveryMenu();

                if (in.hasNextInt()) {

                    //get the menu choice from the user
                    menuChoice = in.nextInt();

                    switch (menuChoice) {
                        case 1 -> {
                            // create a delivery docket
                            creatingDeliveryDocket();
                        }
                        case 2 -> {
                            // update delivery docket
                        }
                        case 3 -> {
                            // delete delivery docket
                        }
                        case 4 -> {
                            // see all customer deliveries
                            seeAllCustomerDeliveries();
                        }
                        case 5 -> {
                            // see all publication deliveries
                        }
                        case 6 -> {

                        }
                        case 9 -> {
                            System.out.println("Returning to the Main Menu...");
                        }
                        default -> System.out.println("You entered an invalid choice, please try again...");
                    }
                } else {
                    //clear the input buffer and start again
                    in.nextLine();
                    System.out.println("You entered an invalid choice, please try again...");
                }
            }
        } catch (Exception e) {
            System.out.println("Error in the first menu");
            System.out.println(e.getMessage());
        }

        in.close();
    }

    // ask user to enter delivery person id
    public void creatingDeliveryDocket() {
        // 1. Display all delivery people and the delivery areas they work on
        // 2. Ask user to enter id of the delivery person
        // 3. Ask user to enter the date of the delivery docket
        // 4. Check if deliveries for that date are available
        // 5. Generate deliveries if they are not in the DB
        // 6. Create delivery docket file and show on console

        // 1. Display all delivery people and the delivery areas they work on
        utility.displayDeliveryPeopleWithDeliveryAreas();

        // 2. Ask user to enter id of the delivery person
        boolean isValid = false;
        int deliveryPersonId = 0;


        // getting id if the customer
        while (!isValid) {
            System.out.println("\nEnter id of the delivery person: ");
            if (in.hasNextInt()) {
                deliveryPersonId = in.nextInt();
                // checking if id exists

                boolean deliveryPersonExists = utility.deliveryPersonExists(deliveryPersonId);
                boolean deleveryPersonActive = utility.deliveryPersonActive(deliveryPersonId);
                if (deliveryPersonExists && deleveryPersonActive) {
                    isValid = true;
                }


            } else {
                in.nextLine();
                System.out.println("Delivery area id should be a number");
            }
        }

        // 3. Ask user to enter the date of the delivery docket
        String date = askUserToEnterDate();

        // 4. Check if deliveries for that date are available
        // 5. Generate deliveries if they are not in the DB
        DeliveryDocketDB deliveryDocketDB = new DeliveryDocketDB();
        deliveryDocketDB.generateDeliveriesIfNeeded(date);


        // 6. Create delivery docket file and show on console
        try {

            DeliveryDocket docket = deliveryDocketDB.createDeliveryDocketFor(deliveryPersonId, date);
            System.out.println(docket);
            System.out.println("\n***Saving...");
            System.out.println("***Delivery dockets will be available to print after program closes");
            deliveryDocketDB.createDeliveryDocketFile(docket);
        } catch (DeliveryDocketExceptionHandler e) {
            System.out.println(e.getMessage());
        }

    }


    public String askUserToEnterDate() {

        DeliveryDocket deliveryDocket = new DeliveryDocket(); // to access the validation methods
        String date = "";
        boolean inputValid = false;
        in.nextLine();

        while (!inputValid) {
            System.out.println("Enter the date of the delivery docket (Example: 2021-08-27): ");
            if (in.hasNextLine()) {
                date = in.nextLine();
                try {
                    deliveryDocket.validateDate(date);
                    // if validation was successful
                    inputValid = true;
                } catch (DeliveryDocketExceptionHandler e) {
                    System.out.println(e.getMessage());
                }
            } else {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an invalid date, please try again...");
            }
        }
        return date;
    }

    public void seeAllCustomerDeliveries() {
        try {
            int customerId = askUserToEnterCustomerID();
            utility.displayAllDeliveriesOfCustomer(customerId);
        } catch (DeliveryDocketExceptionHandler e) {
            System.out.println(e.getMessage());
        }
    }


    public int askUserToEnterCustomerID() {
        Scanner in = new Scanner(System.in);
        boolean isValid = false;
        int customerID = 0;

        // getting id if the customer
        while (!isValid) {
            System.out.println("Enter id of the customer: ");
            if (in.hasNextInt()) {
                customerID = in.nextInt();
                // checking if student exists
                if (utility.ifCustomerExists(customerID)) {
                    isValid = true;
                } else {
                    System.out.println("Customer with id " + customerID + " doesn't exist");
                }
            } else {
                in.next();
                System.out.println("Customer id should be a number");
            }
        }
        return customerID;
    }


    public void displayDeliveryMenu() {
        System.out.println("\n Delivery Menu");
        System.out.println("1: Create Delivery Docket");
        System.out.println("2: Update Delivery Docket");
        System.out.println("3: Delete Delivery Docket");
        System.out.println("4: See all deliveries of a customer");
        System.out.println("5: See all deliveries of a publication");
        System.out.println("6: ");
        System.out.println("9: Return to Main Menu\n");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {
        // run the menu
        DBconnection.init_db();
        DeliveryView view = new DeliveryView();
        view.deliveryMainPage();
    }
}
