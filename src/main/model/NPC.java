package model;

import java.util.ArrayList;
import java.util.HashMap;

// A class representing the NPC Game Object with the NPC's position that they face, the npcType, NPC name, NPC title,
// NPC lines, NPC stats
public class NPC {
    private String direction;
    private String npcType; // can be friend  or enemy
    private String name;
    private String title;
    private ArrayList<String> listOfNpcLines;
    private HashMap<String, Integer> npcStats;

    //EFFECTS: constructs an npc with given parameters
    public NPC(String name, String npcType, String title, String direction,
               ArrayList<String> line, HashMap<String, Integer> stats) {
        this.name = name;
        this.npcType = npcType;
        this.title = title;
        this.direction = direction;
        this.listOfNpcLines = line;
        this.npcStats = stats;
    }

    public String getName() {
        return name;
    }

    public void setNpcType(String npcType) {
        this.npcType = npcType;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNpcStats(HashMap<String, Integer> npcStats) {
        this.npcStats = npcStats;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNpcType() {
        return npcType;
    }

    public String getTitle() {
        return title;
    }

    public String getDirection() {
        return  direction;
    }

    public ArrayList<String> getListOfNpcLines() {
        return listOfNpcLines;
    }

    public HashMap<String, Integer> getNpcStats() {
        return npcStats;
    }


}
