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

    //HP,ATK,DEF,SPD
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

    //EFFECTS: returns the npc name
    public String getName() {
        return name;
    }

    /*
    //MODIFIES: this
    //EFFECTS: sets the npcType
    public void setNpcType(String npcType) {
        this.npcType = npcType;
    }

    //MODIFIES: this
    //EFFECTS: sets the npc direction
    public void setDirection(String direction) {
        this.direction = direction;
    }

    //MODIFIES: this
    //EFFECTS: sets the npc name
    public void setName(String name) {
        this.name = name;
    }*/

    /*
    //MODIFIES: this
    //EFFECTS: sets the npc stats
    public void setNpcStats(HashMap<String, Integer> npcStats) {
        this.npcStats = npcStats;
    }

    //MODIFIES: this
    //EFFECTS: sets the npc title
    public void setTitle(String title) {
        this.title = title;
    } */


    //EFFECTS: returns  the npcType
    public String getNpcType() {
        return npcType;
    }

    //EFFECTS: returns  the npc title
    public String getTitle() {
        return title;
    }

    //EFFECTS: returns  the npc direction
    public String getDirection() {
        return  direction;
    }

    //EFFECTS: returns  the npc lines
    public ArrayList<String> getListOfNpcLines() {
        return listOfNpcLines;
    }

    //EFFECTS: returns  the npc stats
    public HashMap<String, Integer> getNpcStats() {
        return npcStats;
    }


}
