package edu.ucalgary.ensf409;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.sql.*;

public class InitializeConnectionTest {
    
    @Test
    public void InitializeTest() {
        
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
        String[] expected = {"D1927", "D2341", "D3820", "D4438", "D9387"};

        assertEquals("Connection was not established and correct ID's not retrieved", expected, results2);
    }
    @Test
    public void TestClose() throws SQLException {
        Chair newChair = new Chair(1);
        newChair.selectChairInfo("mesh");
        Connection newConnect = newChair.getDbConnect();
        newChair.close();
        boolean connect = newConnect.isClosed();
        boolean expected = true;

        assertEquals("Connection was not closed succesfully", expected, connect);
    }

}
