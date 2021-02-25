import java.sql.SQLException;
import java.util.Scanner;

public class InvoiceMain
{
    static Scanner in = new Scanner(System.in);
    Invoice invoice = new Invoice();

    public void runInvoiceMenu() throws SQLException
    {
        InvoiceDB invoice = new InvoiceDB();

        // Variable used to store menu choice.
        int menuChoice = 0;

        // value that wil close the application.
        final int STOP_APP = 6;

        while (menuChoice != STOP_APP)
        {
            invoice.displayMainMenu();
            if (in.hasNextInt())
            {
                menuChoice = in.nextInt();
                switch (menuChoice)
                {
                    case 1:
                        invoice.getCustomerFromInvoice(DBconnection.stmt);
                        break;

                    case 2:
                        invoice.getCustomerNameFromId(DBconnection.stmt);
                        break;

//                    case 3:
//                        dav.createNewDeliveryArea(DBconnection.stmt);
//                        break;
//
//                    case 4:
//                        dav.editDeliveryArea(DBconnection.stmt);
//                        break;
//
//                    case 5:
//                        dav.deleteDeliveryArea(DBconnection.stmt);
//                        break;
//
//                    case 6:
//                        System.out.println("Returning to the Main Menu...");
//                        break;
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



    public static void main(String[] args) throws CustomerExceptionHandler {
        DBconnection.init_db(); //Opens the connection to the database.
        InvoiceDB invoice = new InvoiceDB();

        // Variable used to store menu choice.
        int menuChoice = 0;

        // value that wil close the application.
        final int STOP_APP = 6;

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

//                    case 4:
//                        dav.editDeliveryArea(DBconnection.stmt);
//                        break;
//
//                    case 5:
//                        dav.deleteDeliveryArea(DBconnection.stmt);
//                        break;

                    case 6:
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