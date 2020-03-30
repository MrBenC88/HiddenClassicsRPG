package ui;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GamePanel;

import java.applet.Applet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static ui.MasterFrame.mainMenumusic;

/**
 * A class which represents the GameGUI. It consists of the ui elements related to this scene.
 * This includes the stage, game scene, stackpane, buttons, and texts.
 *
 * This class includes button events and the button listeners.
 *
 */

public class GameGUI extends SceneSettings {
    GamePanel game;
    Stage stage;
    Scene gameScene;
    Scene inGameMenuScene;
    StackPane mainLayout;
    Button okButton;
    Button walkButton;
    Text gamePrompt;
    Text gameResponse;
    Text gameResponse2;
    GameTools gameTools;

    //EFFECTS: constructs the gameGUI with its components
    public GameGUI(GamePanel game, Stage stage, Scene scene) {
        this.game = game;
        this.stage = stage;
        this.inGameMenuScene = scene;

        mainLayout = new StackPane(); // controls layout of how it is displayed -> our root node
        gameScene = new Scene(mainLayout);
        gameTools = new GameTools(mainLayout);

        setUpButtonsAndText();
        setUpKeyEvent();
        setUpButtonEventListeners();
        setUpGameTools();

        mainLayout.getChildren().addAll(okButton);
        mainLayout.getChildren().addAll(walkButton, gamePrompt,gameResponse, gameResponse2);

        stage.show();
    }

    //MODIFIES: this
    //EFFECTS: sets up game loop and game tools such as camera, sprites
    public void setUpGameTools() {
        gameTools.setUpGameLoop();
    }

    //MODIFIES: this
    //EFFECTS: Helper function to check for conditions of key events for WASD key based on key handler and the
    // ENTER key if pressed and within the coordinates specified
    public void keyEventConditionsCharSprite(KeyEvent event, String left, String back, String right, String front) {
        boolean inEventTriggerGoal = gameTools.cameraX >= -450.0 && gameTools.cameraX <= -400
                && gameTools.cameraY >= -80 && gameTools.cameraY <= -50;
        if (event.getCode().equals(KeyCode.A)) {
            gameTools.setUpSprite(left);
        } else if (event.getCode().equals(KeyCode.W)) {
            gameTools.setUpSprite(back);
        } else if (event.getCode().equals(KeyCode.D)) {
            gameTools.setUpSprite(right);
        } else if (event.getCode().equals(KeyCode.S)) {
            gameTools.setUpSprite(front);
        } else if (event.getCode().equals(KeyCode.ENTER)) {
            if (inEventTriggerGoal) {
                walkInGame();
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: Overrides an KeyEvent handle method to respond to pressing the WASD keys
    //  if user makes it to the specific coordinate and presses ENTER key, they can initiate an npc battle event
    public void setUpKeyEvent() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyEventConditionsCharSprite(event, "left", "back", "right", "front");
                resetText();
            }
        });
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyEventConditionsCharSprite(event, "StandLeft", "StandBack", "StandRight",
                        "StandFront");
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: resets the text to blank string
    public void resetText() {
        gameResponse.setText("");
        gameResponse2.setText("");
    }

    //MODIFIES: okButton, walkButton
    //EFFECTS: sets up button event listeners
    public void setUpButtonEventListeners() {
        okButton.setOnAction(e -> switchToInGameMenu());
        walkButton.setOnAction(e -> walkInGame());
    }

    //MODIFIES: stage
    //EFFECTS: sets the stage to the in game menu scene
    public void switchToInGameMenu() {
        mainMenumusic.stop();
        mainMenumusic = Applet.newAudioClip(getClass().getResource("./asset/music/ingamemenumusic.wav"));
        mainMenumusic.loop();
        stage.setScene(inGameMenuScene);
    }

    //MODIFIES: game.character
    //EFFECTS: sets up a battle encounter with NPC and displays results
    // if the winner is npc, the player loses 50 gold, else the player earns 200
    public void walkInGame() {
        gameResponse.setText("You have chosen to walk\n\n"
                + "You have encountered\t " + game.gameObjects.getNPC(0).getName());
        String winner;
        winner = game.order(game.gameObjects.getNPC(0));
        if (winner.equals("npc")) {
            game.character.removeBalance(50);
            gameResponse2.setText("The winner of the encounter is:\t"
                    + game.gameObjects.getNPC(0).getName()
                    + "\tYour new balance is\t\t" + game.character.getBalance());
            game.character.removeBalance(10);
        } else if (winner.equals("player")) {
            game.character.addBalance(200);
            gameResponse2.setText("You have won! \t Your new balance is\t\t" + game.character.getBalance());
        }
    }

    //MODIFIES: this
    //EFFECTS: sets up the buttons and text, the orientation and instantiation
    public void setUpButtonsAndText() {
        okButton = new Button("Menu");
        walkButton = new Button("Walk");
        gamePrompt = new Text();
        gameResponse = new Text();
        gameResponse2 = new Text();

        gamePrompt.setText("In game! Select Walk To Explore. WASD Keys to move character.\n"
                + "Walk up to the interesting futuristic machine and tap enter to battle npc.\n"
                + "(It is near the Pokemon center and Pokeball item)");

        StackPane.setMargin(walkButton, new Insets(500,0,600,100));
        StackPane.setMargin(gamePrompt, new Insets(300,0,600,100));
        StackPane.setMargin(gameResponse, new Insets(700,0,600,100));
        StackPane.setMargin(gameResponse2, new Insets(800,0,600,100));
        StackPane.setMargin(okButton, new Insets(500,0,0,700));
    }

    //MODIFIES: stage
    //EFFECTS: sets the stage to the current game gui scene
    @Override
    void setCurrentScene() {
        stage.setScene(gameScene);
    }
}
