package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.soap.Text;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;



public class TextCollectionTest {
    private TextCollection textCollection;
    private ArrayList<TextItem>unclaimedTextItems;
    private GamePanel game;

    @BeforeEach
    void runBefore() {
        game = new GamePanel();
        game.addUnclaimedTexts();
        textCollection = new TextCollection();
        unclaimedTextItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            unclaimedTextItems.add(game.gameObjects.getUnClaimedTextItem(i));
        }
    }

    @Test
    void testConstructor() {
        assertEquals(0, textCollection.getAllTextItems().size());
        assertEquals(0, textCollection.getTextCollectionTotalTexts());
    }

    @Test
    void testAddTextItem() {
        textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(0));
        assertEquals(1, textCollection.getTextCollectionTotalTexts());
        textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(1));
        assertEquals(2, textCollection.getTextCollectionTotalTexts());
        textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(2));
        assertEquals(3, textCollection.getTextCollectionTotalTexts());
        textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(3));
        assertEquals(4, textCollection.getTextCollectionTotalTexts());
        textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(4));
        assertEquals(5, textCollection.getTextCollectionTotalTexts());
        textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(4));
        assertEquals(5, textCollection.getTextCollectionTotalTexts());

    }
}
