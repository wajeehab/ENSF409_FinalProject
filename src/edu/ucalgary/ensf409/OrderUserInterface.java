package edu.ucalgary.ensf409;

import java.util.Scanner;

public class OrderUserInterface {
    public String furnitureCategory;
    public String furnitureType;
    public int numberItems;

    public OrderUserInterface(){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter furniture category:  ");
        this.furnitureCategory = reader.nextLine(); //

        System.out.println("Enter furniture type:  ");
        this.furnitureType = reader.nextLine();

        System.out.println("Enter number of items:  ");
        this.numberItems = reader.nextInt();

        reader.close();
    }

    public String getFurnitureCategory() {
        return furnitureCategory;
    }
    public String getFurnitureType(){
        return furnitureType;
    }
    public int getNumberItems() {
        return numberItems;
    }
}
