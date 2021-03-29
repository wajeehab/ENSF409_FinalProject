package edu.ucalgary.ensf409;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
        private Connection dbConnect;
        private DataBaseUserInterface user = new DataBaseUserInterface();

        public DataBase(){};

        public void initializeConnection() {
            try{
                dbConnect = DriverManager.getConnection(user.getDburl(), user.getUsername(), user.getPassword());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public static void main(String[] args) {
            DataBase user1 = new DataBase();
            user1.initializeConnection();
        }

    }
