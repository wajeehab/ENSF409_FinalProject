//package edu.ucalgary.ensf409;

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
     * This constructor prompts the user for furniture category, type and number of items
     * which they want constructed and stores the input.
     */
    public OrderUserInterface(){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter furniture category:  ");
        this.furnitureCategory = reader.nextLine(); //Scans the next token of the input as a String

        System.out.println("Enter furniture type:  ");
        this.furnitureType = reader.nextLine();

        System.out.println("Enter number of items:  ");
        this.numberItems = reader.nextInt(); //Scans the next token of the input as an int

        reader.close();
    }

    /**
     * This method calls the corresponding classes and generates the combination and orderform
     * depending on the furniture category which the user enters
     */
    public void selectFurnitureCategory(){
        if (getFurnitureCategory().equals("chair") | (getFurnitureCategory().equals("Chair"))) { //if user input matches
            chair = new Chair(getNumberItems()); //instantiating the class
            chair.selectChairInfo(getFurnitureType()); //creating the combinations
            if(!chair.getIsEmpty()) { //if combinations were created successfully
                text.writeOrderFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), chair.getSmallest(), chair.getIdCombo()); //write to order fulfilled text file
                update.deleteFromDataBase(chair.getIdCombo(), getFurnitureCategory()); //update database
            }
            else if (chair.getIsEmpty()){ //if combinations not created
                text.writeNotFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), orderN.findChairManu()); //write to order not fulfilled and find the furniture manufacturers
            }

        }

        else if(getFurnitureCategory().equals("desk") | (getFurnitureCategory().equals("Desk"))){
             desk = new Desk(getNumberItems()); //instantiating the class
             desk.selectDeskInfo(getFurnitureType()); //creating the combinations
            if(!desk.getIsEmpty()) { //if combinations were created successfully
                text.writeOrderFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), desk.getSmallest(), desk.getIdCombo()); //write to order fulfilled text file
                update.deleteFromDataBase(desk.getIdCombo(), getFurnitureCategory());//update database
            }
            else if (desk.getIsEmpty()){ //if combinations not created
                text.writeNotFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), orderN.findDeskManu()); //write to order not fulfilled and find the furniture manufacturer
            }
        }
//
        else if(getFurnitureCategory().equals("Filing") | (getFurnitureCategory().equals("filing"))){
            filing = new Filing(getNumberItems()); //instantiating the class
            filing.selectFilingInfo(getFurnitureType()); //creating the combinations
            if(!filing.getIsEmpty()) { //if combinations were created successfully
                text.writeOrderFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), filing.getSmallest(), filing.getIdCombo()); //write to order fulfilled text file
                update.deleteFromDataBase(filing.getIdCombo(), getFurnitureCategory());
            }
            else if (filing.getIsEmpty()){//if combinations not created
                text.writeNotFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), orderN.findFilingManu()); //write to order not fulfilled and find the furniture manufacturer
            }        }
//
        else if(getFurnitureCategory().equals("lamp") | (getFurnitureCategory().equals("Lamp"))){
            lamp = new Lamp (getNumberItems()); //instantiating the class
            lamp.selectLampInfo(getFurnitureType()); //creating the combinations
            if(!lamp.getIsEmpty()) {  //if orders can be made, write to text file and
                text.writeOrderFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), lamp.getSmallest(), lamp.getIdCombo()); //write to order fulfilled text file
                update.deleteFromDataBase(lamp.getIdCombo(), getFurnitureCategory());
            }
            else if (lamp.getIsEmpty()){//if combinations not created
                text.writeNotFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), orderN.findLampManu()); //write to order not fulfilled and find the furniture manufacturer
            }

        }
        else {
            throw new IllegalArgumentException("Order Input Invalid"); //if the input is not valid then throw an illegal argument exception
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

}
