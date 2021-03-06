package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GamePanel;
import model.TextItem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *  This class represents the character stats gui
 *  It deals with all the functions associated with displaying the character Stats.
 */

public class CharStatGUI extends SceneSettings {
    GamePanel game;
    Stage stage;
    Scene charStatScene;
    StackPane mainLayout;
    Scene inGameMenuScene;
    Button okButton;
    Button refreshButton;
    ImageView charStatScreen;
    Text charStatsText;

    //EFFECTS: Constructor initializes the character stat scene and its associated layouts/buttons/components/images
    public CharStatGUI(GamePanel game, Stage stage, Scene scene) {
        this.game = game;
        this.stage = stage;
        this.inGameMenuScene = scene;
        mainLayout = new StackPane(); // controls layout of how it is displayed -> our root node
        okButton = new Button("Done");
        refreshButton = new Button("Refresh");
        charStatsText = new Text();
        StackPane.setMargin(refreshButton, new Insets(170,0,600,500));
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
        StackPane.setMargin(charStatsText, new Insets(0,0,0,0));

        mainLayout.getChildren().addAll(okButton,charStatsText,refreshButton);
        okButton.setOnAction(e -> switchToInGameMenu());
        refreshButton.setOnAction(e -> refreshData());
        displayStats();
    }

    //MODIFIES: scene
    //EFFECTS: displays the character stats onto the scene
    public void displayStats() {
        charStatsText.setFont(Font.font("Arial", 14));
        String formattedTextItemsStr = "";
        String formattedInventoryItemsString = "";
        for (String t: game.textCollection.getAllTextItemNames()) {
            formattedTextItemsStr += "\n" + t;
        }
        for (String i: game.inventory.getAllInventoryItemGameItemNames()) {
            formattedInventoryItemsString += "\n" + i;
        }

        charStatsText.setText(" Name:\t" + game.character.getName()
                + "\n Balance: \t" + game.character.getBalance()
                + "\n Stats:\t" + game.character.getCharacterAttributes()
                + "\n Starting Class:\t" + game.character.getCharacterClass()
                + "\n\nInventory:\nTotal Inventory Items:\t" + game.inventory.getInventoryTotalItems()
                + "\n Inventory Items:\t" + formattedInventoryItemsString
                + "\n\nText Collection Items:\t" + formattedTextItemsStr
                + "\nTotal Text Items:\t" + game.textCollection.getTextCollectionTotalTexts()
        );
    }

    //MODIFIES: scene
    //EFFECTS: refreshes and reloads the Stats
    public void refreshData() {
        displayStats();
    }

    //MODIFIES: stage
    //EFFECTS: switches the stage scene back to in game menu
    public void switchToInGameMenu() {
        stage.setScene(inGameMenuScene);
    }

    //MODIFIES: stage
    //EFFECTS: sets the character stats scene as the scene shown on the stage
    @Override
    void setCurrentScene() {
        stage.setScene(charStatScene);
    }
}
