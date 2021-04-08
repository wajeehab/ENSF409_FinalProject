//package edu.ucalgary.ensf409;

import java.util.Scanner;

public class DataBaseUserInterface {
    public final String DBURL; //store the database url information
    public final String USERNAME; //store the user's account username
    public final String PASSWORD; //store the user's account password

    public DataBaseUserInterface() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter DataBase URL:  ");
        DBURL = reader.nextLine(); // Scans the next token of the input as an int.
        System.out.println("Enter DataBase username:  ");
        USERNAME= reader.nextLine(); // Scans the next token of the input as an int.

        System.out.println("Enter DataBase password:  ");
        PASSWORD = reader.nextLine(); // Scans the next token of the input as an int.

//once finished
        reader.close();
    }
    public String getDburl(){
        return DBURL;
    }
    public String getUsername(){
        return USERNAME;
    }

    public String getPassword(){
        return PASSWORD;
    }

}

