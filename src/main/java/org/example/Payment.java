package org.example;

public class Payment {
    private String month;
    private double amount;

    //constructor to initialise the vars
    public Payment(String monthIn,double amountIn){
        month = monthIn;
        amount =  amountIn;
    }

    //returns the month for which the payment was made

    public String getMonth() {
        return month;

    }

    //returns the amount paid
    public double getAmount(){
        return amount;

    }

    //converts the month and amount into string
    public String toString(){
        return "("+month+ ","+amount+")";


    }
}
