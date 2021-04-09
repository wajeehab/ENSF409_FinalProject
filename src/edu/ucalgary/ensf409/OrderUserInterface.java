package edu.ucalgary.ensf409;

import java.util.Scanner;

/**
 * This class is the User interface and calls the respective classes which are needed to create the order
 * based on the user's input
 */
public class OrderUserInterface {
    private String furnitureCategory;
    private String furnitureType;
    private int numberItems;
    private Chair chair;
    private Lamp lamp;
    private Desk desk;
    private Filing filing;
    private TextFile text = new TextFile();
    private UpdateDatabase update = new UpdateDatabase();
    private OrderNotFulfilled orderN = new OrderNotFulfilled();

    /**
     * empty constructor
     */

    public OrderUserInterface(){ }


    /**
     * This function prompts the user for furniture category, type and number of items
     * which they want constructed and stores the input.
     */
    public void startProgram(){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter furniture category:  ");
        setFurnitureCategory(reader.nextLine()); //Scans the next token of the input as a String

        System.out.println("Enter furniture type:  ");
        setFurnitureType(reader.nextLine().toLowerCase());

        System.out.println("Enter number of items:  ");
        setNumberItems(reader.nextInt()); //Scans the next token of the input as an int

        reader.close();
    }

    /**
     * This method calls the corresponding classes and generates the combination and orderform
     * depending on the furniture category which the user enters
     */
    public void selectFurnitureCategory(){
        if (checkChairInput(getFurnitureCategory(), getFurnitureType())) { //if user input matches
            chair = new Chair(getNumberItems()); //instantiating the class
            chair.selectChairInfo(getFurnitureType()); //creating the combinations
            if(!chair.getIsEmpty()) { //if combinations were created successfully
                text.writeOrderFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), chair.getSmallest(), chair.getIdCombo()); //write to order fulfilled text file
                update.deleteFromDataBase(chair.getIdCombo(), getFurnitureCategory()); //update database
            }
            else if (chair.getIsEmpty()){ //if combinations not created
                text.writeNotFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), orderN.findChairManu()); //write to order not fulfilled and find the furniture manufacturers
            }
            chair.close();
        }

        else if (checkDeskInput(getFurnitureCategory(), getFurnitureType())){
             desk = new Desk(getNumberItems()); //instantiating the class
             desk.selectDeskInfo(getFurnitureType()); //creating the combinations
            if(!desk.getIsEmpty()) { //if combinations were created successfully
                text.writeOrderFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), desk.getSmallest(), desk.getIdCombo()); //write to order fulfilled text file
                update.deleteFromDataBase(desk.getIdCombo(), getFurnitureCategory());//update database
            }
            else if (desk.getIsEmpty()){ //if combinations not created
                text.writeNotFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), orderN.findDeskManu()); //write to order not fulfilled and find the furniture manufacturer
            }

            desk.close();
            update.close();
        }
//
        else if(checkFilingInput(getFurnitureCategory(), getFurnitureType())){
            filing = new Filing(getNumberItems()); //instantiating the class
            filing.selectFilingInfo(getFurnitureType()); //creating the combinations
            if(!filing.getIsEmpty()) { //if combinations were created successfully
                text.writeOrderFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), filing.getSmallest(), filing.getIdCombo()); //write to order fulfilled text file
                update.deleteFromDataBase(filing.getIdCombo(), getFurnitureCategory());
            }
            else if (filing.getIsEmpty()){//if combinations not created
                text.writeNotFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), orderN.findFilingManu()); //write to order not fulfilled and find the furniture manufacturer
            }
            filing.close();
            update.close();
        }
//
        else if(checkLampInput(getFurnitureCategory(), getFurnitureType())){
            lamp = new Lamp (getNumberItems()); //instantiating the class
            lamp.selectLampInfo(getFurnitureType()); //creating the combinations
            if(!lamp.getIsEmpty()) {  //if orders can be made, write to text file and
                text.writeOrderFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), lamp.getSmallest(), lamp.getIdCombo()); //write to order fulfilled text file
                update.deleteFromDataBase(lamp.getIdCombo(), getFurnitureCategory());
            }
            else if (lamp.getIsEmpty()){//if combinations not created
                text.writeNotFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), orderN.findLampManu()); //write to order not fulfilled and find the furniture manufacturer
            }
            lamp.close();
            update.close();
        }

        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * this method checks to ensure the user input was valid
     * @param categoryInput - user input for furniture category
     * @param type - user input for furniture type
     * @return true or throws illegal exaception
     */
    public boolean checkChairInput(String categoryInput, String type){
        if ((categoryInput.equals("chair") | (categoryInput.equals("Chair"))) & (type.equals("mesh") | type.equals("task")|type.equals("kneeling")|type.equals("ergonomic")|type.equals("executive"))){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * this method checks to ensure the user input was valid
     * @param categoryInput - user input for furniture category
     * @param type - user input for furniture type
     * @return true or throws illegal exaception
     */
    public boolean checkDeskInput(String categoryInput, String type){
        if ((categoryInput.equals("desk") | (categoryInput.equals("Desk"))) & (type.equals("traditional") | type.equals("standing")|type.equals("adjustable"))){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * this method checks to ensure the user input was valid
     * @param categoryInput - user input for furniture category
     * @param type - user input for furniture type
     * @return true or throws illegal exaception
     */
    public boolean checkFilingInput(String categoryInput, String type){
        if ((categoryInput.equals("Filing") | (categoryInput.equals("filing"))) & (type.equals("small") | type.equals("medium")|type.equals("large"))){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * this method checks to ensure the user input was valid
     * @param categoryInput - user input for furniture category
     * @param type - user input for furniture type
     * @return true or throws illegal exaception
     */
    public boolean checkLampInput(String categoryInput, String type){
        if ((categoryInput.equals("lamp") | (categoryInput.equals("Lamp"))) & (type.equals("desk") | type.equals("swing arm")|type.equals("study"))){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * getter method for furniture category
     * @return - returns the furniture category which has been requested
     */
    public String getFurnitureCategory() {
        return furnitureCategory;
    }

    /**
     * getter method for the furniture type
     * @return - returns the furniture type which has been requested
     */
    public String getFurnitureType(){
        return furnitureType;
    }

    /**
     * getter method for number of items
     * @return - returns the number of items which have been requested
     */
    public int getNumberItems() {
        return numberItems;
    }

    /**
     * sets the number of items which need to be ordered and error checks to ensure the input
     * is valid
     * @param numberItems - user input for number of items
     */
    public void setNumberItems(int numberItems) {
        if(numberItems<=0){
            throw new IllegalArgumentException("Cannot have 0 or less order");
        }
        else {
            this.numberItems = numberItems;
        }
    }

    /**
     *  sets the furniture category which need to be ordered
     *  is valid
     * @param furnitureCategory - furniture category from user input
     */
    public void setFurnitureCategory(String furnitureCategory) {
        this.furnitureCategory = furnitureCategory;
    }

    /**
     * sets the furniture type which need to be ordered
     * is valid
     * @param furnitureType - furniture type from user input
     */
    public void setFurnitureType(String furnitureType) {
        this.furnitureType = furnitureType;
    }

}
