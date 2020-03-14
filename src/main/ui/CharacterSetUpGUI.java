package ui;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.GamePanel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CharacterSetUpGUI  {
    GamePanel game;
    InGameMenu inGameMenu;
    Stage stage;
    Scene characterSetUpScene;
    ImageView charSetUpScreen;
    TextField charName;
    ComboBox classSelect;
    String characterName;
    String classSelection;
    StackPane mainLayout;
    javafx.scene.control.Button submitButton;

    public CharacterSetUpGUI(GamePanel game, Stage stage)  {
        this.game = game;
        this.stage = stage;

        mainLayout = new StackPane(); // controls layout of how it is displayed -> our root node
        submitButton = new Button("Submit");

        StackPane.setMargin(submitButton, new Insets(300,0,0,165));
        characterSetUpScene = new Scene(mainLayout);
        characterSetUpScene.setCursor(Cursor.DEFAULT);
        setCurrentScene(stage);
        try {
            Image mainMenuImage = new Image(new FileInputStream("src/main/ui/asset/image/menu/charcreate.png"));
            charSetUpScreen = new ImageView(mainMenuImage);
            mainLayout.getChildren().add(charSetUpScreen);
        } catch (FileNotFoundException e) {
            //
        }
        mainLayout.getChildren().add(submitButton);
        setUpTextFieldAndButton(game, stage);
    }

    public void setCurrentScene(Stage stage) {
        stage.setScene(characterSetUpScene);
    }

    public void setUpTextFieldAndButton(GamePanel game, Stage stage) {
        charName = new TextField("Name");
        charName.setMinHeight(30);
        charName.setMinWidth(100);
        charName.setPrefHeight(30);
        charName.setPrefWidth(100);
        charName.setMaxWidth(100);
        submitButton.setMinHeight(30);
        submitButton.setMinWidth(100);
        submitButton.setPrefHeight(30);
        submitButton.setPrefWidth(100);
        submitButton.setMaxWidth(100);
        mainLayout.getChildren().add(charName);
        StackPane.setMargin(charName, new Insets(0,0,140,165));

        setUpClassSelectComboBox();

        //Button Listeners
        submitButton.setOnAction(e -> setUserSetupInfo());
    }

    public void setUserSetupInfo() {
        characterName = charName.getText();
        classSelection = classSelect.getValue().toString();
        setUpCharacter(characterName, classSelection);
        switchToInGameMenu();
    }

    public void switchToInGameMenu() {
        inGameMenu = new InGameMenu(game, stage);
    }

    public void setUpCharacter(String characterName, String classSelection) {
        game.createCharacter(characterName);
        System.out.println("Your character name has been set to: " + game.getCharacterName());
        int choice;
        if (classSelection.equals("Knight")) {
            choice  = 1;
        } else if (classSelection.equals("Mage")) {
            choice = 2;
        } else if (classSelection.equals("Assassin")) {
            choice = 3;
        } else {
            choice = 4;
        }
        game.setCharacterClass(choice);
        System.out.println(game.getCharacterChoice());
        game.adjustAttributes(game.getCharacter().setCharacterClass(choice));
        System.out.println(game.getCharacterStats());

    }

    public void setUpClassSelectComboBox() {
        classSelect = new ComboBox();
        classSelect.getItems().addAll("Knight", "Mage", "Assassin", "Wanderer");
        StackPane.setMargin(classSelect, new Insets(0,0,0,165));
        mainLayout.getChildren().add(classSelect);
    }
}
