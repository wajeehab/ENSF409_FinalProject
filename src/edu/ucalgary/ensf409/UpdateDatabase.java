package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.*;

/**
 * This class updates the database after an order has been fulfilled
 * and removes the ID's which have been used up
 */
public class UpdateDatabase {
    private final InitializeConnection DATABASE = new InitializeConnection();
    private Connection dbConnect;

    /**
     * Constructor method which initializes the connection to the database
     */
    public UpdateDatabase(String user, String url, String password){
        DATABASE.initialize(user, url, password);
        dbConnect = DATABASE.getDbConnect();
    }

    /**
     * This method deleted the ID's from its respective table
     * @param idCombo - takes in combination of ID's which have been used in that order
     * @param table - the table from which the ID's need to be deleted
     */
    public void deleteFromDataBase(ArrayList<String> idCombo, String table){
        try {
            for(int i =0; i< idCombo.size();i++){
                String query = "DELETE FROM " + table + " WHERE ID = ? ";
                PreparedStatement myStmt = dbConnect.prepareStatement(query);
                myStmt.setString(1, idCombo.get(i));
                int rowCount = myStmt.executeUpdate();
                System.out.println("Rows affected: " + rowCount);
                myStmt.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * getter method for the connection
     * @return dbConnect - the connection
     */
    public Connection getDbConnect() {
        return dbConnect;
    }

    /**
     * This method closes all database connections
     */
    public void close() {
        try {
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
