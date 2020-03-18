package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.GamePanel;

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

    //EFFECTS: constructs the gameGUI with its components
    public GameGUI(GamePanel game, Stage stage, Scene scene) {
        this.game = game;
        this.stage = stage;
        this.inGameMenuScene = scene;
        mainLayout = new StackPane(); // controls layout of how it is displayed -> our root node
        okButton = new Button("Menu");
        StackPane.setMargin(okButton, new Insets(500,0,0,700));

        gameScene = new Scene(mainLayout);
        mainLayout.getChildren().add(okButton);
        okButton.setOnAction(e -> switchToInGameMenu());
    }

    //MODIFIES: stage
    //EFFECTS: sets the stage to the in game menu scene
    public void switchToInGameMenu() {
        stage.setScene(inGameMenuScene);
    }


    //MODIFIES: stage
    //EFFECTS: sets the stage to the current game gui scene
    @Override
    void setCurrentScene() {
        stage.setScene(gameScene);
    }
}
