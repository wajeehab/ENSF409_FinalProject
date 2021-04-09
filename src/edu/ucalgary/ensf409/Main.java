package edu.ucalgary.ensf409;

/**
 * @author Wajeeha Bushra, Asad Anjum, Sam Hannon, Stefanos Rizopoulos
 * @version 1.12.5
 */



/**
 * Main class
 */
public class Main {
    /**
     * Call OrderUserInterface class which will prompt the user and then based on the input
     * it will try to create the correct combinations and compute the cheapest price to complete the order
     * and writes the corresponding information if the order can be or cannot be completed. Accepted responses are only
     * case insensitive when it comes to the first letter of input. For example, "Chair" and "chair" are both
     * accepted, but "CHAIR" and "cHAIr" is not. The same is applied to type of furniture.
     * @param args
     */
    public static void main(String args[]){
        OrderUserInterface order = new OrderUserInterface();
        order.startProgram();
        order.selectFurnitureCategory();
    }
}
