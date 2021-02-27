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
import java.util.Date;
import java.util.Scanner;

    public class DailySummaryView {
        //SQL Command to find revenue per publication on a specific date.
        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);


        Scanner in = new Scanner(System.in);

        DailySummary ds = new DailySummary();


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


    public void dailyReport() throws SQLException {
        System.out.println("You choose Daily");


        Statement addNew = DBconnection.con.createStatement();


        String str = "select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as publications_revenue from daily_summary where delivery_date='"+modifiedDate+"';";
        try {
            ResultSet rs = DBconnection.stmt.executeQuery(str);

            System.out.printf("\n%-12s %-20s %-15s %-10s %-20s \n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold", "Revenue");
            while (rs.next()) {
                int daily_summary_id = rs.getInt("daily_summary_id");
                String delivery_date = rs.getString("delivery_date");
                int total_revenue = rs.getInt("total_revenue");
                int publications_sold = rs.getInt("publications_sold");
                double publications_revenue = rs.getDouble("publications_revenue");

                publications_revenue = Math.round(publications_revenue * 100.0) / 100.0;

                System.out.printf("%-12s %-20s %-15s %-10s %-20s \n", daily_summary_id, delivery_date, total_revenue, publications_sold, publications_revenue);
            }

        } catch (
                SQLException sqle) {
            System.out.println("Error: failed to display all daily summaries.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }


    }
//Needs to be coded
    public void weeklyReport(){ }

    public void monthlyReport() throws SQLException {
        System.out.println("You choose Monthly");


        Statement addNew = DBconnection.con.createStatement();
        System.out.println("Please select the month you want a report for in the format YYYY-MM");
        String delivery_month=in.next();

        String str = "select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as publications_revenue from daily_summary where delivery_date like '"+delivery_month+"%';";
        try {
            ResultSet rs = DBconnection.stmt.executeQuery(str);

            System.out.printf("\n%-12s %-20s %-15s %-10s %-20s \n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold", "Revenue");
            while (rs.next()) {
                int daily_summary_id = rs.getInt("daily_summary_id");
                String delivery_date = rs.getString("delivery_date");
                int total_revenue = rs.getInt("total_revenue");
                int publications_sold = rs.getInt("publications_sold");
                double publications_revenue = rs.getDouble("publications_revenue");

                publications_revenue = Math.round(publications_revenue * 100.0) / 100.0;

                System.out.printf("%-12s %-20s %-15s %-10s %-20s \n", daily_summary_id, delivery_date, total_revenue, publications_sold, publications_revenue);
            }

        } catch (
                SQLException sqle) {
            System.out.println("Error: failed to display all summaries.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }


    }

    public void yearlyReport() throws SQLException {

        System.out.println("You choose Yearly");


        Statement addNew = DBconnection.con.createStatement();
        System.out.println("Please select the year you wish to have a report for");
    String delivery_year=in.next();

        String str = "select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as publications_revenue from daily_summary where delivery_date like '"+delivery_year+"%';";
        try {
            ResultSet rs = DBconnection.stmt.executeQuery(str);

            System.out.printf("\n%-12s %-20s %-15s %-10s %-20s \n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold", "Revenue");
            while (rs.next()) {
                int daily_summary_id = rs.getInt("daily_summary_id");
                String delivery_date = rs.getString("delivery_date");
                int total_revenue = rs.getInt("total_revenue");
                int publications_sold = rs.getInt("publications_sold");
                double publications_revenue = rs.getDouble("publications_revenue");

                publications_revenue = Math.round(publications_revenue * 100.0) / 100.0;

                System.out.printf("%-12s %-20s %-15s %-10s %-20s \n", daily_summary_id, delivery_date, total_revenue, publications_sold, publications_revenue);
            }

        } catch (
                SQLException sqle) {
            System.out.println("Error: failed to display all Publications.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }


}

    public void chooseDate() throws SQLException {

        System.out.println("You choose user selected date");


        Statement addNew = DBconnection.con.createStatement();
        System.out.println("Please select the date you want a report for in the format YYYY-MM-DD");
        String delivery_input=in.next();

                String str = "select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as publications_revenue from daily_summary where delivery_date = '" + delivery_input + "';";
                try {
                    ResultSet rs = DBconnection.stmt.executeQuery(str);

                    System.out.printf("\n%-12s %-20s %-15s %-10s %-20s \n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold", "Revenue");
                    while (rs.next()) {
                        int daily_summary_id = rs.getInt("daily_summary_id");
                        String delivery_date = rs.getString("delivery_date");
                        int total_revenue = rs.getInt("total_revenue");
                        int publications_sold = rs.getInt("publications_sold");
                        double publications_revenue = rs.getDouble("publications_revenue");

                        publications_revenue = Math.round(publications_revenue * 100.0) / 100.0;

                        System.out.printf("%-12s %-20s %-15s %-10s %-20s \n", daily_summary_id, delivery_date, total_revenue, publications_sold, publications_revenue);
                    }

                } catch (
                        SQLException sqle) {
                    System.out.println("Error: failed to display all daily summaries.");
                    System.out.println(sqle.getMessage());
                    System.out.println(str);
                }




    }

    public void fromChosenDate() throws SQLException{
        System.out.println("You choose user selected date to today");



        System.out.println("Please select the date you want a report from in the format YYYY-MM-DD");
        String delivery_input=in.next();

        String str = "select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as publications_revenue from daily_summary where delivery_date BETWEEN '"+delivery_input+"' and '"+modifiedDate+"'";
        try {
            ResultSet rs = DBconnection.stmt.executeQuery(str);

            System.out.printf("\n%-12s %-20s %-15s %-10s %-20s \n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold", "Revenue");
            while (rs.next()) {
                int daily_summary_id = rs.getInt("daily_summary_id");
                String delivery_date = rs.getString("delivery_date");
                int total_revenue = rs.getInt("total_revenue");
                int publications_sold = rs.getInt("publications_sold");
                double publications_revenue = rs.getDouble("publications_revenue");

                publications_revenue = Math.round(publications_revenue * 100.0) / 100.0;

                System.out.printf("%-12s %-20s %-15s %-10s %-20s \n", daily_summary_id, delivery_date, total_revenue, publications_sold, publications_revenue);
            }

        } catch (
                SQLException sqle) {
            System.out.println("Error: failed to display all daily summaries.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }


    }

    public void chosenRange(){
        System.out.println("You choose user chosen range");



        System.out.println("Please select the date you want a report from in the format YYYY-MM-DD");
        String delivery_choice1=in.next();
        System.out.println("Please select the date you want a report to in the format YYYY-MM-DD");
        String deliver_choice2=in.next();

        String str = "select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as publications_revenue from daily_summary where delivery_date BETWEEN '"+delivery_choice1+"' and '"+deliver_choice2+"'";
        try {
            ResultSet rs = DBconnection.stmt.executeQuery(str);

            System.out.printf("\n%-12s %-20s %-15s %-10s %-20s \n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold", "Revenue");
            while (rs.next()) {
                int daily_summary_id = rs.getInt("daily_summary_id");
                String delivery_date = rs.getString("delivery_date");
                int total_revenue = rs.getInt("total_revenue");
                int publications_sold = rs.getInt("publications_sold");
                double publications_revenue = rs.getDouble("publications_revenue");

                publications_revenue = Math.round(publications_revenue * 100.0) / 100.0;

                System.out.printf("%-12s %-20s %-15s %-10s %-20s \n", daily_summary_id, delivery_date, total_revenue, publications_sold, publications_revenue);
            }

        } catch (
                SQLException sqle) {
            System.out.println("Error: failed to display all daily summaries.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }


    }

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
                    case 2:
                        weeklyReport();
                        break;
                    case 3:
                        monthlyReport();
                        break;
                    case 4:
                         yearlyReport();
                        break;
                    case 5:
                        chooseDate();
                            break;
                    case 6:
                        fromChosenDate();
                            break;
                    case 7:
                        chosenRange();
                            break;
                    case 8:
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
        System.out.println("4: Yearly");
        System.out.println("5: User entry");
        System.out.println("6: From chosen date");
        System.out.println("7: User Chosen Range");
        System.out.println("8: Main Menu");
        System.out.print("\nEnter your choice: ");
    }


    //Monthly report. Seperate method from method 1
    public void monthlyReportFile() throws SQLException, FileNotFoundException {
        System.out.println("You choose Monthly");


        Statement addNew = DBconnection.con.createStatement();
        System.out.println("Please select the month you want a report for in the format YYYY-MM");
        String delivery_month=in.next();

        String str = "select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as publications_revenue from daily_summary where delivery_date like '"+delivery_month+"%';";
        try {
            ResultSet rs = DBconnection.stmt.executeQuery(str);
            PrintWriter writer1 =null;
            writer1 = new PrintWriter(new File("C:\\Users\\jackw\\Desktop\\Saved files from monthly report test\\Report"+delivery_month+".txt"));

            writer1.printf("\n%-12s %-20s %-15s %-20s %-20s \n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold", "Revenue Per Pub");


            for(int i=0;i<32;i++)
            {
            while (rs.next()) {
                int daily_summary_id = rs.getInt("daily_summary_id");
                String delivery_date = rs.getString("delivery_date");
                double total_revenue = rs.getDouble("total_revenue");
                int publications_sold = rs.getInt("publications_sold");
                double publications_revenue = rs.getDouble("publications_revenue");

                publications_revenue = Math.round(publications_revenue * 100.0) / 100.0;


                    writer1.printf("%-12s %-20s %-15s %-20s %-20s \n", daily_summary_id, delivery_date, total_revenue, publications_sold, publications_revenue);

            }

                writer1.flush();
                writer1.close();
            }

        } catch (
                SQLException sqle) {
            System.out.println("Error: failed to display all summaries.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
        System.out.println("File created successfully!");

    }
}


