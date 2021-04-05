package edu.ucalgary.ensf409;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * This class gathers information from the Desk database and finds
 * the cheapest combination of items which will
 * create a whole desk, or multiple desks
 */
public class Desk {
        private final InitializeConnection database = new InitializeConnection();
        private ArrayList<String> hasLegs;
        private ArrayList<String>hasTop;
        private ArrayList<String> hasDrawer;
        private ArrayList<ArrayList<String>> combinations = new ArrayList<>();
        private ArrayList<ArrayList<String>> price = new ArrayList<>();
        private ResultSet results;
        private Connection dbConnect;
        private int smallest;
        private boolean isEmpty;
        private int numberOfItems;
        private String[] orderCombo = new String[16];
        private ArrayList<String> totalOrder = new ArrayList<>();
    /**
     * Constructor method which initializes the database connection and takes in
     * the number of desks required for the order.
     *
     * @param numberItems - the  number of items required in the order
     */
        public Desk(int numberItems) {
            database.Initialize();
            dbConnect = database.getDbConnect();
            this.numberOfItems = numberItems; //initializes the number of items needed in that order
            this.smallest =0; //initializing the smallest sum to zero
        }
    /**
     * This function searches the Desk table in the inventory database
     * and creates table which stores the ID and inventory of the items
     * which match the given type. From the extracted inventory from the database,
     * HasArrays are created which store the ID's which have "Y" values for
     * specific parts.
     *
     * @param type - the type of furniture which is requested in the order
     */
        public void selectDeskInfo(String type) {
            System.out.println("Starting");
            ArrayList<ArrayList<String>> legs = new ArrayList<>();
            ArrayList<ArrayList<String>> top = new ArrayList<>();
            ArrayList<ArrayList<String>> drawer = new ArrayList<>();

            try {
                Statement myStmt = dbConnect.createStatement();
                results = myStmt.executeQuery("SELECT * FROM  DESK  WHERE Type = '" + type + "'");
                int i = 0;
                while (results.next()) {
                    legs.add(i, new ArrayList<>());
                    top.add(i, new ArrayList<>());
                    drawer.add(i, new ArrayList<>());

                    legs.get(i).add(0, results.getString("ID")); 
                    legs.get(i).add(1, results.getString("Legs")); //associates 'Y' or 'N' for the legs of the ID in question

                    top.get(i).add(0, results.getString("ID"));
                    top.get(i).add(1, results.getString("Top")); //associates 'Y' or 'N' for the top of the ID in question

                    drawer.get(i).add(0, results.getString("ID"));
                    drawer.get(i).add(1, results.getString("Drawer")); //associates 'Y' or 'N' for the drawers of the ID in question

                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            hasDrawer = createHasArrays(drawer); //creating the HasArrays
            hasTop = createHasArrays(top);
            hasLegs = createHasArrays(legs);

            isEmpty = checkEmpty(); //checking to make sure the HasArrays are not empty
            if (!isEmpty) { //if not empty, then create combinations with the hasArrays
                orderCombos(this.numberOfItems);
            }
            else{
                return; //if the hasArrays are empty, then return back to main and do not make combinations
            }
        }

    /**
     * This method will go through the hasArrays and create every combination possible for a full order
     */
        public void createCombinations() {
            ArrayList<ArrayList<String>> result = new ArrayList<>();
            int i = 0;
            for (String s1 : hasLegs) { //going through the specific hasArrays
                for (String s2 : hasDrawer) {
                    for (String s3 : hasTop) {
                            result.add(i, new ArrayList<>());
                            result.get(i).add(s1);
                            result.get(i).add(s2); //creating every type of combination
                            result.get(i).add(s3);
                            i++;
                    }
                }
            }
            combinations= getRidofDuplicates(result); //getting rid of duplicate ID'S within each combination
            selectPrice(); //finding the prices of each combination
        }
    /**
     * This method goes through the combination lists and finds the price associated
     * with each ID in the combination.
     */
        public void selectPrice() {
            try{
                Statement myStmt = dbConnect.createStatement();
                for (int i = 0; i < combinations.size(); i++) {
                    price.add(i, new ArrayList<>());
                    for (int j = 0; j < combinations.get(i).size(); j++) {
                        results = myStmt.executeQuery("SELECT * FROM  DESK WHERE ID = '" + combinations.get(i).get(j) + "'"); //get price of every element in combination
                        while (results.next()) {
                            price.get(i).add(results.getString("Price")); //create price array
                        }
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    /** This is a recursive method which finds the cheapest combination of parts for a given number of orders
     * @param num - number of orders
     */
    private void orderCombos(int num) {
        if(num<1){
            return; //base case
        }
        isEmpty = checkEmpty(); //checks if any of the hasArrays are empty and if no more combinations can be made
        if(isEmpty){
            return; //returns if any of them are empty
        }
        combinations.clear(); //clear the previous combinations from the order before
        price.clear(); //clear the previous prices from the order before
        createCombinations(); //creating the combinations and their prices

        int orderPrice = findPriceAndCombo(); //calls function which finds the smallest order price

        setSmallest(orderPrice); //calling setter method to set the smallest price
        addToOrder(); //for multiple orders, this function will add on to the first generated combination

        updateHasArrays(hasLegs, orderCombo); //updating the hasArrays
        updateHasArrays(hasDrawer, orderCombo);
        updateHasArrays(hasTop, orderCombo);

        orderCombos(num-1); //decrementing the number of order for recursion
        return;
    }

    /** This method finds the combination with the lowest price and returns the price
     * @return
     */
    public int findPriceAndCombo(){
        int cost = 1000;
        for(int a =0;a< price.size();a++){
            int sum =0;
            for(int b =0; b<price.get(a).size();b++){
                boolean used = false;
                for(int c = 0; c<totalOrder.size();c++){
                    if(combinations.get(a).get(b).equals(totalOrder.get(c))){
                        used = true;     //for multiple orders, if an ID is reused for another part, this
                                         //will ignore the price of that already used ID
                    }
                }
                if(!used) {
                    sum = sum + Integer.parseInt(price.get(a).get(b)); //if the ID has not been used in the previous combination, then add to the price
                }
            }
            if(sum<cost){
                cost = sum;
                orderCombo = combinations.get(a).toArray(new String[0]); //storing the combination with the lowest price
            }
        }
        return cost; //returns the lowest price

    }

    /**
     * This method will add ID's to the total order, ignoring duplicates (IDs passed in during the previous recursion)
     */
    public void addToOrder(){
        boolean exists;
        for(int i =0; i<this.orderCombo.length;i++){
            exists = false;
            for (int j =0; j<this.totalOrder.size();j++){
                if(this.totalOrder.get(j).equals(this.orderCombo[i])){ //if ID already exists in totalOrder
                    exists = true; //will not add the ID again
                }
            }
            if(!exists){
                this.totalOrder.add(this.orderCombo[i]); //add the combination for the current order number to the overall order combination
            }
        }
    }

    /** This method will remove duplicate values within the passed array in order to create unique combinations
     * @param result - the list which needs duplicates removes
     * @return returns updated list
     */
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

    /** This method checks the size of the hasArrays and returns true or false
     * @return true or false
     */
        private boolean checkEmpty() {
            return hasLegs.size() == 0| hasTop.size() == 0 | hasDrawer.size() == 0;
        }

    /**This method creates hasArrays
     * @param arr - the initial array which stores all inventory from the Database for a given ID
     * @return the correct hasArray which contains ID which contains the correct inventory for each part
     */
        public ArrayList<String> createHasArrays(ArrayList<ArrayList<String>> arr) {
            ArrayList<String> newArr = new ArrayList<>();
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).get(1).equals("Y")) {
                    newArr.add((arr.get(i).get(0)));
                }
            }
            return newArr;
        }

    /** For multiple orders, this method updates the hasArrays to remove the ID's who's parts have been
     * used to create an item
     * @param hasArr - the hasArray
     * @param IDs - array which stores the ID's of the combination
     */
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

    /**
     * getter method for isEmpty, which indicates if any of the hasArrays are empty
     * and no more combinations can be made
     * @return
     */
        public boolean getIsEmpty() {
            return isEmpty;
        }

    /**
     * getter method for the combination which makes the cheapest order
     * @return
     */
        public List<String> getIdCombo() {
            return totalOrder;
        }

    /**
     * getter method for the cheapest price
     * @return
     */
        public int getSmallest() {
            return smallest;
        }

    /**
     * setter method for the price.
     * @param p - price for combination
     */
        public void setSmallest(int p){
            this.smallest += p;
        }
}

