package edu.ucalgary.ensf409;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        private String orderCombo[] = new String[16];
        private ArrayList<String> totalOrder = new ArrayList<>();

        public Desk(int numberItems) {
            database.Initialize();
            dbConnect = database.getDbConnect();
            this.numberOfItems = numberItems;
            this.smallest =0;
        }

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
                    legs.get(i).add(1, results.getString("Legs"));

                    top.get(i).add(0, results.getString("ID"));
                    top.get(i).add(1, results.getString("Top"));

                    drawer.get(i).add(0, results.getString("ID"));
                    drawer.get(i).add(1, results.getString("Drawer"));

                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            hasDrawer = createHasArrays(drawer);
            hasTop = createHasArrays(top);
            hasLegs = createHasArrays(legs);

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
                for (String s2 : hasDrawer) {
                    for (String s3 : hasTop) {
                            result.add(i, new ArrayList<>());
                            result.get(i).add(s1);
                            result.get(i).add(s2);
                            result.get(i).add(s3);
                            i++;
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
                        results = myStmt.executeQuery("SELECT * FROM  DESK WHERE ID = '" + combinations.get(i).get(j) + "'");
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
            updateHasArrays(hasLegs, orderCombo);
            updateHasArrays(hasDrawer, orderCombo);
            updateHasArrays(hasTop, orderCombo);
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
            return hasLegs.size() == 0| hasTop.size() == 0 | hasDrawer.size() == 0;
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

