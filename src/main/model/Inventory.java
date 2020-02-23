package model;

import java.util.ArrayList;

//a class representing the user's inventory which is a list of inventory items, and keeps track of the total items
// in the inventory, the max items allowed per slot, and the max items in the inventory
public class Inventory {

    private ArrayList<InventoryItem> inventoryItems;
    private int totalItems;
    private boolean fullInventorySlot;
    private int maxItemsPerInventorySlot = 10;
    private int maxInventoryItemSlots = 10;

    //EFFECTS: Constructs the empty Inventory with 0 items
    public Inventory() {
        inventoryItems = new ArrayList<>(maxInventoryItemSlots);
        totalItems = 0;
    }

    //EFFECTS: Constructs an Inventory based on parameters
    public Inventory(ArrayList<String> itemNames, ArrayList<Double> itemPrices, ArrayList<ArrayList<Long>> itemStats,
                     ArrayList<String> itemDescrip) {
        int count = 0;
        inventoryItems = new ArrayList<>(maxInventoryItemSlots);
        for (int i = 0; i < itemNames.size(); i++) {
            count++;
            String itemName = itemNames.get(i);
            double itemPrice = itemPrices.get(i);
            ArrayList<Long> itemStat = itemStats.get(i);
            String itemDescription = itemDescrip.get(i);
            inventoryItems.add(new InventoryItem(new GameItem(itemName, itemPrice, itemStat, itemDescription), 1));
        }
        totalItems = count;
    }

    //REQUIRES: quantity > 0
    //MODIFIES: InventoryItem, this
    //EFFECTS: adds quantity to the quantity of an InventoryItem if it exists already, else adds a new InventoryItem
    //         to the inventoryItems list with specified quantity and game item
    public void addInventoryItem(GameItem item, int quantity) {
        boolean foundItem = false;
        fullInventorySlot = false;

        for (InventoryItem i : inventoryItems) {
            if (i.getGameItem() == item) {
                if (i.getQuantity() < maxItemsPerInventorySlot) {
                    i.addQuantity(quantity);
                    foundItem = true;
                } else {
                    fullInventorySlot = true;
                }
                break;
            }
        }
        if (!foundItem && inventoryItems.size() < maxInventoryItemSlots) {
            inventoryItems.add(new InventoryItem(item, quantity));
        }
        totalItems = totalItems + quantity;
    }

    //REQUIRES: quantity >= 0 ;
    //MODIFIES: InventoryItem, this
    //EFFECTS: removes quantity to the quantity of an existing InventoryItem (if quantity > 0) in the list of
    //          InventoryItems, if the  InventoryItem  has a quantity of 0, then removes the entire InventoryItem
    //          from the InventoryItem list
    public void removeInventoryItem(GameItem item, int quantity) {
        for (InventoryItem i : inventoryItems) {
            if (i.getGameItem() == item) {
                i.removeQuantity(quantity);

                if (i.getQuantity() == 0) {
                    inventoryItems.remove(i);
                }
                break;

            }
        }
        totalItems = totalItems - quantity;
    }

    //EFFECTS: returns the total inventory items within the list of inventory items
    public int getInventoryTotalItems() {
        return totalItems;
    }

    //EFFECTS: returns the array containing all the inventory items within the list of inventory items
    public ArrayList<InventoryItem> getAllInventoryItems() {
        return inventoryItems;
    }

    //EFFECTS: returns a list of all the names of the game items in the list of inventory items
    public ArrayList<String> getAllInventoryItemGameItemNames() {
        ArrayList<String> listOfString = new ArrayList<>();
        for (InventoryItem i : inventoryItems) {
            listOfString.add(i.getGameItem().getGameItemName());
        }
        return listOfString;
    }

    //EFFECTS: returns the total number of a specific GameItem
    public int getTotalNumberOfSpecificGameItem(GameItem item) {
        int totalQuantityItem = 0;
        for (InventoryItem i : inventoryItems) {
            if (i.getGameItem() == item) {
                totalQuantityItem = i.getQuantity();
            }
        }
        return totalQuantityItem;
    }

    //EFFECTS: returns the price of a specific GameItem
    public double getGameItemPrice(GameItem item) {
        return item.getMarketPrice();
    }

    //EFFECTS: returns the description of a specific GameItem
    public String getGameItemDescription(GameItem item) {
        return item.getDescription();
    }

    //EFFECTS: returns an arraylist of a specific GameItem's stats
    public ArrayList<Integer> getGameItemStats(GameItem item) {
        return item.getStats();
    }

    //REQUIRES: maxInventorySlots > 0
    //MODIFIES: this
    //EFFECTS: sets the max number of inventory item slots
    public void setMaxInventoryItemSlots(int maxInventoryItemSlots) {
        this.maxInventoryItemSlots = maxInventoryItemSlots;
    }

    //REQUIRES: maxItemsPerInventorySlot > 0
    //MODIFIES: this
    //EFFECTS: sets the max number of items per inventory slot
    public void setMaxItemsPerInventorySlot(int maxItemsPerInventorySlot) {
        this.maxItemsPerInventorySlot = maxItemsPerInventorySlot;
    }

    //EFFECTS: returns the name of a specific game item
    public String getGameItemName(GameItem item) {
        return item.getGameItemName();
    }

    //EFFECTS: returns the max number of items per inventory slot
    public int getMaxItemsPerInventorySlot() {
        return maxInventoryItemSlots;
    }

    //EFFECTS: returns the max number of inventory item slots
    public int getMaxInventoryItemSlots() {
        return maxItemsPerInventorySlot;
    }

}
