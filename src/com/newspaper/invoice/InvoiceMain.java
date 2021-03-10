package com.newspaper.invoice;

import com.newspaper.customer.CustomerExceptionHandler;
import com.newspaper.db.DBconnection;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class InvoiceMain
{
    static Scanner in = new Scanner(System.in);
    Invoice invoice = new Invoice();

    public void runInvoiceMenu() throws SQLException, FileNotFoundException {
        DBconnection.init_db(); //Opens the connection to the database.
        InvoiceDB invoice = new InvoiceDB();

        // Variable used to store menu choice.
        int menuChoice = 0;

        // value that wil close the application.
        final int STOP_APP = 8;

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
                        invoice.getCusAddressFromInvoiceId(DBconnection.stmt);
                        break;

                    case 4:
                        invoice.printPublications(DBconnection.stmt);
                        break;

                    case 5:
                        invoice.deleteInvoice(DBconnection.stmt);
                        break;

                    case 6:
                        invoice.paidUpdate(DBconnection.stmt);
                        break;

                    case 7:
                        invoice.generateInvoice(DBconnection.stmt);
                        break;

                    case 8:
                        return;

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




    public static void main(String[] args) throws SQLException, FileNotFoundException {
        DBconnection.init_db(); //Opens the connection to the database.
        InvoiceDB invoice = new InvoiceDB();

        // Variable used to store menu choice.
        int menuChoice = 0;

        // value that wil close the application.
        final int STOP_APP = 8;

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
                        invoice.getCusAddressFromInvoiceId(DBconnection.stmt);
                        break;

                    case 4:
                        invoice.printPublications(DBconnection.stmt);
                        break;

                    case 5:
                        invoice.deleteInvoice(DBconnection.stmt);
                        break;

                    case 6:
                        invoice.paidUpdate(DBconnection.stmt);
                        break;

                    case 7:
                        invoice.generateInvoice(DBconnection.stmt);
                        break;

                    case 8:
                        return;

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
}
