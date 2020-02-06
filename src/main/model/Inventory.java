package model;

import java.util.ArrayList;

public class Inventory {
    //In the ArrayList,
    private ArrayList<InventoryItem> inventoryItems;
    private int totalItems;
    private boolean fullInventorySlot;
    private int maxItemsPerInventorySlot = 10;
    private int maxItemsInInventory = 10;

    //EFFECTS: Constructs the empty Inventory with 0 items
    public Inventory() {
        inventoryItems = new ArrayList<>(maxItemsInInventory);
        totalItems = 0;
    }

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
        if (!foundItem && inventoryItems.size() < maxItemsInInventory) {
            inventoryItems.add(new InventoryItem(item, quantity));
        } else if (fullInventorySlot && inventoryItems.size() < maxItemsInInventory) {
            inventoryItems.add(new InventoryItem(item, quantity));
        }
        totalItems = totalItems + quantity;
    }

    public void removeInventoryItem(GameItem item, int quantity) {
        boolean foundItem = true;
        for (InventoryItem i : inventoryItems) {
            if (i.getGameItem() == item) {
                if (i.getQuantity() > 0) {
                    i.removeQuantity(quantity);
                    foundItem = false;
                    break;
                }
            }
        }
        totalItems = totalItems + quantity;
    }

    public int getInventoryTotalItems() {
        return totalItems;
    }

    public ArrayList<InventoryItem> showInventoryItems() {
        return inventoryItems;
    }

    public int getTotalNumberOfSpecificGameItem(GameItem item) {
        int totalQuantityItem = 0;
        for (InventoryItem i : inventoryItems) {
            if (i.getGameItem() == item) {
                totalQuantityItem = i.getQuantity();
            }
        }
        return totalQuantityItem;
    }

    public double getGameItemPrice(GameItem item) {
        return item.getMarketPrice();
    }

    public String getGameItemDescription(GameItem item) {
        return item.getDescription();
    }

    public ArrayList<Integer> getGameItemStats(GameItem item) {
        return item.getStats();
    }

    public void setMaxItemsInInventory(int maxItemsInInventory) {
        this.maxItemsInInventory = maxItemsInInventory;
    }

    public void setMaxItemsPerInventorySlot(int maxItemsPerInventorySlot) {
        this.maxItemsPerInventorySlot = maxItemsPerInventorySlot;
    }


}
