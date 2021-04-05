package edu.ucalgary.ensf409;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class initializes the database connection
 */
public class InitializeConnection {
    private Connection dbConnect;
    public InitializeConnection(){}

    /**
     * this method takes in the url, user and password for the database and initializes the connection
     */
    public void Initialize() {
        try{
            dbConnect = DriverManager.getConnection("JDBC:mysql://localhost/inventory","root","Hannan!2");
        } catch (SQLException e) {
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
