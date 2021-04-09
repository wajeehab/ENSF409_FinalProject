package edu.ucalgary.ensf409;

import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.assertEquals;

/**
 * This class tests the functionality of the UpdateDatabase class, specifically checking
 * the database updating and deleting methods.
 */

/**
 * This test ensures that the UpdateDatabase function is updating the database correctly by updating 
 * database values, retrieving those values into a String, and comparing that string 
 * with an expected String which we know is correct.
 */
public class UpdateDatabaseTest {
    /**
     * Before testing, a custom component is added to the database which will later be removed by the test
     */
    @Before
    public void addDeletedValuesBack() {
        InitializeConnection connection = new InitializeConnection();
        connection.Initialize("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        try {
            String query = "INSERT INTO DESK (ID, Type, Legs, Top, Drawer, Price, ManuID) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement myStmt = connection.getDbConnect().prepareStatement(query);
            myStmt.setString(1, "D1920");
            myStmt.setString(2, "Standing");
            myStmt.setString(3, "Y");
            myStmt.setString(4, "Y");
            myStmt.setString(5, "N");
            myStmt.setInt(6, 100);
            myStmt.setString(7, "005");

            int rowCount = myStmt.executeUpdate();
            System.out.println("Rows affected: " + rowCount);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This test removes the ID which was inserted in the before setup of the test and
     * accesses the database to create an arraylist of which ID's should remain in the Desk
     * database for the type 'Standing' after this ID is removed.
     */
    @Test
    public void deleteFromDataBaseTest() {
        UpdateDatabase update = new UpdateDatabase("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        ResultSet results;
        ArrayList<String> idCombo = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        idCombo.add("D1920");

        expected.add("D9387");
        expected.add("D4438");
        expected.add("D3820");
        expected.add("D2341");
        expected.add("D1927");

        update.deleteFromDataBase(idCombo, "Desk");

        try {
            Statement myStmt = update.getDbConnect().createStatement();
            results = myStmt.executeQuery("SELECT * FROM  DESK  WHERE Type = 'standing'" );
            int i = 0;
            while (results.next()) {
                result.add(i, results.getString("ID"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Collections.sort(expected);
        Collections.sort(result);

        assertEquals("Item not properly deleted", expected, result);
    }
}
