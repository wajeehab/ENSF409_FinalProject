//package edu.ucalgary.ensf409;

import static org.junit.Assert.assertEquals;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.junit.*;

/**
 * This class tests the functionality of the TextFile class.
 */

public class TextFileTest {

    /*
     * Pre- and Post-test processes
     */

    @Before
    public void start() {
        deleteTextFile("orderForm.txt");
        deleteTextFile("orderFormTest.txt");
    }

    @After
    public void end() {
        deleteTextFile("orderForm.txt");
        deleteTextFile("orderFormTest.txt");
    }

    /**
     * This method tests the fucntionality of the WriteOrderFulfilled method within
     * the TextFile class.
     */
    @Test
    public void WriteOrderFulfilledTest() {

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("C1320");
        stringList.add("C3405");
        TextFile newTextFile = new TextFile();
        newTextFile.WriteOrderFulfilled("Cushion", "Chair", 2, 3, stringList);

        textFileFulfilled();

        String result = readFile("orderForm.txt");
        String expResult = readFile("orderFormTest.txt");

        deleteTextFile("orderForm.txt");
        deleteTextFile("orderFormTest.txt");

        assertEquals("WriteOrderFulfilled text file is not what was expected", expResult, result);
    }

    /**
     * This method tests the fucntionality of the WriteNotFulfilled method within
     * the TextFile class.
     */
    @Test
    public void WriteNotFulfilledTest() {

        TextFile newTextFile = new TextFile();
        newTextFile.WriteNotFulfilled("Cushion", "Chair", 5, "Chairs R Us");

        textFileNotFulfilled();

        String result = readFile("orderForm.txt");
        String expResult = readFile("orderFormTest.txt");

        deleteTextFile("orderForm.txt");
        deleteTextFile("orderFormTest.txt");

        assertEquals("WriteNotFulfilled text file is not what was expected", expResult, result);
    }

    // ----------------------------------------------------------------------------------

    /**
     * This method creates a sample file with the expected output, so that the
     * WriteOrderFulfilledTest can compare results.
     */
    public void textFileFulfilled() {
        try {
            FileWriter myWriter = new FileWriter("orderformTest.txt");
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
            myWriter.write("Original Request: Cushion Chair, 2");
            myWriter.write("\n");
            myWriter.write("\n");
            myWriter.write("Items Ordered");
            myWriter.write("\n");
            myWriter.write("ID: C1320" + '\n' + "ID: C3405" + '\n');
            myWriter.write("\n");
            myWriter.write("Total Price: " + "$" + 3);

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * This method creates a sample file with the expected output, so that the
     * WriteNotFulfilledTest can compare results.
     */
    public void textFileNotFulfilled() {
        try {
            FileWriter myWriter = new FileWriter("orderformTest.txt");
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
            myWriter.write("Original Request: Cushion Chair, 5");
            myWriter.write("\n");
            myWriter.write("\n");
            myWriter.write(
                    "Order cannot be fulfilled based on current inventory. Suggested manufacturers are: Chairs R Us");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads a text file and returns it as a string.
     * 
     * @param fileAndPath - the path (or file) to be read.
     */
    public String readFile(String fileAndPath) {
        String x = "";
        try {
            Path filePath = Paths.get(fileAndPath);
            x = Files.readString(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return x;
    }

    /**
     * This method deletes the given text file.
     * 
     * @param fileName - the file name to be deleted.
     */
    public void deleteTextFile(String fileName) {
        File path = new File(fileName);
        path.delete();
    }
}
