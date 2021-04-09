package edu.ucalgary.ensf409;

import static org.junit.Assert.assertEquals;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.junit.*;
import java.sql.*;

public class LampTest {

    @Test
    public void createHasArraysTest() {
        
        ArrayList<ArrayList<String>> testArray = new ArrayList<>();
        testArray.add(0, new ArrayList<>());
        testArray.add(1, new ArrayList<>());
        testArray.add(2, new ArrayList<>());
        testArray.get(0).add(0, "L013");
        testArray.get(0).add(1, "Y");
        testArray.get(0).add(2, "18");
        testArray.get(1).add(0, "L342");
        testArray.get(1).add(1, "N");
        testArray.get(1).add(2, "2");
        testArray.get(2).add(0, "L564");
        testArray.get(2).add(1, "Y");
        testArray.get(2).add(2, "20");


        Lamp newLamp = new Lamp(1);
        ArrayList<ArrayList<String>> result = newLamp.createHasArrays(testArray);

        ArrayList<ArrayList<String>> expectedHasArray = new ArrayList<>();
        expectedHasArray.add(0, new ArrayList<>());
        expectedHasArray.add(1, new ArrayList<>());
        expectedHasArray.get(0).add(0, "L013");
        expectedHasArray.get(0).add(1, "18");
        expectedHasArray.get(1).add(0, "L564");
        expectedHasArray.get(1).add(1, "20");


        assertEquals("createHasArrays did not provide the expected hasArray ArrayList.", expectedHasArray, result);

    }

    @Test
    public void orderCombosTest() {
        
        Lamp newLamp = new Lamp(2);
        //newLamp.findPriceAndCombo();
        newLamp.selectLampInfo("desk");
        int result = newLamp.getSmallest();
        int expected = 40;

        assertEquals("findPriceAndCombo did not return the lowest price", expected, result);
    }

    @Test
    public void findPriceAndComboTest() {
        
        Lamp newLamp = new Lamp(1);
        //newLamp.findPriceAndCombo();
        newLamp.selectLampInfo("desk");
        int result = newLamp.getSmallest();
        int expected = 20;

        assertEquals("findPriceAndCombo did not return the lowest price", expected, result);
    }

    @Test
    public void updateHasArraysTest() {
        
        ArrayList<ArrayList<String>> newHasArray = new ArrayList<>();
        newHasArray.add(0, new ArrayList<>());
        newHasArray.add(1, new ArrayList<>());
        newHasArray.add(2, new ArrayList<>());
        newHasArray.get(0).add(0, "C3400");
        newHasArray.get(0).add(1, "150");
        newHasArray.get(1).add(0, "C3401");
        newHasArray.get(1).add(1, "100");
        newHasArray.get(2).add(0, "C3402");
        newHasArray.get(2).add(1, "50");

        ArrayList<String> newIDs = new ArrayList<>();
        newIDs.add("C3400");
        newIDs.add("C3401");
        
        Lamp newLamp = new Lamp(1); 
        newLamp.updateHasArrays(newHasArray, newIDs);

        ArrayList<ArrayList<String>> expected = new ArrayList<>();
        expected.add(0, new ArrayList<>());
        expected.add(1, new ArrayList<>());
        expected.get(0).add(0,"C3401");
        expected.get(0).add(1,"100");
        expected.get(1).add(0,"C3402");
        expected.get(1).add(1,"50");


        assertEquals("UpdateHasArrays did not properly update the ArrayList.", expected, newHasArray);
    }

    @Test
    public void checkEmptyTest() {
        
        Lamp newLamp = new Lamp(1);
        newLamp.selectLampInfo("Desk");
        boolean x = newLamp.checkEmpty();
        

        boolean expected = false;

        assertEquals("checkEmpty did not check properly.", expected, x);
    }
}
