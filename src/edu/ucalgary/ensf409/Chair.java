package edu.ucalgary.ensf409;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Chair {
    private final InitializeConnection database = new InitializeConnection();
    private ArrayList<ArrayList<String>> hasLegs;;
    private ArrayList<ArrayList<String>> hasArms;
    private ArrayList<ArrayList<String>> hasSeats;
    private ArrayList<ArrayList<String>> hasCushions;
    private ArrayList<String> combinations = new ArrayList<>();
    private ResultSet results;
    private Connection dbConnect;
    private int smallest;
    private boolean isEmpty;
    private int numberOfItems;
    public Chair(int numberItems) {
        database.Initialize();
        dbConnect = database.getDbConnect();
        this.numberOfItems = numberItems;
        this.smallest =0;
    }
    public void selectChairInfo(String furnitureType) {
    }

    public boolean getIsEmpty() {
        return isEmpty
    }

    public List<String> getIdCombo() {
        return combinations;
    }

    public int getSmallest() {
        return smallest;
    }
}
