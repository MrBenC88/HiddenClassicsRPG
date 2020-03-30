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

/**
 *  This class represents the CharacterSetUpGUI. A gui targeted towards the initial creation of the character
 *  It allows the user to input a username and select a class. This class also displays all the GUI components
 */

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

    //EFFECTS: constructor which initializes the components of the gui, the images, buttons, etc.
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

    //MODIFIES: stage
    //EFFECTS: sets the current character set up scene as the current displaying scene on the stage
    public void setCurrentScene(Stage stage) {
        stage.setScene(characterSetUpScene);
    }

    //MODIFIES: this
    //EFFECTS: sets up the text fields, combobox,  buttons, and button listeners
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

    //MODIFIES: this
    //EFFECTS: sets up the user info text that user inputs and creates character with specified name and class
    public void setUserSetupInfo() {
        characterName = charName.getText();
        classSelection = classSelect.getValue().toString();
        setUpCharacter(characterName, classSelection);
        switchToInGameMenu();
    }

    //MODIFIES: InGameMenu
    //EFFECTS: constructs a new in game menu object
    public void switchToInGameMenu() {
        inGameMenu = new InGameMenu(game, stage);
    }

    //MODIFIES: game
    //EFFECTS: Takes the user input and determines the appropriate class. Sets up character by changing the
    // character fields
    public void setUpCharacter(String characterName, String classSelection) {
        game.createCharacter(characterName);
        //System.out.println("Your character name has been set to: " + game.getCharacterName());
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
        game.adjustAttributes(game.getCharacter().setCharacterClass(choice));
    }

    //MODIFIES: this
    //EFFECTS: sets up the ComboBox for the class selection and adds to main layout
    public void setUpClassSelectComboBox() {
        classSelect = new ComboBox();
        classSelect.getItems().addAll("Knight", "Mage", "Assassin", "Wanderer");
        StackPane.setMargin(classSelect, new Insets(0,0,0,165));
        mainLayout.getChildren().add(classSelect);
    }
}
