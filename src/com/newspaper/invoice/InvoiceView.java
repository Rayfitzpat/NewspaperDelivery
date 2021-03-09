package com.newspaper.invoice;

import com.newspaper.db.DBconnection;
import com.newspaper.deliverydocket.DeliveryView;
import com.newspaper.deliverydocket.Utility;

import java.util.Scanner;

public class InvoiceView {

    private Utility utility = new Utility();
    private DeliveryView helper = new DeliveryView();
    Scanner in = new Scanner(System.in);
    InvoiceGenerator invoiceDB = new InvoiceGenerator();

    public void runMenu() {
        InvoiceDB invoice = new InvoiceDB();

        // Variable used to store menu choice.
        int menuChoice = 0;

        // value that wil close the application.
        final int STOP_APP = 10;

        while (menuChoice != STOP_APP)
        {
            invoice.displayMainMenu();
            if (in.hasNextInt())
            {
                menuChoice = in.nextInt();

                switch(menuChoice)
                {
                    case 1:
                        invoice.getCustomerFromInvoice(DBconnection.stmt);
                        break;

                    case 2:
                        invoice.getCustomerNameFromId(DBconnection.stmt);
                        break;

                    case 3:
                        invoice.getCusAddressFromInvoiceId();
                        break;

                    case 4:
                        invoice.printPublications(DBconnection.stmt);
                        break;

                    case 5:
                        invoice.paidUpdate(DBconnection.stmt);
                        break;

                    case 6:
                        // create invoice

                        break;

                    case 7:
                        // generate invoices
                        break;
                    case 8:
                        // update invoice
                        break;
                    case 9:
                        // delete invoice
                        break;

                    default:
                        System.out.println("You entered an invalid choice please try again.");
                }
            }
            else
            {
                in.nextLine();
                System.out.println("You entered an invalid choice please try again.");
            }
        }
    }


    public void createInvoice() {
        // 1. Ask user to enter id of the customer
        // 2. Ast user to enter the month he wants to create invoice for
        // 3. Run generation of invoices for that month if its not there yet
        // 4. Create invoice file, save in file and output to console window
        int customerId = helper.askUserToEnterCustomerID();
        int month = askUserToEnterMonth();

        invoiceDB.generateInvoicesIfNeeded(month);
    }

    public int askUserToEnterMonth() {
        boolean isValid = false;
        int month = -1;
        // getting month
        while (!isValid) {
            System.out.println("\nEnter the month (1 - 12), deliveries of which will be included in invoice: ");
            in.nextLine();
            if (in.hasNextInt()) {
                month = in.nextInt();
                // checking if id exists

                if (month > 0 && month < 13) {
                    isValid = true;
                } else {
                    System.out.println("Month " + month + " is invalid, please enter the correct month number (1-12)");
                }


            } else {
                System.out.println("Month should be the number (1 - 12)");

            }
        }

        return month;
    }
}
