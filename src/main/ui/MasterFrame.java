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
import org.json.simple.parser.ParseException;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static persistence.Reader.readData;

/**
 * The main class to represent the main welcome screen GUI. This is where the game GUI occurs using Java FX
 * The class represents the frame and entry point to program where three options are available.
 *  - New Game
 *  - Load Game
 *  - Quit
 *
 *  The class consists of all GUI components such as buttons, event listeners, screen
 *  It also initializes components which are important to the program such as music
 */
public class MasterFrame extends Application {
    Stage mainWindow;
    Button newGameButton;
    Button loadGameButton;
    Button quitButton;
    public static AudioClip mainMenumusic; // AudioClip only supports .wav, .au, .mid and .aiffs Pavilion
    GamePanel game;

    private static final String GAME_FILE = "./data/game_data.json";
    InGameMenu inGameMenu;
    CharacterSetUpGUI characterSetUpGUI;
    LoadGameAssetManager loadGameAssetManager;

    //MODIFIES: mainMenumusic
    //EFFECTS: sets up the music and loops it as well as the loadGameAssetManager
    @Override
    public void init() throws Exception {
        //useful for loading assets / whatnot before app launches //gamemusic.mid
        mainMenumusic = Applet.newAudioClip(getClass().getResource("./asset/music/gracefully.wav"));
        mainMenumusic.loop();
        loadGameAssetManager = new LoadGameAssetManager(GAME_FILE);
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
        setUpButtonListeners();
    }

    //MODIFIES: stage
    //EFFECTS: sets up the button listeners
    public void setUpButtonListeners() {
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
        mainWindow.setResizable(true);
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
        //System.out.println("CREATING NEW GAME");
        loadGameAssetManager.addAssetsToGame(game);
        characterSetUpGUI = new CharacterSetUpGUI(game, mainWindow);
    }

    //MODIFIES: this
    //EFFECTS: loads game data from GAME_FILE, if the file exists
    // otherwise initializes new GAME with default values
    public void loadGame() {
        try {
            game = loadGameAssetManager.loadGameData();
            inGameMenu = new InGameMenu(game, mainWindow);
        } catch (IOException | ParseException e) {
            createNewGame();
        }
    }
}
