package model;

import java.util.ArrayList;

// a class representing game objects within the game
// this includes items and npcs
public class GameObject {
    private ArrayList<GameItem> unclaimedGameItems;
    //private ArrayList<NPC> npcs;

    public GameObject() {
        unclaimedGameItems = new ArrayList<>();
    }

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

    public GameItem getUnClaimedGameItem(int positionInArray) {
        GameItem itemFromWild;
        itemFromWild = unclaimedGameItems.get(positionInArray);
        return  itemFromWild;
    }

    public void removeUnClaimedGameItem(GameItem gameItem) {
        unclaimedGameItems.remove(gameItem);
    }
}
