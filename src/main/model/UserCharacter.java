package model;

import java.util.ArrayList;
import java.util.HashMap;

// Represents the user's character having a name, balance (in dollars), attributes, and inventory
public class UserCharacter {
    private String name;
    private double balance;
    private HashMap<String, Integer> characterAttributes;
    private String characterChoice;


    //EFFECTS: constructs the user's character with no name, 0 balance, 0 stats, and 0 inventory items
    public UserCharacter() {
        name = "";
        balance = 0.00;

        characterAttributes = new HashMap<>();
        characterAttributes.put("Health", 0);
        characterAttributes.put("Attack", 0);
        characterAttributes.put("Defense", 0);
        characterAttributes.put("Speed", 0);
    }

    public String setCharacterClass(int num) {
        if (num == 1) {
            characterChoice = "Knight";
        } else if (num == 2) {
            characterChoice = "Mage";
        } else if (num == 3) {
            characterChoice = "Assassin";
        } else if (num == 4) {
            characterChoice = "Wanderer";
        }
        return  characterChoice;
    }

    public String getCharacterClass() {
        return characterChoice;
    }


    public HashMap<String,Integer> getCharacterAttributes() {
        return characterAttributes;
    }

    public void setAttack(int num) {
        characterAttributes.replace("Attack",num);
    }

    public void addAttack(int num) {
        int oldAtk = characterAttributes.get("Attack");
        int newAtk = oldAtk + num;
        characterAttributes.replace("Attack", newAtk);
    }

    public void removeAtk(int num) {
        int oldAtk = characterAttributes.get("Attack");
        int newAtk = oldAtk - num;

        if (newAtk < 0) {
            newAtk = 0;
        }
        characterAttributes.replace("Attack", newAtk);
    }

    public void setDef(int num) {
        characterAttributes.replace("Defense",num);
    }

    public void addDef(int num) {
        int oldDef = characterAttributes.get("Defense");
        int newDef = oldDef + num;
        characterAttributes.replace("Defense", newDef);
    }

    public void removeDef(int num) {
        int oldDef = characterAttributes.get("Defense");
        int newDef = oldDef - num;

        if (newDef < 0) {
            newDef = 0;
        }
        characterAttributes.replace("Defense", newDef);
    }

    public void setSpeed(int num) {
        characterAttributes.replace("Speed",num);
    }

    public void addSpd(int num) {
        int oldSpd = characterAttributes.get("Speed");
        int newSpd = oldSpd + num;
        characterAttributes.replace("Speed", newSpd);
    }

    public void removeSpd(int num) {
        int oldSpd = characterAttributes.get("Speed");
        int newSpd = oldSpd - num;

        if (newSpd < 0) {
            newSpd = 0;
        }
        characterAttributes.replace("Speed", newSpd);
    }

    public void setHealth(int num) {
        characterAttributes.replace("Health",num);
    }

    public void addHealth(int num) {
        int oldHealth = characterAttributes.get("Health");
        int newHealth = oldHealth + num;
        characterAttributes.replace("Health", newHealth);
    }

    public void removeHealth(int num) {
        int oldHealth = characterAttributes.get("Health");
        int newHealth = oldHealth - num;

        if (newHealth < 0) {
            newHealth = 0;
        }
        characterAttributes.replace("Health", newHealth);
    }

    //MODIFIES:  this
    //EFFECTS: sets the character name
    public void setName(String name) {
        this.name = name;
    }

    //EFFECTS: gets the character name
    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: adds amount to current balance
    public void addBalance(double amount) {
        this.balance += amount;

    }

    //MODIFIES: this
    //EFFECTS: removes amount to current balance
    public void removeBalance(double amount) {
        this.balance -= amount;

    }

    public double getBalance() {
        return balance;
    }



}
