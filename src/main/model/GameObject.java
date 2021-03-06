package model;

import javax.xml.soap.Text;
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

    //EFFECTS: constructs a game object and initializes the gameobjects of unclaimedGameItems, unclaimedTextItems,
    // and npcs and populates the ArrayList of each
    public GameObject(ArrayList<String> unclaimedGameItemName,
                      ArrayList<Double> unclaimedGameItemPrice, ArrayList<ArrayList<Long>> unclaimedGameItemStat,
                      ArrayList<String> unclaimedGameItemDescrip, ArrayList<ArrayList<String>> npcLines,
                      ArrayList<String> npcTitle, ArrayList<String> npcDirection, ArrayList<String> npcType,
                      ArrayList<String> npcNames, ArrayList<HashMap<String, Long>> npcStats,
                      ArrayList<String> unclaimedTextName,
                      ArrayList<String> unclaimedTextContent, ArrayList<String> unclaimedTextBookId) {
        unclaimedGameItems = new ArrayList<>();
        unclaimedTextItems = new ArrayList<>();
        npcs = new ArrayList<>();
        rebuildUnclaimedGameItems(unclaimedGameItemName,unclaimedGameItemPrice, unclaimedGameItemStat,
                unclaimedGameItemDescrip, unclaimedGameItems);
        rebuildUnclaimedTextItems(unclaimedTextName,unclaimedTextContent,unclaimedTextBookId, unclaimedTextItems);
        rebuildNpcs(npcLines, npcTitle, npcDirection,  npcType, npcNames, npcStats, npcs);
    }

    //EFFECTS: Adds new npc objects to the list of npcs based on data from parameters
    public void rebuildNpcs(ArrayList<ArrayList<String>> npcLines,
                            ArrayList<String> npcTitle, ArrayList<String> npcDirection, ArrayList<String> npcType,
                            ArrayList<String> npcNames, ArrayList<HashMap<String, Long>> npcStats,
                            ArrayList<NPC> npcs) {
        for (int i = 0; i < npcNames.size(); i++) {
            String npcName = npcNames.get(i);
            String npcTitl = npcTitle.get(i);
            String npcTy = npcType.get(i);
            ArrayList<String> npcLin = npcLines.get(i);
            String dir = npcDirection.get(i);
            HashMap<String, Long> npcStat = npcStats.get(i);
            npcs.add(new NPC(npcName, npcTy, npcTitl, dir, npcStat, npcLin));
        }
    }

    //EFFECTS: adds new text items to array list based on data from parameters
    public void rebuildUnclaimedTextItems(ArrayList<String> unclaimedTextName,
                                          ArrayList<String> unclaimedTextContent, ArrayList<String> unclaimedTextBookId,
                                          ArrayList<TextItem> unclaimedTextItems) {
        for (int i = 0; i < unclaimedTextName.size(); i++) {
            String unclTextName = unclaimedTextName.get(i);
            String unclTextContent = unclaimedTextContent.get(i);
            String unclTextBookId = unclaimedTextBookId.get(i);
            unclaimedTextItems.add(new TextItem(unclTextName,unclTextContent,unclTextBookId));
        }
    }

    //EFFECTS: adds new game items to list of unclaimed game items based on data from parameters
    public void rebuildUnclaimedGameItems(ArrayList<String> unclaimedGameItemName,
                                          ArrayList<Double> unclaimedGameItemPrice,
                                          ArrayList<ArrayList<Long>> unclaimedGameItemStat,
                                          ArrayList<String> unclaimedGameItemDescrip,
                                          ArrayList<GameItem> unclaimedGameItems) {
        for (int i = 0; i < unclaimedGameItemName.size(); i++) {
            String uncGameItemName = unclaimedGameItemName.get(i);
            double uncGameItemPrice = unclaimedGameItemPrice.get(i);
            ArrayList<Long> uncGameItemStat = unclaimedGameItemStat.get(i);
            String uncGameItemDesc = unclaimedGameItemDescrip.get(i);
            unclaimedGameItems.add(new GameItem(uncGameItemName,uncGameItemPrice, uncGameItemStat,uncGameItemDesc));
        }
    }

    //EFFECTS: returns unclaimed text item name
    public String getUnclaimedTextItemName(TextItem i) {
        return i.getTextItemName();
    }

    //EFFECTS: returns unclaimed text item content
    public String getUnclaimedTextItemContent(TextItem i) {
        return i.getTextItemContent();
    }

    //EFFECTS: returns unclaimed text item book id
    public String getUnclaimedTextItemBookId(TextItem i) {
        return i.getBookID();
    }

    //EFFECTS: returns npc title
    public String getNpcTitle(NPC n) {
        return n.getTitle();
    }

    //EFFECTS: returns npc direction
    public String getNpcDirection(NPC n) {
        return n.getDirection();
    }

    //EFFECTS: returns npc list of lines
    public ArrayList<String> getListOfNpcLines(NPC n) {
        return n.getListOfNpcLines();
    }

    //EFFECTS: returns npc type
    public String getNpcType(NPC n) {
        return n.getNpcType();
    }

    //EFFECTS: returns npc name
    public String getNpcName(NPC n) {
        return n.getName();
    }

    //EFFECTS: returns npc name
    public HashMap<String, Integer> getNpcStat(NPC n) {
        return n.getNpcStats();
    }


    //MODIFIES: npcs
    //EFFECTS: adds NPC into list of npcs
    public void addNpcs() {
        ArrayList<String> npcLines1 = new ArrayList<>();
        npcLines1.add("Hello, I am NPC 1 !");
        npcLines1.add("???");
        npcLines1.add("I don't understand.");
        HashMap<String, Integer> npcStat1 = new HashMap<>();
        npcStat1.put("Health", 10);
        npcStat1.put("Attack", 5);
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

    //EFFECTS: returns all npcs
    public ArrayList<NPC> getAllNPCs() {
        return npcs;
    }

    /*
    //EFFECTS: returns an array list of the the npc's data
    public ArrayList<String> getAllNpcData(NPC n) {
        ArrayList<String> listOfString = new ArrayList<>();
        listOfString.add(n.getName());
        listOfString.add(n.getTitle());
        listOfString.add(n.getNpcType());
        listOfString.add(n.getDirection());
        listOfString.add(n.getNpcStats().toString());
        listOfString.add(n.getListOfNpcLines().toString());
        return listOfString;
    }*/

    //EFFECTS: returns all unclaimed text items
    public ArrayList<TextItem> getAllUnclaimedTextItems() {
        return unclaimedTextItems;
    }

    //EFFECTS: returns all unclaimed text items
    public ArrayList<GameItem> getAllUnclaimedGameItems() {
        return unclaimedGameItems;
    }

    /*
    //EFFECTS: returns an array list of the unclaimed text item's data
    public ArrayList<String> getAllUnclaimedTextItemsData(TextItem item) {
        ArrayList<String> listOfString = new ArrayList<>();
        listOfString.add(item.getTextItemName());
        listOfString.add(item.getTextItemContent());
        listOfString.add(item.getBookID());
        return listOfString;
    }

    //EFFECTS: returns an array list of the unclaimed text item's data
    public ArrayList<String> getAllUnclaimedGameItemsData(GameItem item) {
        ArrayList<String> listOfString = new ArrayList<>();
        listOfString.add(item.getGameItemName());
        listOfString.add(item.getDescription());
        listOfString.add(item.getStats().toString());
        listOfString.add(String.valueOf(item.getMarketPrice()));
        return listOfString;
    }*/

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

    /*
    //MODIFIES: unclaimedTextItems
    //EFFECTS: removes a specific TextItem from the list of unclaimed TextItems
    public void removeUnClaimedTextItem(TextItem textItem) {
        unclaimedTextItems.remove(textItem);
    }*/


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
