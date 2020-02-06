package model;

import java.util.ArrayList;

// Represents the user's character having a name, balance (in dollars), attributes,
// inventory, and free allocation points.
public class UserCharacter {
    private String name;
    private CharacterAttributes characterAttributes;

    private double balance;
    private ArrayList<InventoryItem> inventoryItems;


    //EFFECTS: constructs the user's character with no name, 0 balance, 0 stats, and 0 inventory items
    public UserCharacter() {
        name = "";
        balance = 0;
        characterAttributes = new CharacterAttributes(100,0,0,0);
        inventoryItems = new ArrayList<>();
    }

    
}
