package edu.ucalgary.ensf409;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UpdateDatabase {
    private final InitializeConnection database = new InitializeConnection();
    private ResultSet results;
    private Connection dbConnect;

    public UpdateDatabase(){
        database.Initialize();
        dbConnect = database.getDbConnect();
    }
    public void deleteChairFromDataBase(List<String> idCombo){

        try {
            for(int i =0; i< idCombo.size();i++){
                String query = "DELETE FROM CHAIR WHERE ID = ? ";
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
}
