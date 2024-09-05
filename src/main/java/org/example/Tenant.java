package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Tenant {
    private String name;
    private int room;
    private  PaymentList payments;
    public static final int MAX = 12;

    /**constructor initialises the name,room number of the tenant
     * sets the  payment made to the empty list
     */
    public Tenant(String nameIn,int roomIn){
        name = nameIn;
        room = roomIn;
        payments = new PaymentList(MAX);
    }

    /**records the payment for the tenant
     * yeah
     */

    public void makePayment(Payment paymentIn){

        payments.addPayment(paymentIn); //calls paymentList method
    }

    /**reads the name of the tenant
     * return name of the tenant
     */

    public int getRoom(){

        return room;
    }

    /**gets the name of the tenant
     * returns his/her name
     */
    public String getName(){
        return name;
    }

    /**reads the payment of the tenant
     * returns the payment by the tenant
     */
    public PaymentList getPayments(){
        return payments;

    }

    public String toString(){

        return name+", "+room+", "+payments;
    }
}
