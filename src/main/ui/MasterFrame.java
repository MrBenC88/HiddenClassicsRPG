package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.GamePanel;
import org.json.simple.JSONObject;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static persistence.Reader.readData;

/**
 * The main class to represent the GUI. This is where the game GUI occurs using Java FX
 */
public class MasterFrame extends Application {
    Stage mainWindow;
    Button newGameButton;
    Button loadGameButton;
    Button quitButton;
    public static AudioClip mainMenumusic; // AudioClip only supports .wav, .au, .mid and .aiffs Pavilion
    public GamePanel game;
    CharacterSetUpGUI characterSetUpGUI;
    private static final String GAME_FILE = "./data/game_data.json";
    InGameMenu inGameMenu;

    //MODIFIES: mainMenumusic
    //EFFECTS: sets up the music and loops it
    @Override
    public void init() throws Exception {
        //useful for loading assets / whatnot before app launches //gamemusic.mid
        mainMenumusic = Applet.newAudioClip(getClass().getResource("./asset/music/gracefully.wav"));
        mainMenumusic.loop();
    }

    //MODIFIES: this
    //EFFECTS: shuts all music and exits program
    @Override
    public void stop() throws Exception {
        //useful to run after we close the window
        mainMenumusic.stop();
        System.exit(0);
    }

    //MODIFIES: this
    //EFFECTS: Starts the program and creates main window and main scene
    @Override
    public void start(Stage stage) throws Exception {
        this.mainWindow = stage;
        setUpMainWindow(mainWindow);

        StackPane mainLayout = new StackPane(); // controls layout of how it is displayed -> our root node
        try {
            Image mainMenuImage = new Image(new FileInputStream("src/main/ui/asset/image/menu/mainimg.jpg"));
            ImageView mainMenuImgView = new ImageView(mainMenuImage);
            mainLayout.getChildren().add(mainMenuImgView);
        } catch (FileNotFoundException e) {
            //
        }
        setUpMainMenuButtons();
        mainLayout.getChildren().addAll(newGameButton, loadGameButton, quitButton);
        StackPane.setMargin(newGameButton, new Insets(100,0,0,0));
        StackPane.setMargin(loadGameButton, new Insets(220,0,0,0));
        StackPane.setMargin(quitButton, new Insets(340,0,0,0));
        Scene scene = new Scene(mainLayout);
        scene.setCursor(Cursor.DEFAULT);
        mainWindow.setScene(scene);
        mainWindow.show();

        //Button Listeners
        newGameButton.setOnAction(e -> createNewGame());
        loadGameButton.setOnAction(e -> loadGame());
        quitButton.setOnAction(e -> System.exit(0));
    }

    //MODIFIES: stage
    //EFFECTS: setup the main stage, the size, window style. title
    public void setUpMainWindow(Stage stage) {
        mainWindow.setTitle("Hidden Classics Pavilion");
        mainWindow.setWidth(800);
        mainWindow.setHeight(625);
        mainWindow.setMinWidth(800);
        mainWindow.setMinHeight(625);
        mainWindow.setMaxWidth(800);
        mainWindow.setMaxHeight(625);
        mainWindow.setResizable(false);
        mainWindow.initStyle(StageStyle.DECORATED); //set window style - UNDECORATED- window with no frame
    }

    //MODIFIES: stage
    //EFFECTS: setup the main menu buttons, the size,  style. text
    public void setUpMainMenuButtons() {
        newGameButton = new Button("New Game");
        loadGameButton = new Button("Load Game");
        quitButton = new Button(("Quit"));
        newGameButton.setTextFill(Color.web("#d1f0f2"));
        newGameButton.setStyle("-fx-background-color: #0c537d");
        newGameButton.setFont(new Font("Arial", 16));
        loadGameButton.setTextFill(Color.web("#d1f0f2"));
        loadGameButton.setStyle("-fx-background-color: #0c537d");
        loadGameButton.setFont(new Font("Arial", 16));
        quitButton.setTextFill(Color.web("#d1f0f2"));
        quitButton.setStyle("-fx-background-color: #0c537d");
        quitButton.setFont(new Font("Arial", 16));

        // Set button size
        newGameButton.setMinHeight(16);
        newGameButton.setMinWidth(130);
        loadGameButton.setMinHeight(16);
        loadGameButton.setMinWidth(130);
        quitButton.setMinHeight(16);
        quitButton.setMinWidth(130);
    }

    //MODIFIES: this
    //EFFECTS: constructs a new GamePanel and creates a character Setup Screen
    public void createNewGame() {
        game = new GamePanel();
        System.out.println("CREATING NEW GAME");
        addAssetsToGame(game);
        characterSetUpGUI = new CharacterSetUpGUI(game, mainWindow);

    }

    //MODIFIES: GamePanel
    //EFFECTS: adds game items to game Panel
    public void addAssetsToGame(GamePanel game) {
        game.addUnclaimedItems();
        game.addUnclaimedTexts();
        game.addNpcs();
        //Hardcoded this for testing purposes
        game.textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(0));
        game.textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(1));
        game.textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(2));
        game.textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(3));
        game.textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(4));

        //Added Some Inventory Items to Inventory
        game.inventory.addInventoryItem(game.gameObjects.getUnClaimedGameItem(0),2);
        game.inventory.addInventoryItem(game.gameObjects.getUnClaimedGameItem(2),3);
        game.inventory.addInventoryItem(game.gameObjects.getUnClaimedGameItem(1),1);


    }


    //MODIFIES: this
    //EFFECTS: loads game data from GAME_FILE, if the file exists
    // otherwise initializes new GAME with default values
    public void loadGame() {
        try {
            FileReader reader = new FileReader(GAME_FILE);
            JSONObject jsonObj = readData(reader);
            game = new GamePanel((String) jsonObj.get("char_name"), (double) jsonObj.get("char_balance"),
                    (HashMap<String, Long>)jsonObj.get("char_attributes"),(String)jsonObj.get("char_class"),
                    (ArrayList<String>) jsonObj.get("inv_item_names"),(ArrayList<Double>) jsonObj.get("inv_item_price"),
                    (ArrayList<ArrayList<Long>>) jsonObj.get("inv_item_stats"),
                    (ArrayList<String>) jsonObj.get("inv_item_des"),(ArrayList<String>) jsonObj.get("uncl_i_name"),
                    (ArrayList<Double>) jsonObj.get("uncl_i_pri"),(ArrayList<ArrayList<Long>>) jsonObj.get("uncl_i_s"),
                    (ArrayList<String>) jsonObj.get("uncl_i_desc"),
                    (ArrayList<ArrayList<String>>)jsonObj.get("npclines"),
                    (ArrayList<String>) jsonObj.get("npctitles"),(ArrayList<String>) jsonObj.get("npcdirs"),
                    (ArrayList<String>)jsonObj.get("npctypes"), (ArrayList<String>)jsonObj.get("npcnames"),
                    (ArrayList<HashMap<String, Long>>) jsonObj.get("npcstat"),
                    (ArrayList<String>)jsonObj.get("uncl_ti_name"), (ArrayList<String>)jsonObj.get("uncl_ti_content"),
                    (ArrayList<String>)jsonObj.get("uncl_ti_bookId"),
                    (ArrayList<String>)jsonObj.get("text_item_name"),(ArrayList<String>)jsonObj.get("text_item_cont"),
                    (ArrayList<String>)jsonObj.get("text_item_bookId"));

            inGameMenu = new InGameMenu(game, mainWindow);

        } catch (IOException | org.json.simple.parser.ParseException e) {
            createNewGame();
        }
    }

}
