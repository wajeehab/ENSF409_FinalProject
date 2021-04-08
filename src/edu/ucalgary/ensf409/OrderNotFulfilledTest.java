package edu.ucalgary.ensf409;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class OrderNotFulfilledTest {
    
    @Test
    public void findChairManuTest() {
        StringProcessor newTest = new StringProcessor("Test");
        String result = newTest.idProcessing("Samuel", "Hannon", "Geo", 2015);

        String expResult = "SHG2015";
        assertEquals("ID does not match expected ID", expResult, result);
    }

    @Test
    public void findLampManuTest() {
        StringProcessor newTest = new StringProcessor("Test");
        String result = newTest.idProcessing("Samuel", "Hannon", "Geo", 2015);

        String expResult = "SHG2015";
        assertEquals("ID does not match expected ID", expResult, result);
    }

    @Test
    public void findFilingManuTest() {
        StringProcessor newTest = new StringProcessor("Test");
        String result = newTest.idProcessing("Samuel", "Hannon", "Geo", 2015);

        String expResult = "SHG2015";
        assertEquals("ID does not match expected ID", expResult, result);
    }

    @Test
    public void findDeskManuTest() {
        StringProcessor newTest = new StringProcessor("Test");
        String result = newTest.idProcessing("Samuel", "Hannon", "Geo", 2015);

        String expResult = "SHG2015";
        assertEquals("ID does not match expected ID", expResult, result);
    }

}
