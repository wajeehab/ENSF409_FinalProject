//package edu.ucalgary.ensf409;

import static org.junit.Assert.assertEquals;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.junit.*;
import java.sql.*;

public class FilingTest {

    @Test
    public void createCombinationsTest() {
        
        Filing newFiling = new Filing(1);
        newFiling.selectFilingInfo("Large");

        ArrayList<ArrayList<String>> result = newFiling.getCombinations();

        ArrayList<ArrayList<String>> expected = new ArrayList<>();
        expected.add(0, new ArrayList<>());
        expected.add(1, new ArrayList<>());
        expected.add(2, new ArrayList<>());
        expected.add(3, new ArrayList<>());
        expected.add(4, new ArrayList<>());
        expected.add(5, new ArrayList<>());
        expected.add(6, new ArrayList<>());
        expected.add(7, new ArrayList<>());
        expected.add(8, new ArrayList<>());
        expected.add(9, new ArrayList<>());
        expected.add(10, new ArrayList<>());

        expected.get(0).add(0, "F011");
        expected.get(0).add(1, "F012");
        expected.get(0).add(2, "F015");

        expected.get(1).add(0, "F011");
        expected.get(1).add(1, "F015");

        expected.get(2).add(0, "F010");
        expected.get(2).add(1, "F012");
        expected.get(2).add(2, "F015");

        expected.get(3).add(0, "F010");
        expected.get(3).add(1, "F011");
        expected.get(3).add(2, "F015");

        expected.get(4).add(0, "F003");
        expected.get(4).add(1, "F012");
        expected.get(4).add(2, "F015");

        expected.get(5).add(0, "F003");
        expected.get(5).add(1, "F011");
        expected.get(5).add(2, "F015");

        expected.get(6).add(0, "F010");
        expected.get(6).add(1, "F011");
        expected.get(6).add(2, "F012");

        expected.get(7).add(0, "F010");
        expected.get(7).add(1, "F011");

        expected.get(8).add(0, "F010");
        expected.get(8).add(1, "F012");

        expected.get(9).add(0, "F003");
        expected.get(9).add(1, "F010");
        expected.get(9).add(2, "F012");

        expected.get(10).add(0, "F003");
        expected.get(10).add(1, "F010");
        expected.get(10).add(2, "F011");

        assertEquals("createCombinations did not give the proper result.",expected, result);
    }

    @Test
    public void selectPriceTest() {
        
        Filing newFiling = new Filing(1);
        newFiling.selectFilingInfo("Large");
        ArrayList<ArrayList<String>> result = newFiling.getPrice();

        ArrayList<ArrayList<String>> expected = new ArrayList<>();
        expected.add(0, new ArrayList<>());
        expected.add(1, new ArrayList<>());
        expected.add(2, new ArrayList<>());
        expected.add(3, new ArrayList<>());
        expected.add(4, new ArrayList<>());
        expected.add(5, new ArrayList<>());
        expected.add(6, new ArrayList<>());
        expected.add(7, new ArrayList<>());
        expected.add(8, new ArrayList<>());
        expected.add(9, new ArrayList<>());
        expected.add(10, new ArrayList<>());

        expected.get(0).add(0, "225");
        expected.get(0).add(1, "75");
        expected.get(0).add(2, "75");

        expected.get(1).add(0, "225");
        expected.get(1).add(1, "75");

        expected.get(2).add(0, "225");
        expected.get(2).add(1, "75");
        expected.get(2).add(2, "75");

        expected.get(3).add(0, "225");
        expected.get(3).add(1, "225");
        expected.get(3).add(2, "75");

        expected.get(4).add(0, "150");
        expected.get(4).add(1, "75");
        expected.get(4).add(2, "75");

        expected.get(5).add(0, "150");
        expected.get(5).add(1, "225");
        expected.get(5).add(2, "75");

        expected.get(6).add(0, "225");
        expected.get(6).add(1, "225");
        expected.get(6).add(2, "75");

        expected.get(7).add(0, "225");
        expected.get(7).add(1, "225");

        expected.get(8).add(0, "225");
        expected.get(8).add(1, "75");

        expected.get(9).add(0, "150");
        expected.get(9).add(1, "225");
        expected.get(9).add(2, "75");

        expected.get(10).add(0, "150");
        expected.get(10).add(1, "225");
        expected.get(10).add(2, "225");
        assertEquals("selectPrice did not set the proper values.",expected, result);
    }

    @Test
    public void orderCombosTest() {
        

        Filing newFiling = new Filing(2);
        newFiling.selectFilingInfo("Medium");
        //newFiling.orderCombos(2);

        int result = newFiling.getSmallest();
        int expected = 400;

        assertEquals("findCombos does not give the proper values.", expected, result);
    }

    @Test
    public void findPriceAndComboTest() {
        Filing newFiling = new Filing(1);
        newFiling.selectFilingInfo("Medium");
        int result = newFiling.getSmallest();

        int expected = 200;

        assertEquals("findPriceAndCombo does not give the proper price.", expected, result);
    }

    @Test
    public void addToOrderTest() {
        
        Filing newFiling = new Filing(1);
        newFiling.selectFilingInfo("Medium");
        newFiling.addToOrder();
        ArrayList<String> result = newFiling.getIdCombo();

        ArrayList<String> expected = new ArrayList<>();
        expected.add("F014");

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

        Filing newFiling = new Filing(1);
        ArrayList<ArrayList<String>> result = newFiling.getRidofDuplicates(testArray);

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

        Filing newFiling = new Filing(1);
        newFiling.selectFilingInfo("Medium");
        boolean x = newFiling.checkEmpty();
        

        boolean expected = false;

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

        Filing newFiling = new Filing(1);
        ArrayList<String> result = newFiling.createHasArrays(testArray);

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
        
        Filing newFiling = new Filing(1); 
        newFiling.updateHasArrays(newHasArray, newIDs);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("C4300");
        expected.add("C4400");
        expected.add("C4501");

        assertEquals("UpdateHasArrays did not properly update the ArrayList.", expected, newHasArray);
    }

    //---------------------------------------------------------------------------------



}
