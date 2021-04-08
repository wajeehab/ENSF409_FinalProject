package edu.ucalgary.ensf409;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class writes to the text file in both the cases of orders which have been fulfilled and orders
 * which have not been fulfilled.
 */
public class TextFile {
    /**
     * empty constructor
     */
    public TextFile() { }

    /**
     * This method creates an output file called "orderform.txt" and writes the required information if
     * the order can be fulfilled
     * @param type - the furniture type from user input
     * @param category - the furniture category from user input
     * @param amount - the amount of items from user input
     * @param price - the price of the combination
     * @param idCombo - the list which contains the ID's of the items which can complete the order
     */
    public void writeOrderFulfilled(String type, String category, int amount, int price, List<String> idCombo) {
        try {
            FileWriter myWriter = new FileWriter("orderform.txt");
            myWriter.write("Furniture Order Form");
            myWriter.write("\n");
            myWriter.write("\n");
            myWriter.write("Faculty Name:");
            myWriter.write("\n");
            myWriter.write("Contact:");
            myWriter.write("\n");
            myWriter.write("Date");
            myWriter.write("\n");
            myWriter.write("\n");
            myWriter.write("Original Request: " + type + " " + category + "," + " " + amount);
            myWriter.write("\n");
            myWriter.write("\n");
            myWriter.write("Items Ordered");
            myWriter.write("\n");
            for (int i =0;i<idCombo.size();i++) {
                myWriter.write("ID: " + idCombo.get(i) + '\n');
            }
            myWriter.write("\n");
            myWriter.write("Total Price: " + "$" + price);

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * This method creates an output file called "orderform.txt" and writes the required information if
     * the order cannot be fulfilled
     * @param type - the furniture type from user input
     * @param category - the furniture category from user input
     * @param amount - the amount of items from user input
     * @param manuNames - the manufacturer names for the required furniture category
     */
    public void writeNotFulfilled(String type, String category, int amount, String manuNames) {
        try {
            FileWriter myWriter = new FileWriter("orderform.txt");
            myWriter.write("Furniture Order Form");
            myWriter.write("\n");
            myWriter.write("\n");
            myWriter.write("Faculty Name:");
            myWriter.write("\n");
            myWriter.write("Contact:");
            myWriter.write("\n");
            myWriter.write("Date");
            myWriter.write("\n");
            myWriter.write("\n");
            myWriter.write("Original Request: " + type + " " + category + "," + " " + amount);
            myWriter.write("\n");
            myWriter.write("\n");
            myWriter.write("Order cannot be fulfilled based on current inventory. Suggested manufacturers are: " + manuNames);
            myWriter.close();
            System.out.println("Successfully wrote to Order Not Fulfilled file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
