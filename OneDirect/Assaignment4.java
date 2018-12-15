import java.sql.*;

class Mythread1 implements Runnable {
    String name;
    Thread t;

    Mythread1(String ThreadName) {
        name = ThreadName;
        t = new Thread(this, name);
        System.out.println("New Thread : " + t.getName());
        t.start();
    }

    public void run() {
        try {
            System.out.println("JDBC CODE HERE ....Handled by ThreadID ");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
                Statement smt = con.createStatement();
                ResultSet rs = smt.executeQuery("select * from item_details");
                while (rs.next()) {
                    System.out
                            .println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getInt(3) + " " + rs.getString(4));
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
