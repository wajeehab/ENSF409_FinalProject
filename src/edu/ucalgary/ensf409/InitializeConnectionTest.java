//package edu.ucalgary.ensf409;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.sql.*;

public class InitializeConnectionTest {
    
    @Test
    public void InitializeTest() throws SQLException{
        
        InitializeConnection newInitializeConnection = new InitializeConnection();
        newInitializeConnection.Initialize();
        Connection newConnect = newInitializeConnection.getDbConnect();
        ResultSet results;
        String[] results2 = new String[5];

        try {
            Statement myStmt = newConnect.createStatement();
            results = myStmt.executeQuery("SELECT ID FROM  DESK  WHERE Type = 'Standing'");
            int i = 0;
            while (results.next()) {
                results2[i] = results.getString("ID");
                i++;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        //boolean connect = newConnect.isValid(30);
        //boolean expected = false;
        String[] expected = {"D1927", "D2341", "D3820", "D4438", "D9387"};

        assertEquals("", expected, results2);
    }

}
