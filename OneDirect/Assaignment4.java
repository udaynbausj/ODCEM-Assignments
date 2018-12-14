package com.example.helloworld;

import java.sql.*;

public class Assaignment2 implements Runnable{
    public void run(){
        //Here goes JDBC Code : )
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sonoo",
                    "root",
                    "root"
            );
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("select * from emp");
            while(rs.next()){
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));

            }

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        Assaignment2 as2 = new Assaignment2();
        Thread t1 = new Thread(as2);

        System.out.println("The Thread named : "+t1.getName()+" with ID : "+t1.getId()+" started Execution ");

        t1.start();

    }
}
