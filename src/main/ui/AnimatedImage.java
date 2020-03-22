package ui;

import javafx.scene.image.Image;

/**
 * A class that represents an animated image.
 * Reference: https://github.com/tutsplus/Introduction-to-JavaFX-for-Game-Development/blob/master/AnimatedImage.java
 */

public class AnimatedImage {
    // assumes animation loops,
    //  each image displays for equal time
    public Image[] frames;
    public double duration;

    //EFFECTS: gets the frame and returns it
    public Image getFrame(double time) {

        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }
}