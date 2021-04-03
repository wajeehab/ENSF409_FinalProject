package edu.ucalgary.ensf409;

import java.util.Scanner;

public class OrderUserInterface {
    private String furnitureCategory;
    private String furnitureType;
    private int numberItems;
    private Chair chair = new Chair();
    private Lamp lamp;
    private Desk desk = new Desk();
    private Filing filing = new Filing();
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
                chair.selectChairInfo(getFurnitureType());
            if(!chair.getIsEmpty()) {
                text.WriteOrderFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), chair.getSmallest(), chair.getIdCombo());
                update.deleteFromDataBase(chair.getIdCombo(), getFurnitureCategory());
            }
            else if (chair.getIsEmpty()){
                text.WriteNotFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), orderN.findChairManu());
            }

        }

//        else if(getFurnitureCategory().equals("desk") | (getFurnitureCategory().equals("Desk"))){
//            selectDeskInfo();
//        }
//
//        else if(getFurnitureCategory().equals("Filing") | (getFurnitureCategory().equals("filing"))){
//            selectFilingInfo();
//        }
//
        else if(getFurnitureCategory().equals("lamp") | (getFurnitureCategory().equals("Lamp"))){
            lamp = new Lamp(getNumberItems());
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
