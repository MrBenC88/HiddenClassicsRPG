package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

// A class representing game objects within the game which includes unclaimed game items , unclaimed text items and npcs
public class GameObject {
    private ArrayList<GameItem> unclaimedGameItems;
    private ArrayList<TextItem> unclaimedTextItems;
    private ArrayList<NPC> npcs;

    //EFFECTS: constructs a game object and initializes an empty array of unclaimedGameItems and unclaimedTextItems
    public GameObject() {
        unclaimedGameItems = new ArrayList<>();
        unclaimedTextItems = new ArrayList<>();
        npcs = new ArrayList<>();
    }

    //MODIFIES: npcs
    //EFFECTS: adds NPC into list of npcs
    public void addNpcs() {
        ArrayList<String> npcLines1 = new ArrayList<>();
        npcLines1.add("Hello, I am NPC 1 !");
        npcLines1.add("???");
        npcLines1.add("I don't understand.");
        HashMap<String, Integer> npcStat1 = new HashMap<>();
        npcStat1.put("Health", 1000);
        npcStat1.put("Attack", 10000);
        npcStat1.put("Defense", 2);
        npcStat1.put("Speed", 3);

        npcs.add(new NPC("NPC1", "enemy", "villager", "down", npcLines1,npcStat1));
    }

    //REQUIRES: num must be within range of the array
    //EFFECTS: returns the npc  in the specified position of the arrayList
    public NPC getNPC(int positionInArray) {
        NPC npc;
        npc = npcs.get(positionInArray);
        return  npc;
    }


    //EFFECTS: returns the total unclaimed text items
    public int getTotalUnclaimedTextItems() {
        return unclaimedTextItems.size();
    }

    //MODIFIES: unclaimedGameItems
    //EFFECTS: adds Text Items to the list of unclaimed TextItems
    public void addUnclaimedTextItems() {
        unclaimedTextItems.add(new TextItem("Book of Recursion",
                "Trust the natural recursion","1"));
        unclaimedTextItems.add(new TextItem("Book of Recipes", "Follow the Recipe. HTDF", "2"));
        unclaimedTextItems.add(new TextItem("Book of Advancement",
                "The truth starts with BSL which then leads to ISL, and finally ASL.", "3"));
        unclaimedTextItems.add(new TextItem("Book of Truth",
                "Functional programming is magical", "4"));
        unclaimedTextItems.add(new TextItem("Book of Trials",
                "A test is an example before it is actually a test.", "5"));
    }

    //REQUIRES: num must be within range of the array
    //EFFECTS: returns the unclaimed TextItem in the specified position of the arrayList
    public TextItem getUnClaimedTextItem(int positionInArray) {
        TextItem textItemFromWild;
        textItemFromWild = unclaimedTextItems.get(positionInArray);
        return  textItemFromWild;
    }

    //MODIFIES: unclaimedTextItems
    //EFFECTS: removes a specific TextItem from the list of unclaimed TextItems
    public void removeUnClaimedTextItem(TextItem textItem) {
        unclaimedTextItems.remove(textItem);
    }


    //EFFECTS: gets the total unclaimed game items
    public int getTotalUnClaimedGameItem() {
        return unclaimedGameItems.size();
    }

    //MODIFIES: unclaimedGameItems
    //EFFECTS: adds Game Items to the list of unclaimed GameItems
    public void addUnclaimedItems() {
        unclaimedGameItems.add(new GameItem("Golden Apple",
                "A golden apple which works as a decoration", 10.00, 3,1,0,1));
        unclaimedGameItems.add(new GameItem("Golden Pear",
                "A golden pear which works as a decoration", 11.00, 2,2,2,0));
        unclaimedGameItems.add(new GameItem("Golden Strawberry",
                "A golden strawberry which works as a decoration",
                9.00, 1,1,2,2));
        unclaimedGameItems.add(new GameItem("Golden ?",
                "A golden kiwi which works as a decoration",
                1.00, 2,2,3,12));
        unclaimedGameItems.add(new GameItem("Golden ??",
                "A golden kiwi which works as a decoration",
                1.00, 2,2,3,12));
        unclaimedGameItems.add(new GameItem("Golden ???",
                "A golden kiwi which works as a decoration",
                1.00, 2,2,3,12));
        unclaimedGameItems.add(new GameItem("Golden ????",
                "A golden kiwi which works as a decoration",
                1.00, 2,2,3,12));
        unclaimedGameItems.add(new GameItem("Golden ???????",
                "A golden kiwi which works as a decoration",
                1.00, 2,2,3,12));
    }

    //MODIFIES: unclaimedGameItems
    //EFFECTS: shuffles the order of the game items
    public void shuffleUnclaimedGameItems() {
        Collections.shuffle(unclaimedGameItems);
    }

    //REQUIRES: num must be within range of the array
    //EFFECTS: returns the unclaimedGameItem in the specified position of the arrayList
    public GameItem getUnClaimedGameItem(int positionInArray) {
        GameItem itemFromWild;
        itemFromWild = unclaimedGameItems.get(positionInArray);
        return  itemFromWild;
    }

    //MODIFIES: unclaimedGameItems
    //EFFECTS: removes a specific GameItem from the list of unclaimedGameItems
    public void removeUnClaimedGameItem(GameItem gameItem) {
        unclaimedGameItems.remove(gameItem);
    }
}
