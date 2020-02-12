package model;

import java.util.ArrayList;
import java.util.HashMap;

// A class representing a GamePanel Object which contains all the mechanics of the character customization/selection,
// call to inventory,  calls to gameObjects, call to TextCollection, and Store
public class GamePanel {
    public UserCharacter character;
    public Inventory inventory;
    public GameObject gameObjects;
    //private TextCollection textCollection;
    //private Store store;


    // EFFECTS: initializes the UserCharacter,Inventory, GameObjects, TextCollection, and Store classes
    public GamePanel() {
        character = new UserCharacter();
        inventory = new Inventory();
        gameObjects = new GameObject();
        //textCollection = new TextCollection();
        //store = new Store();
        System.out.println("Initialized");
    }

    //REQUIRES: characterChoice must be either "Knight", "Mage", "Assassin", or "Wanderer"
    //MODIFIES: character
    //EFFECTS: based on the characterChoice, adjusts the character attributes accordingly
    public HashMap<String, Integer> adjustAttributes(String characterChoice) {
        if (characterChoice.equals("Knight")) {
            setKnight();
        } else if (characterChoice.equals("Mage")) {
            setMage();
        } else if (characterChoice.equals("Assassin")) {
            setAssassin();
        } else if (characterChoice.equals("Wanderer")) {
            setWanderer();
        }
        return character.getCharacterAttributes();
    }

    //MODIFIES: character
    //EFFECTS: sets the character attributes and returns the character attributes
    public HashMap<String, Integer> setKnight() {
        character.setHealth(200);
        character.setAttack(12);
        character.setDef(30);
        character.setSpeed(4);
        character.addBalance(50);
        return character.getCharacterAttributes();
    }

    //MODIFIES: character
    //EFFECTS: sets the character attributes and returns the character attributes
    public HashMap<String, Integer> setMage() {
        character.setHealth(100);
        character.setAttack(20);
        character.setDef(12);
        character.setSpeed(6);
        character.addBalance(50);
        return character.getCharacterAttributes();
    }

    //MODIFIES: character
    //EFFECTS: sets the character attributes and returns the character attributes
    public HashMap<String, Integer> setAssassin() {
        character.setHealth(60);
        character.setAttack(50);
        character.setDef(9);
        character.setSpeed(8);
        character.addBalance(50);
        return character.getCharacterAttributes();
    }

    //MODIFIES: character
    //EFFECTS: sets the character attributes and returns the character attributes
    public HashMap<String, Integer> setWanderer() {
        character.setHealth(99);
        character.setAttack(99);
        character.setDef(99);
        character.setSpeed(99);
        character.addBalance(50);
        return character.getCharacterAttributes();
    }

    //MODIFIES: character, inventory
    //EFFECTS: sets the character attributes removes the gameItem from the user inventory
    public void useGameItem(GameItem item) {
        int hp;
        int atk;
        int def;
        int spd;
        hp = item.getStats().get(0);
        atk = item.getStats().get(1);
        def = item.getStats().get(2);
        spd = item.getStats().get(3);

        character.addHealth(hp);
        character.addAttack(atk);
        character.addDef(def);
        character.addSpd(spd);
        removeGameObjectInInventory(item, 1);
    }

    //MODIFIES: character
    //EFFECTS:  sets the character's name
    public void createCharacter(String name) {
        character.setName(name);
    }

    //EFFECTS: gets the character's name
    public String getCharacterName() {
        return character.getName();
    }

    //REQUIRES: charChoice within [1:4]
    //MODIFIES: character
    //EFFECTS: sets the character's class
    public void setCharacterClass(int charChoice) {
        character.setCharacterClass(charChoice);
    }

    //EFFECTS: returns the user's character
    public UserCharacter getCharacter() {
        return character;
    }

    //EFFECTS: returns the character's class
    public String getCharacterChoice() {
        return character.getCharacterClass();
    }

    //EFFECTS: returns the character stats as a String
    public String getCharacterStats() {
        String output = "";
        output += character.getCharacterAttributes();
        return output;
    }

    //EFFECTS: returns all the InventoryItems within user inventory
    public ArrayList<InventoryItem> showInventoryItems() {
        return inventory.getAllInventoryItems();
    }

    //MODIFIES: gameObjects
    //EFFECTS: removes a specified GameItem from the list of unclaimedGameItems
    public void removeGameObject(GameItem gameItem) {
        gameObjects.removeUnClaimedGameItem(gameItem);
    }

    //REQUIRES: quantity > 0
    //MODIFIES: inventory
    //EFFECTS: adds a InventoryItem to user inventory
    public void addGameObjectToInventory(GameItem gameItem, int quantity) {
        inventory.addInventoryItem(gameItem, quantity);
    }

    //REQUIRES: quantity > 0
    //MODIFIES: inventory
    //EFFECTS: removes a InventoryItem from user inventory
    public void removeGameObjectInInventory(GameItem gameItem, int quantity) {
        inventory.removeInventoryItem(gameItem, quantity);

    }

    //EFFECTS: returns a string of the inventory item details
    public String showInventoryItemDetails() {
        String strOutput = "";
        String itemName;
        int itemQuantity;
        double itemPrice;
        int count = 0;
        String itemDescription;
        for (InventoryItem i : inventory.getAllInventoryItems()) {

            itemName = inventory.getGameItemName(i.getGameItem());
            itemQuantity = inventory.getTotalNumberOfSpecificGameItem(i.getGameItem());
            itemPrice = inventory.getGameItemPrice(i.getGameItem());
            itemDescription = inventory.getGameItemDescription(i.getGameItem());
            strOutput += "Item Number: " + count + "|\t\t\t " + itemName + "\t\tQuantity: " + itemQuantity
                    + "\t\tDescription:\t" + itemDescription + "\t @ $" + itemPrice + "\n";
            count++;

        }
        return strOutput;
    }

    //EFFECTS: returns an unClaimedItem's name based on the index position
    public String getUnclaimedItemName(int position) {
        return gameObjects.getUnClaimedGameItem(position).getGameItemName();
    }

    //EFFECTS: returns an unClaimedItem's description based on the index position
    public String getUnclaimedItemDescription(int position) {
        return gameObjects.getUnClaimedGameItem(position).getDescription();
    }

    //MODIFIES: gameObjects
    //EFFECTS: adds the unClaimedItems into the unClaimedGameItem list
    public void addUnclaimedItems() {
        gameObjects.addUnclaimedItems();
    }

    //EFFECTS: returns an InventoryItem Object
    public InventoryItem getInventoryItemObject(int position) {
        return  inventory.getAllInventoryItems().get(position);
    }

    //EFFECTS: returns a GameItem's Stats
    public ArrayList<Integer> getGameItemStats(GameItem item) {
        return inventory.getGameItemStats(item);
    }

    //EFFECTS: returns a game item's name and description as a single string
    public String getGameItemNameAndDescription(GameItem item) {
        String output = "";
        output += "Name:\t" + inventory.getGameItemName(item) + "\nDescription:\t"
                + inventory.getGameItemDescription(item) + "\nWorth:\t" + inventory.getGameItemPrice(item);
        return output;
    }

    //EFFECTS: returns a game item's name
    public String getGameItemName(GameItem item) {
        return inventory.getGameItemName(item);
    }



}
