package ui;

import model.GamePanel;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import persistence.Writer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * this is the GUI for the In Game Menu. It is the master UI panel essentially
 * and redirects the screen accordingly.
 *
 * There are two cases where this will be constructed.
 *
 * 1. New Game- it will be constructed via Character Setup
 * 2. Load Game - will be constructed via MasterFrame upon loading the Game Data
 *
 * This will also be called by its subGUI classes.
 */

public class InGameMenu {
    GamePanel game;
    Stage stage;
    StackPane mainLayout;
    Scene inGameMenuScene;
    ImageView inGameScreen;

    Button inventoryButton;
    Button storeButton;
    Button collectionButton;
    Button charStatButton;
    Button exitButton;
    Button saveButton;

    InventoryGUI inventoryGUI;
    StoreGUI storeGUI;
    CollectionGUI collectionGUI;
    CharStatGUI charStatGui;
    GameGUI inGameGui;
    private static final String GAME_FILE = "./data/game_data.json";

    //EFFECTS: constructs the in game menu gui and all its components
    // initializes the other scene objects
    public InGameMenu(GamePanel game, Stage stage) {
        this.game = game;
        this.stage = stage;
        mainLayout = new StackPane(); // controls layout of how it is displayed -> our root node
        inGameMenuScene = new Scene(mainLayout);
        inGameMenuScene.setCursor(Cursor.DEFAULT);
        setCurrentScene(stage);
        instantiateAllScenes(game,stage, inGameMenuScene);

        try {
            Image igMenuImage = new Image(new FileInputStream("src/main/ui/asset/image/menu/ingamemenu.png"));
            inGameScreen = new ImageView(igMenuImage);
            mainLayout.getChildren().add(inGameScreen);
        } catch (FileNotFoundException e) {
            //
        }
        inventoryButton = new Button("Inventory");
        storeButton = new Button("Store");
        collectionButton = new Button("Collection");
        charStatButton = new Button("Character Stats");
        exitButton = new Button("Exit");
        saveButton = new Button("Save");
        setUpButtons();
    }

    //MODIFIES: inventoryGUI, storeGUI, collectionGUI,charStatGui, inGamegui
    //EFFECTS: instantiate all the other scene objects
    public void instantiateAllScenes(GamePanel game, Stage stage, Scene scene) {
        inventoryGUI = new InventoryGUI(game, stage, scene);
        storeGUI = new StoreGUI(game, stage,  scene);
        collectionGUI = new CollectionGUI(game, stage,  scene);
        charStatGui = new CharStatGUI(game, stage,  scene);
        inGameGui = new GameGUI(game, stage,  scene);
    }


    //EFFECTS: sets up the buttons and places it on the scene with correct dimensions
    public void setUpButtons() {
        inventoryButton.setMinHeight(30);
        inventoryButton.setMinWidth(100);
        inventoryButton.setPrefHeight(30);
        inventoryButton.setPrefWidth(100);
        inventoryButton.setMaxWidth(100);

        storeButton.setMinHeight(30);
        storeButton.setMinWidth(100);
        storeButton.setPrefHeight(30);
        storeButton.setPrefWidth(100);
        storeButton.setMaxWidth(100);

        collectionButton.setMinHeight(30);
        collectionButton.setMinWidth(100);
        collectionButton.setPrefHeight(30);
        collectionButton.setPrefWidth(100);
        collectionButton.setMaxWidth(100);
        setUpButtonHelper();
    }

    //EFFECTS: a helper function which sets up the buttons and places it on the scene with correct dimensions
    public void setUpButtonHelper() {
        charStatButton.setMinHeight(30);
        charStatButton.setMinWidth(100);
        charStatButton.setPrefHeight(30);
        charStatButton.setPrefWidth(100);
        charStatButton.setMaxWidth(100);

        exitButton.setMinHeight(30);
        exitButton.setMinWidth(100);
        exitButton.setPrefHeight(30);
        exitButton.setPrefWidth(100);
        exitButton.setMaxWidth(100);

        saveButton.setMinHeight(30);
        saveButton.setMinWidth(100);
        saveButton.setPrefHeight(30);
        saveButton.setPrefWidth(100);
        saveButton.setMaxWidth(100);
        setUpButtonsHelperPositioning();
    }

    //EFFECTS:sets up the button positioning
    public void setUpButtonsHelperPositioning() {
        mainLayout.getChildren().addAll(inventoryButton, storeButton,collectionButton,charStatButton,
                exitButton,saveButton);
        StackPane.setMargin(inventoryButton, new Insets(0,0,140,350));
        StackPane.setMargin(storeButton, new Insets(100,0,140,350));
        StackPane.setMargin(collectionButton, new Insets(200,0,140,350));
        StackPane.setMargin(charStatButton, new Insets(300,0,140,350));
        StackPane.setMargin(exitButton, new Insets(400,0,140,350));
        StackPane.setMargin(saveButton, new Insets(500,0,140,350));
        setUpButtonEventListeners();
    }

    //EFFECTS: sets up the button event listeners
    public void setUpButtonEventListeners() {
        inventoryButton.setOnAction(e -> openInventory());
        storeButton.setOnAction(e -> openStore());
        collectionButton.setOnAction(e -> openCollection());
        charStatButton.setOnAction(e -> openCharStats());
        exitButton.setOnAction(e -> openGame());
        saveButton.setOnAction(e -> saveGame(game));
    }

    //MODIFIES: inventoryGUI
    //EFFECTS:  sets the inventoryGUI scene onto stage
    public void openInventory() {
        inventoryGUI.setCurrentScene();
    }

    //MODIFIES: storeGUI
    //EFFECTS:  sets the storeGUI scene onto stage
    public void openStore() {
        storeGUI.setCurrentScene();
    }

    //MODIFIES: collectionGUI
    //EFFECTS:  sets the collectionGUI scene onto stage
    public void openCollection() {
        collectionGUI.setCurrentScene();
    }

    //MODIFIES: charStatGui
    //EFFECTS:  sets the charStatGui scene onto stage
    public void openCharStats() {
        charStatGui.setCurrentScene();
    }

    //MODIFIES: inGameGui
    //EFFECTS:  sets the inGameGui scene onto stage
    public void openGame() {
        inGameGui.setCurrentScene();
    }


    //MODIFIES: stage
    //EFFECTS:  sets the current in game menu scene onto stage
    public void setCurrentScene(Stage stage) {
        stage.setScene(inGameMenuScene);
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

}



