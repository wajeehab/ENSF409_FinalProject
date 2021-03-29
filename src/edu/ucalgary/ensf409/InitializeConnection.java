package edu.ucalgary.ensf409;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InitializeConnection {
    private Connection dbConnect;
    public InitializeConnection(){};
    public void Initiazlize() {
        try{
            dbConnect = DriverManager.getConnection("JDBC:mysql://localhost/inventory","root","Hannan!2");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getDbConnect() {
        return dbConnect;
    }
}
