package org.example;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TenantList {
    /**
     * collection class to hold a list of tenants
     * boys
     */
    private ArrayList<Tenant> tList;
    public final int MAX;


    /**
     * constructor initialises the empty tenant list and sets the max list size
     * maxIn the max number of tenants in the list
     */
    public TenantList(int maxIn) {
        tList = new ArrayList<>();
        MAX = maxIn;
    }

    /**
     * adds a new tenant to the list
     * tIn is the tenant to add
     * returns true if the tenant was added successfully
     */
    public boolean addTenant(Tenant tIn) {
        if (!isFull()) {
            tList.add(tIn);
            return true;
        } else {

            return false;
        }
    }

    /**
     * returns the tenant in the given room number
     * roomIn the room number of the tenant to remove
     * returns true if the tenant is removed
     */
    public boolean removeTenant(int roomIn) {
        Tenant findT = search(roomIn);//calls the search method
        if (findT != null) {//checks tenant is found at given room
            tList.remove(findT);//remove a given tenant
            return true;
        }
        else {

            return false;//no tenant is given room
        }
    }

    /**searches for the tenant in the given room number
     * roomIn the room number to search for
     * returns the tenant in the given room or null if no tenant in the given room
     */
    public  Tenant search(int roomIn){
        for (Tenant currentTenant : tList){
            //find tenant with given room number
            if (currentTenant.getRoom() == roomIn){

                return currentTenant;
            }
        }

        return null; //no tenant found with the given room number
    }

    /**reads the tenant at the given position in the list
     * positionIn the logical position of the tenant in the list
     * returns the tenant in the given logical position or null if  none
     */

    public Tenant getTenant(int positionIn){
        if (positionIn < 1 || positionIn > getTotal()){//checks for valid position
            return null;

        }
        else {
            //remove one from logical position to get ArrayList position
            return tList.get(positionIn - 1);
        }
    }

    /**reports on wheather or not the list is empty
     * returns true if the list is empty  and false otherwise
     */
    public boolean isEmpty(){

        return tList.isEmpty();
    }

    /**reports on whether or not the list is full
     * returns true if the list full and false otherwise
     */
    public boolean isFull(){

        return tList.size() == MAX;
    }

    /**gets the total of tenants
     * returns total number of tenants  currently in the list
     */
    public int getTotal(){

        return tList.size();
    }

    public String toString(){

        return tList.toString();
    }
}
