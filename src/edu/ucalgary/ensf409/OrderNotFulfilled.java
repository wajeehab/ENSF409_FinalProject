package edu.ucalgary.ensf409;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringJoiner;

public class OrderNotFulfilled {
    private final InitializeConnection DATABASE = new InitializeConnection();
    private Connection dbConnect;
    private ArrayList<String> chairId = new ArrayList<>();
    private ArrayList<String> deskId = new ArrayList<>();
    private ArrayList<String> filingId = new ArrayList<>();
    private ArrayList<String> lampId = new ArrayList<>();
    private ResultSet results;

    /**
     * constructor method which initializes the DATABASE connection and
     * initializes the Id arrays with their respective manufacturer ID's
     */
    public OrderNotFulfilled() {
        DATABASE.Initialize();
        dbConnect = DATABASE.getDbConnect();

        chairId.add("002");
        chairId.add("005");
        chairId.add("003");

        deskId.add("002");
        deskId.add("005");
        deskId.add("001");
        deskId.add("004");

        filingId.add("004");
        filingId.add("005");
        filingId.add("002");

        lampId.add("004");
        lampId.add("002");
        lampId.add("005");
    }

    /**
     * This method finds the names of the manufacturers for chair in the case of an order not fulfilled
     * @return the string containing the names of the manufacturers
     */
    public String findChairManu(){
        StringJoiner str = new StringJoiner(", ");
        try {
            Statement myStmt = dbConnect.createStatement();
            for(int i =0; i<chairId.size();i++) {
                results = myStmt.executeQuery("SELECT * FROM  manufacturer  WHERE ManuID = '" + chairId.get(i) + "'");
                while (results.next()) {
                    str.add(results.getString("Name"));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return str.toString();
    }
    /**
     * This method finds the names of the manufacturers for lamp in the case of an order not fulfilled
     * @return the string containing the names of the manufacturers
     */
    public String findLampManu(){
        StringJoiner str = new StringJoiner(", ");
        try {
            Statement myStmt = dbConnect.createStatement();
            for(int i =0; i<chairId.size();i++) {
                results = myStmt.executeQuery("SELECT * FROM  manufacturer  WHERE ManuID = '" + lampId.get(i) + "'");
                while (results.next()) {
                    str.add(results.getString("Name"));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return str.toString();
    }
    /**
     * This method finds the names of the manufacturers for filing in the case of an order not fulfilled
     * @return the string containing the names of the manufacturers
     */
    public String findFilingManu(){
        StringJoiner str = new StringJoiner(", ");
        try {
            Statement myStmt = dbConnect.createStatement();
            for(int i =0; i<chairId.size();i++) {
                results = myStmt.executeQuery("SELECT * FROM  manufacturer  WHERE ManuID = '" + filingId.get(i) + "'");
                while (results.next()) {
                    str.add(results.getString("Name"));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return str.toString();
    }
    /**
     * This method finds the names of the manufacturers for desk in the case of an order not fulfilled
     * @return the string containing the names of the manufacturers
     */
    public String findDeskManu(){
        StringJoiner str = new StringJoiner(", ");
        try {
            Statement myStmt = dbConnect.createStatement();
            for(int i =0; i<deskId.size();i++) {
                results = myStmt.executeQuery("SELECT * FROM  manufacturer  WHERE ManuID = '" + deskId.get(i) + "'");
                while (results.next()) {
                    str.add(results.getString("Name"));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return str.toString();
    }
}