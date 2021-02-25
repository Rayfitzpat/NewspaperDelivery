package com.newspaper.dailysummary;

import com.newspaper.db.DBconnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DailySummaryView {
    //SQL Command to find revenue per publication on a specific date.
    //select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as Revenue from daily_summary where delivery_date='2021-02-08';
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
            System.out.println("Error: failed to display all Publications.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
    }




























    public void revenueReport() throws SQLException {
        System.out.println("Please select which report you would like");
        System.out.println("1: Daily");
        System.out.println("2: Weekly");
        System.out.println("3: Monthly");
        System.out.println("4: Yearly");
        System.out.println("5: User entry");
        System.out.println("6: From chosen date");
        int reportChoice = in.nextInt();
        String dateToday = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        if(reportChoice==1)
        {
            System.out.println("You choose Daily");


            Statement addNew = DBconnection.con.createStatement();


            String str = "select daily_summary_id,delivery_date, total_revenue, publications_sold, total_revenue/publications_sold as publications_revenue from daily_summary where delivery_date='2021-02-08'";
            try {
                ResultSet rs = DBconnection.stmt.executeQuery(str);

                System.out.printf("\n%-12s %-20s %-15s %-10s %-10s \n", "Summary ID", "Delivery Date", "Total revenue", "Publications Sold", "Revenue");
                while (rs.next()) {
                    int daily_summary_id = rs.getInt("daily_summary_id");
                    String delivery_date = rs.getString("delivery_date");
                    int total_revenue = rs.getInt("total_revenue");
                    int publications_sold = rs.getInt("publications_sold");
                    double publications_revenue = rs.getDouble("publications_revenue");

                    System.out.printf("%-12s %-20s %-15s %-10s \n", daily_summary_id, delivery_date, total_revenue, publications_sold, publications_revenue);
                }

            } catch (
                    SQLException sqle) {
                System.out.println("Error: failed to display all Publications.");
                System.out.println(sqle.getMessage());
                System.out.println(str);
            }
            System.out.println("done");



        }

        else if(reportChoice==2){
            System.out.println("You have chosen Weekly");


        }

        else if(reportChoice==3){
            System.out.println("You have chosen Monthly");
        }

        else if(reportChoice==4){
            System.out.println("You have chosen Year");
        }

        else if(reportChoice==5){
            System.out.println("Please enter the date you want to view a report from.Please use the format YYYY-MM-DD");
            String dateChoice = in.next();
            Statement addNew = DBconnection.con.createStatement();
            addNew.executeQuery("select * from daily_summary where delivery_date = '"+dateChoice+"'");


        }

        else if(reportChoice==6){
            System.out.println("Please enter the date you want to view a report from.Please use the format YYYY-MM-DD");
            String dateChoice = in.next();
            Statement addNew = DBconnection.con.createStatement();
            addNew.executeQuery("select * from daily_summary where delivery_date BETWEEN'"+dateChoice+"' AND '"+dateToday+"'");
        }

    }





    public void monthlyReport(){
        System.out.println("Tessssst");
    }


}
