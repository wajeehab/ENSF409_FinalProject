package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.*;

public class Chair {
    private final InitializeConnection database = new InitializeConnection();
    private ArrayList<String> hasLegs;
    private ArrayList<String> hasArms;
    private ArrayList<String> hasSeats;
    private ArrayList<String> hasCushions;
    private ArrayList<Integer> totalPrice;
    private ArrayList<List<String>> combinations;
    private ResultSet results;
    private Connection dbConnect;
    private int smallest;
    private int sIndex;

    public Chair() {
        database.Initialize();
        dbConnect = database.getDbConnect();
    }

    public void selectChairInfo(String type) {
        System.out.println("Starting");
        List<List<String>> legs = new ArrayList<>();
        List<List<String>> arms = new ArrayList<>();
        List<List<String>> seat = new ArrayList<>();
        List<List<String>> cushion = new ArrayList<>();

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM  CHAIR  WHERE Type = '" + type + "'");
            int i = 0;
            while (results.next()) {
                legs.add(i, new ArrayList<>());
                arms.add(i, new ArrayList<>());
                seat.add(i, new ArrayList<>());
                cushion.add(i, new ArrayList<>());

                legs.get(i).add(0, results.getString("Legs"));
                legs.get(i).add(1, results.getString("ID"));

                arms.get(i).add(0, results.getString("Arms"));
                arms.get(i).add(1, results.getString("ID"));

                seat.get(i).add(0, results.getString("Seat"));
                seat.get(i).add(1, results.getString("ID"));

                cushion.get(i).add(0, results.getString("Cushion"));
                cushion.get(i).add(1, results.getString("ID"));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        createHasArrays(legs, arms, seat, cushion);
        boolean isEmpty = checkEmpty();
        if(isEmpty){
            OrderNotFulfilled order1 = new OrderNotFulfilled();
            order1.message();
            System.exit(1);
        }
        else if (!isEmpty){
            createCombinations();
        }
    }


    public void createHasArrays(List<List<String>> legs, List<List<String>> arms, List<List<String>> seat, List<List<String>> cushion) {
        hasLegs = new ArrayList<>();
        hasArms = new ArrayList<>();
        hasSeats = new ArrayList<>();
        hasCushions = new ArrayList<>();

            for (int i = 0; i < legs.size(); i++) {
                if (legs.get(i).get(0).equals("Y")) {
                    hasLegs.add((legs.get(i).get(1)));
                }
            }
            for (int i = 0; i < arms.size(); i++) {
                if (arms.get(i).get(0).equals("Y")) {
                    hasArms.add((arms.get(i).get(1)));
                }
            }
            for (int i = 0; i < seat.size(); i++) {
                if (seat.get(i).get(0).equals("Y")) {
                    hasSeats.add((seat.get(i).get(1)));
                }
            }
            for (int i = 0; i < cushion.size(); i++) {
                if (cushion.get(i).get(0).equals("Y")) {
                    hasCushions.add((legs.get(i).get(1)));
                }
            }

    }

    public boolean checkEmpty(){
        return hasLegs.size() == 0 | hasArms.size() == 0 | hasCushions.size() == 0 | hasSeats.size() == 0;
    }

    public void createCombinations() {
        ArrayList<List<String>> result = new ArrayList<>();
        int i = 0;
        for (String s1 : hasLegs) {
            for (String s2 : hasArms) {
                for (String s3 : hasSeats) {
                    for (String s4 : hasCushions) {
                        result.add(i, new ArrayList<>());
                        result.get(i).add(String.valueOf(s1));
                        result.get(i).add(String.valueOf(s2));
                        result.get(i).add(String.valueOf(s3));
                        result.get(i).add(String.valueOf(s4));
                        i++;
                    }
                }
            }
        }
        combinations = new ArrayList<>();
        for (int b = 0; b < result.size(); b++) {
            combinations.add(b, new ArrayList<>());
            for (int c = 0; c < result.get(b).size(); c++) {
                if (!combinations.get(b).contains(result.get(b).get(c))) {
                    combinations.get(b).add(result.get(b).get(c));
                }
            }
        }
        selectPrice();
    }

    public void selectPrice() {
      ArrayList<List<String>> price = new ArrayList<>();
        try {
            Statement myStmt = dbConnect.createStatement();
            for (int i = 0; i < combinations.size(); i++) {
                price.add(i, new ArrayList<>());
                for (int j = 0; j < combinations.get(i).size(); j++) {
                   results = myStmt.executeQuery("SELECT * FROM  CHAIR  WHERE ID = '" + combinations.get(i).get(j) + "'");
                    while (results.next()) {
                        price.get(i).add(results.getString("Price"));
                    }
                }

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addPrice(price);
    }

    public void addPrice(ArrayList<List<String>> price){
        totalPrice = new ArrayList<>();
        for(int a =0;a< price.size();a++){
            int sum =0;
            for(int b =0; b<price.get(a).size();b++){
                sum = sum+Integer.parseInt(price.get(a).get(b));
            }
            totalPrice.add((sum));
        }
        findCheapest();
    }

    public void findCheapest(){
        smallest = totalPrice.get(0);
        sIndex = 0;
        for (int i =0; i< totalPrice.size();i++){
            if(totalPrice.get(i)<smallest){
                smallest = totalPrice.get(i);
                sIndex = i;
            }
        }
    }

    public List<String> getIdCombo(){
        return combinations.get(sIndex);
    }

    public int getSmallest(){
        return smallest;
    }


    public void close() {
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//What needs to be added?
    //how to handle more than 1 order

}
