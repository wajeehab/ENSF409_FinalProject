//package edu.ucalgary.ensf409;

import static org.junit.Assert.assertEquals;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.junit.*;
import java.sql.*;

public class LampTest {
    
    @Test
    public void selectLampInfoTest() {
        
        
    }

    @Test
    public void createHasArraysTest() {
        
        
    }

    @Test
    public void orderCombosTest() {
        
        
    }

    @Test
    public void findPriceAndComboTest() {
        
        
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
