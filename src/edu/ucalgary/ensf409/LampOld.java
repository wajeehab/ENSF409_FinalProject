package edu.ucalgary.ensf409;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LampOld{
    private final InitializeConnection database = new InitializeConnection();
    private ArrayList<String> hasBase;
    private ArrayList<String> hasBulb;
    private ArrayList<Integer> totalPrice;
    private ArrayList<ArrayList<String>> combinations;
    private ResultSet results;
    private Connection dbConnect;
    private int smallest;
    private int sIndex;
    private boolean isEmpty;
    private int numberOfItems;

    public LampOld(int num) {
        database.Initialize();
        dbConnect = database.getDbConnect();
        this.numberOfItems = num;
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
        this.hasBase = createHasArrays(base);
        this.hasBulb = createHasArrays(bulb);

        isEmpty = checkEmpty();
        if (!isEmpty) {
            createCombinations(this.hasBase, this.hasBulb);
        }
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public ArrayList<String> createHasArrays(List<List<String>> arr) {
        ArrayList<String> newArr = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).get(0).equals("Y")) {
                newArr.add((arr.get(i).get(1)));
            }
        }
    return newArr;
    }

    public boolean checkEmpty() {
        return hasBase.size() == 0 | hasBulb.size() == 0;
    }

    public void createCombinations(ArrayList<String> arr1, ArrayList<String> arr2) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        int i = 0;
        for (String s1 : arr1) {
            for (String s2 : arr2) {
                result.add(i, new ArrayList<>());
                result.get(i).add(String.valueOf(s1));
                result.get(i).add(String.valueOf(s2));
                i++;
            }
        }
        this.combinations = getRidofDuplicates(result);
        selectPrice();
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
    public void selectPrice() {
        ArrayList<ArrayList<String>> price = new ArrayList<>();
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

    public void addPrice(ArrayList<ArrayList<String>> price) {
        totalPrice = new ArrayList<>();
        for (int a = 0; a < price.size(); a++) {
            int sum = 0;
            for (int b = 0; b < price.get(a).size(); b++) {
                sum = sum + Integer.parseInt(price.get(a).get(b));
            }
            totalPrice.add((sum));
        }
        findCheapest();
    }

    public void findCheapest() {
        smallest = totalPrice.get(0);
        sIndex = 0;
        for (int i = 0; i < totalPrice.size(); i++) {
            if (totalPrice.get(i) < smallest) {
                smallest = totalPrice.get(i);
                sIndex = i;
            }
        }
        if (numberOfItems > 1){
            multipleOrders();
        }
    }

    public void multipleOrders() {
        System.out.println(combinations.get(sIndex));
        ArrayList<List<String>> arr = new ArrayList<>();
        try {
            Statement myStmt = dbConnect.createStatement();
            for (int i = 0; i < combinations.get(sIndex).size(); i++) {
                results = myStmt.executeQuery("SELECT * FROM  lamp  WHERE ID = '" + combinations.get(sIndex).get(i)+ "'");
                while (results.next()) {
                    arr.add(i, new ArrayList<>());
                    arr.get(i).add(0, results.getString("ID"));
                    arr.get(i).add(1, results.getString("Base"));
                    arr.get(i).add(2, results.getString("Bulb"));
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ArrayList <String> missingBase = new ArrayList<>();
        ArrayList <String> missingBulb = new ArrayList<>();

        for (int a = 0; a<arr.size();a++){
                if(arr.get(a).get(1).equals("N")){
                    missingBase.add((arr.get(a).get(0) + "-" + arr.get(a).get(1)));
                }
                else if (arr.get(a).get(2).equals("N")) {
                    missingBulb.add((arr.get(a).get(0) + "-" + arr.get(a).get(2)));
                }
        }
        if(missingBase.size()!=0){
           this.hasBase = updateHasArrays(this.hasBase);
           multipleOrderCombos(combinations.get(sIndex), hasBase);
        }
        if (missingBulb.size()!=0){
           this.hasBulb = updateHasArrays(this.hasBulb);
//            createCombinations((ArrayList<String>) combinations.get(sIndex), hasBulb);

        }
        System.out.println(hasBulb);
        System.out.println(hasBase);
    }

    public void multipleOrderCombos(ArrayList<String> combo, ArrayList<String> hasArr){
        String comboIds = combo.toString();
        String newComboIds = comboIds.substring(1, comboIds.length()-1);
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        int i = 0;
        for (String s1 : hasArr) {
            {
                result.add(i, new ArrayList<>());
                result.get(i).add(String.valueOf(s1));
                result.get(i).add(newComboIds);
                i++;
            }
        }
        System.out.println(result);
    }

    public ArrayList<String> updateHasArrays(ArrayList<String> hasArray){
        for (int a =0; a<combinations.get(sIndex).size();a++){
            hasArray.remove(combinations.get(sIndex).get(a));
        }
        return hasArray;
    }


    public List<String> getIdCombo() {
        return combinations.get(sIndex);
    }

    public int getSmallest() {
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

//    public void combinations2(ArrayList<String> hasArr, List<String> prevCombo) {
////        System.out.println(prevCombo);
//        ArrayList<List<String>> result = new ArrayList<>();
//        int i = 0;
//        for (String s1 : hasArr) {
//            for (String s2 : prevCombo) {
//                result.add(i, new ArrayList<>());
//                if(!s1.equals(s2) | !prevCombo.contains(s1)) {
//                    result.get(i).add(s1);
//                    result.get(i).add(String.valueOf(s2));
//                    i++;
//                }
//            }
//        }
//
//        System.out.println(result);
////        getRidofDuplicates(result);
////        System.out.println(combinations);
//    }

    //    public void priceCalculator(ArrayList<String> hasArr, List<String> prevCombo) {
//        ArrayList<String> price = new ArrayList<>();
//        System.out.println(hasArr);
//        try {
//            Statement myStmt = dbConnect.createStatement();
//            for (int i = 0; i < hasArr.size(); i++) {
//                    results = myStmt.executeQuery("SELECT * FROM  lamp  WHERE ID = '" + hasArr.get(i) + "'");
//                    while (results.next()) {
//                            price.add(results.getString("Price"));
//                    }
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        System.out.println(price);
//    }
    //dealing with multiple order:
    //if order number is more than 1, take the combinations made with 1 order and create combinations all over again????
}
