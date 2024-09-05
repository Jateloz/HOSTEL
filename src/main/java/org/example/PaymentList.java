package org.example;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PaymentList {
    //attributes
    private ArrayList<Payment> pList;
    public final int MAX;

    //constructor to initialise the empty payment list and sets the max list size
    public PaymentList(int maxIn){
        pList = new ArrayList<>();
        MAX = maxIn;
    }

    /**Checks if the payment list is full
     * returns true if full and false otherwise
     */
    public boolean isFull(){

        return pList.size() == MAX;
    }

    /**gets the total number  of payments
     * returns the total number of payments currently in the list
     */
    public int getTotal(){

        return pList.size();
    }

    /**adds new payment to the end of the list
     * the payment to add
     * returns true if was added successfully and false otherwise
     */
    public boolean addPayment(Payment pIn){
        if (!isFull()){
            pList.add(pIn);
            return true;
        }
        else {

            return false;
        }
    }

    /**reads payment at a given position in the list
     * positionIn:the logical position in the list
     * returns payment at a given logical position in the list or null if no payment at the logical position
     */
    public  Payment getPayment(int positionIn){
        //check for valid logical position
        if (positionIn<1 || positionIn>getTotal()){
            //no object found
            return null;
        }
        else{
            //take one off logical position to get ArrayList position
            return pList.get(positionIn-1);
        }
    }

    /**calculates the total payments made by the tenant
     * returns the total value of payments recorded
     */
    public double calculateTotalPaid(){
        double totalPaid = 0;   //initialises total paid
        //loop through all payments
        for (Payment p : pList){
            //add current payment to running total
            totalPaid = totalPaid + p.getAmount();
        }
        return totalPaid;
    }
    @Override
    public String toString(){
        return pList.toString();

    }
}
