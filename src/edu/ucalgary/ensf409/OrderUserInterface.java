package edu.ucalgary.ensf409;

import java.util.Scanner;

public class OrderUserInterface {
    private String furnitureCategory;
    private String furnitureType;
    private int numberItems;
    private Chair chair = new Chair();
    private Lamp lamp = new Lamp();
    private TextFile text = new TextFile();
    private UpdateDatabase update = new UpdateDatabase();


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

    public void selectFurnitureCategory() throws Exception {
        if (getFurnitureCategory().equals("chair") | (getFurnitureCategory().equals("Chair"))) {
                chair.selectChairInfo(getFurnitureType());
                text.WriteOrderFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), chair.getSmallest(), chair.getIdCombo());
                update.deleteChairFromDataBase(chair.getIdCombo());
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
            lamp.selectLampInfo(getFurnitureType());
            text.WriteOrderFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), lamp.getSmallest(), lamp.getIdCombo());
            update.deleteLampFromDataBase(lamp.getIdCombo());
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
