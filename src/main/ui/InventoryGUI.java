package ui;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.GamePanel;


public class InventoryGUI extends SceneSettings {
    GamePanel game;
    Stage stage;
    Scene inventoryScene;
    StackPane mainLayout;

    public InventoryGUI(GamePanel game, Stage stage) {
        this.game = game;
        this.stage = stage;
        mainLayout = new StackPane(); // controls layout of how it is displayed -> our root node

    }


    @Override
    void setCurrentScene() {
        stage.setScene(inventoryScene);
    }
}
