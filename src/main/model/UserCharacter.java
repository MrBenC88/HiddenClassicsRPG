package model;

import java.util.ArrayList;
import java.util.HashMap;

// Class is a representation of the user's character having a name, balance (in dollars), attributes, and inventory
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

    //REQUIRES: num [1:4]
    //EFFECTS: based on user input, sets the characterChoice to a specific type
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

    //EFFECTS: returns the characterChoice
    public String getCharacterClass() {
        return characterChoice;
    }

    //EFFECTS: returns the character's attributes
    public HashMap<String,Integer> getCharacterAttributes() {
        return characterAttributes;
    }

    //MODIFIES: characterAttributes
    //EFFECTS: Sets the user's attack attribute
    public void setAttack(int num) {
        characterAttributes.replace("Attack",num);
    }

    //REQUIRES: num >= 0
    //MODIFIES: characterAttributes
    //EFFECTS: Adds the specified attack to the character Attack attribute
    public void addAttack(int num) {
        int oldAtk = characterAttributes.get("Attack");
        int newAtk = oldAtk + num;
        characterAttributes.replace("Attack", newAtk);
    }

    //REQUIRES: num <=0
    //MODIFIES: characterAttributes
    //EFFECTS: removes the specified attack from the character Attack attribute
    public void removeAtk(int num) {
        int oldAtk = characterAttributes.get("Attack");
        int newAtk = oldAtk - num;

        if (newAtk < 0) {
            newAtk = 0;
        }
        characterAttributes.replace("Attack", newAtk);
    }

    //MODIFIES: characterAttributes
    //EFFECTS: Sets the user's defense attribute
    public void setDef(int num) {
        characterAttributes.replace("Defense",num);
    }

    //REQUIRES: num >= 0
    //MODIFIES: characterAttributes
    //EFFECTS: Adds the specified defense to the character defense attribute
    public void addDef(int num) {
        int oldDef = characterAttributes.get("Defense");
        int newDef = oldDef + num;
        characterAttributes.replace("Defense", newDef);
    }

    //REQUIRES: num <=0
    //MODIFIES: characterAttributes
    //EFFECTS: removes the specified defense from the character defense attribute
    public void removeDef(int num) {
        int oldDef = characterAttributes.get("Defense");
        int newDef = oldDef - num;

        if (newDef < 0) {
            newDef = 0;
        }
        characterAttributes.replace("Defense", newDef);
    }

    //MODIFIES: characterAttributes
    //EFFECTS: Sets the user's speed  attribute
    public void setSpeed(int num) {
        characterAttributes.replace("Speed",num);
    }

    //REQUIRES: num >= 0
    //MODIFIES: characterAttributes
    //EFFECTS: Adds the specified speed to the character speed attribute
    public void addSpd(int num) {
        int oldSpd = characterAttributes.get("Speed");
        int newSpd = oldSpd + num;
        characterAttributes.replace("Speed", newSpd);
    }

    //REQUIRES: num <=0
    //MODIFIES: characterAttributes
    //EFFECTS: removes the specified speed from the character speed attribute
    public void removeSpd(int num) {
        int oldSpd = characterAttributes.get("Speed");
        int newSpd = oldSpd - num;

        if (newSpd < 0) {
            newSpd = 0;
        }
        characterAttributes.replace("Speed", newSpd);
    }

    //MODIFIES: characterAttributes
    //EFFECTS: Sets the user's health attribute
    public void setHealth(int num) {
        characterAttributes.replace("Health",num);
    }

    //REQUIRES: num >= 0
    //MODIFIES: characterAttributes
    //EFFECTS: Adds the specified health to the character health attribute
    public void addHealth(int num) {
        int oldHealth = characterAttributes.get("Health");
        int newHealth = oldHealth + num;
        characterAttributes.replace("Health", newHealth);
    }

    //REQUIRES: num <=0
    //MODIFIES: characterAttributes
    //EFFECTS: removes the specified health from the character health attribute
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

    //REQUIRES: amount >=0
    //MODIFIES: this
    //EFFECTS: adds amount to current balance
    public void addBalance(double amount) {
        this.balance += amount;

    }

    //REQUIRES: amount <= 0
    //MODIFIES: this
    //EFFECTS: removes amount to current balance
    public void removeBalance(double amount) {
        this.balance -= amount;
        if (this.balance <= 0) {
            this.balance = 0;
        }

    }

    //EFFECTS: returns the balance
    public double getBalance() {
        return balance;
    }





}
