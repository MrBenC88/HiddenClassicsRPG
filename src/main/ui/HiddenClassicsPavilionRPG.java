package ui;

import model.GameItem;
import model.GamePanel;
import model.InventoryItem;
import model.TextCollection;

import java.util.Scanner;

// Class representation for the RPG Game Application's User interface which includes Game Menus ,
// Set Up for Character Customization, and Item Selection UIs
public class HiddenClassicsPavilionRPG {
    private Scanner input;
    protected GamePanel game;
    public String currentScreen;
    private TextCollection textCollection;
    //private Store store;


    // EFFECTS: runs the game application and initializes a GamePanel object
    public HiddenClassicsPavilionRPG() {
        currentScreen = "main_menu";
        game = new GamePanel();
        game.addUnclaimedItems();
        textCollection = new TextCollection();
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
            if (command.equals("quit")) {
                gameRunning = false;
            } else if (command.equals("main menu")) {
                displayMainMenu();
            }
        }
    }

    //EFFECTS: displays the main menu
    private void displayMainMenu() {
        System.out.println("Main Menu\n\t\t\t Welcome to the Hidden Classics Pavilion RPG");
        System.out.println("Select an option:\n\t\t\t 1. Start Game\t\t\t 2. Info\t\t\t 3. Quit");
        int optionSelect;
        optionSelect = input.nextInt(); // can probably put a try catch here / exception handling

        if (optionSelect == 1) {
            initialCharacterSetUp(); //method for setting up the character
            selectItem();
            selectItem(); //added another to test adding two items
            inGameMenu();

        } else if (optionSelect == 2) {
            currentScreen = "info";
            System.out.println("Info and Instructions go here! \n\t\t\t Text Text Text Text Text Text Text\n\n");
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
        //call to method for show character attribute
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
        System.out.println("\nInput value [1,5]: ");
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
            System.out.println("function call to store method");
            //call to ui method for store related
        } else if (num == 3) {
            currentScreen = "collection";
            System.out.println("function call to collection method");
            //call to ui method for collection related
        } else if (num == 4) {
            currentScreen = "stats";
            System.out.println("Name:\t" + game.character.getName());
            System.out.println("Balance:\t" + game.character.getBalance());
            System.out.println("Stats:\t" + game.character.getCharacterAttributes());
            System.out.println("Starting Class:\t" + game.character.getCharacterClass());
            System.out.println("\nInventory:\nTotal Inventory Items:\t" + game.inventory.getInventoryTotalItems()
                    + "\nInventory Item Objects (DEVELOPER USE):\t" + game.inventory.getAllInventoryItems()
                    + "\nInventory Item List:\t" + game.inventory.getAllInventoryItemGameItemNames());
            inGameMenu();
        } else if (num == 5) {
            System.out.println("Exited InGame Menu.");
            // a call to resume game method

        }
    }

    //MODIFIES: gameObject
    //EFFECTS: selects a item from unclaimed items
    private void selectItem() {
        currentScreen = "item_select";
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

}
