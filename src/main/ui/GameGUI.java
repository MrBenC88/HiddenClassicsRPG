package ui;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GamePanel;

import java.applet.Applet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static ui.MasterFrame.mainMenumusic;

/**
 * A class which represents the GameGUI. It consists of the full game elements.
 *
 * Reference for GameLoop:
 * https://github.com/tutsplus/Introduction-to-JavaFX-for-Game-Development
 */

public class GameGUI extends SceneSettings {
    GamePanel game;
    Stage stage;
    Scene gameScene;
    Scene inGameMenuScene;
    StackPane mainLayout;
    Button okButton;
    Button walkButton;
    Text gamePrompt;
    Text gameResponse;
    Text gameResponse2;
    //Image miscIMG;
    GraphicsContext gc;
    AnimatedImage charSprite;
    final long startNanoTime = System.nanoTime();
    private final double walkingSpeed = 0.120;

    //EFFECTS: constructs the gameGUI with its components
    public GameGUI(GamePanel game, Stage stage, Scene scene) {
        this.game = game;
        this.stage = stage;
        this.inGameMenuScene = scene;
        mainLayout = new StackPane(); // controls layout of how it is displayed -> our root node
        setUpButtonsAndText();

        gameScene = new Scene(mainLayout);

        setUpKeyEvent();
        setUpButtonEventListeners();

        Canvas canvas = new Canvas(800, 625); //Instantiate canvas
        mainLayout.getChildren().add(canvas);
        mainLayout.getChildren().addAll(okButton);
        mainLayout.getChildren().addAll(walkButton, gamePrompt,gameResponse, gameResponse2);

        gc = canvas.getGraphicsContext2D(); //SeUp Canvas

        /*
        try {
            // load images here
            // miscIMG = new Image(new FileInputStream("src/main/ui/asset/image/misc/miscimg.png"));

        } catch (FileNotFoundException e) {
            //
        }*/
        setUpSprite("StandBack");
        setUpGameLoop();
        stage.show();
    }

    //MODIFIES: this
    //EFFECTS: sets up Sprite
    public void setUpSprite(String dir) {
        charSprite = new AnimatedImage();
        String imageSprite = "src/main/ui/asset/image/misc/sprite/sprite";

        if (dir.equals("front")) {
            frontSprite(imageSprite);
        } else if (dir.equals("back")) {
            backSprite(imageSprite);
        } else if (dir.equals("left")) {
            leftSprite(imageSprite);
        } else if (dir.equals("right")) {
            rightSprite(imageSprite);
        } else if (dir.equals("StandFront")) {
            standSprites(imageSprite, 1);
        } else if (dir.equals("StandLeft")) {
            standSprites(imageSprite, 4);
        } else if (dir.equals("StandRight")) {
            standSprites(imageSprite, 10);
        } else { // for StandBack
            standSprites(imageSprite, 7);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads sprite images
    public void standSprites(String imageSprite, int i) {
        Image[] imageArray = new Image[1];
        try {
            imageArray[0] = new Image(new FileInputStream(imageSprite + i + ".png"));
            charSprite.frames = imageArray;
        } catch (FileNotFoundException e) {
            //
        }
    }

    //MODIFIES: this
    //EFFECTS: loads sprite images
    public void backSprite(String imageSprite) {
        Image[] imageArray = new Image[3];
        int count = 6;
        for (int i = 0; i < 3; i++) {

            try {
                imageArray[i] = new Image(new FileInputStream(imageSprite + count + ".png"));
                charSprite.frames = imageArray;
                charSprite.duration = walkingSpeed;
                count++;
                if (count == 9) {
                    count = 6;
                }
            } catch (FileNotFoundException e) {
                //
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: loads sprite images
    public void leftSprite(String imageSprite) {
        Image[] imageArray = new Image[3];
        int count = 3;
        for (int i = 0; i < 3; i++) {

            try {
                imageArray[i] = new Image(new FileInputStream(imageSprite + count + ".png"));
                charSprite.frames = imageArray;
                charSprite.duration = walkingSpeed;
                count++;
                if (count == 6) {
                    count = 3;
                }
            } catch (FileNotFoundException e) {
                //
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: loads sprite images
    public void rightSprite(String imageSprite) {
        Image[] imageArray = new Image[3];
        int count = 9;
        for (int i = 0; i < 3; i++) {

            try {
                imageArray[i] = new Image(new FileInputStream(imageSprite + count + ".png"));
                charSprite.frames = imageArray;
                charSprite.duration = walkingSpeed;
                count++;
                if (count == 12) {
                    count = 9;
                }
            } catch (FileNotFoundException e) {
                //
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: loads sprite images
    public void frontSprite(String imageSprite) {
        Image[] imageArray = new Image[3];
        for (int i = 0; i < 3; i++) {
            try {
                imageArray[i] = new Image(new FileInputStream(imageSprite + i + ".png"));
                charSprite.frames = imageArray;
                charSprite.duration = walkingSpeed;
            } catch (FileNotFoundException e) {
                //
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: sets up game loop and corresponding game loop functions
    //Reference: https://github.com/tutsplus/Introduction-to-JavaFX-for-Game-Development
    public void setUpGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);
                gc.clearRect(0, 0, 800,625);
                gc.drawImage(charSprite.getFrame(t), 400, 312.5);
            }
        }.start();
    }

    //MODIFIES: this
    //EFFECTS: Overrides an KeyEvent handle method to respond to pressing the WASD keys after selecting an item in
    // the list view
    public void setUpKeyEvent() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyEventConditionsCharSprite(event, "left", "back", "right", "front");
            }
        });
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyEventConditionsCharSprite(event, "StandLeft", "StandBack", "StandRight",
                        "StandFront");
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: Helper function to check for conditions of key events for WASD key based on key handler
    public void keyEventConditionsCharSprite(KeyEvent event, String left, String back, String right, String front) {
        if (event.getCode().equals(KeyCode.A)) {
            setUpSprite(left);
        } else if (event.getCode().equals(KeyCode.W)) {
            setUpSprite(back);
        } else if (event.getCode().equals(KeyCode.D)) {
            setUpSprite(right);
        } else if (event.getCode().equals(KeyCode.S)) {
            setUpSprite(front);
        }
    }

    //MODIFIES: okButton, walkButton
    //EFFECTS: sets up button event listeners
    public void setUpButtonEventListeners() {
        okButton.setOnAction(e -> switchToInGameMenu());
        walkButton.setOnAction(e -> walkInGame());
    }


    //MODIFIES: stage
    //EFFECTS: sets the stage to the in game menu scene
    public void switchToInGameMenu() {
        mainMenumusic.stop();
        mainMenumusic = Applet.newAudioClip(getClass().getResource("./asset/music/ingamemenumusic.wav"));
        mainMenumusic.loop();
        stage.setScene(inGameMenuScene);
    }

    //MODIFIES: game.character
    //EFFECTS: sets up a battle encounter with NPC and displays results
    // if the winner is npc, the player loses 50 gold, else the player earns 200
    public void walkInGame() {
        gameResponse.setText("You have chosen to walk\n\n"
                + "You have encountered\t " + game.gameObjects.getNPC(0).getName());
        String winner;
        winner = game.order(game.gameObjects.getNPC(0));
        if (winner.equals("npc")) {
            game.character.removeBalance(50);
            gameResponse2.setText("The winner of the encounter is:\t"
                    + game.gameObjects.getNPC(0).getName()
                    + "\tYour new balance is\t\t" + game.character.getBalance());
            game.character.removeBalance(10);
        } else if (winner.equals("player")) {
            game.character.addBalance(200);
            gameResponse2.setText("You have won! \t Your new balance is\t\t" + game.character.getBalance());
        }
    }

    //MODIFIES: this
    //EFFECTS: sets up the buttons and text, the orientation and instantiation
    public void setUpButtonsAndText() {
        okButton = new Button("Menu");
        walkButton = new Button("Walk");
        gamePrompt = new Text();
        gameResponse = new Text();
        gameResponse2 = new Text();

        gamePrompt.setText("In game! Select Walk To Explore. WASD Keys to move character.");

        StackPane.setMargin(walkButton, new Insets(500,0,600,100));
        StackPane.setMargin(gamePrompt, new Insets(300,0,600,100));
        StackPane.setMargin(gameResponse, new Insets(700,0,600,100));
        StackPane.setMargin(gameResponse2, new Insets(800,0,600,100));
        StackPane.setMargin(okButton, new Insets(500,0,0,700));
    }


    //MODIFIES: stage
    //EFFECTS: sets the stage to the current game gui scene
    @Override
    void setCurrentScene() {
        stage.setScene(gameScene);
    }



}
