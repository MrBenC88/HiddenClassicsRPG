package model;

import java.util.HashMap;

// Class for the RPG Game Application, Runs the Game Menus , Set Up for Character Customization and Selection
public class GamePanel {
    public UserCharacter character;
    public Inventory inventory;
    //private TextCollection textCollection;
    //private Store store;


    // EFFECTS: runs the game application
    public GamePanel() {
        character = new UserCharacter();
        inventory = new Inventory();
        //textCollection = new TextCollection();
        //store = new Store();
        System.out.println("Initialized");
    }



    public HashMap<String, Integer> adjustAttributes(String characterChoice) {
        if (characterChoice == "Knight") {
            setKnight();
        } else if (characterChoice == "Mage") {
            setMage();
        } else if (characterChoice == "Assassin") {
            setAssassin();
        } else if (characterChoice == "Wanderer") {
            setWanderer();
        }
        return character.getCharacterAttributes();
    }

    public HashMap<String, Integer> setKnight() {
        character.setHealth(200);
        character.setAttack(12);
        character.setDef(30);
        character.setSpeed(4);
        character.addBalance(50);
        return character.getCharacterAttributes();
    }

    public HashMap<String, Integer> setMage() {
        character.setHealth(100);
        character.setAttack(20);
        character.setDef(12);
        character.setSpeed(6);
        character.addBalance(60);
        return character.getCharacterAttributes();
    }

    public HashMap<String, Integer> setAssassin() {
        character.setHealth(60);
        character.setAttack(50);
        character.setDef(9);
        character.setSpeed(8);
        character.addBalance(45);
        return character.getCharacterAttributes();
    }

    public HashMap<String, Integer> setWanderer() {
        character.setHealth(99);
        character.setAttack(99);
        character.setDef(99);
        character.setSpeed(99);
        character.addBalance(999);
        return character.getCharacterAttributes();
    }

    public void useGameItem(GameItem item) {
        int hp;
        int atk;
        int def;
        int spd;
        hp = item.getStats().indexOf(0);
        atk = item.getStats().indexOf(1);
        def = item.getStats().indexOf(2);
        spd = item.getStats().indexOf(3);

        character.addHealth(hp);
        character.addAttack(atk);
        character.addDef(def);
        character.addSpd(spd);
    }

    public void createCharacter(String name) {
        character.setName(name);
    }

    public String getCharacterName() {
        return character.getName();
    }

    public void setCharacterClass(int charChoice) {
        character.setCharacterClass(charChoice);
    }

    public UserCharacter getCharacter() {
        return character;
    }

    public String getCharacterChoice() {
        return character.getCharacterClass();
    }


}
