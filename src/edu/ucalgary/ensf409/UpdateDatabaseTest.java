package edu.ucalgary.ensf409;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UpdateDatabaseTest {

    @Test
    public void deleteFromDataBaseTest() {
        UpdateDatabase update = new UpdateDatabase();
        ResultSet results;
        ArrayList<String> idCombo = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();


        idCombo.add("D1927");
        idCombo.add("D2341");
        idCombo.add("D3820");

        expected.add("D9387");
        expected.add("D4438");


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

        assertEquals("Items not properly deleted", expected, result);
    }
}
