package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Chair {
    private final InitializeConnection database = new InitializeConnection();
    private ArrayList<String> hasLegs;
    private ArrayList<String>hasArms;
    private ArrayList<String> hasSeats;
    private ArrayList<String> hasCushions;
    private ArrayList<ArrayList<String>> combinations = new ArrayList<>();
    private ArrayList<ArrayList<String>> price = new ArrayList<>();
    private ResultSet results;
    private Connection dbConnect;
    private int smallest;
    private boolean isEmpty;
    private int numberOfItems;
    private String orderCombo[] = new String[16];
    private ArrayList<String> totalOrder = new ArrayList<>();

    public Chair(int numberItems) {
        database.Initialize();
        dbConnect = database.getDbConnect();
        this.numberOfItems = numberItems;
        this.smallest =0;
    }

    public void selectChairInfo(String type) {
        System.out.println("Starting");
        ArrayList<ArrayList<String>> legs = new ArrayList<>();
        ArrayList<ArrayList<String>> arms = new ArrayList<>();
        ArrayList<ArrayList<String>> seat = new ArrayList<>();
        ArrayList<ArrayList<String>> cushion = new ArrayList<>();

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM  CHAIR  WHERE Type = '" + type + "'");
            int i = 0;
            while (results.next()) {
                legs.add(i, new ArrayList<>());
                arms.add(i, new ArrayList<>());
                seat.add(i, new ArrayList<>());
                cushion.add(i, new ArrayList<>());

                legs.get(i).add(0, results.getString("ID"));
                legs.get(i).add(1, results.getString("Legs"));

                arms.get(i).add(0, results.getString("ID"));
                arms.get(i).add(1, results.getString("Arms"));

                seat.get(i).add(0, results.getString("ID"));
                seat.get(i).add(1, results.getString("Seat"));

                cushion.get(i).add(0, results.getString("ID"));
                cushion.get(i).add(1, results.getString("Cushion"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        hasSeats = createHasArrays(seat);
        hasArms = createHasArrays(arms);
        hasLegs = createHasArrays(legs);
        hasCushions = createHasArrays(cushion);

        isEmpty = checkEmpty();
        if (!isEmpty) {
            orderCombos(this.numberOfItems);
        }
        else{
            return;
        }
    }


    public void createCombinations() {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        int i = 0;
        for (String s1 : hasLegs) {
            for (String s2 : hasArms) {
                for (String s3 : hasSeats) {
                    for (String s4 : hasCushions) {
                        result.add(i, new ArrayList<>());
                            result.get(i).add(s1);
                            result.get(i).add(s2);
                            result.get(i).add(s3);
                            result.get(i).add(s4);
                            i++;
                         }
                    }
                }
            }
        combinations= getRidofDuplicates(result);
        selectPrice();
    }


    public void selectPrice() {
        try{
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

        System.out.println(price);
    }

    private void orderCombos(int num) {
        if(num<1){
            return;
        }
        int orderPrice = 1000;

        isEmpty = checkEmpty();
        if(isEmpty){
            return;
        }
        combinations.clear();
        price.clear();
        createCombinations();
        for(int a =0;a< price.size();a++){
            int sum =0;
            for(int b =0; b<price.get(a).size();b++){
                boolean used = false;
                for(int c = 0; c<totalOrder.size();c++){
                    if(combinations.get(a).get(b).equals(totalOrder.get(c))){
                        used = true;
                    }
                }
                if(!used) {
                    sum = sum + Integer.parseInt(price.get(a).get(b));
                }
            }
            if(sum<orderPrice){
                orderPrice = sum;
                orderCombo = combinations.get(a).toArray(new String[0]);
            }
        }
        setSmallest(orderPrice);
        boolean exists;
        for(int i =0; i<orderCombo.length;i++){
            exists = false;
            for (int j =0; j<totalOrder.size();j++){
                if(totalOrder.get(j).equals(orderCombo[i])){
                    exists = true;
                }
            }
            if(!exists){
                totalOrder.add(orderCombo[i]);
            }
        }
        updateHasArrays(hasArms, orderCombo);
        updateHasArrays(hasLegs, orderCombo);
        updateHasArrays(hasSeats, orderCombo);
        updateHasArrays(hasCushions, orderCombo);
        orderCombos(num-1);
        return;
    }


    public ArrayList<ArrayList<String>> getRidofDuplicates(ArrayList<ArrayList<String>> result){
        ArrayList<ArrayList<String>> combos = new ArrayList<>();
        for (int b = 0; b < result.size(); b++) {
            combos.add(b, new ArrayList<>());
            for (int c = 0; c < result.get(b).size(); c++) {
                if (!combos.get(b).contains(result.get(b).get(c))) {
                    combos.get(b).add(result.get(b).get(c));
                }
            }
        }
        return combos;
    }


    private boolean checkEmpty() {
        return hasLegs.size() == 0| hasArms.size() == 0 | hasCushions.size() == 0 | hasSeats.size() == 0;
    }

    public ArrayList<String> createHasArrays(ArrayList<ArrayList<String>> arr) {
        ArrayList<String> newArr = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).get(1).equals("Y")) {
                newArr.add((arr.get(i).get(0)));
            }
        }
        return newArr;
    }

    public void updateHasArrays(ArrayList<String> hasArr, String[] IDs){
        for (int a =0; a< IDs.length;a++){
            for(int j =0; j<hasArr.size();j++){
                if(hasArr.get(j).equals(IDs[a])){
                    hasArr.remove(j);
                    return;
                }
            }
        }
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public List<String> getIdCombo() {
        return totalOrder;
    }

    public int getSmallest() {
        return smallest;
    }

    public void setSmallest(int p){
        this.smallest += p;
    }
}
