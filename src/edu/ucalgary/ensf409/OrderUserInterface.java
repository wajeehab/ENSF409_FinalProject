package edu.ucalgary.ensf409;

import java.util.Scanner;

public class OrderUserInterface {
    public String furnitureCategory;
    public String furnitureType;
    public int numberItems;
    public Chair chair = new Chair();
    public TextFile text = new TextFile();

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
            for (int i = getNumberItems(); i> 0 ;i--){
                chair.selectChairInfo(getFurnitureType());
//                    text.WriteOrderFulfilled(getFurnitureType(), getFurnitureCategory(), getNumberItems(), chair.getSmallest());
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
//        else if(getFurnitureCategory().equals("lamp") | (getFurnitureCategory().equals("Lamp"))){
//            selectLampInfo();
//        }
        else {
            throw new IllegalArgumentException("Illegal input");
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
