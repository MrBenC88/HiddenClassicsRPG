package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.GamePanel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class CharStatGUI extends SceneSettings {
    GamePanel game;
    Stage stage;
    Scene charStatScene;
    StackPane mainLayout;
    Scene inGameMenuScene;
    Button okButton;
    ImageView charStatScreen;

    public CharStatGUI(GamePanel game, Stage stage, Scene scene) {
        this.game = game;
        this.stage = stage;
        this.inGameMenuScene = scene;
        mainLayout = new StackPane(); // controls layout of how it is displayed -> our root node
        okButton = new Button("Done");
        StackPane.setMargin(okButton, new Insets(500,0,0,700));

        charStatScene = new Scene(mainLayout);

        try {
            Image charStatImg = new Image(
                    new FileInputStream("src/main/ui/asset/image/menu/charstatpage.png"));
            charStatScreen = new ImageView(charStatImg);
            mainLayout.getChildren().add(charStatScreen);
        } catch (FileNotFoundException e) {
            //
        }

        mainLayout.getChildren().add(okButton);
        okButton.setOnAction(e -> switchToInGameMenu());
    }


    public void switchToInGameMenu() {
        stage.setScene(inGameMenuScene);
    }

    @Override
    void setCurrentScene() {
        stage.setScene(charStatScene);
    }
}
