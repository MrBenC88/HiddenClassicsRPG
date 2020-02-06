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
        displayMenu();

        while (gameRunning) {

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                gameRunning = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes player
    private void init() {
        character = new UserCharacter();
        System.out.println("Initialized");
        input = new Scanner(System.in);
        createCharacter();
    }

    private void processCommand(String command) {
        System.out.println(command);
    }

    private void displayMenu() {
        System.out.println("Main Menu");
    }

    private void createCharacter() {
        String name;
        System.out.println("Please enter your name: ");
        name = input.next();
        character.setName(name);
        System.out.println("Your character name has been set to: " + character.getName());
    }

    private void allocateInitialStat() {
        HashMap<String, Integer> stats =  character.getCharacterAttributes();

    }
}
