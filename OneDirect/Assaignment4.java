import java.sql.*;
import java.util.*;

import javax.xml.soap.Detail;

class ITEM_DETAILS {
    String name, type;
    double price;
    int quantity;
    double total;

    ITEM_DETAILS(String name, String type, double price, int quantity, double total) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }
}

class Mythread1 implements Runnable {
    String name;
    Thread t;

    Mythread1(String ThreadName) {
        name = ThreadName;
        t = new Thread(this, name);
        System.out.println("New Thread : " + t.getName());
        t.start();
    }

    Vector<ITEM_DETAILS> Details = new Vector<>();

    public void run() {
        try {
            System.out.println("JDBC CODE HERE ....Handled by ThreadID ");

            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
                Statement smt = con.createStatement();
                ResultSet rs = smt.executeQuery("select * from item_details");
                while (rs.next()) {
                    ITEM_DETAILS d = new ITEM_DETAILS(rs.getString(1), rs.getString(4), rs.getInt(2), rs.getInt(3), 0);
                    System.out
                            .println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getInt(3) + " " + rs.getString(4));

                    // we shall calculate the total here;
                    double TOTAL = 0;
                    d.type = d.type.toLowerCase();
                    if (d.type.equals("raw")) {
                        TOTAL = d.price + 0.125 * (d.price);
                    } else if (d.type.equals("manufactured")) {
                        TOTAL = d.price + 0.125 * d.price + 0.02 * (d.price + 0.125 * (d.price));

                    } else if (d.type.equals("imported")) {
                        TOTAL = d.price + 0.1 * (d.price) + 0.125 * (d.price);
                        if (TOTAL <= 100) {
                            TOTAL += 5;
                        } else if (TOTAL > 100 && TOTAL <= 200) {
                            TOTAL += 10;
                        } else {
                            TOTAL += (0.05 * TOTAL);
                        }
                    }

                    d.total = TOTAL;
                    System.out.println("\n\nTotal Cost of the item " + d.name + " : " + d.total);
                    Details.add(d);
                }

                con.close();
            } catch (Exception e) {
                System.out.println("Exception Occured at Database connection and Retrieval " + e);
            }

        } catch (Exception e) {
            System.out.println("Exception Occured : " + e);
        }
    }
}

class Mythread2 implements Runnable {
    String name;
    Thread t;

    Mythread2(String ThreadName) {
        name = ThreadName;
        t = new Thread(this, name);
        System.out.println("New Thread : " + t.getName());
        t.start();
    }

    public void run() {
        try {
            System.out.println("Retrieve from collection code is Handled by ThreadID :");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Exception Occured : " + e);
        }
    }
}

public class Threads {
    public static void main(String[] args) {
        new Mythread1("ThreadOne");
        new Mythread2("ThreadTwo");
        System.out.println("Main Thread Exiting : ");
    }
}
