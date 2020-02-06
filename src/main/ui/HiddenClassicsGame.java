package ui;

import model.UserCharacter;

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

        while (gameRunning) {
            displayMenu();
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
    }

    private void processCommand(String command) {
        System.out.println(command);
    }

    private void displayMenu() {
        System.out.println("Main Menu");
    }
}
