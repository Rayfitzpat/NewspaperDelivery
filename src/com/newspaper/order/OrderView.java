package com.newspaper.order;

import com.newspaper.db.DBconnection;

public class OrderView {
    public static void main(String[] args) {
        DBconnection.init_db();
    }



    public static void displayMainOrderMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1: Display all Orders");
        System.out.println("2: Display an Order by customer ID ");
        System.out.println("3: Add new Order");
        System.out.println("4: Edit Order");
        System.out.println("5: Delete Order");
        System.out.println("6: Return to Main Menu\n");
        System.out.print("Enter your choice: ");
    }
}
