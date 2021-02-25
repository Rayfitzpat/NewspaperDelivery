import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DailySummaryMain {

    public void dailySummaryMainPage() throws  SQLException{

        Scanner in  = new Scanner(System.in);

        DailySummary ds = new DailySummary();

        DailySummaryView dsv = new DailySummaryView();

        int menuChoice = 0;

        final int STOP_APP = 7;

        while (menuChoice != STOP_APP) {
            ds.displayDailySummaryMainMenu();//display the primary menu
            if (in.hasNextInt()) {
                //get the menu choice from the user
                menuChoice = in.nextInt();

                switch (menuChoice) {
                    case 1:
                        dsv.revenueReport();
                        break;
                    case 2:
                        dsv.monthlyReport();
                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:
                        return;
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

    public static void main(String[] args) throws SQLException {

        DBconnection.init_db();  // open the connection to the database
        Scanner in  = new Scanner(System.in);

        DailySummary ds = new DailySummary();

        DailySummaryView dsv = new DailySummaryView();

        int menuChoice = 0;

        final int STOP_APP = 7;

        while (menuChoice != STOP_APP) {
            ds.displayDailySummaryMainMenu(); //display the primary menu
            if (in.hasNextInt()) {
                //get the menu choice from the user
                menuChoice = in.nextInt();

                switch (menuChoice) {
                    case 1:
                        dsv.revenueReport();

                    case 2:
                        dsv.monthlyReport();
                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:
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



