package edu.ucalgary.ensf409;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]) throws SQLException {
        OrderUserInterface order = new OrderUserInterface();
        order.selectFurnitureCategory();
    }
}
