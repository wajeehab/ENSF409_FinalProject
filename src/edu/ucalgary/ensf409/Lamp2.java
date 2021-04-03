package edu.ucalgary.ensf409;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Lamp2 {
    private final InitializeConnection database = new InitializeConnection();
    private ArrayList<ArrayList<String>> hasBase;
    private ArrayList<ArrayList<String>> hasBulb;
    private ArrayList<String> combinations = new ArrayList<>();
    private ResultSet results;
    private Connection dbConnect;
    private int smallest;
    private boolean isEmpty;
    private int numberOfItems;

    public Lamp2(int numberItems) {
        database.Initialize();
        dbConnect = database.getDbConnect();
        this.numberOfItems = numberItems;
        this.smallest =0;
    }

    public void selectLampInfo(String type) {
        System.out.println("Starting");
        ArrayList<ArrayList<String>> base = new ArrayList<>();
        ArrayList<ArrayList<String>> bulb = new ArrayList<>();

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM  LAMP  WHERE Type = '" + type + "'");
            int i = 0;
            while (results.next()) {
                base.add(i, new ArrayList<>());
                bulb.add(i, new ArrayList<>());

                base.get(i).add(0, results.getString("ID"));
                base.get(i).add(1, results.getString("Base"));
                base.get(i).add(2, results.getString("Price"));


                bulb.get(i).add(0, results.getString("ID"));
                bulb.get(i).add(1, results.getString("Bulb"));
                bulb.get(i).add(2, results.getString("Price"));


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.hasBase = createHasArrays(base);
        this.hasBulb = createHasArrays(bulb);
        isEmpty = checkEmpty();
        if (!isEmpty) {
            orderCombos(this.numberOfItems);
        }

        this.combinations = getRidofDuplicates(this.combinations);
    }

    public ArrayList<ArrayList<String>> createHasArrays(ArrayList<ArrayList<String>> arr) {
        ArrayList<ArrayList<String>> newArr = new ArrayList<>();
        int j =0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).get(1).equals("Y")) {
                    newArr.add(j, new ArrayList<>());
                    newArr.get(j).add(arr.get(i).get(0));
                    newArr.get(j).add(arr.get(i).get(2));
                    j++;
                }
        }
        return newArr;
    }

    public void orderCombos(int num){
        if(num<1){
            return;
        }
        int price = 100;
//        this.smallest = 100;
        int bulbIndex = 0;
        int baseIndex = 0;

        isEmpty = checkEmpty();
        if(isEmpty){
            System.exit(1);

        }

        for (int i =0; i< hasBulb.size();i++){
            for (int j=0; j<hasBase.size();j++){

                if(hasBulb.get(i).get(0).equals(hasBase.get(j).get(0))){
                    if(Integer.parseInt(hasBulb.get(i).get(1)) < price){
                        price = Integer.parseInt(hasBulb.get(i).get(1));
                        bulbIndex = i;
                        baseIndex = j;

                    }
                }

                else if (Integer.parseInt(hasBulb.get(i).get(1)) + Integer.parseInt(hasBase.get(j).get(1)) < price){
                    price = Integer.parseInt(hasBulb.get(i).get(1)) + Integer.parseInt(hasBase.get(j).get(1));
                    bulbIndex = i;
                    baseIndex = j;
                }
            }
        }
        setSmallest(price);
        combinations.add(hasBulb.get(bulbIndex).get(0));
        combinations.add(hasBase.get(baseIndex).get(0));

        updateHasArrays(hasBulb, combinations);
        updateHasArrays(hasBase, combinations);
        orderCombos(num -1);
        return;
    }

    public void updateHasArrays(ArrayList<ArrayList<String>> hasArr, ArrayList<String> IDs){
        for (int a =0; a< IDs.size();a++){
            for (int j =0; j<hasArr.size();j++) {
                if (hasArr.get(j).get(0).equals(IDs.get(a))) {
                    hasArr.remove(j);

                }
            }
            }
    }

    public boolean checkEmpty() {
        return hasBase.size() == 0 | hasBulb.size() == 0;
    }
     public boolean getIsEmpty(){
        return this.isEmpty;
     }

    public int getSmallest() {
        return this.smallest;
    }

    public void setSmallest(int p){
        this.smallest += p;
    }

    public ArrayList<String> getRidofDuplicates(ArrayList<String> result){
        ArrayList<String> combos = new ArrayList<>();
        for (int b = 0; b < result.size(); b++) {
                if (!combos.contains(result.get(b))){
                    combos.add(result.get(b));
                }
        }
        return combos;
    }


    public ArrayList<String> getIdCombo() {
        return combinations;
    }

}
