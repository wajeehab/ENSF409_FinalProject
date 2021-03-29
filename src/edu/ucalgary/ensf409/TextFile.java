package edu.ucalgary.ensf409;
import java.io.FileWriter;
import java.io.IOException;

public class TextFile {
    public TextFile(){};

    public void WriteFile(String type, String category, String amount, String price, String ID){
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
            myWriter.write("Original Request: " );
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
