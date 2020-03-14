package ui;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.GamePanel;


public class StoreGUI {
    GamePanel game;
    Stage stage;
    Scene storeScene;
    StackPane mainLayout;

    public StoreGUI(GamePanel game, Stage stage) {
        this.game = game;
        this.stage = stage;
        mainLayout = new StackPane(); // controls layout of how it is displayed -> our root node

    }

    public void setCurrentStage() {
        stage.setScene(storeScene);
    }
}
