package com.example.helloworld;
import java.util.*;
import java.io.*;

//following are the JCommander library's imports
import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;



//following is a Detail's class which has a blueprint of an item and it's assets

class DETAILS{
    String name,type;
    double price;
    int quantity;
    double Total_price;
    DETAILS(String name,int quantity,double price,String type,double Total_price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
        this.Total_price = Total_price;
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
    double price;
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
        double total_price=0;
        //Creating a Datastructure to add new details
        if(type.equals("manufactured")){
            total_price = price + 0.125*price + 0.02*(price + 0.125*(price));
        }else if(type.equals("raw")){
            total_price =  price + 0.125*(price);
        }else{
            total_price =  price + 0.1*(price) + 0.125*(price);
            if(total_price<=100){
                total_price+=5;
            }else if(total_price>100 && total_price<=200){
                total_price+=10;
            }else{
                total_price += (0.05*total_price);
            }
        }

        Vector<DETAILS>detailsArray = new Vector<>();
        DETAILS d = new DETAILS(Name,quantity,price,type,total_price);

        //add user details
        detailsArray.add(d);
        System.out.println("Do you want to add more ? (y/n)");
        //takes  the input choice
        choice = (char) br.read();
        char choice_end;
        if(choice=='y') {


            do {
                float Price=0,Sales_tax = 0;int Quantity=0,type_choice = 0;String Name,Type="";
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


                            type_choice = Integer.parseInt(br.readLine());

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
                if(type_choice == 1){
                    total_price = Price + 0.125*Price;
                }else if(type_choice==2){
                    total_price = Price + 0.125*Price + 0.02*(Price + 0.125*(Price));
                }else{
                    total_price =  price + 0.1*(price) + 0.125*(price);
                    if(total_price<=100){
                        total_price+=5;
                    }else if(total_price>100 && total_price<=200){
                        total_price+=10;
                    }else{
                        total_price += (0.05*total_price);
                    }
                }
                DETAILS new_d  = new DETAILS(Name,Quantity,Price,Type,total_price*quantity);
                detailsArray.add(new_d);
            } while (choice_end != 'n');

            System.out.println("The items registered so far.....");
            Iterator itr = detailsArray.iterator();
            while(itr.hasNext()){
                DETAILS dt = (DETAILS) itr.next();
                System.out.println(dt.name + " " + dt.type+ " "+dt.price+" "+dt.quantity+" "+dt.Total_price);
            }

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



