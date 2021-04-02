package edu.ucalgary.ensf409;
import java.io.FileWriter;
import java.io.IOException;

public class TextFile {
    public TextFile() {
    }

    public void WriteOrderFulfilled(String type, String category, int amount, int price) {
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
            myWriter.write("ID: ");
            myWriter.write("\n");
            myWriter.write("\n");
            myWriter.write("Total Price: " + "$" + price);

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void WriteNotFulfilled(String type, String category, int amount, int price) {
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
            myWriter.write("Order cannot be fulfilled based on current inventory. Suggested manufacturers are: ");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
