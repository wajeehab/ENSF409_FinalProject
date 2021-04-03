package edu.ucalgary.ensf409;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UpdateDatabase {
    private final InitializeConnection database = new InitializeConnection();
    private Connection dbConnect;

    public UpdateDatabase(){
        database.Initialize();
        dbConnect = database.getDbConnect();
    }
    public void deleteFromDataBase(List<String> idCombo, String table){
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

//    public void deleteLampFromDataBase(List<String> idCombo){
//
//        try {
//            for(int i =0; i< idCombo.size();i++){
//                String query = "DELETE FROM LAMP WHERE ID = ? ";
//                PreparedStatement myStmt = dbConnect.prepareStatement(query);
//
//                myStmt.setString(1, idCombo.get(i));
//
//                int rowCount = myStmt.executeUpdate();
//                System.out.println("Rows affected: " + rowCount);
//
//                myStmt.close();
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//

}
