package edu.ucalgary.ensf409;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Lamp {
    private final InitializeConnection database = new InitializeConnection();
    private ArrayList<String> hasBase;
    private ArrayList<String> hasBulb;
    private ArrayList<Integer> totalPrice;
    private ArrayList<List<String>> combinations;
    private ResultSet results;
    private Connection dbConnect;
    private int smallest;
    private int sIndex;

    public Lamp() {
        database.Initialize();
        dbConnect = database.getDbConnect();
    }

    public void selectLampInfo(String type) {
        System.out.println("Starting");
        List<List<String>> base = new ArrayList<>();
        List<List<String>> bulb = new ArrayList<>();

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM  LAMP  WHERE Type = '" + type + "'");
            int i = 0;
            while (results.next()) {
                base.add(i, new ArrayList<>());
                bulb.add(i, new ArrayList<>());

                base.get(i).add(0, results.getString("Base"));
                base.get(i).add(1, results.getString("ID"));

                bulb.get(i).add(0, results.getString("Bulb"));
                bulb.get(i).add(1, results.getString("ID"));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        createHasArrays(base, bulb);
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


    public void createHasArrays(List<List<String>> base, List<List<String>> bulb) {
        hasBase = new ArrayList<>();
        hasBulb = new ArrayList<>();

        for (int i = 0; i < base.size(); i++) {
            if (base.get(i).get(0).equals("Y")) {
                hasBase.add((base.get(i).get(1)));
            }
        }
        for (int i = 0; i < bulb.size(); i++) {
            if (bulb.get(i).get(0).equals("Y")) {
                hasBulb.add((bulb.get(i).get(1)));
            }
        }

    }

    public boolean checkEmpty(){
        return hasBase.size() == 0 | hasBulb.size() == 0;
    }

    public void createCombinations() {
        ArrayList<List<String>> result = new ArrayList<>();
        int i = 0;
        for (String s1 : hasBase) {
            for (String s2 : hasBulb) {
                        result.add(i, new ArrayList<>());
                        result.get(i).add(String.valueOf(s1));
                        result.get(i).add(String.valueOf(s2));
                        i++;
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
        System.out.println(combinations);
        selectPrice();
    }

    public void selectPrice() {
        ArrayList<List<String>> price = new ArrayList<>();
        try {
            Statement myStmt = dbConnect.createStatement();
            for (int i = 0; i < combinations.size(); i++) {
                price.add(i, new ArrayList<>());
                for (int j = 0; j < combinations.get(i).size(); j++) {
                    results = myStmt.executeQuery("SELECT * FROM  lamp  WHERE ID = '" + combinations.get(i).get(j) + "'");
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
}
