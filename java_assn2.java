import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

class User implements java.io.Serializable {
    String Name, Address;
    int age, roll_number;
    char courses[] = {};

}

class Display {
    void print_userdetails(HashMap<Integer, User> map) {
        if (map.size() == 0) {
            System.out.println("No data available : (");
        } else {
            System.out.println();
            for (int i = 0; i < 75; i++) {
                System.out.print("-");
            }
            System.out.println();
            System.out.print("Name\t");
            System.out.print("Roll Number\t");
            System.out.print("Age\t");
            System.out.print("Address\t\t\t");
            System.out.print("Courses\n\n");

            // System.out.println("User Details : ) ");
            for (Map.Entry itr : map.entrySet()) {
                int roll_num = (int) itr.getKey();
                User user = (User) itr.getValue();
                System.out.print(user.Name + "\t\t");

                System.out.print(roll_num + "\t");

                System.out.print(user.age + "\t");

                System.out.print(user.Address + "\t");

                for (int j = 0; j < user.courses.length; j++) {
                    System.out.print(user.courses[j] + " ");
                }
            }
        }
    }
}

class java_assn2 {
    public static void main(String[] args) throws IOException {
        HashMap<Integer, User> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        User user_object = new User();
        Display Print = new Display();
        int choice;
        do {
            System.out.println("Select one of the following options : ");
            System.out.println("1.Add User Details");
            System.out.println("2.Display User Details");
            System.out.println("3.Delete User Details");
            System.out.println("4.Save User Details");
            System.out.println("5.Exit");

            choice = sc.nextInt();
            char choice2;

            if (choice == 1) {
                System.out.println("\nPlease Enter Full Name : ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String Fullname = br.readLine();
                if (Fullname == "") {
                    System.out.println("Wrong Input");
                    return;
                }
                System.out.println("Please enter your Address : )");
                String Address = br.readLine();
                if (Address == "") {
                    System.out.println("Wrong Input");
                    return;
                }
                System.out.println("Please enter your age : )");
                int age = sc.nextInt();
                if (age <= 0 || age > 120) {
                    System.out.println("Wrong Input");
                }
                System.out.println("Please enter your roll number : ) ");
                int roll_number = sc.nextInt();
                if (roll_number <= 0 || roll_number >= 1000) {
                    System.out.println("Wrong Input");
                }
                System.out.println("Please enter following informations : ) ");
                char courses[] = new char[6];
                System.out.println("How many courses have you registered ? ");
                int num_courses = sc.nextInt();
                if (num_courses < 4) {
                    System.out.println("No sufficient Courses : )");
                    return;
                } else {
                    System.out.println("Enter the courses One by one : ) ");
                    for (int i = 0; i < num_courses; i++) {
                        courses[i] = sc.next().charAt(0);
                    }
                }
                // lets prepare object and prepopulate it with user input : )
                // User user_object = new User(Fullname, Address, age, courses,roll_number);
                user_object.Name = Fullname;
                user_object.Address = Address;
                user_object.age = age;
                user_object.courses = courses;
                user_object.roll_number = roll_number;
                map.put(roll_number, user_object);
                System.out.println("User added Successfully : ) ");
                // Print.print_userdetails(map);

                // System.out.println("Do you want to add more ? ");

                // choice2 = sc.next().charAt(0);
            } else if (choice == 2) {
                Print.print_userdetails(map);
            } else if (choice == 3) {
                if (map.size() > 0) {
                    System.out.println("Please enter the roll number of the user to delete his/her details : ");
                    int deleteRollNumber = sc.nextInt();
                    map.remove(deleteRollNumber);
                    System.out.println("User's Details are Purged : | ");
                } else {
                    System.out.println("Nothing to Delete : () ");
                    return;
                }
            } else if (choice == 4) {
                // serialization of JAVA goes here : )
                if (map.size() > 0) {
                    System.out.println("Saving the current state : ) ");
                    String filename = "Assaignment2_OneDirect.txt";
                    FileOutputStream file = new FileOutputStream(filename);
                    ObjectOutputStream out = new ObjectOutputStream(file);
                    out.writeObject(user_object);
                    out.close();
                    file.close();
                } else {
                    System.out.println("Nothing to save :(");
                }
            } else {
                System.out.println("Thanks for choosing our services ; )");
                return;
            }
        } while (choice != 5);
    }
}