package com.newspaper.dailysummary;

public class DailySummary {

    private String delivery_date;
    private double total_revenue;
    private int publications_sold;
    private int daily_summary_id;
    private double publications_revenue;

    public void DailySummaryConstructor(String delivery_date, double total_revenue, int publications_sold, int daily_summary_id) {
        this.delivery_date = delivery_date;
        this.total_revenue = total_revenue;
        this.publications_sold = publications_sold;
        this.daily_summary_id = daily_summary_id;
        this.publications_revenue = publications_revenue;
    }


    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public void setTotal_revenue(double total_revenue) {
        this.total_revenue = total_revenue;
    }

    public void setPublications_sold(int publications_sold) {
        this.publications_sold = publications_sold;
    }

    public void setDaily_summary_id(int daily_summary_id) {
        this.daily_summary_id = daily_summary_id;
    }


    public int getDaily_summary_id() {
        return daily_summary_id;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public double getTotal_revenue() {
        return total_revenue;
    }

    public int getPublications_sold() {
        return publications_sold;
    }

    public double getPublications_revenue() {
        return publications_revenue;
    }

    public double setPublications_Revenue() {
        return publications_revenue;
    }


    public static void displayDailySummaryMainMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1: Revenue Report");
        System.out.println("2: Save Monthly Report\n");
        System.out.print("Enter your choice: ");
    }


    public static boolean validateDate(String date) {
        if (date.matches("^20[0-2][0-9]-((0[1-9])|(1[0-2]))-([0-2][1-9]|3[0-1])$")) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean validateYear(String year) {
        if (year.matches("^20((1[1-9])|([2-9][0-9]))$")) {

            return true;
        } else
            return false;
    }

    public static boolean validateMonth(String month) {
        if (month.matches("^20((1[1-9])|([2-9][0-9]))\\-(0[1-9]|1[012])")) {
            return true;
        } else
            return false;
    }


}
