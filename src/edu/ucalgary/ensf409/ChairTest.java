package edu.ucalgary.ensf409;

import static org.junit.Assert.assertEquals;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.junit.*;
import java.sql.*;

/**
 * This class tests the functionality of the Chair class. It does this by creating test 
 * values and comparing those values to values affected by each method that is tested.
 */

public class ChairTest {

    /**
     * This method tests the functionality of the createCombinations method within
     * the Chair class by comparing an expected ArrayList with the stored Combinations
     * ArrayList within the class after running the method.
     */
    @Test
    public void createCombinationsTest() {
        
        Chair newChair = new Chair(1);
        newChair.selectChairInfo("Executive");

        ArrayList<ArrayList<String>> result = newChair.getCombinations();

        ArrayList<ArrayList<String>> expected = new ArrayList<>();
        expected.add(0, new ArrayList<>());
        expected.get(0).add(0, "C2483");
        expected.get(0).add(1, "C7268");
        expected.get(0).add(2, "C5784");

        for(int i =0;i< expected.size();i++){
            Collections.sort(expected.get(i));
        }
        assertEquals("createCombinations did not give the proper result.",expected, result);
    }

    /**
     * This method tests the functionality of the selectPrice method within
     * the Chair class by comparing an expected ArrayList with the stored Price
     * ArrayList within the class after running the method.
     */
    @Test
    public void selectPriceTest() {
        
        Chair newChair = new Chair(1);
        newChair.selectChairInfo("Executive");
        ArrayList<ArrayList<String>> result = newChair.getPrice();

        ArrayList<ArrayList<String>> expected = new ArrayList<>();

        expected.add(0, new ArrayList<>());
        expected.get(0).add(0, "175");
        expected.get(0).add(1, "150");
        expected.get(0).add(2, "75");


        assertEquals("selectPrice did not set the proper values.",expected, result);
    }

    /**
     * This method tests the functionality of the orderCombos method within
     * the Chair class by comparing an expected lowest-price int value with the stored 
     * lowest-price int value within the class after running the method.
     */
    @Test
    public void orderCombosTest() {
        Chair newChair = new Chair(2);
        newChair.selectChairInfo("Mesh");

        int result = newChair.getSmallest();
        int expected = 200;

        assertEquals("orderCombos does not give the proper value.", expected, result);
    }

    /**
     * This method tests the functionality of the findPriceAndCombo method within
     * the Chair class by comparing an lowest-price int value with the stored lowest-
     * price int value within the class after running the method.
     */
    @Test
    public void findPriceAndComboTest() {
        Chair newChair = new Chair(1);
        newChair.selectChairInfo("Mesh");
        int result = newChair.getSmallest();

        int expected = 200;

        assertEquals("findPriceAndCombo does not give the proper price.", expected, result);
    }

    /**
     * This method tests the functionality of the addToOrder method within
     * the Chair class by comparing a sorted expected ArrayList of Strings with the sorted
     * stored resulting ArrayList to ensure they match.
     */
    @Test
    public void addToOrderTest() {

        //generating the correct from the program
        Chair newChair = new Chair(1);
        newChair.selectChairInfo("Mesh");
        ArrayList<String> result = newChair.getIdCombo();

//
//        //hardcoding in the expected to make sure the addToOrder method works properly on its own
        Chair expectedChair= new Chair(1);
        String [] newComboId = {"C6748"};
        expectedChair.setOrderCombo(newComboId);
        ArrayList<String> newAddition = new ArrayList<>();
        newAddition.add("C8138");
        newAddition.add("C9890");
        expectedChair.setTotalOrder(newAddition);
        expectedChair.addToOrder();

        ArrayList<String> expected = expectedChair.getIdCombo();

        Collections.sort(expected);
        Collections.sort(result);

        assertEquals("addToOrder does not properly update the totalOrder ArrayList.", expected, result);
    }

    /**
     * This method tests the functionality of the getRidofDuplicates method within
     * the Chair class by comparing an expected ArrayList with returned ArrayList
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

        Chair newChair = new Chair(1);
        ArrayList<ArrayList<String>> result = newChair.getRidofDuplicates(testArray);

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
     * the Chair class by comparing an expected boolean value with the boolean
     * returned by the method, ensuring that the hasArray is empty.
     */
    @Test
    public void checkEmptyTest() {

        Chair newChair = new Chair(1);
        //After running through and creating an order, 
        //one of the hasArrays for chair should become empty.
        newChair.selectChairInfo("mesh"); 
        boolean x = newChair.checkEmpty();

        boolean expected = true;

        assertEquals("checkEmpty did not check properly.", expected, x);
    }

    /**
     * This method tests the functionality of the createHasArrays method within
     * the Chair class by comparing an expected ArrayList with returned ArrayList
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

        Chair newChair = new Chair(1);
        ArrayList<String> result = newChair.createHasArrays(testArray);

        ArrayList<String> expectedHasArray = new ArrayList<>();
        expectedHasArray.add("C1010");
        expectedHasArray.add("C1012");

        assertEquals("createHasArrays did not provide the expected hasArray ArrayList.", expectedHasArray, result);
    }

    /**
     * This method tests the fucntionality of the updateHasArray method within
     * the Chair class by comparing an expected ArrayList with returned ArrayList
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
        
        Chair newChair = new Chair(1); 
        newChair.updateHasArrays(newHasArray, newIDs);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("C4300");
        expected.add("C4400");
        expected.add("C4501");

        assertEquals("UpdateHasArrays did not properly update the ArrayList.", expected, newHasArray);
    }

}
