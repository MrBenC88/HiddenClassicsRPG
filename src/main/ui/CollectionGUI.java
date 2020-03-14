package ui;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.GamePanel;


public class CollectionGUI {
    GamePanel game;
    Stage stage;
    Scene collectionScene;
    StackPane mainLayout;

    public CollectionGUI(GamePanel game, Stage stage) {
        this.game = game;
        this.stage = stage;
        mainLayout = new StackPane(); // controls layout of how it is displayed -> our root node

    }

    public void setCurrentStage() {
        stage.setScene(collectionScene);
    }
}
