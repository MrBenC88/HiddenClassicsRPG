package ui;

import model.UserCharacter;

import java.util.HashMap;
import java.util.Scanner;

// RPG Game Application
public class HiddenClassicsGame {
    private Scanner input;
    private UserCharacter character;


    // EFFECTS: runs the game application
    public HiddenClassicsGame() {
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGame() {
        boolean gameRunning = true;
        String command = null;

        //initializes classes.
        init();
        displayMainMenu();



        while (gameRunning) {
            System.out.println("game running");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                gameRunning = false;
            } else if (command.equals("main menu")) {
                displayMainMenu();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes player
    private void init() {
        character = new UserCharacter();
        System.out.println("Initialized");
        input = new Scanner(System.in);
    }

    private void displayMainMenu() {
        System.out.println("Main Menu\n Welcome to the Hidden Classics Pavilion RPG");
        System.out.println("Select an option below.\n 1. Start Game\n 2. Info\n 3. Quit");
        int optionSelect;
        optionSelect = input.nextInt();

        if (optionSelect == 1) {
            init();
            createCharacter();
            chooseClass();
            //call to playGame
        } else if (optionSelect == 2) {
            System.out.println("Info: \n");
            displayMainMenu();
        } else if (optionSelect == 3) {
            quit();
        } else {
            System.out.println("Please enter a valid integer (1, 2, 3)");
            displayMainMenu();
        }
    }

    private void createCharacter() {
        String name;
        System.out.println("Please enter your name: ");
        name = input.next();
        character.setName(name);
        System.out.println("Your character name has been set to: " + character.getName());
    }

    //EFFECTS: Terminates the Java Program with Status Code 0
    private void quit() {
        System.exit(0);
    }

    private void chooseClass() {
        System.out.println("Select your class! \n");
        System.out.println("1. Knight \n HP: 200 ATK: 12 DEF: 30 SPD: 4");
        System.out.println("\n2. Mage \n HP: 100 ATK: 20 DEF: 12 SPD: 6");
        System.out.println("\n3. Assassin \n HP: 60 ATK: 50 DEF: 9 SPD: 8");
        System.out.println("\n4. Wanderer \n HP: 99 ATK: 99 DEF: 99 SPD: 99");

        int charChoice;
        charChoice = input.nextInt();
        character.setCharacter(charChoice);
        System.out.println(character.setCharacter(charChoice));
        adjustAttributes(character.setCharacter(charChoice));
        System.out.println(adjustAttributes(character.setCharacter(charChoice)));
    }

    private HashMap<String, Integer> adjustAttributes(String characterChoice) {
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

    private HashMap<String, Integer> setKnight() {
        character.setHealth(200);
        character.setAttack(12);
        character.setDef(30);
        character.setSpeed(4);
        character.addBalance(50);
        return character.getCharacterAttributes();
    }

    private HashMap<String, Integer> setMage() {
        character.setHealth(100);
        character.setAttack(20);
        character.setDef(12);
        character.setSpeed(6);
        character.addBalance(60);
        return character.getCharacterAttributes();
    }

    private HashMap<String, Integer> setAssassin() {
        character.setHealth(60);
        character.setAttack(50);
        character.setDef(9);
        character.setSpeed(8);
        character.addBalance(45);
        return character.getCharacterAttributes();
    }

    private HashMap<String, Integer> setWanderer() {
        character.setHealth(99);
        character.setAttack(99);
        character.setDef(99);
        character.setSpeed(99);
        character.addBalance(999);
        return character.getCharacterAttributes();
    }
}
