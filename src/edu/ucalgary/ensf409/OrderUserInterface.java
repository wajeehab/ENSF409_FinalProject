package edu.ucalgary.ensf409;

import java.util.Scanner;

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


    public OrderUserInterface(){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter furniture category:  ");
        this.furnitureCategory = reader.nextLine(); //

        System.out.println("Enter furniture type:  ");
        this.furnitureType = reader.nextLine();

        System.out.println("Enter number of items:  ");
        this.numberItems = reader.nextInt();

        reader.close();
    }

    public void selectFurnitureCategory(){
        if (getFurnitureCategory().equals("chair") | (getFurnitureCategory().equals("Chair"))) {
            chair = new Chair(getNumberItems());
            chair.selectChairInfo(getFurnitureType());
            if(!chair.getIsEmpty()) {
                text.WriteOrderFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), chair.getSmallest(), chair.getIdCombo());
                update.deleteFromDataBase(chair.getIdCombo(), getFurnitureCategory());
            }
            else if (chair.getIsEmpty()){
                text.WriteNotFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), orderN.findChairManu());
            }

        }

        else if(getFurnitureCategory().equals("desk") | (getFurnitureCategory().equals("Desk"))){
             desk = new Desk(getNumberItems());
             desk.selectDeskInfo(getFurnitureType());
            if(!desk.getIsEmpty()) {
                text.WriteOrderFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), desk.getSmallest(), desk.getIdCombo());
                update.deleteFromDataBase(desk.getIdCombo(), getFurnitureCategory());
            }
            else if (desk.getIsEmpty()){
                text.WriteNotFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), orderN.findDeskManu());
            }
        }
//
        else if(getFurnitureCategory().equals("Filing") | (getFurnitureCategory().equals("filing"))){
            filing = new Filing(getNumberItems());
            filing.selectFilingInfo(getFurnitureType());
            if(!filing.getIsEmpty()) {
                text.WriteOrderFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), filing.getSmallest(), filing.getIdCombo());
                update.deleteFromDataBase(filing.getIdCombo(), getFurnitureCategory());
            }
            else if (filing.getIsEmpty()){
                text.WriteNotFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), orderN.findFilingManu());
            }        }
//
        else if(getFurnitureCategory().equals("lamp") | (getFurnitureCategory().equals("Lamp"))){
            lamp = new Lamp (getNumberItems());
            lamp.selectLampInfo(getFurnitureType());
            if(!lamp.getIsEmpty()) {
                text.WriteOrderFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), lamp.getSmallest(), lamp.getIdCombo());
                update.deleteFromDataBase(lamp.getIdCombo(), getFurnitureCategory());
            }
            else if (lamp.getIsEmpty()){
                text.WriteNotFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), orderN.findLampManu());
            }

        }
        else {
            throw new IllegalArgumentException("Order Input Invalid");
        }
    }

    public String getFurnitureCategory() {
        return furnitureCategory;
    }
    public String getFurnitureType(){
        return furnitureType;
    }
    public int getNumberItems() {
        return numberItems;
    }

}
