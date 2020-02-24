package model;

import java.util.ArrayList;


//A class which represents a game item having a name, description, market price, and stats consisting of health,
// attack, defense, and speed
public class GameItem {
    private String name;
    private String description;
    private double marketPrice;
    private int hp;
    private int atk;
    private int def;
    private  int spd; //    Consists of hp, atk, def, spd


    //EFFECTS: constructs the game item with name, description , market price, and the stats
    public GameItem(String name,String description, double marketPrice, int hp, int atk, int def, int spd) {
        this.name = name;
        this.description = description;
        this.marketPrice = marketPrice;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
    }

    //EFFECTS: constructs the game item from parameters and casts the array of longs to integer
    public GameItem(String name, double price, ArrayList<Long> itemStats, String descrp) {
        this.name = name;
        marketPrice = price;
        //We have to get the intValue so we can get an integer rather than long
        //When reading from JSON, it casts all integer values to longs so, we need to change these long values back
        // into integer values with .intValue()
        this.hp = itemStats.get(0).intValue();
        this.atk = itemStats.get(1).intValue();
        this.def = itemStats.get(2).intValue();
        spd = itemStats.get(3).intValue();
        description = descrp;
    }



    //EFFECTS: gets the game item description
    public String getDescription() {
        return description;
    }

    //EFFECTS: gets the game item name
    public String getGameItemName() {
        return name;
    }

    //EFFECTS: gets the game item market price in dollars
    public double getMarketPrice() {
        return marketPrice;
    }

    //EFFECTS: gets the game item stats in the form of an ArrayList
    public ArrayList<Integer> getStats() {
        ArrayList<Integer> stats = new ArrayList<>(4);
        stats.add(hp);
        stats.add(atk);
        stats.add(def);
        stats.add(spd);
        return stats;
    }

}
