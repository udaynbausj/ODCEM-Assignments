package com.example.helloworld;
import java.util.*;
import java.io.*;

//following are the JCommander library's imports
import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;



//following is a Detail's class which has a blueprint of an item and it's assets

class DETAILS{
    String name,type;
    float price;
    int quantity;
    DETAILS(String name,int quantity,float price,String type){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }
}



class User_Choice_1 extends Exception{
    User_Choice_1(String s){
        super(s);
    }
}

class User_choice_rem extends Exception{
    User_choice_rem(String s){
        super(s);
    }
}

class type_chice extends Exception{
    type_chice(String s){
        super(s);
    }
}



public class Assaignment1 {

    //Below code will allow user to enter the details from commandline

    @Parameter(names={"-name"})
    String Name;
    @Parameter(names={"-price"})
    float price;
    @Parameter(names={"-quantity"})
    int quantity;
    @Parameter(names={"-type"},required = true)
    String type;

    public static void main(String[] args) throws IOException{
        Assaignment1 main = new Assaignment1();
        JCommander.newBuilder().addObject(main).build().parse(args);
        main.run();
    }

    public void run() throws IOException{

        //BufferedReader input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char choice;

        //Creating a Datastructure to add new details
        Vector<DETAILS>detailsArray = new Vector<>();
        DETAILS d = new DETAILS(Name,quantity,price,type);

        //add user details
        detailsArray.add(d);
        System.out.println("Do you want to add more ? (y/n)");
        //takes  the input choice
        choice = (char) br.read();
        char choice_end;
        if(choice=='y') {


            do {
                float Price=0;int Quantity=0;String Name,Type="";
                HashSet<Integer> choices = new HashSet<>();
                System.out.println("\nEnter the name of the items : ");
                String name = br.readLine();
                Name = br.readLine();
                //lets provide the option to enter the item's details as he wishes;

                while(choices.size()<3) {

                    System.out.println("Please select one of the following : ");
                    System.out.println("1.Type\n2.Price\n3.Quantity");

                    //input from user;
                    int choice1 = Integer.parseInt(br.readLine());
                    if (choices.contains(choice1) == false) {
                        if(choice1>0 && choice1<=3)
                            choices.add(choice1);
                        try {
                            validate_user_choice2(choice1);
                        } catch (Exception e) {
                            System.out.println("Exception Occured : " + e);
                        }

                        if (choice1 == 1) {
                            System.out.println("\nYou have Selected" + "'Type'\n");
                            System.out.println("Plese select one of the following ; ) ");
                            System.out.println("1.Raw\n2.Manufactured\n3.Imported");


                            int type_choice = Integer.parseInt(br.readLine());

                            try {
                                validate_user_type_choice(type_choice);
                            } catch (Exception e) {
                                System.out.println("Not a valid choice" + e);
                            }



                            if (type_choice == 1) {
                                Type = "Raw";
                            } else if (type_choice == 2) {
                                Type = "Manufactured";
                            } else {
                                Type = "Imported";
                            }
                        } else if (choice1 == 2) {
                            System.out.println("You have Choosen 'Price' : ");
                            System.out.println("\nPLease enter the price of the item : ");
                            Price = Float.parseFloat(br.readLine());
                        } else if(choice1==3){
                            System.out.println("You have Choosen 'Quantity' : ");
                            System.out.println("\nPlease enter the Quantity : ");
                            Quantity = Integer.parseInt(br.readLine());
                        }
                    } else {
                        System.out.println("You already entered the details of this type : )");
                    }

                }
                System.out.println("Do you want to add more ? (y/n)");
                //takes  the input choice
                choice_end = (char) br.read();
                try {
                    validate_user_choice1(choice_end);
                } catch (Exception e) {
                    System.out.println("Exception Occured : " + e);
                }

                DETAILS new_d  = new DETAILS(Name,Quantity,Price,Type);
                detailsArray.add(new_d);
            } while (choice_end != 'n');
        }else{
            System.out.println("Thankyou :)");
        }


    }

    static void validate_user_choice1(char c) throws User_Choice_1{
        if(c!='y' && c!='n'){
            throw new User_Choice_1("Not valid Choice");
        }
    }

    static void validate_user_choice2(int c) throws User_choice_rem{
        if(c > 3 || c < 1){
            throw new User_choice_rem("Not valid Choice");
        }
    }

    static void validate_user_type_choice(int c) throws type_chice{
        if(c < 1 || c > 3){
            throw new type_chice("Not a valid choice");
        }
    }


}



