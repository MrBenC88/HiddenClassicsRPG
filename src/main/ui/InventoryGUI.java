package ui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GamePanel;
import model.InventoryItem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The InventoryGUI class represents the inventory user interface that shows all components such as buttons, images,
 * listview, etc.
 * It handles all the user inventory needs and displays the user inventory.
 */

public class InventoryGUI extends SceneSettings {
    GamePanel game;
    Stage stage;
    Scene inventoryScene;
    Scene inGameMenuScene;
    StackPane mainLayout;
    Button okButton;
    ImageView inventoryScreen;
    ImageView noItemImage;
    Button useItemButton;
    Button discardItemButton;
    Button refreshButton;
    Button viewItemDetailsButton;
    ListView<String> listView;
    Text userInfoAfterItemEvent;

    //EFFECTS: constructs the inventoryGUi object and initializes the layout, images, components, etc.
    public InventoryGUI(GamePanel game, Stage stage, Scene scene) {
        this.game = game;
        this.stage = stage;
        this.inGameMenuScene = scene;
        mainLayout = new StackPane(); // controls layout of how it is displayed -> our root node
        userInfoAfterItemEvent = new Text();

        inventoryScene = new Scene(mainLayout);

        try {
            Image invImage = new Image(new FileInputStream("src/main/ui/asset/image/menu/invpage.png"));
            inventoryScreen = new ImageView(invImage);

            Image noItemImg  = new Image(new FileInputStream("src/main/ui/asset/image/misc/noItemScaled.png"));
            noItemImage = new ImageView(noItemImg);
            mainLayout.getChildren().add(inventoryScreen);
        } catch (FileNotFoundException e) {
            //
        }
        setUpListView();
        setUpButtons();
        setUpKeyEvent();
        StackPane.setMargin(noItemImage, new Insets(0,0,22,0));
        mainLayout.getChildren().add(noItemImage);
        StackPane.setMargin(userInfoAfterItemEvent, new Insets(370,0,0,0));
    }

    //EFFECTS: sets up the ListView and adds inventory data
    public void setUpListView() {
        listView = new ListView<>();
        int countItems = 0;
        for (InventoryItem i: game.inventory.getAllInventoryItems()) {
            String gameItemName = i.getGameItem().getGameItemName();
            String gameItemDescription = i.getGameItem().getDescription();
            double gameItemMarketPrice = i.getGameItem().getMarketPrice();
            listView.getItems().add("Item # " + countItems + "  Quantity " + i.getQuantity() + " | "
                    + gameItemName
                    + " | " + "\tDescription: " + gameItemDescription
                    + " |\tItem Price: "
                    +  gameItemMarketPrice);
            countItems++;
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        StackPane.setMargin(listView, new Insets(120, 20, 200, 20));
        mainLayout.getChildren().addAll(listView, userInfoAfterItemEvent);
    }

    //EFFECTS: sets up the buttons, event listeners and its orientation
    public void setUpButtons() {
        okButton = new Button("Done");
        StackPane.setMargin(okButton, new Insets(500,0,0,700));

        useItemButton = new Button("Use Item");
        StackPane.setMargin(useItemButton, new Insets(170,0,600,80));

        discardItemButton = new Button("Discard Item");
        StackPane.setMargin(discardItemButton, new Insets(170,0,600,250));

        viewItemDetailsButton = new Button("View Details");
        StackPane.setMargin(viewItemDetailsButton, new Insets(170,0,600,-90));

        refreshButton = new Button("Refresh");
        StackPane.setMargin(refreshButton, new Insets(170,0,600,-260));

        mainLayout.getChildren().addAll(refreshButton,okButton,useItemButton, discardItemButton, viewItemDetailsButton);
        okButton.setOnAction(e -> switchToInGameMenu());

        refreshButton.setOnAction(e -> reloadListView());
        useItemButton.setOnAction(e -> useGameItem());
        discardItemButton.setOnAction(e -> discardGameItem());
        viewItemDetailsButton.setOnAction(e -> viewItemDetails());

    }

    //EFFECTS: displays alert box if there are no items or if nothing is selected, else shows item details
    public void viewItemDetails() {
        if (game.inventory.getInventoryTotalItems() == 0) {
            Alert noItems = new Alert(Alert.AlertType.INFORMATION);
            noItems.setHeaderText("No Inventory Items Alert");
            noItems.setContentText("You have no items in your inventory!");
            noItems.show();

        } else if (listView.getSelectionModel().getSelectedItem() == null) {
            Alert noSelectedItem = new Alert(Alert.AlertType.INFORMATION);
            noSelectedItem.setHeaderText("No Item Selected Alert");
            noSelectedItem.setContentText("You have not selected an item in your inventory!");
            noSelectedItem.show();
        } else if (game.inventory.getInventoryTotalItems() >= 1) {
            int userSelection;
            userSelection = listView.getSelectionModel().getSelectedIndex();
            InventoryItem selectedItem = game.getInventoryItemObject(userSelection);

            userInfoAfterItemEvent.setText("Item Details: \n"
                    + game.getGameItemNameAndDescription(selectedItem.getGameItem())
                    + "\n\nYour current stats: \n" + game.character.getCharacterAttributes()
                    + "\n\nCurrent Balance: \t" + game.character.getBalance());
        }
    }

    //MODIFIES: game
    //EFFECTS: displays alert box if there are no items or if nothing is selected, else uses selected item
    public void useGameItem() {
        if (game.inventory.getInventoryTotalItems() == 0) {
            Alert noItems = new Alert(Alert.AlertType.INFORMATION);
            noItems.setHeaderText("No Inventory Items Alert");
            noItems.setContentText("You have no items in your inventory!");
            noItems.show();

        } else if (listView.getSelectionModel().getSelectedItem() == null) {
            Alert noSelectedItem = new Alert(Alert.AlertType.INFORMATION);
            noSelectedItem.setHeaderText("No Item Selected Alert");
            noSelectedItem.setContentText("You have not selected an item in your inventory!");
            noSelectedItem.show();
        } else if (game.inventory.getInventoryTotalItems() >= 1) {
            int userSelection;
            userSelection = listView.getSelectionModel().getSelectedIndex();
            InventoryItem selectedItem = game.getInventoryItemObject(userSelection);
            game.useGameItem(selectedItem.getGameItem());
            userInfoAfterItemEvent.setText("You have used the following item: \n"
                    + game.getGameItemNameAndDescription(selectedItem.getGameItem())
                    + "\n\nYour current stats: \n" + game.character.getCharacterAttributes()
                    + "\n\nCurrent Balance: \t" + game.character.getBalance());
            reloadListView();

        }
    }

    //MODIFIES: this
    //EFFECTS: Overrides an KeyEvent handle method to respond to pressing the "ENTER" key after selecting an item in
    // the list view
    public void setUpKeyEvent() {
        listView.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    viewItemDetails();
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: refreshes the ListView load updated inventory items
    public void reloadListView() {
        ArrayList<String> reloadedList = new ArrayList<>();
        int countItems = 0;
        for (InventoryItem i: game.inventory.getAllInventoryItems()) {
            String gameItemName = i.getGameItem().getGameItemName();
            String gameItemDescription = i.getGameItem().getDescription();
            double gameItemMarketPrice = i.getGameItem().getMarketPrice();
            reloadedList.add("Item # " + countItems + "  Quantity " + i.getQuantity() + " | "
                    + gameItemName
                    + " | " + "\tDescription: " + gameItemDescription
                    + " |\tItem Price: "
                    +  gameItemMarketPrice);
            countItems++;
        }
        listView.getItems().setAll(reloadedList);
    }

    //MODIFIES: game
    //EFFECTS: discards game item depending on conditions
    public void discardGameItem() {
        discardGameItemConditions();
    }

    //MODIFIES: game
    //EFFECTS: displays alert box if there are no items or if nothing is selected, else discards selected item
    public void discardGameItemConditions() {
        if (game.inventory.getInventoryTotalItems() == 0) {
            Alert noItems = new Alert(Alert.AlertType.INFORMATION);
            noItems.setHeaderText("No Inventory Items Alert");
            noItems.setContentText("You have no items in your inventory!");
            noItems.show();
        }  else if (listView.getSelectionModel().getSelectedItem() == null) {
            Alert noSelectedItem = new Alert(Alert.AlertType.INFORMATION);
            noSelectedItem.setHeaderText("No Item Selected Alert");
            noSelectedItem.setContentText("You have not selected an item in your inventory!");
            noSelectedItem.show();
        } else if (game.inventory.getInventoryTotalItems() >= 1) {
            int userSelection;
            userSelection = listView.getSelectionModel().getSelectedIndex();
            InventoryItem selectedItem = game.getInventoryItemObject(userSelection);
            game.removeGameObjectInInventory(selectedItem.getGameItem(), 1);
            game.character.addBalance(25);
            String discardedItemName = game.getGameItemName(selectedItem.getGameItem());
            userInfoAfterItemEvent.setText("You have discarded: 1 x\t" + discardedItemName
                    + "\nYou have collected 25 gold which has been added to your balance."
                    + "\n Current Balance \t" + game.character.getBalance());
            reloadListView();
        }
    }

    //MODIFIES: stage
    //EFFECTS: sets the stage to the in game menu scene
    public void switchToInGameMenu() {
        stage.setScene(inGameMenuScene);
    }

    //MODIFIES: stage
    //EFFECTS: sets the stage to the current inventory scene
    @Override
    void setCurrentScene() {
        stage.setScene(inventoryScene);
    }
}
