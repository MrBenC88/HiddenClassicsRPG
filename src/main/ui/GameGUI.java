package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GamePanel;

import java.applet.Applet;

import static ui.MasterFrame.mainMenumusic;

/**
 * A class which represents the GameGUI. It consists of the full game elements.
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


    //EFFECTS: constructs the gameGUI with its components
    public GameGUI(GamePanel game, Stage stage, Scene scene) {
        this.game = game;
        this.stage = stage;
        this.inGameMenuScene = scene;
        mainLayout = new StackPane(); // controls layout of how it is displayed -> our root node
        setUpButtonsAndText();

        gameScene = new Scene(mainLayout);
        mainLayout.getChildren().addAll(okButton, walkButton, gamePrompt,gameResponse, gameResponse2);
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

        gamePrompt.setText("In game! Select Walk To Explore");

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
