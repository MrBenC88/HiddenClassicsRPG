package ui;

import model.GameItem;
import model.GamePanel;
import model.InventoryItem;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import persistence.Writer;
import persistence.Reader;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static persistence.Reader.readData;

// Class representation for the RPG Game Application's User interface which includes Game Menus ,
// Set Up for Character Customization, and Item Selection UIs
public class HiddenClassicsPavilionRPG {
    private static final String GAME_FILE = "./data/gamefile.json";
    private Scanner input;
    public GamePanel game;
    public String currentScreen;


    // EFFECTS: runs the game application and initializes a GamePanel object
    public HiddenClassicsPavilionRPG() {
        currentScreen = "main_menu";
        game = new GamePanel();
        game.addUnclaimedItems();
        game.addUnclaimedTexts();
        game.addNpcs();
        //Hardcoded this for testing purposes
        game.textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(0));
        game.textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(1));
        game.textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(2));
        game.textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(3));
        game.textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(4));
        System.out.println("Initialized");
        input = new Scanner(System.in);
        startGame();
    }

    // MODIFIES: this
    // EFFECTS: calls upon the main menu method and run game
    private void startGame() {
        displayMainMenu();
        runGame();
    }

    //EFFECTS: displays the main menu
    private void displayMainMenu() {
        System.out.println("Main Menu\n\t\t\t Welcome to the Hidden Classics Pavilion RPG");
        System.out.println("Select an option:\n\t\t\t 1. New Game\t\t\t 2. Load Game\t\t\t 3. Quit");
        int optionSelect;
        optionSelect = input.nextInt(); // can probably put a try catch here / exception handling

        if (optionSelect == 1) {
            initialCharacterSetUp(); //method for setting up the character
        } else if (optionSelect == 2) {
            currentScreen = "load";
            loadGame();
            displayMainMenu();
        } else if (optionSelect == 3) {
            quit();
        } else {
            System.out.println("Please enter a valid integer (1, 2, 3)");
            displayMainMenu();
        }
    }

    //MODIFIES: character
    //EFFECTS: changes the name of the character
    private void createCharacter() {
        currentScreen = "character_creation";
        System.out.println("Customize Character---");
        String name;
        System.out.println("Please enter your name: ");
        name = input.next();
        game.createCharacter(name);
        System.out.println("Your character name has been set to: " + game.getCharacterName());
    }

    //EFFECTS: initializes character setup
    private void initialCharacterSetUp() {
        createCharacter();
        chooseClass();
        inGameMenu();
    }

    //EFFECTS: Terminates the Java Program with Status Code 0
    private void quit() {
        System.exit(0);
    }

    //MODIFIES: character
    //EFFECTS:  sets the character class and its stats
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
        System.out.println(game.getCharacterStats());
    }

    //EFFECTS: displays the in game menu based on user input
    private void inGameMenu() {
        currentScreen = "inGameMenu";
        System.out.println("\n\t\t\t\tIn Game Menu--\n");
        System.out.println("Select an option:\n\t\t\t 1. Inventory\n\t\t\t\t\tManage your assets!");
        System.out.println("\t\t\t 2. Store\n\t\t\t\t\tBuy and sell your assets!");
        System.out.println("\t\t\t 3. Collection\n\t\t\t\t\tView your collection of texts!");
        System.out.println("\t\t\t 4. Character Stats\n\t\t\t\t\tView your current stats!");
        System.out.println("\t\t\t 5. Exit\n\t\t\t\t\tExit this in game menu!");
        System.out.println("\t\t\t 6. Save\n\t\t\t\t\tSave your progress!");
        System.out.println("\nInput value [1,6]: ");
        int optionSelect;
        optionSelect = input.nextInt();
        inGameMenuSelector(optionSelect);
    }

    //REQUIRES: num from [1:5]
    //EFFECTS: calls upon corresponding method depending on user input
    private void inGameMenuSelector(int num) {
        if (num == 1) {
            currentScreen = "inventory";
            openInventory();
        } else if (num == 2) {
            currentScreen = "store";
            openStore();
        } else if (num == 3) {
            currentScreen = "collection";
            openCollection();
        } else if (num == 4) {
            currentScreen = "stats";
            System.out.println("Name:\t" + game.character.getName());
            System.out.println("Balance:\t" + game.character.getBalance());
            System.out.println("Stats:\t" + game.character.getCharacterAttributes());
            System.out.println("Starting Class:\t" + game.character.getCharacterClass());
            System.out.println("\nInventory:\nTotal Inventory Items:\t" + game.inventory.getInventoryTotalItems()
                    + "\nText Collection:\nTotal Text Items:\t" + game.textCollection.getAllTextItemNames());
            inGameMenu();
        } else if (num == 5) {
            System.out.println("Exited InGame Menu.");
        } else if (num == 6) {
            saveGame(game);
        }
    }

    //MODIFIES: writer
    //EFFECTS: saves the game data to file
    public void saveGame(GamePanel game) {
        try {
            Writer file = new Writer(new File(GAME_FILE));
            file.save(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: loads game data from GAME_FILE, if the file exists
    // otherwise initializes GAME with default values
    private void loadGame() {
        try {
            FileReader reader = new FileReader(GAME_FILE);
            JSONObject jsonObj = readData(reader);
            game = new GamePanel((String) jsonObj.get("character_name"), (double) jsonObj.get("character_balance"),
                    (HashMap<String, Integer>)jsonObj.get("character_attributes"),
                    (String)jsonObj.get("character_class"));



            inGameMenu();
        } catch (IOException | ParseException e) {
            System.out.println("Didn't work?");
            initialCharacterSetUp();
        }
    }


    //MODIFIES: gameObject
    //EFFECTS: selects a item from unclaimed items
    private void selectItem() {
        currentScreen = "item_select";
        game.gameObjects.shuffleUnclaimedGameItems();
        // in future iterations, this item selection will be RANDOMIZED and used whenever a user
        // encounters an item or wins / defeats opponent
        System.out.println("\n\t\t\t\tItem Selection--\n");
        System.out.println("Select an item:\n\t\t\t 1.\n\t\t\t\t\t "
                + game.getUnclaimedItemName(0)
                + " " + game.getUnclaimedItemDescription(0));
        System.out.println("\t\t\t 2. \n\t\t\t\t\t "
                + game.getUnclaimedItemName(1)
                + " " + game.getUnclaimedItemDescription(1));
        System.out.println("\t\t\t 3. \n\t\t\t\t\t "
                + game.getUnclaimedItemName(2)
                + " " + game.getUnclaimedItemDescription(2));
        int optionSelect = input.nextInt();
        optionSelect = optionSelect - 1;
        game.addGameObjectToInventory(game.gameObjects.getUnClaimedGameItem(optionSelect), 1);
        game.removeGameObject(game.gameObjects.getUnClaimedGameItem(optionSelect));
        System.out.println("The following has been added to your inventory:\n");
        System.out.println(game.showInventoryItemDetails());

    }

    //MODIFIES: character, inventory
    //EFFECTS: opens the store menu
    private void openStore() {
        currentScreen = "store";
        System.out.println("\n\t\t\t\tWelcome to the Store--\nOur policy is based off luck.--");
        System.out.println("\n\tWould you like to purchase a random item? (50 gold each):\t(Y/N)--\n");
        String optionSelect = input.next();
        if (optionSelect.equals("Y") || optionSelect.equals("yes") || optionSelect.equals("y")) {
            if (game.character.getBalance() < 50) {
                System.out.println("Sorry, your balance is insufficient!");
                inGameMenu();
            } else {
                System.out.println("50 gold has been removed from your balance!");
                game.character.removeBalance(50);
                System.out.println("Your current balance is:\t" + game.character.getBalance());
                selectItem();
                openStore();
            }
        } else if (optionSelect.equals("N") || optionSelect.equals("no") || optionSelect.equals("n")) {
            System.out.println("Have a good day!");
            inGameMenu();
        } else {
            System.out.println("You entered an invalid character. Please try again.");
            openStore();;
        }

    }

    //EFFECTS: opens the collection menu to show text collection details
    private void openCollection() {
        if (game.textCollection.getTextCollectionTotalTexts() != 0) {
            currentScreen = "textcollection";
            System.out.println("\n\t\t\t\tWelcome to your Text Collection--\n");
            System.out.println(game.showTextDetails());
            System.out.println("Input: q to go back.");
            String optionSelect = input.next();
            if (optionSelect.equals("q")) {
                inGameMenu();;
            } else {
                System.out.println("You entered an invalid character. Please try again.");
                openCollection();;
            }
        } else {
            System.out.println("You have no texts in your text collection!\n\nExiting Collection.");
            inGameMenu();
        }
    }

    //EFFECTS: shows inventory items and gets user input to select an inventory item
    private void openInventory() {
        if (game.inventory.getInventoryTotalItems() != 0) {
            currentScreen = "inventory";
            System.out.println("\n\t\t\t\tWelcome to your Inventory--\n");
            System.out.println(game.showInventoryItemDetails());
            System.out.println("Select an item by inputting the item number\t\t");
            int itemSelect = input.nextInt();

            InventoryItem selectedItem = game.getInventoryItemObject(itemSelect);
            System.out.println(selectedItem);
            System.out.println(game.getGameItemNameAndDescription(selectedItem.getGameItem()));
            viewItemInInventoryMenu(selectedItem.getGameItem());
        } else {
            System.out.println("You have no items in your inventory!\n\nExiting Inventory.");
            inGameMenu();
        }

    }

    //EFFECTS: Allows the user to select an option on what to do with their item
    private void viewItemInInventoryMenu(GameItem item) {
        System.out.println("What would like to do with the item?");
        System.out.println("Select an option:\t\n1. Use Item\n2. Discard Item\n3. View Different Item\n4. Quit Menu");
        int optionSelect = input.nextInt();
        viewItemInInventoryOptions(optionSelect, item);

    }

    //REQUIRES: a number for option select between 1-4
    //EFFECTS: based on input from user, show corresponding menu or adjust stat based on item
    private void viewItemInInventoryOptions(int optionSelect, GameItem item) {
        if (optionSelect == 1) {
            game.useGameItem(item);
            System.out.println("You have used: 1 x\t" + game.getGameItemName(item)
                    + "\nNew Stats:" + game.getCharacterStats());
            openInventory();
        } else if (optionSelect == 2) {
            game.removeGameObjectInInventory(item, 1);
            System.out.println("You have discarded: 1 x\t" + game.getGameItemName(item)
                    + "\nYou have collected 25 gold which has been added to your balance.");
            game.character.addBalance(25);
            System.out.println(game.character.getBalance());
            openInventory();
        } else if (optionSelect == 3) {
            System.out.println("View a Different Item!");
            openInventory();
        } else if (optionSelect == 4) {
            System.out.println("Exit Inventory Item View");
            inGameMenu();
        } else {
            System.out.println("Please input a valid number.");
        }

    }

    //EFFECTS: Starts the Game
    private void runGame() {
        boolean characterAlive = true;
        while (characterAlive) {
            System.out.println("In game! Select the following:\t\n walk, \tmenu");
            String option = input.next();
            if (option.equals("walk")) {
                String winner;
                System.out.println("Walking");
                System.out.println("You have encountered\t" + game.gameObjects.getNPC(0).getName());
                winner = game.order(game.gameObjects.getNPC(0));
                if (winner.equals("npc")) {
                    game.character.removeBalance(50);
                    System.out.println("The winner of the encounter is:\t"
                            + game.gameObjects.getNPC(0).getName()
                            + "\tYour new balance is\t\t" + game.character.getBalance());
                    game.character.removeBalance(10);
                } else if (winner.equals("player")) {
                    game.character.addBalance(50);
                    System.out.println("You have won! \t Your new balance is\t\t" + game.character.getBalance());
                }
            } else if (option.equals("menu")) {
                inGameMenu();
            }
        }
    }





}
