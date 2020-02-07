package ui;

import model.GamePanel;

import java.util.Scanner;

// Class for the RPG Game Application, Runs the Game Menus , Set Up for Character Customization and Selection
public class MainMenu {
    private Scanner input;
    protected GamePanel game;
    public String currentScreen;
    //private TextCollection textCollection;
    //private Store store;


    // EFFECTS: runs the game application
    public MainMenu() {
        currentScreen = "main_menu";
        game = new GamePanel();
        //textCollection = new TextCollection();
        //store = new Store();
        System.out.println("Initialized");
        input = new Scanner(System.in);
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGame() {
        boolean gameRunning = true;
        String command = null;


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

    private void displayMainMenu() {
        System.out.println("Main Menu\n\t\t\t Welcome to the Hidden Classics Pavilion RPG");
        System.out.println("Select an option:\n\t\t\t 1. Start Game\t\t\t 2. Info\t\t\t 3. Quit");
        int optionSelect;
        optionSelect = input.nextInt(); // can probably put a try catch here / exception handling

        if (optionSelect == 1) {
            createCharacter();
            chooseClass();
            //call to playGame
        } else if (optionSelect == 2) {
            currentScreen = "info";
            System.out.println("Info and Instructions go here! \n TextTextTextTextTextTextTextTextText");
            displayMainMenu();
        } else if (optionSelect == 3) {
            quit();
        } else {
            System.out.println("Please enter a valid integer (1, 2, 3)");
            displayMainMenu();
        }
    }

    private void createCharacter() {
        currentScreen = "character_creation";
        System.out.println("Customize Character---");
        String name;
        System.out.println("Please enter your name: ");
        name = input.next();
        game.createCharacter(name);
        System.out.println("Your character name has been set to: " + game.getCharacterName());
    }

    //EFFECTS: Terminates the Java Program with Status Code 0
    private void quit() {
        System.exit(0);
    }

    private void chooseClass() {
        currentScreen = "class_selection";
        System.out.println("Select your class--- \n");
        System.out.println("1. Knight \n HP: 200 ATK: 12 DEF: 30 SPD: 4");
        System.out.println("\n2. Mage \n HP: 100 ATK: 20 DEF: 12 SPD: 6");
        System.out.println("\n3. Assassin \n HP: 60 ATK: 50 DEF: 9 SPD: 8");
        System.out.println("\n4. Wanderer \n HP: 99 ATK: 99 DEF: 99 SPD: 99");

        int charChoice;
        charChoice = input.nextInt(); // can probably put a try catch here / exception handling
        game.setCharacterClass(charChoice);
        System.out.println(game.getCharacterChoice());
        game.adjustAttributes(game.getCharacter().setCharacterClass(charChoice));
        System.out.println(game.adjustAttributes(game.getCharacter().setCharacterClass(charChoice)));
    }


}
