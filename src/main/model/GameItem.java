package model;

import java.util.ArrayList;


//Represents a game item having a description, market price, and stats
public class GameItem {
    private String description;
    private double marketPrice;
    private int hp;
    private int atk;
    private int def;
    private  int spd; //    Consists of hp, atk, def, spd


    //EFFECTS: constructs the game item with description , market price,
    // and the stats
    public GameItem(String description, double marketPrice, int hp, int atk, int def, int spd) {
        this.description = description;
        this.marketPrice = marketPrice;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;

    }

    //EFFECTS: returns the game item description
    public String getDescription() {
        return description;
    }

    //EFFECTS: returns the game item market price in dollars
    public double getMarketPrice() {
        return marketPrice;
    }

    //EFFECTS: returns the game item stats in the form of an ArrayList
    public ArrayList<Integer> getStats() {
        ArrayList<Integer> stats = new ArrayList<>(4);
        stats.add(hp);
        stats.add(atk);
        stats.add(def);
        stats.add(spd);
        return stats;
    }
}
