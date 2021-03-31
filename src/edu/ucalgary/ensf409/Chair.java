package edu.ucalgary.ensf409;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chair {
    private ResultSet results;
    private InitializeConnection database = new InitializeConnection();
    private ArrayList<List<String>> hasLegs = new ArrayList<>();
    private ArrayList<List<String>> hasArms = new ArrayList<>();
    private ArrayList<List<String>> hasSeats = new ArrayList<>();
    private ArrayList<List<String>> hasCushions= new ArrayList<>();
    private ArrayList<List<String>> ID = new ArrayList<>();

    private ArrayList<List<String>> unique = new ArrayList<>();


    public Chair(){ database.Initiazlize();};

    public void selectChairInfo(String type) throws SQLException {
        List<List<String>> legs = new ArrayList<>();
        List<List<String>> arms = new ArrayList<>();
        List<List<String>> seat = new ArrayList<>();
        List<List<String>> cushion = new ArrayList<>();

        try {
            Statement myStmt = database.getDbConnect().createStatement();
            results = myStmt.executeQuery("SELECT * FROM  CHAIR  WHERE Type = '" + type + "'");
            int i = 0;
            while (results.next()) {
                legs.add(i, new ArrayList<>());
                arms.add(i, new ArrayList<>());
                seat.add(i, new ArrayList<>());
                cushion.add(i, new ArrayList<>());
                ID.add(i, new ArrayList<>());

                legs.get(i).add(0, results.getString("Legs"));
                legs.get(i).add(1, results.getString("ID"));

                arms.get(i).add(0, results.getString("Arms"));
                arms.get(i).add(1, results.getString("ID"));

                seat.get(i).add(0, results.getString("Seat"));
                seat.get(i).add(1, results.getString("ID"));

                cushion.get(i).add(0, results.getString("Cushion"));
                cushion.get(i).add(1, results.getString("ID"));
                ID.get(i).add(0, results.getString("ID"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        createHasArrays(legs,arms,seat,cushion);
        createCombinations();
//        System.out.println(hasLegs);
//        System.out.println(hasArms);
//        System.out.println(hasSeats);
//        System.out.println(hasCushions);
//        System.out.println(ID);
    }

    public void createHasArrays(List<List<String>> legs,List<List<String>> arms,List<List<String>> seat,List<List<String>> cushion ){
        for(int i=0;i<legs.size();i++){
            if(legs.get(i).get(0).equals("Y")){
                hasLegs.add(Collections.singletonList(legs.get(i).get(1)));
            }
        }
        for(int i=0;i<arms.size();i++){
            if(arms.get(i).get(0).equals("Y")){
                hasArms.add(Collections.singletonList(arms.get(i).get(1)));
            }
        }
        for(int i=0;i<seat.size();i++){
            if(seat.get(i).get(0).equals("Y")){
                hasSeats.add(Collections.singletonList(seat.get(i).get(1)));
            }
        }
        for(int i=0;i<cushion.size();i++) {
            if (cushion.get(i).get(0).equals("Y")) {
                hasCushions.add(Collections.singletonList(legs.get(i).get(1)));
            }
        }
    }

    public void createCombinations() {
   ArrayList<List<String>>result = new ArrayList<>();
        int i =0;
        for(List<String> s1 : hasLegs) {
            for(List<String> s2: hasArms) {
                for(List<String> s3 : hasSeats){
                    for(List<String> s4 : hasCushions){
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
        for(int b = 0; b<result.size();b++){
            unique.add(b, new ArrayList<>());
            for (int c = 0; c<result.get(b).size();c++){
                if(!unique.get(b).contains(result.get(b).get(c))){
                    unique.get(b).add(result.get(b).get(c));
                }
            }
        }
//        for (int k =0; k<result.size(); k++){
//            System.out.println(unique.get(k));
//        }

    }

}
