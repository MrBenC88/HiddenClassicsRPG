package ui;

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
import model.TextItem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class CollectionGUI extends SceneSettings {
    GamePanel game;
    Stage stage;
    Scene collectionScene;
    Scene inGameMenuScene;
    StackPane mainLayout;
    Button okButton;
    ImageView collectionScreen;
    ImageView collectionDecorImage;
    Button viewDetailsButton;
    ListView<String> listView;
    Text userInfoAfterItemEvent;


    public CollectionGUI(GamePanel game, Stage stage, Scene scene) {
        this.game = game;
        this.stage = stage;
        this.inGameMenuScene = scene;
        mainLayout = new StackPane(); // controls layout of how it is displayed -> our root node
        userInfoAfterItemEvent = new Text();

        collectionScene = new Scene(mainLayout);

        try {
            Image invImage = new Image(new FileInputStream("src/main/ui/asset/image/menu/collectionpage.png"));
            collectionScreen = new ImageView(invImage);

            Image collectDecorImg  = new Image(new FileInputStream("src/main/ui/asset/image/misc/textImgScaled.png"));
            collectionDecorImage = new ImageView(collectDecorImg);
            mainLayout.getChildren().add(collectionScreen);
        } catch (FileNotFoundException e) {
            //
        }
        setUpListView();
        setUpButtons();
        StackPane.setMargin(collectionDecorImage, new Insets(0,0,50,0));

        StackPane.setMargin(userInfoAfterItemEvent, new Insets(370,0,0,0));
        mainLayout.getChildren().add(collectionDecorImage);
        setUpKeyEvent();

    }


    public void setUpKeyEvent() {
        listView.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    viewTextDetails();
                }
            }
        });
    }



    public void setUpListView() {
        listView = new ListView<>();

        for (TextItem t: game.textCollection.getAllTextItems()) {
            String textName = t.getTextItemName();
            String textID = t.getBookID();
            listView.getItems().add("Book ID:\t" + textID + "\tText Name: \t" + textName);
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        StackPane.setMargin(listView, new Insets(120, 20, 200, 20));
        mainLayout.getChildren().addAll(listView, userInfoAfterItemEvent);
    }

    public void setUpButtons() {
        okButton = new Button("Done");
        StackPane.setMargin(okButton, new Insets(500,0,0,700));

        viewDetailsButton = new Button("View Details");
        StackPane.setMargin(viewDetailsButton, new Insets(170,0,600,250));



        mainLayout.getChildren().addAll(okButton, viewDetailsButton);
        okButton.setOnAction(e -> switchToInGameMenu());
        viewDetailsButton.setOnAction(e -> viewTextDetails());

    }

    public void viewTextDetails() {
        if (game.textCollection.getTextCollectionTotalTexts() == 0) {
            Alert noItems = new Alert(Alert.AlertType.INFORMATION);
            noItems.setHeaderText("No Text Items Alert");
            noItems.setContentText("You have no text(s) in your collections!");
            noItems.show();

        } else if (listView.getSelectionModel().getSelectedItem() == null) {
            Alert noSelectedItem = new Alert(Alert.AlertType.INFORMATION);
            noSelectedItem.setHeaderText("No Text Selected Alert");
            noSelectedItem.setContentText("You have not selected a text in your collections!");
            noSelectedItem.show();
        } else if (game.textCollection.getTextCollectionTotalTexts() >= 1) {
            int textSelection;
            textSelection = listView.getSelectionModel().getSelectedIndex();
            TextItem textSelected = game.textCollection.getAllTextItems().get(textSelection);
            userInfoAfterItemEvent.setText("You have selected the following text: \n"
                    + "Book ID:\t" + textSelected.getBookID() + "\nBook Title:\t" + textSelected.getTextItemName()
                    + "\nContent: \t" + textSelected.getTextItemContent());
        }
    }


    public void switchToInGameMenu() {
        stage.setScene(inGameMenuScene);
    }

    @Override
    void setCurrentScene() {
        stage.setScene(collectionScene);
    }
}
