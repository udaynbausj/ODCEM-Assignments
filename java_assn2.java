import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

class User {
    String Name, Address;
    int age;
    char courses[] = {};

    User(String Name, String Address, int age, char courses[]) {
        this.Name = Name;
        this.Address = Address;
        this.age = age;
        this.courses = courses;
    }

}

class Display {
    void print_userdetails(HashMap<Integer, User> map) {
        if (map.size() == 0) {
            System.out.println("No data available : (");
        } else {

            System.out.println("User Details : ) ");
            for (Map.Entry itr : map.entrySet()) {
                int roll_num = (int) itr.getKey();
                User user = (User) itr.getValue();
                System.out.println("Roll number : " + roll_num);
                System.out.println("User's Fullname : " + user.Name);
                System.out.println("User's Address : " + user.Address);
                System.out.println("User's Age : " + user.age);
                System.out.println("User's Courses : ");
                for (int j = 0; j < user.courses.length; j++) {
                    System.out.println(user.courses[j]);
                }
            }
        }
    }
}

class java_assn2 {
    public static void main(String[] args) throws IOException {
        HashMap<Integer, User> map = new HashMap<>();
        System.out.println("Select one of the following options : ");
        System.out.println("1.Add User Details");
        System.out.println("2.Display User Details");
        System.out.println("3.Delete User Details");
        System.out.println("4.Save User Details");
        System.out.println("5.Exit");
        Scanner sc = new Scanner(System.in);

        Display Print = new Display();

        int choice = sc.nextInt();
        char choice2;
        if (choice == 1) {
            do {
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
                User user_object = new User(Fullname, Address, age, courses);
                map.put(roll_number, user_object);
                System.out.println("User added Successfully : ) ");
                Print.print_userdetails(map);

                System.out.println("Do you want to add more ? ");

                choice2 = sc.next().charAt(0);
            } while (choice2 == 'y');
        }
    }
}