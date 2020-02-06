package model;

import java.util.ArrayList;


//Represents a game item having a description, market price, and stats
public class GameItem {
    private String description;
    private double marketPrice;
    private ArrayList<Integer> stats; //    Consists of hp, atk, def, spd


    //EFFECTS: constructs the game item with description , market price,
    // and the stats
    public GameItem(String description, double marketPrice, ArrayList<Integer> stats) {
        this.description = description;
        this.marketPrice = marketPrice;
        this.stats = stats;
    }

    //EFFECTS: returns the game item description
    public String getDescription() {
        return description;
    }

    //EFFECTS: returns the game item market price in dollars
    public double getMarketPrice() {
        return marketPrice;
    }

    //EFFECTS: returns the game item stats
    public ArrayList<Integer> getStats() {
        return stats;
    }
}
