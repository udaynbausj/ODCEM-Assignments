package com.example.helloworld;
import java.io.IOException;
import java.util.Scanner;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;

import javax.swing.text.html.HTMLDocument;
import javax.xml.soap.Detail;
import java.util.*;
import java.io.*;

class details{
    String name,type;
    float price;
    int quantity;
    details(String name_,String type_,float price_,int quantity){
        this.name = name_;
        this.type = type_;
        this.price = price_;
        this.quantity = quantity;
    }
}


public class HelloWorld {
    @Parameter(names={"-name"})
    String Name;
    @Parameter(names={"-price"})
    float price;
    @Parameter(names={"-quantity"})
    int quantity;
    @Parameter(names={"-type"},required = true)
    String type;
    public static void main(String[] args) throws IOException {
        HelloWorld main = new HelloWorld();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        //we shall implement a data structure with name as key and other vals as Values;
        main.run();
        //System.out.println(System.getProperty(sysProp));
    }
    public void run() throws IOException{
        Vector<details>detailsArray = new Vector<>();
        details d = new details(Name,type,price,quantity);
        detailsArray.add(d);
        System.out.println("Do you want one more item ? ");
        Scanner sc = new Scanner(System.in);
        char choice ;
        System.out.println("Do you want to add more ? ");
        choice = sc.next().charAt(0);
        do{
            String name,type;
            int quantity;
            float price;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            name = br.readLine();
            type = br.readLine();

            quantity = Integer.parseInt(br.readLine());
            price = Float.parseFloat(br.readLine());
            d = new details(name,type,price,quantity);
            detailsArray.add(d);
            System.out.println("Do you want to add more ? ");
            choice = sc.next().charAt(0);
        }while(choice=='y');


        //lets print the stored information
        Iterator itr = detailsArray.iterator();
        while(itr.hasNext()){
            details dt = (details) itr.next();
            System.out.println(dt.name + dt.type + dt.quantity + dt.price);
        }


    }
}
