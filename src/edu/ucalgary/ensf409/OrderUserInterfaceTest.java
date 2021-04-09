package edu.ucalgary.ensf409;

import org.junit.Test;

/**
 * This test class tests the Order user interface and ensures that the code can successfully
 * handle wrong user inputs
 */
public class OrderUserInterfaceTest {

    /**
     * This test initializes the incorrect furniture category and type
     * and expected an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testChairInputWrongString() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("chAIR");
        newOrder.setFurnitureType("study");
        newOrder.selectFurnitureCategory();
    }

    /**
     * this test initializes the correct type, but initializes category as
     * furniture type incorrectly and expects an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testChairAccidentTest() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("mesh");
        newOrder.setFurnitureType("mesh");
        newOrder.selectFurnitureCategory();

    }

    /**
     * This test initializes the correct category, but incorrect type and expects
     * an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testChairWrongType() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("chair");
        newOrder.setFurnitureType("study");
        newOrder.selectFurnitureCategory();
    }

    /**
     *This test initializes the category incorrectly as number order, but correct
     * type and expects an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testChairInputNumber() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("1");
        newOrder.setFurnitureType("mesh");
        newOrder.selectFurnitureCategory();
    }

    /**
     * This test initializes the incorrect furniture category and type
     * and expected an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeskWrongString() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("DESK");
        newOrder.setFurnitureType("standing");
        newOrder.selectFurnitureCategory();
    }

    /**
     * This test initializes the correct category, but incorrect type and expects
     * an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeskWrongType() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("desk");
        newOrder.setFurnitureType("mesh");
        newOrder.selectFurnitureCategory();

    }

    /**
     *This test initializes the category incorrectly as number order, but correct
     * type and expects an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeskInputNumber() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("1");
        newOrder.setFurnitureType("standing");
        newOrder.selectFurnitureCategory();
    }

    /**
     * this test initializes the correct type, but initializes category as
     * furniture type incorrectly and expects an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeskAccidentTest() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("standing");
        newOrder.setFurnitureType("standing");
        newOrder.selectFurnitureCategory();
    }

    /**
     * This test initializes the incorrect furniture category and type
     * and expected an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFilingWrongString() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("fILING");
        newOrder.setFurnitureType("small");
        newOrder.selectFurnitureCategory();
    }

    /**
     * This test initializes the correct category, but incorrect type and expects
     * an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFilingWrongType() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("filing");
        newOrder.setFurnitureType("executive");
        newOrder.selectFurnitureCategory();
    }

    /**
     *This test initializes the category incorrectly as number order, but correct
     * type and expects an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFilingInputNumber() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("1");
        newOrder.setFurnitureType("small");
        newOrder.selectFurnitureCategory();
    }

    /**
     * this test initializes the correct type, but initializes category as
     * furniture type incorrectly and expects an illegal argument exception
     */

    @Test(expected = IllegalArgumentException.class)
    public void testFilingAccidentTest() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("small");
        newOrder.setFurnitureType("small");
        newOrder.selectFurnitureCategory();
    }

    /**
     * This test initializes the incorrect furniture category and type
     * and expected an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLampWrongString() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("lAMP");
        newOrder.setFurnitureType("desk");
        newOrder.selectFurnitureCategory();
    }

    /**
     * This test initializes the correct category, but incorrect type and expects
     * an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLampWrongType() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("lamp");
        newOrder.setFurnitureType("executive");
        newOrder.selectFurnitureCategory();

    }

    /**
     * this test initializes the correct type, but initializes category as
     * furniture type incorrectly and expects an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLampAccidentTest() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("swing arm");
        newOrder.setFurnitureType("swing arm");
        newOrder.selectFurnitureCategory();
    }

    /**
     *This test initializes the category incorrectly as number order, but correct
     * type and expects an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLampInputNumber() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setFurnitureCategory("1");
        newOrder.setFurnitureType("swing arm");
        newOrder.selectFurnitureCategory();
    }

    /**
     * This test initializes the the number of items to be ordered to a negative order
     * and expects an illegal argument exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testOrderNumber() {
        OrderUserInterface newOrder = new OrderUserInterface();
        newOrder.setNumberItems(-1);
    }

}
