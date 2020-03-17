package ui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GameItem;
import model.GamePanel;
import model.TextItem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class StoreGUI extends SceneSettings {
    GamePanel game;
    Stage stage;
    Scene storeScene;
    Scene inGameMenuScene;
    StackPane mainLayout;
    Button okButton;
    ImageView storeScreen;

    Button yesButton;
    Button noButton;
    Button submitPurchase;

    Text currentBalance;
    Text responsePrompt;
    boolean paidGold;
    ListView<String> listOfItemAvailable;


    public StoreGUI(GamePanel game, Stage stage, Scene scene) {
        this.game = game;
        this.stage = stage;
        this.inGameMenuScene = scene;
        mainLayout = new StackPane(); // controls layout of how it is displayed -> our root node

        setUpButtons();
        setUpTextListView();

        storeScene = new Scene(mainLayout);
        paidGold = false;
        try {
            Image storeImg = new Image(new FileInputStream("src/main/ui/asset/image/menu/storepage.png"));
            storeScreen = new ImageView(storeImg);
            mainLayout.getChildren().add(storeScreen);
        } catch (FileNotFoundException e) {
            //
        }

        mainLayout.getChildren().addAll(okButton, yesButton, noButton, currentBalance);
        mainLayout.getChildren().addAll(responsePrompt,submitPurchase, listOfItemAvailable);
        setUpEvents();
        setUpKeyEvent();
    }

    public void switchToInGameMenu() {
        stage.setScene(inGameMenuScene);
    }


    @Override
    void setCurrentScene() {
        stage.setScene(storeScene);
    }

    public void setUpButtons() {
        okButton = new Button("Done");
        yesButton = new Button("Yes");
        noButton = new Button("No");
        submitPurchase = new Button("Submit");

        StackPane.setMargin(submitPurchase, new Insets(500,0,0,590));

        StackPane.setMargin(yesButton, new Insets(300,0,600,100));
        StackPane.setMargin(noButton, new Insets(300,0,600,220));
        StackPane.setMargin(okButton, new Insets(500,0,0,700));

    }

    public void setUpTextListView() {
        currentBalance = new Text();
        setTextBalAmount();
        responsePrompt = new Text();
        listOfItemAvailable = new ListView<>();
        StackPane.setMargin(listOfItemAvailable, new Insets(230, 20, 200, 20));
        StackPane.setMargin(responsePrompt, new Insets(370,0,0,0));
        StackPane.setMargin(currentBalance, new Insets(300,0,600,540));

    }

    public void setTextBalAmount() {
        currentBalance.setText("Current Balance:\t" + game.character.getBalance());
    }

    public void setUpEvents() {
        okButton.setOnAction(e -> switchToInGameMenu());
        yesButton.setOnAction(e -> checkPurchase());
        noButton.setOnAction(e -> switchToInGameMenu());
        submitPurchase.setOnAction(e -> confirmPurchase());
    }

    public void checkPurchase() {
        if (game.character.getBalance() < 50) {
            responsePrompt.setText("Sorry, your balance is insufficient!");
            paidGold = false;
        } else {
            responsePrompt.setText("50 gold has been removed from your balance!\n"
                    + "Current Balance:\t" + game.character.getBalance());
            game.character.removeBalance(50);
            paidGold = true;
            setTextBalAmount();
            selectItem();
        }
    }

    public void selectItem() {
        if (paidGold) {
            game.gameObjects.shuffleUnclaimedGameItems();
            listOfItemAvailable.getItems().add(game.getUnclaimedItemName(0));
            listOfItemAvailable.getItems().add(game.getUnclaimedItemName(1));
            listOfItemAvailable.getItems().add(game.getUnclaimedItemName(2));
            listOfItemAvailable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        } else {
            responsePrompt.setText("You haven't paid yet!");
        }
    }

    public void confirmPurchase() {

        if (paidGold) {
            purchaseConditions();
        }
    }

    public void purchaseConditions() {
        if (listOfItemAvailable.getItems().size() == 0) {
            Alert noItems = new Alert(Alert.AlertType.INFORMATION);
            noItems.setHeaderText("No Items Alert");
            noItems.setContentText("You have no items(s) to select!");
            noItems.show();

        } else if (listOfItemAvailable.getSelectionModel().getSelectedItem() == null) {
            Alert noSelectedItem = new Alert(Alert.AlertType.INFORMATION);
            noSelectedItem.setHeaderText("No Item Selected Alert");
            noSelectedItem.setContentText("You have not selected an item from the store!");
            noSelectedItem.show();
        } else if (listOfItemAvailable.getItems().size() >= 1) {
            int itemSelection;
            itemSelection = listOfItemAvailable.getSelectionModel().getSelectedIndex();
            GameItem gameSelected = game.gameObjects.getUnClaimedGameItem(itemSelection);
            game.addGameObjectToInventory(game.gameObjects.getUnClaimedGameItem(itemSelection), 1);
            game.removeGameObject(game.gameObjects.getUnClaimedGameItem(itemSelection));
            responsePrompt.setText("The following has been added to your inventory:\n"
                    + gameSelected.getGameItemName() + "\n"
                    + gameSelected.getDescription() + "\n");
            paidGold = false;
            reloadListView();

        }
    }

    public void reloadListView() {
        ArrayList<String> reloadedList = new ArrayList<>();
        if (paidGold) {
            game.gameObjects.shuffleUnclaimedGameItems();
            reloadedList.add(game.getUnclaimedItemName(0));
            reloadedList.add(game.getUnclaimedItemName(1));

            reloadedList.add(game.getUnclaimedItemName(2));
            listOfItemAvailable.getItems().setAll(reloadedList);
        } else {
            listOfItemAvailable.getItems().setAll(reloadedList);
        }
    }

    public void getSelectedItemDetails() {
        if (paidGold) {
            conditionsForItemDetailsShown();
        }
    }

    public void conditionsForItemDetailsShown() {
        if (listOfItemAvailable.getItems().size() == 0) {
            Alert noItems = new Alert(Alert.AlertType.INFORMATION);
            noItems.setHeaderText("No Items Alert");
            noItems.setContentText("You have no items(s) to select!");
            noItems.show();

        } else if (listOfItemAvailable.getSelectionModel().getSelectedItem() == null) {
            Alert noSelectedItem = new Alert(Alert.AlertType.INFORMATION);
            noSelectedItem.setHeaderText("No Item Selected Alert");
            noSelectedItem.setContentText("You have not selected an item from the store!");
            noSelectedItem.show();
        } else if (listOfItemAvailable.getItems().size() >= 1) {
            int itemSelection;
            itemSelection = listOfItemAvailable.getSelectionModel().getSelectedIndex();
            GameItem gameSelected = game.gameObjects.getUnClaimedGameItem(itemSelection);
            responsePrompt.setText("Item Stats:\n"
                    + gameSelected.getGameItemName() + "\n"
                    + gameSelected.getDescription() + "\n"
                    + gameSelected.getMarketPrice() + "\n"
                    + "Stats\t(HP,ATK,DEF,SPD):\t" + gameSelected.getStats());
        }
    }

    public void setUpKeyEvent() {
        listOfItemAvailable.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    getSelectedItemDetails();
                }
            }
        });
    }
}
