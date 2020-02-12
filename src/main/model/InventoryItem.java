package model;

//A class that represents an item in one spot of the user inventory.
// Has the specific game item, and the quantity of that item
public class InventoryItem {
    private GameItem gameItem;
    private int quantity;

    //EFFECTS: constructs an Inventory item for a given game item and quantity
    public InventoryItem(GameItem gameItem, int quantity) {
        this.gameItem = gameItem;
        this.quantity = quantity;
    }

    // EFFECTS: returns the quantity of this specific item type
    public int getQuantity() {
        return quantity;
    }

    //EFFECTS: returns the game item in this specific inventory spot
    public GameItem getGameItem() {
        return  gameItem;
    }

    // REQUIRES: quantity > 0
    // MODIFIES: this
    // EFFECTS: adds the additional quantity of item specified to quantity.
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    // REQUIRES: quantity < 0
    // MODIFIES: this
    // EFFECTS: removes the  quantity of item specified.
    public void removeQuantity(int quantity) {
        this.quantity -= quantity;
    }

}
