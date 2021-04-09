package edu.ucalgary.ensf409;

import org.junit.Test;

public class OrderUserInterfaceTest {
    @Test(expected = IllegalArgumentException.class)
    public void testChairInputWrongString() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("cHAIR");
        newOrder.selectFurnitureCategory();
//        newOrder.checkChairInput("cHAIR");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChairAccidentTest() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("mesh");
        newOrder.selectFurnitureCategory();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testChairInputNumber() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("1");
        newOrder.selectFurnitureCategory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeskWrongString() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("DESK");
        newOrder.selectFurnitureCategory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeskInputNumber() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("1");
        newOrder.selectFurnitureCategory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeskAccidentTest() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("standing");
        newOrder.selectFurnitureCategory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFilingWrongString() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("fILING");
        newOrder.selectFurnitureCategory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFilingInputNumber() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("1");
        newOrder.selectFurnitureCategory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFilingAccidentTest() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("small");
        newOrder.selectFurnitureCategory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLampWrongString() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("lAMP");
        newOrder.selectFurnitureCategory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLampAccidentTest() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("swing arm");
        newOrder.selectFurnitureCategory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLampInputNumber() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("1");
        newOrder.selectFurnitureCategory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOrderNumber() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setNumberItems(-1);

    }

}
