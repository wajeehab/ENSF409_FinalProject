package edu.ucalgary.ensf409;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class Lamp {
    private final InitializeConnection DATABASE = new InitializeConnection();
    private ArrayList<ArrayList<String>> hasBase;
    private ArrayList<ArrayList<String>> hasBulb;
    private ArrayList<String> combinations = new ArrayList<>();
    private ResultSet results;
    private Connection dbConnect;
    private int smallest;
    private boolean isEmpty;
    private int numberOfItems;

    /**
     * Constructor method which initializes the DATABASE connection and takes in
     * the number of desks required for the order.
     *
     * @param numberItems - the  number of items required in the order
     */
    public Lamp (int numberItems, String user, String url, String password) {
        DATABASE.Initialize(user, url, password);
        dbConnect = DATABASE.getDbConnect();
        this.numberOfItems = numberItems; //initializes the number of items needed in that order
        this.smallest =0; //initializing the smallest sum to zero
    }

    /**
     * This function searches the Desk table in the inventory DATABASE
     * and creates table which stores the ID and inventory of the items
     * which match the given type. From the extracted inventory from the DATABASE,
     * HasArrays are created which store the ID's which have "Y" values for
     * specific parts.
     *
     * @param type - the type of furniture which is requested in the order
     */
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
                base.get(i).add(1, results.getString("Base")); //associates 'Y' or 'N' for the base of the ID in question
                base.get(i).add(2, results.getString("Price")); //associates price with ID

                bulb.get(i).add(0, results.getString("ID"));
                bulb.get(i).add(1, results.getString("Bulb")); //associates 'Y' or 'N' for the legs of the ID in question
                bulb.get(i).add(2, results.getString("Price")); //associates price with ID
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.hasBase = createHasArrays(base); //creating the HasArrays
        this.hasBulb = createHasArrays(bulb);

        isEmpty = checkEmpty(); //checking to make sure the HasArrays are not empty
        if (!isEmpty) { //if not empty, then create combinations with the hasArrays
            orderCombos(this.numberOfItems);
        }
        else{
            return; //if the hasArrays are empty, then return back to main and do not make combinations
        }

        this.combinations = getRidofDuplicates(this.combinations); //remove duplicate ID from overall order combination
    }

    /**This method creates hasArrays
     * @param arr - the initial array which stores all inventory from the DATABASE for a given ID
     * @return the correct hasArray which contains ID which contains the correct inventory for each part
     */
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

    /** This is a recursive method which finds the cheapest combination of parts for a given number of orders
     * @param num - number of orders
     */
    public void orderCombos(int num){
        if(num<1){ //base case
            return;
        }

        isEmpty = checkEmpty(); //checks if any of the hasArrays are empty and if no more combinations can be made
        if(isEmpty){
            return; //returns if any of them are empty
        }
        
        int price = findPriceAndCombo(); //calls function which finds the smallest order price
        setSmallest(price); //calling setter method to set the smallest price

        updateHasArrays(hasBulb, combinations); //updating the hasArrays
        updateHasArrays(hasBase, combinations);

        orderCombos(num -1); //decrementing the number of order for recursion
        return;
    }

    /** This method finds the combination with the lowest price and returns the price
     * @return
     */
    public int findPriceAndCombo(){
        int cost = 100;
        int bulbIndex = 0;
        int baseIndex = 0;
        for (int i =0; i< hasBulb.size();i++){
            for (int j=0; j<hasBase.size();j++){
                if(hasBulb.get(i).get(0).equals(hasBase.get(j).get(0))){ //if the ID matches in the 2 has Arrays
                    if(Integer.parseInt(hasBulb.get(i).get(1)) < cost){ //if the price of the ID is lower than current lowest price
                        cost = Integer.parseInt(hasBulb.get(i).get(1)); //replace lowest price
                        bulbIndex = i; //points to index which gave lowest cost
                        baseIndex = j;
                    }
                }
                else if (Integer.parseInt(hasBulb.get(i).get(1)) + Integer.parseInt(hasBase.get(j).get(1)) < cost){ //if the price of the (non-matching) IDs is lower than current lowest price
                    cost = Integer.parseInt(hasBulb.get(i).get(1)) + Integer.parseInt(hasBase.get(j).get(1)); //replace lowest price
                    bulbIndex = i; //points to index which gave lowest cost
                    baseIndex = j;
                }
            }
        }
        combinations.add(hasBulb.get(bulbIndex).get(0)); //add IDs to order combination
        combinations.add(hasBase.get(baseIndex).get(0));
        return cost;
    }

    /** For multiple orders, this method updates the hasArrays to remove the ID's who's parts have been
     * used to create an item
     * @param hasArr - the hasArray
     * @param IDs - array which stores the ID's of the combination
     */
    public void updateHasArrays(ArrayList<ArrayList<String>> hasArr, ArrayList<String> IDs){
        for (int a =0; a<IDs.size();a++){
            for (int j =0; j<hasArr.size();j++) {
                if (hasArr.get(j).get(0).equals(IDs.get(a))) {
                    hasArr.remove(j);
                    return;
                }
            }
        }
    }

    /** This method checks the size of the hasArrays and returns true or false
     * @return true or false
     */
    public boolean checkEmpty() {
        return hasBase.size() == 0 | hasBulb.size() == 0;
    }

    /**
     * getter method for isEmpty, which indicates if any of the hasArrays are empty
     * and no more combinations can be made
     * @return
     */
     public boolean getIsEmpty(){
        return this.isEmpty;
     }

     /**
     * getter method for the cheapest price
     * @return
     */
    public int getSmallest() {
        return this.smallest;
    }

    /**
     * setter method for the price.
     * @param p - price for combination
     */
    public void setSmallest(int p){
        this.smallest += p;
    }

    /** This method will remove duplicate values within the passed array in order to create unique combinations
     * @param result - the list which needs duplicates removes
     * @return returns updated list
     */
    public ArrayList<String> getRidofDuplicates(ArrayList<String> result){
        ArrayList<String> combos = new ArrayList<>();
        for (int b = 0; b < result.size(); b++) {
                if (!combos.contains(result.get(b))){
                    combos.add(result.get(b));
                }
        }
        return combos;
    }

    /**
     * getter method for the combination which makes the cheapest order
     * @return
     */
    public ArrayList<String> getIdCombo() {
        return combinations;
    }

    /**
     * This method closes all database connections
     */
    public void close() {
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
