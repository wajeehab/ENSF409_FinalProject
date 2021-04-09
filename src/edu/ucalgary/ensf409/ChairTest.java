package edu.ucalgary.ensf409;

import static org.junit.Assert.assertEquals;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.junit.*;
import java.sql.*;

public class ChairTest {

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

    @Test
    public void orderCombosTest() {
        Chair newChair = new Chair(2);
        newChair.selectChairInfo("Mesh");

        int result = newChair.getSmallest();
        int expected = 200;

        assertEquals("orderCombos does not give the proper value.", expected, result);
    }

    @Test
    public void findPriceAndComboTest() {
        Chair newChair = new Chair(1);
        newChair.selectChairInfo("Mesh");
        int result = newChair.getSmallest();

        int expected = 200;

        assertEquals("findPriceAndCombo does not give the proper price.", expected, result);
    }

    @Test
    public void addToOrderTest() {
        
        Chair newChair = new Chair(1);
        newChair.selectChairInfo("Mesh");
        newChair.addToOrder();
        ArrayList<String> result = newChair.getIdCombo();


        ArrayList<String> expected = new ArrayList<>();
        expected.add("C6748");
        expected.add("C9890");
        expected.add("C8138");

        Collections.sort(expected);
        Collections.sort(result);
        assertEquals("addToOrder does not properly update the totalOrder ArrayList.", expected, result);
    }

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

    @Test
    public void checkEmptyTest() {

        Chair newChair = new Chair(1);
        newChair.selectChairInfo("mesh");
        boolean x = newChair.checkEmpty();

        boolean expected = true;

        assertEquals("checkEmpty did not check properly.", expected, x);
    }

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
