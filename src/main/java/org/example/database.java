package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class database {


        //define connection String
        static String url = "jdbc:mysql://localhost/maseno";
        public static void main(String[] args){
            try{
                //engage the driver
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url,"root","");

                //prepare sql statements for execution
                Statement stm = conn.createStatement();
                String sql = "insert into student(room,name) values(ccs/00040/021,Jatelo,56)";


                //create the sql statements
                stm.executeUpdate(sql);

            }catch (Exception e){

                e.printStackTrace();
            }
        }
    }