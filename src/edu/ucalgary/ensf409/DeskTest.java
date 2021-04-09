package edu.ucalgary.ensf409;

import static org.junit.Assert.assertEquals;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.junit.*;
import java.sql.*;

/**
 * This class tests the functionality of the Desk class. It does this by creating test 
 * values and comparing those values to values affected by each method that is tested.
 */

public class DeskTest {

    /**
     * This method tests the functionality of the createCombinations method within
     * the Desk class by comparing an expected ArrayList with the stored Combinations
     * ArrayList within the class after running the method. The reason for the large 
     * amount of inputs is due to the large amount of combinations that the method 
     * creates, meaning that there is a lot of data to compare to ensure proper 
     * functionality.
     */
    @Test
    public void createCombinationsTest() {
        
        Desk newDesk = new Desk(1);
        newDesk.selectDeskInfo("Traditional");

        ArrayList<ArrayList<String>> result = newDesk.getCombinations();

        ArrayList<ArrayList<String>> expected = new ArrayList<>();
        expected.add(0, new ArrayList<>());
        expected.add(1, new ArrayList<>());
        expected.add(2, new ArrayList<>());
        expected.add(3, new ArrayList<>());
        expected.add(4, new ArrayList<>());
        expected.add(5, new ArrayList<>());
        expected.add(6, new ArrayList<>());
        expected.add(7, new ArrayList<>());

        expected.get(0).add(0, "D8675");
        expected.get(0).add(1, "D9352");

        expected.get(1).add(0, "D4231");
        expected.get(1).add(1, "D9352");

        expected.get(2).add(0, "D4231");
        expected.get(2).add(1, "D8675");
        expected.get(2).add(2, "D9352");

        expected.get(3).add(0, "D0890");
        expected.get(3).add(1, "D8675");
        expected.get(3).add(2, "D9352");

        expected.get(4).add(0, "D0890");
        expected.get(4).add(1, "D4231");
        expected.get(4).add(2, "D9352");

        expected.get(5).add(0, "D4231");
        expected.get(5).add(1, "D8675");

        expected.get(6).add(0, "D0890");
        expected.get(6).add(1, "D8675");

        expected.get(7).add(0, "D0890");
        expected.get(7).add(1, "D4231");
        expected.get(7).add(2, "D8675");

        for (int i =0; i<expected.size();i++){
            Collections.sort(expected.get(i));
        }

        assertEquals("createCombinations did not give the proper result.",expected, result);
    }

    /**
     * This method tests the functionality of the selectPrice method within
     * the Desk class by comparing an expected ArrayList with the stored Price
     * ArrayList within the class after running the method. The reason for the large 
     * amount of inputs is due to the large amount of combinations that the method 
     * creates, meaning that there is a lot of data to compare to ensure proper 
     * functionality.
     */
    @Test
    public void selectPriceTest() {
        
        Desk newDesk = new Desk(1);
        newDesk.selectDeskInfo("Traditional");
        ArrayList<ArrayList<String>> result = newDesk.getPrice();

        ArrayList<ArrayList<String>> expected = new ArrayList<>();
        expected.add(0, new ArrayList<>());
        expected.add(1, new ArrayList<>());
        expected.add(2, new ArrayList<>());
        expected.add(3, new ArrayList<>());
        expected.add(4, new ArrayList<>());
        expected.add(5, new ArrayList<>());
        expected.add(6, new ArrayList<>());
        expected.add(7, new ArrayList<>());

        expected.get(0).add(0, "75");
        expected.get(0).add(1, "75");

        expected.get(1).add(0, "50");
        expected.get(1).add(1, "75");

        expected.get(2).add(0, "50");
        expected.get(2).add(1, "75");
        expected.get(2).add(2, "75");

        expected.get(3).add(0, "25");
        expected.get(3).add(1, "75");
        expected.get(3).add(2, "75");

        expected.get(4).add(0, "25");
        expected.get(4).add(1, "50");
        expected.get(4).add(2, "75");

        expected.get(5).add(0, "50");
        expected.get(5).add(1, "75");

        expected.get(6).add(0, "25");
        expected.get(6).add(1, "75");

        expected.get(7).add(0, "25");
        expected.get(7).add(1, "50");
        expected.get(7).add(2, "75");

        for (int i =0; i<expected.size();i++){
            Collections.sort(expected.get(i));
        }

        assertEquals("selectPrice did not set the proper values.",expected, result);
    }

    /**
     * This method tests the functionality of the orderCombos method within
     * the Desk class by comparing an expected lowest-price int value with the stored 
     * lowest-price int value within the class after running the method.
     */
    @Test
    public void orderCombosTest() {

        Desk newDesk = new Desk(2);
        newDesk.selectDeskInfo("Standing");

        int result = newDesk.getSmallest();
        int expected = 600;

        assertEquals("orderCombos does not give the proper value.", expected, result);
    }

    /**
     * This method tests the functionality of the findPriceAndCombo method within
     * the Desk class by comparing an lowest-price int value with the stored lowest-
     * price int value within the class after running the method.
     */
    @Test
    public void findPriceAndComboTest() {
        Desk newDesk = new Desk(1);
        newDesk.selectDeskInfo("Standing");
        int result = newDesk.getSmallest();

        int expected = 300;

        assertEquals("findPriceAndCombo does not give the proper price.", expected, result);
    }

    /**
     * This method tests the functionality of the addToOrder method within
     * the Desk class by comparing a sorted expected ArrayList of Strings with the sorted
     * stored resulting ArrayList to ensure they match.
     */
    @Test
    public void addToOrderTest() {

        //generating the correct from the program
        Desk newDesk = new Desk(1);
        newDesk.selectDeskInfo("standing");
        ArrayList<String> result = newDesk.getIdCombo();

        //hardcoding in the expected to make sure the addToOrder method works properly on its own
        Desk expectedDesk = new Desk(1);
        String [] newComboId = {"D3820"};
        expectedDesk.setOrderCombo(newComboId);
        ArrayList<String> newAddition = new ArrayList<>();
        newAddition.add("D4438");
        expectedDesk.setTotalOrder(newAddition);
        expectedDesk.addToOrder();

        ArrayList<String> expected = expectedDesk.getIdCombo();

        Collections.sort(expected);
        Collections.sort(result);

        assertEquals("addToOrder does not properly update the totalOrder ArrayList.", expected, result);
    }

    /**
     * This method tests the functionality of the getRidofDuplicates method within
     * the Desk class by comparing an expected ArrayList with returned ArrayList
     * provided after running the method. It ensures that duplicate IDs are removed. 
     */
    @Test
    public void getRidofDuplicatesTest() {
        
        ArrayList<ArrayList<String>> testArray = new ArrayList<>();
        testArray.add(0, new ArrayList<>());
        testArray.add(1, new ArrayList<>());

        testArray.get(0).add(0, "C1010");
        testArray.get(0).add(1, "C1010");
        testArray.get(0).add(2, "C1010");
        testArray.get(0).add(3, "C1010");

        testArray.get(1).add(0, "C1011");
        testArray.get(1).add(1, "C1012");
        testArray.get(1).add(2, "C1013");
        testArray.get(1).add(3, "C1014");

        Desk newDesk = new Desk(1);
        ArrayList<ArrayList<String>> result = newDesk.getRidofDuplicates(testArray);

        ArrayList<ArrayList<String>> expected = new ArrayList<>();
        expected.add(0, new ArrayList<>());
        expected.add(1, new ArrayList<>());

        expected.get(0).add(0, "C1010");

        expected.get(1).add(0, "C1011");
        expected.get(1).add(1, "C1012");
        expected.get(1).add(2, "C1013");
        expected.get(1).add(3, "C1014");

        assertEquals("getRidofDuplicates did not return the proper ArrayList.", expected, result);
    }

    /**
     * This method tests the functionality of the checkEmpty method within
     * the Desk class by comparing an expected boolean value with the boolean
     * returned by the method, ensuring that the hasArray is empty.
     */
    @Test
    public void checkEmptyTest() {

        Desk newDesk = new Desk(1);
        newDesk.selectDeskInfo("Standing");
        boolean x = newDesk.checkEmpty();
        

        boolean expected = false;

        assertEquals("checkEmpty did not check properly.", expected, x);
    }

    /**
     * This method tests the functionality of the createHasArrays method within
     * the Desk class by comparing an expected ArrayList with returned ArrayList
     * provided after running the method. It ensures that duplicate IDs are removed. 
     */
    @Test
    public void createHasArraysTest() {

        ArrayList<ArrayList<String>> testArray = new ArrayList<>();
        testArray.add(0, new ArrayList<>());
        testArray.add(1, new ArrayList<>());
        testArray.add(2, new ArrayList<>());
        testArray.get(0).add(0, "C1010");
        testArray.get(0).add(1, "Y");
        testArray.get(1).add(0, "C1011");
        testArray.get(1).add(1, "N");
        testArray.get(2).add(0, "C1012");
        testArray.get(2).add(1, "Y");

        Desk newDesk = new Desk(1);
        ArrayList<String> result = newDesk.createHasArrays(testArray);

        ArrayList<String> expectedHasArray = new ArrayList<>();
        expectedHasArray.add("C1010");
        expectedHasArray.add("C1012");

        assertEquals("createHasArrays did not provide the expected hasArray ArrayList.", expectedHasArray, result);
    }

    /**
     * This method tests the fucntionality of the updateHasArray method within
     * the Desk class by comparing an expected ArrayList with returned ArrayList
     * provided after running the method with given test values. It ensures that 
     * the hasArray is updated to the proper format needed for following methods.
     */
    @Test
    public void updateHasArraysTest() {

        ArrayList<String> newHasArray = new ArrayList<>();
        newHasArray.add("C3400");
        newHasArray.add("C4300");
        newHasArray.add("C4400");
        newHasArray.add("C4501");
        String[] newIDs = {"C3400", "C4400"};
        
        Desk newDesk = new Desk(1); 
        newDesk.updateHasArrays(newHasArray, newIDs);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("C4300");
        expected.add("C4400");
        expected.add("C4501");

        assertEquals("UpdateHasArrays did not properly update the ArrayList.", expected, newHasArray);
    }

    //---------------------------------------------------------------------------------



}
