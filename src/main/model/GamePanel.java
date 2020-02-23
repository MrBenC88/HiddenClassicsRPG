package model;

import persistence.Reader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

// A class representing a GamePanel Object which contains all the mechanics of the character customization/selection,
// call to inventory,  calls to gameObjects, call to TextCollection, and Store
public class GamePanel {
    public UserCharacter character;
    public Inventory inventory;
    public GameObject gameObjects;
    public TextCollection textCollection;


    // EFFECTS: initializes the UserCharacter,Inventory, GameObjects, TextCollection, and Store classes
    public GamePanel()  {
        character = new UserCharacter();
        inventory = new Inventory();
        gameObjects = new GameObject();
        textCollection = new TextCollection();
    }

    //EFFECTS: another constructor which takes in parameters to reconstruct the game from loaded data
    public GamePanel(String characterName, double bal,HashMap<String, Integer> attr, String charChoice,
                     ArrayList<String> itemNames, ArrayList<Double> itemPrices, ArrayList<ArrayList<Long>> itemStats,
                     ArrayList<String> itemDescrip, ArrayList<String> uncGin,
                     ArrayList<Double> uncGip, ArrayList<ArrayList<Long>> uncGiS,
                     ArrayList<String> unclaimedGameItemDescrip, ArrayList<ArrayList<String>> npcLines,
                     ArrayList<String> npcTitle, ArrayList<String> npcDirection, ArrayList<String> npcType,
                     ArrayList<String> npcNames, ArrayList<HashMap<String, Integer>> npcStats,
                     ArrayList<String> unclaimedTextName,
                     ArrayList<String> unclaimedTextContent, ArrayList<String> unclaimedTextBookId,
                     ArrayList<String> textName, ArrayList<String> textContent, ArrayList<String> textBookId) {
        character = new UserCharacter(characterName, bal, attr, charChoice);
        inventory = new Inventory(itemNames, itemPrices, itemStats, itemDescrip);
        //uncGin is unclaimedGameItem name
        //uncGip is unclaimedGameItem price - simplified to keep line to <= 120 char long
        gameObjects = new GameObject(uncGin,uncGip, uncGiS,
                unclaimedGameItemDescrip, npcLines,npcTitle, npcDirection, npcType, npcNames, npcStats,
                unclaimedTextName,unclaimedTextContent, unclaimedTextBookId);
        textCollection = new TextCollection(textName, textContent, textBookId);

    }



    //EFFECTS: initiates the battle event with a specific NPC and determines order
    public String order(NPC n) {
        HashMap<String, Integer> npcStats;
        HashMap<String, Integer> charStats;
        npcStats =  n.getNpcStats();
        charStats = character.getCharacterAttributes();
        String order = "npcFirst";
        if (charStats.get("Speed") > npcStats.get("Speed")) {
            order = "playerFirst";
        }
        String result = battle(order, npcStats);
        return result;
    }

    //EFFECTS: initiates the actual battle event with who goes first
    public String battle(String n, HashMap<String, Integer> npcStats) {
        String winner = "";

        if (n.equals("npcFirst")) { //NPC first
            winner = battleEventNPC(winner, npcStats);

        } else if (n.equals("playerFirst")) { //player  first
            winner = battleEventPlayer(winner,npcStats);

        }
        return winner;
    }

    //EFFECTS: returns the winning player or npc based on stats/attributes
    public String battleEventNPC(String winner, HashMap<String, Integer> npcStats) {
        String battleWinner;
        int npcHP = npcStats.get("Health");
        int npcAtk = npcStats.get("Attack");
        int npcDef = npcStats.get("Defense");
        HashMap<String, Integer> charStats = character.getCharacterAttributes();
        int playerHp = charStats.get("Health");
        int playerAttack = charStats.get("Attack");
        int playerDefense = charStats.get("Defense");
        while (winner.equals("")) {
            playerHp -= (npcAtk * (10) / playerDefense);
            npcHP -= (playerAttack * (10) / npcDef);

            battleWinner = checkWinner(playerHp, npcHP);
            if (battleWinner.equals("npc") || battleWinner.equals("player")) {
                winner = battleWinner;
            }
        }
        return winner;
    }


    //EFFECTS: returns the winner based on health
    public String checkWinner(int playerHp, int npcHP) {
        String winner = "";

        if (playerHp <= 0) {
            winner = "npc";
        }
        if (npcHP <= 0) {
            winner = "player";
        }
        return winner;
    }

    //EFFECTS: returns the winner based on battle event
    public String battleEventPlayer(String winner, HashMap<String, Integer> npcStats) {
        String battleWinner;
        int npcHP = npcStats.get("Health");
        int npcAtk = npcStats.get("Attack");
        int npcDef = npcStats.get("Defense");
        HashMap<String, Integer> charStats = character.getCharacterAttributes();
        int playerHp = charStats.get("Health");
        int playerAttack = charStats.get("Attack");
        int playerDefense = charStats.get("Defense");
        while (winner.equals("")) {
            npcHP -= (playerAttack * (10) / npcDef);
            playerHp -= (npcAtk * (10) / playerDefense);
            battleWinner = checkWinner(playerHp, npcHP);
            if (battleWinner.equals("npc") || battleWinner.equals("player")) {
                winner = battleWinner;
            }
        }
        return winner;
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
            setAssassin();;
        } else {
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

    //EFFECTS: returns the total number of unclaimed items in the list of unclaimed items
    public int getNumberOfUnclaimedItems() {
        return gameObjects.getTotalUnClaimedGameItem();
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

    //EFFECTS: returns a string of the text item details
    public String showTextDetails() {
        String strOutput = "";
        String textName;
        String textDescription;
        String textID;

        for (TextItem i : textCollection.getAllTextItems()) {

            textName = textCollection.getTextName(i);
            textDescription = textCollection.getTextContent(i);
            textID = textCollection.getBookID(i);
            strOutput += "Text ID: " + textID + "|\t\t\t " + textName +  "\n\tContent:\t" + textDescription + "\n\n";
        }
        return strOutput;
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

    //MODIFIES: gameObjects
    //EFFECTS: adds the unclaimed texts into the unClaimed textItem list
    public void addUnclaimedTexts() {
        gameObjects.addUnclaimedTextItems();
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


    //MODIFIES: gameObjects
    //EFFECTS: adds the npcs to the gameobjects
    public void addNpcs() {
        gameObjects.addNpcs();
    }


}
