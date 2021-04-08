package edu.ucalgary.ensf409;

/**
 * Main class
 */
public class Main {
    /**
     * Call OrderUserInterface class which will prompt the user and then based on the input
     * it will try to create the correct combinations and compute the cheapest price to complete the order
     * and writes the corresponding information if the order can be or cannot be completed.
     * @param args
     */
    public static void main(String args[]){
        OrderUserInterface order = new OrderUserInterface();
        order.selectFurnitureCategory();
    }
}
