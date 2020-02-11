package model;

import java.util.ArrayList;
import java.util.HashMap;

// Class for the RPG Game Application, Runs the Game Menus , Set Up for Character Customization and Selection
public class GamePanel {
    public UserCharacter character;
    public Inventory inventory;
    public GameObject gameObjects;
    //private TextCollection textCollection;
    //private Store store;


    // EFFECTS: runs the game application
    public GamePanel() {
        character = new UserCharacter();
        inventory = new Inventory();
        gameObjects = new GameObject();
        //textCollection = new TextCollection();
        //store = new Store();
        System.out.println("Initialized");
    }


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

    public HashMap<String, Integer> setKnight() {
        character.setHealth(200);
        character.setAttack(12);
        character.setDef(30);
        character.setSpeed(4);
        character.addBalance(50);
        return character.getCharacterAttributes();
    }

    public HashMap<String, Integer> setMage() {
        character.setHealth(100);
        character.setAttack(20);
        character.setDef(12);
        character.setSpeed(6);
        character.addBalance(60);
        return character.getCharacterAttributes();
    }

    public HashMap<String, Integer> setAssassin() {
        character.setHealth(60);
        character.setAttack(50);
        character.setDef(9);
        character.setSpeed(8);
        character.addBalance(45);
        return character.getCharacterAttributes();
    }

    public HashMap<String, Integer> setWanderer() {
        character.setHealth(99);
        character.setAttack(99);
        character.setDef(99);
        character.setSpeed(99);
        character.addBalance(999);
        return character.getCharacterAttributes();
    }

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

    public void createCharacter(String name) {
        character.setName(name);
    }

    public String getCharacterName() {
        return character.getName();
    }

    public void setCharacterClass(int charChoice) {
        character.setCharacterClass(charChoice);
    }

    public UserCharacter getCharacter() {
        return character;
    }

    public String getCharacterChoice() {
        return character.getCharacterClass();
    }

    public String getCharacterStats() {
        String output = "";
        output += character.getCharacterAttributes();
        return output;
    }

    public ArrayList<InventoryItem> showInventoryItems() {
        return inventory.getAllInventoryItems();
    }


    public void removeGameObject(GameItem gameItem) {
        gameObjects.removeUnClaimedGameItem(gameItem);
    }

    public void addGameObjectToInventory(GameItem gameItem, int quantity) {
        inventory.addInventoryItem(gameItem, quantity);
    }

    public void removeGameObjectInInventory(GameItem gameItem, int quantity) {
        inventory.removeInventoryItem(gameItem, quantity);

    }

    public ArrayList<InventoryItem> showInventory() {
        return inventory.getAllInventoryItems();
    }

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

    public String getUnclaimedItemName(int position) {
        return gameObjects.getUnClaimedGameItem(position).getGameItemName();
    }

    public String getUnclaimedItemDescription(int position) {
        return gameObjects.getUnClaimedGameItem(position).getDescription();
    }

    public void addUnclaimedItems() {
        gameObjects.addUnclaimedItems();
    }


    public InventoryItem getInventoryItemObject(int position) {
        return  inventory.getAllInventoryItems().get(position);
    }

    public ArrayList<Integer> getGameItemStats(GameItem item) {
        return inventory.getGameItemStats(item);
    }

    public String getGameItemNameAndDescription(GameItem item) {
        String output = "";
        output += "Name:\t" + inventory.getGameItemName(item) + "\nDescription:\t"
                + inventory.getGameItemDescription(item) + "\nWorth:\t" + inventory.getGameItemPrice(item);
        return output;
    }

    public String getGameItemName(GameItem item) {
        return inventory.getGameItemName(item);
    }



}
