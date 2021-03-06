package edu.ucalgary.ensf409;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * This class tests the functionality of the OrderNotFulfilled class. It does this by creating test 
 * values and comparing those values to values affected by each method that is tested.
 */

public class OrderNotFulfilledTest {
    
    /**
     * This method tests the functionality of the findChairManu method within
     * the OrderNotFulfilled class by comparing an expected manufacturers with the 
     * manufacturers returned by the method.
     */
    @Test
    public void findChairManuTest() {
        
        OrderNotFulfilled newOrderNotFulfilled = new OrderNotFulfilled("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        String result = newOrderNotFulfilled.findChairManu();

        String expected = "Office Furnishings, Fine Office Supplies, Chairs R Us";

        assertEquals("findChairManu returns the proper String", expected, result);
    }

    /**
     * This method tests the functionality of the findLampManu method within
     * the OrderNotFulfilled class by comparing an expected manufacturers with the 
     * manufacturers returned by the method.
     */
    @Test
    public void findLampManuTest() {

        OrderNotFulfilled newOrderNotFulfilled = new OrderNotFulfilled("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        String result = newOrderNotFulfilled.findLampManu();

        String expected = "Furniture Goods, Office Furnishings, Fine Office Supplies";

        assertEquals("findLampManu returns the proper String", expected, result);
    }

    /**
     * This method tests the functionality of the findFilingManu method within
     * the OrderNotFulfilled class by comparing an expected manufacturers with the 
     * manufacturers returned by the method.
     */
    @Test
    public void findFilingManuTest() {

        OrderNotFulfilled newOrderNotFulfilled = new OrderNotFulfilled("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        String result = newOrderNotFulfilled.findFilingManu();

        String expected = "Furniture Goods, Fine Office Supplies, Office Furnishings";

        assertEquals("findFilingManu returns the proper String", expected, result);
    }

    /**
     * This method tests the functionality of the findDeskManu method within
     * the OrderNotFulfilled class by comparing an expected manufacturers with the 
     * manufacturers returned by the method.
     */
    @Test
    public void findDeskManuTest() {

        OrderNotFulfilled newOrderNotFulfilled = new OrderNotFulfilled("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        String result = newOrderNotFulfilled.findDeskManu();

        String expected = "Office Furnishings, Fine Office Supplies, Academic Desks, Furniture Goods";

        assertEquals("findDeskManu returns the proper String", expected, result);
    }

}
