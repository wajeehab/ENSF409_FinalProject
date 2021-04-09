package edu.ucalgary.ensf409;

import java.sql.*;

/**
 * This class initializes the database connection
 */
public class InitializeConnection { //******************** CHANGE DBCONNECT******************* */
    private Connection dbConnect;

    /**
     * empty constructor
     */
    public InitializeConnection(){}

    /**
     * this method takes in the url, user and password for the database and initializes the connection
     */
    public void Initialize() {
        try{
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/inventory","scm","ensf409"); //predefined information for the database as per project specifications
        } catch (SQLException e) {
            System.out.println("DID NOT CONNECT TO SQL DATABASE");
            e.printStackTrace();
        }
    }

    /**
     * returns the connection
     * @return
     */
    public Connection getDbConnect() {
        return dbConnect;
    }

}
