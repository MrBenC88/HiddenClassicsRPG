package model;

import java.util.ArrayList;

// A class representing game objects within the game which includes unclaimed game items and npcs
public class GameObject {
    private ArrayList<GameItem> unclaimedGameItems;
    //private ArrayList<NPC> npcs;

    //EFFECTS: constructs a game object and initializes an empty array of unclaimedGameItems
    public GameObject() {
        unclaimedGameItems = new ArrayList<>();
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
        unclaimedGameItems.add(new GameItem("Golden Kiwi",
                "A golden kiwi which works as a decoration",
                1.00, 2,2,3,12));
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
