package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
        private Connection dbConnect;
        private ResultSet results;
        private InitializeConnection database = new InitializeConnection();
        private ArrayList<String> prices = new ArrayList<>();
        private ArrayList<String> ID = new ArrayList<>();
        private OrderUserInterface order = new OrderUserInterface();


    public DataBase(){ };

    public void SelectFurnitureCategory(){
        database.Initiazlize();
        if (order.getFurnitureCategory() == "chair" | order.getFurnitureCategory() == "Chair"){
            selectChairInfo();
        }

        if(order.getFurnitureCategory() == "desk" || order.getFurnitureCategory() == "Desk"){
            selectDeskInfo();
        }

        if(order.getFurnitureCategory() == "filing" | order.getFurnitureCategory() == "Filing"){
            selectFilingInfo();
        }

        if(order.getFurnitureCategory() == "Lamp" | order.getFurnitureCategory()=="lamp"){
            selectLampInfo();
        }
    }

    public void selectChairInfo() {
        try {
            Statement myStmt = database.getDbConnect().createStatement();
            results = myStmt.executeQuery("SELECT * FROM " + order.getFurnitureCategory() + " WHERE Type = '" + order.getFurnitureType() + "'");
            while (results.next()) {
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void selectDeskInfo(){

    }

    public void selectFilingInfo(){

    }
    public void selectLampInfo(){

    }
     public void selectFurnitureCategoryAndType() {
         try {
             Statement myStmt = database.getDbConnect().createStatement();
             results = myStmt.executeQuery("SELECT * FROM " + order.getFurnitureCategory() + " WHERE Type = '" + order.getFurnitureType() +"'");
             while (results.next()){
                 prices.add(results.getString("Price"));
                 ID.add(results.getString("ID"));
             }
             myStmt.close();

         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }

     }
         public static void main(String[] args) {
            DataBase user1 = new DataBase();
            TextFile text = new TextFile();
            user1.selectFurnitureCategoryAndType();
        }

    }
