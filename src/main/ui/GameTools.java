package ui;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.GamePanel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class represents the Game Tools which complement the game experience and utilized in GameGUI.
 * Specifically, this class handles the camera focused on the player, the game loop, and the player sprites.
 *
 * It also deals with the player's movement position relative to the map
 *
 *  * Reference for GameLoop:
 *  * https://github.com/tutsplus/Introduction-to-JavaFX-for-Game-Development
 */
public class GameTools {
    double charX = (double) 800 / 2;
    double charY = (double) 625 / 2;
    double cameraX = -500;
    double cameraY = -500;
    double movementConstant = 2;
    GraphicsContext gc;

    Image miscIMG;
    AnimatedImage charSprite;
    final long startNanoTime = System.nanoTime();


    private final double walkingSpeed = 0.10;

    //EFFECTS: constructs the GameTools object and loads the images, graphics, sets up sprites .etc
    public GameTools(StackPane mainLayout) {

        Canvas canvas = new Canvas(800, 625); //Instantiate canvas
        gc = canvas.getGraphicsContext2D(); //SeUp Canvas
        mainLayout.getChildren().add(canvas);
        try {
            // load images here- this is the map image
            miscIMG = new Image(new FileInputStream("src/main/ui/asset/image/misc/tile/map2.png"));

        } catch (FileNotFoundException e) {
            //
        }
        setUpSprite("StandFront");
        setUpGameLoop();
    }

    //MODIFIES: this
    //EFFECTS: sets up game loop and corresponding game loop functions and moves map
    //Reference: https://github.com/tutsplus/Introduction-to-JavaFX-for-Game-Development
    public void setUpGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                gc.clearRect(0, 0, 800,625);
                gc.drawImage(miscIMG, cameraX, cameraY);
                gc.drawImage(charSprite.getFrame(t), charX, charY);
                /*System.out.println("==================================================");
                System.out.println("Char Coordinates\t" + charX + ",\t" + charY);
                System.out.println("==================================================");
                System.out.println("Cam Coordinates\t" + cameraX + ",\t" + cameraY);
                System.out.println("==================================================");*/
                conditionsForCamBoundaries();
            }
        }.start();
    }

    //MODIFIES: this
    //EFFECTS: sets up Sprite
    public void setUpSprite(String dir) {
        charSprite = new AnimatedImage();
        String imageSprite = "src/main/ui/asset/image/misc/sprite/sprite";
        if (dir.equals("front")) {
            cameraY -= movementConstant;
            frontSprite(imageSprite);
        } else if (dir.equals("back")) {
            cameraY += movementConstant;
            backSprite(imageSprite);
        } else if (dir.equals("left")) {
            cameraX += movementConstant;
            leftSprite(imageSprite);
        } else if (dir.equals("right")) {
            cameraX -= movementConstant;
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
    //EFFECTS: sets boundaries for camera
    public void conditionsForCamBoundaries() {
        if (cameraX >= 4) {
            cameraX = 4;
        }

        if (cameraY >= 16) {
            cameraY = 16;
        }

        if (cameraX <= -804) {
            cameraX = -804;
        }

        if (cameraY <= -990) {
            cameraY = -990;
        }
    }

}
