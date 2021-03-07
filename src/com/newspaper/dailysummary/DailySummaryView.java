package com.newspaper.dailysummary;

import com.newspaper.db.DBconnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class DailySummaryView {
    DailySummary ds = new DailySummary();
    //SQL Command to find revenue per publication on a specific date.
    Date date = new Date();
    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

    LocalDate weeklyDate = LocalDate.now().minusDays(7);


    Scanner in = new Scanner(System.in);


    public void displayDailySummary() {
        String str = "Select * from daily_summary";
        try {
            ResultSet rs = DBconnection.stmt.executeQuery(str);

            System.out.printf("\n%-12s %-20s %-15s %-10s \n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold");
            while (rs.next()) {
                int daily_summary_id = rs.getInt("daily_summary_id");
                String delivery_date = rs.getString("delivery_date");
                int total_revenue = rs.getInt("total_revenue");
                int publications_sold = rs.getInt("publications_sold");


                System.out.printf("%-12s %-20s %-15s %-10s \n", daily_summary_id, delivery_date, total_revenue, publications_sold);
            }

        } catch (
                SQLException sqle) {
            System.out.println("Error: failed to display daily summaries.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
    }

    //Doesn't need to be validated, automatic.
    public void dailyReport() throws SQLException {
        System.out.println("You choose Daily");


        Statement addNew = DBconnection.con.createStatement();
        boolean exists = ds.checkIfExists(modifiedDate);
        if (!exists) {
            createDailyReportByDate(modifiedDate);
            System.out.println("Daily report created for today: " + modifiedDate + "\n");

        }


        String str = "select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as publications_revenue from daily_summary where delivery_date='" + modifiedDate + "';";
        try {
            ResultSet rs = DBconnection.stmt.executeQuery(str);

            System.out.printf("\n%-12s %-20s %-15s %-20s %-25s \n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold", "Revenue");
            while (rs.next()) {
                int daily_summary_id = rs.getInt("daily_summary_id");
                String delivery_date = rs.getString("delivery_date");
                int total_revenue = rs.getInt("total_revenue");
                int publications_sold = rs.getInt("publications_sold");
                double publications_revenue = rs.getDouble("publications_revenue");

                publications_revenue = Math.round(publications_revenue * 100.0) / 100.0;

                System.out.printf("%-12s %-20s %-15s %-20s %-25s \n", daily_summary_id, delivery_date, total_revenue, publications_sold, publications_revenue);

            }

        } catch (
                SQLException sqle) {
            System.out.println("Error: failed to display all daily summaries.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }


    }

    public void weeklyReport() throws SQLException {


        String str = "select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as publications_revenue from daily_summary where delivery_date BETWEEN '" + weeklyDate + "' and '" + modifiedDate + "'";
        try {
            ResultSet rs = DBconnection.stmt.executeQuery(str);

            System.out.printf("\n%-12s %-20s %-15s %-20s %-25s\n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold", "Revenue");
            while (rs.next()) {
                int daily_summary_id = rs.getInt("daily_summary_id");
                String delivery_date = rs.getString("delivery_date");
                int total_revenue = rs.getInt("total_revenue");
                int publications_sold = rs.getInt("publications_sold");
                double publications_revenue = rs.getDouble("publications_revenue");

                publications_revenue = Math.round(publications_revenue * 100.0) / 100.0;

                System.out.printf("%-12s %-20s %-15s %-20s %-25s \n", daily_summary_id, delivery_date, total_revenue, publications_sold, publications_revenue);
            }

        } catch (
                SQLException sqle) {
            System.out.println("Error: failed to display all daily summaries.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
    }

    //validated
    public void monthlyReport() throws SQLException {


        Statement addNew = DBconnection.con.createStatement();
        System.out.println("Please select the year and month you want a report for in the format YYYY-MM");
        in.nextLine();
        String delivery_month = in.next();
        boolean validMonth = ds.validateMonth(delivery_month);
        if (validMonth) {

            String str = "select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as publications_revenue from daily_summary where delivery_date like '" + delivery_month + "%';";
            try {
                ResultSet rs = DBconnection.stmt.executeQuery(str);

                System.out.printf("\n%-12s %-20s %-15s %-20s %-25s \n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold", "Revenue per pub");
                while (rs.next()) {
                    int daily_summary_id = rs.getInt("daily_summary_id");
                    String delivery_date = rs.getString("delivery_date");
                    int total_revenue = rs.getInt("total_revenue");
                    int publications_sold = rs.getInt("publications_sold");
                    double publications_revenue = rs.getDouble("publications_revenue");

                    publications_revenue = Math.round(publications_revenue * 100.0) / 100.0;

                    System.out.printf("%-12s %-20s %-15s %-20s %-25s \n", daily_summary_id, delivery_date, total_revenue, publications_sold, publications_revenue);
                }

            } catch (
                    SQLException sqle) {
                System.out.println("Error: failed to display all summaries.");
                System.out.println(sqle.getMessage());
                System.out.println(str);
            }
        } else {
            System.out.println("Please enter a month in the format YYYY-MM.\nEnsure your date is in the range of 2011-2099 and the month is in range 1-12");
            monthlyReport();
        }


    }

    //validated
//    public void yearlyReport() throws SQLException {
//
//
//        Statement addNew = DBconnection.con.createStatement();
//        System.out.println("Please select the year you wish to have a report for, (2011-2099)");
//        String delivery_year = in.next();
//        boolean validYear = ds.validateYear(delivery_year);
//        if (validYear) {
//            String str = "select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as publications_revenue from daily_summary where delivery_date like '" + delivery_year + "%';";
//            try {
//                ResultSet rs = DBconnection.stmt.executeQuery(str);
//
//                System.out.printf("\n%-12s %-20s %-15s %-20s %-25s \n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold", "Revenue");
//                while (rs.next()) {
//                    int daily_summary_id = rs.getInt("daily_summary_id");
//                    String delivery_date = rs.getString("delivery_date");
//                    int total_revenue = rs.getInt("total_revenue");
//                    int publications_sold = rs.getInt("publications_sold");
//                    double publications_revenue = rs.getDouble("publications_revenue");
//
//                    publications_revenue = Math.round(publications_revenue * 100.0) / 100.0;
//
//                    System.out.printf("%-12s %-20s %-15s %-20s %-25s \n", daily_summary_id, delivery_date, total_revenue, publications_sold, publications_revenue);
//                }
//
//            } catch (
//                    SQLException sqle) {
//                System.out.println("Error: failed to display all Publications.");
//                System.out.println(sqle.getMessage());
//                System.out.println(str);
//            }
//        } else {
//            System.out.println("Please enter a year in the range of 2011-2099");
//            yearlyReport();
//        }
//
//
//    }

    //validated
    public void chooseDate() throws SQLException {


        Statement addNew = DBconnection.con.createStatement();
        System.out.println("Please select the date you want a report for in the format YYYY-MM-DD");
        String delivery_input = in.next();
        boolean validDate = ds.validateDate(delivery_input);
        if (validDate) {
            boolean exists = ds.checkIfExists(delivery_input);
            if (!exists) {
                //generate
                createDailyReportByDate(delivery_input);
                System.out.println("Report created for " + delivery_input);
            } else {
                String str = "select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as publications_revenue from daily_summary where delivery_date = '" + delivery_input + "';";
                try {
                    ResultSet rs = DBconnection.stmt.executeQuery(str);

                    System.out.printf("\n%-12s %-20s %-15s %-20s %-25s\n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold", "Revenue");
                    while (rs.next()) {
                        int daily_summary_id = rs.getInt("daily_summary_id");
                        String delivery_date = rs.getString("delivery_date");
                        int total_revenue = rs.getInt("total_revenue");
                        int publications_sold = rs.getInt("publications_sold");
                        double publications_revenue = rs.getDouble("publications_revenue");

                        publications_revenue = Math.round(publications_revenue * 100.0) / 100.0;

                        System.out.printf("%-12s %-20s %-15s %-20s %-25s \n", daily_summary_id, delivery_date, total_revenue, publications_sold, publications_revenue);
                    }

                } catch (
                        SQLException sqle) {
                    System.out.println("Error: failed to display all daily summaries.");
                    System.out.println(sqle.getMessage());
                    System.out.println(str);
                }
            }
        } else {
            System.out.println("Please enter your date in the format YYYY-MM-DD\n");
            chooseDate();
        }


    }

    //validated
    public void fromChosenDate() throws SQLException {


        System.out.println("Please select the date you want a report from in the format YYYY-MM-DD");
        String delivery_input = in.next();
        boolean validDate = ds.validateDate(delivery_input);
        if (validDate) {
            String str = "select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as publications_revenue from daily_summary where delivery_date BETWEEN '" + delivery_input + "' and '" + modifiedDate + "'";
            try {
                ResultSet rs = DBconnection.stmt.executeQuery(str);

                System.out.printf("\n%-12s %-20s %-15s %-20s %-25s\n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold", "Revenue");
                while (rs.next()) {
                    int daily_summary_id = rs.getInt("daily_summary_id");
                    String delivery_date = rs.getString("delivery_date");
                    int total_revenue = rs.getInt("total_revenue");
                    int publications_sold = rs.getInt("publications_sold");
                    double publications_revenue = rs.getDouble("publications_revenue");

                    publications_revenue = Math.round(publications_revenue * 100.0) / 100.0;

                    System.out.printf("%-12s %-20s %-15s %-20s %-25s \n", daily_summary_id, delivery_date, total_revenue, publications_sold, publications_revenue);
                }

            } catch (
                    SQLException sqle) {
                System.out.println("Error: failed to display all daily summaries.");
                System.out.println(sqle.getMessage());
                System.out.println(str);
            }
        } else {
            System.out.println("Please enter a date in the format YYYY-MM-DD\n");
            fromChosenDate();
        }
    }

    //validated
    public void chosenRange() throws SQLException {


        System.out.println("Please enter the first date in the format YYYY-MM-DD");
        String delivery_choice1 = in.next();
        boolean delchoice1 = ds.validateDate(delivery_choice1);
        if (delchoice1) {
            boolean exists1 = ds.checkIfExists(delivery_choice1);
            if (!exists1) {
                createDailyReportByDate(delivery_choice1);
                System.out.println("\nDaily report created for " + delivery_choice1);
            }
//            else if (exists1) {

            System.out.println("Please enter the second date in the format YYYY-MM-DD");
            String delivery_choice2 = in.next();
            boolean delchoice2 = ds.validateDate(delivery_choice2);
            if (delchoice2) {
                boolean exists2 = ds.checkIfExists(delivery_choice2);
                if (!exists2) {
                    createDailyReportByDate(delivery_choice2);
                    System.out.println("\nDaily report created for " + delivery_choice2);
                }
//                    else if(exists2){
                String str = "select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as publications_revenue from daily_summary where delivery_date BETWEEN '" + delivery_choice1 + "' and '" + delivery_choice2 + "'";
                try {
                    ResultSet rs = DBconnection.stmt.executeQuery(str);

                    System.out.printf("\n%-12s %-20s %-15s %-20s %-25s\n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold", "Revenue");
                    while (rs.next()) {
                        int daily_summary_id = rs.getInt("daily_summary_id");
                        String delivery_date = rs.getString("delivery_date");
                        int total_revenue = rs.getInt("total_revenue");
                        int publications_sold = rs.getInt("publications_sold");
                        double publications_revenue = rs.getDouble("publications_revenue");

                        publications_revenue = Math.round(publications_revenue * 100.0) / 100.0;

                        System.out.printf("%-12s %-20s %-15s %-20s %-25s \n", daily_summary_id, delivery_date, total_revenue, publications_sold, publications_revenue);
                    }

                } catch (
                        SQLException sqle) {
                    System.out.println("Error: failed to display all daily summaries.");
                    System.out.println(sqle.getMessage());
                    System.out.println(str);
                }
//                }
            } else {
                System.out.println("Please enter a correct date in the format YYYY-MM-DD");
                chosenRange();
            }
            //}
        } else {
            System.out.println("Please enter a correct date in the format YYYY-MM-DD");
            chosenRange();
        }

    }

    //validated
    public void revenueReport() throws SQLException {


        int menuChoice = 0;

        final int STOP_APP = 8;

        while (menuChoice != STOP_APP) {
            displayReportMenu(); //display the primary menu
            if (in.hasNextInt()) {
                //get the menu choice from the user
                menuChoice = in.nextInt();

                switch (menuChoice) {
                    case 1:
                        dailyReport();
                        break;
                    case 2:
                        weeklyReport();
                        break;
                    case 3:
                        monthlyReport();
                        break;
                    case 4:
                        chooseDate();
                        break;
                    case 5:
                        fromChosenDate();
                        break;
                    case 6:
                        chosenRange();
                        break;
                    case 7:
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


    public void displayReportMenu() {
        System.out.println("\nPlease select which report you would like");
        System.out.println("1: Daily");
        System.out.println("2: Weekly");
        System.out.println("3: Monthly");
        System.out.println("4: User entry");
        System.out.println("5: From chosen date");
        System.out.println("6: User Chosen Range");
        System.out.println("7: Main Menu");
        System.out.print("\nEnter your choice: ");
    }


    //validated
    public void monthlyReportFile() throws SQLException, FileNotFoundException {


        Statement addNew = DBconnection.con.createStatement();
        System.out.println("Please select the month you want a report for in the format YYYY-MM");
        String delivery_month = in.next();
        boolean validMonth = ds.validateMonth(delivery_month);
        if (validMonth) {

            String str = "select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as publications_revenue from daily_summary where delivery_date like '" + delivery_month + "%';";
            try {
                ResultSet rs = DBconnection.stmt.executeQuery(str);
                PrintWriter writer1 = null;
                writer1 = new PrintWriter(new File("C:\\Users\\jackw\\Desktop\\Saved files from monthly report test\\Report" + delivery_month + ".txt"));

                writer1.printf("\n%-12s %-20s %-15s %-20s %-25s\n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold", "Revenue Per Pub");


                for (int i = 0; i < 32; i++) {
                    while (rs.next()) {
                        int daily_summary_id = rs.getInt("daily_summary_id");
                        String delivery_date = rs.getString("delivery_date");
                        double total_revenue = rs.getDouble("total_revenue");
                        int publications_sold = rs.getInt("publications_sold");
                        double publications_revenue = rs.getDouble("publications_revenue");

                        publications_revenue = Math.round(publications_revenue * 100.0) / 100.0;


                        writer1.printf("%-12s %-20s %-15s %-20s %-25s\n", daily_summary_id, delivery_date, total_revenue, publications_sold, publications_revenue);

                    }
                }

                String sum = "select sum(total_revenue) as Revenue_Total,sum(publications_sold) as Publications_Sold_Total,sum(total_revenue)/sum(publications_sold) as Revenue_Per_Pub_Total from daily_summary where delivery_date like '" + delivery_month + "%'";

                ResultSet re = DBconnection.stmt.executeQuery(sum);

while(re.next()) {



    double revenueSum = re.getDouble("Revenue_Total");
    int publicationsSum = re.getInt("Publications_Sold_Total");
    double revPerPubTotal = re.getDouble("Revenue_Per_Pub_Total");
    revenueSum = Math.round(revenueSum * 100.0) / 100.0;
    revPerPubTotal = Math.round(revPerPubTotal * 100.0) / 100.0;

    writer1.println("---------------------------------------------------------------");
    writer1.printf("\n %-15s %-20s %-25s\n", "Revenue Total", "Pubs Sold Total", "Revenue Per Total");
    writer1.printf("%-18s %-23s %-28s\n", revenueSum, publicationsSum, revPerPubTotal);


    writer1.flush();
    writer1.close();
}



            }
            catch (
                    SQLException sqle) {
                System.out.println("Error: failed to display all summaries.");
                System.out.println(sqle.getMessage());
                System.out.println(str);
            }
            System.out.println("File created successfully!");

        } else {
            System.out.println("Please enter a month in the format YYYY-MM.\nEnsure your date is in the range of 2011-2099 and the month is in range 1-12");
            monthlyReportFile();
        }
    }


    int count=0;
    String st="";
    public DailySummary createDailyReportByDate(String date) throws SQLException {

        DailySummary dailySummary = new DailySummary();

        st = "select count(*) as total from daily_summary where delivery_date = '" + date +"'";
        ResultSet rss = DBconnection.stmt.executeQuery(st);

        count = 0;
        while (rss.next()) {
            count = rss.getInt("total");
        }

        if (count <= 0)
        {




        String str = "SELECT delivery.delivery_id, delivery.delivery_date, count(delivery.delivery_id) as \"publications_sold\", sum(publication.publication_cost+(publication.publication_cost*0.23))as \"total_revenue\"\n" +
                "FROM delivery, publication\n" +
                "WHERE delivery.publication_id = publication.publication_id AND delivery.delivery_status = 'delivered'\n" +
                "    AND delivery.delivery_date = '" + date + "';";





            try {
                ResultSet rs = DBconnection.stmt.executeQuery(str);


                while (rs.next()) {

                    int publications_sold = rs.getInt("publications_sold");
                    double total_revenue = rs.getDouble("total_revenue");
                    double revenue_per_publication = total_revenue / publications_sold;
                    dailySummary = new DailySummary(date, total_revenue, publications_sold, revenue_per_publication);

                    Statement addNew = DBconnection.con.createStatement();
                    addNew.executeUpdate("insert into daily_summary values(null, '" + date + "'," + total_revenue + ", " + publications_sold + ");");

                }

            } catch (
                    SQLException sqle) {
                System.out.println("Error: failed to display all daily summaries.");
                System.out.println(sqle.getMessage());
                System.out.println(str);
            }
        }
        else {

        }

        return dailySummary;
    }


    public static void main(String[] args) throws SQLException {
        DBconnection.init_db();
        DailySummaryView dailySummaryView = new DailySummaryView();
        dailySummaryView.populateDatabase();

    }


    public void populateDatabase() throws SQLException {

        for (int i = 1; i <= 28; i++) {
            createDailyReportByDate("2021-02-" + i);
        }

    }


}


