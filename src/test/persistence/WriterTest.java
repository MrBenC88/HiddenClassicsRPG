package persistence;

import model.GameItem;
import model.GamePanel;
import model.InventoryItem;
import model.TextItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class WriterTest {
    private static final String TEST_FILE = "./data/gamedatatest.json";
    private Writer testWriter;
    private GamePanel game;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        try {
            testWriter = new Writer(new File(TEST_FILE));
            game = new GamePanel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriteGameData() {
        ArrayList<Long>itemStats = new ArrayList<>();
        itemStats.add((long) 1);
        itemStats.add((long) 1);
        itemStats.add((long) 1);
        itemStats.add((long) 1);
        game.inventory.addInventoryItem(new GameItem ("item1",1.0,itemStats,"des"), 1);
        testWriter.save(game);

        assertEquals("", game.character.getName());
        assertEquals("{Speed=0, Health=0, Attack=0, Defense=0}", game.character.getCharacterAttributes().toString());
        assertEquals(null, game.character.getCharacterClass());
        assertEquals(0.0, game.character.getBalance());
        assertEquals("item1", game.inventory.getAllInventoryItemGameItemNames().get(0));
    }

    @Test
    void testWriteGameDataAddingTextCollectionAndNPCs() {
        game.gameObjects.addUnclaimedTextItems();
        game.gameObjects.addNpcs();
        game.textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(0));

        testWriter.save(game);
        assertEquals("Book of Recursion",game.textCollection.getAllTextItemNames().get(0));
        assertEquals("1",game.textCollection.getAllTextItems().get(0).getBookID());
        assertEquals("Trust the natural recursion",game.textCollection.getAllTextItems().get(0).
                getTextItemContent());

        assertEquals("NPC1", game.gameObjects.getNPC(0).getName());
        assertEquals("[Hello, I am NPC 1 !, ???, I don't understand.]", game.gameObjects.
                getNPC(0).getListOfNpcLines().toString());
        assertEquals("{Speed=3, Health=1000, Attack=10000, Defense=2}", game.gameObjects.
                getNPC(0).getNpcStats().toString());
        assertEquals("villager", game.gameObjects.getNPC(0).getTitle());
        assertEquals("enemy", game.gameObjects.getNPC(0).getNpcType());
        assertEquals("down", game.gameObjects.getNPC(0).getDirection());
    }

    @Test
    void testWriteGameDataAddingUnclaimedItems() {
        game.gameObjects.addUnclaimedItems();
        testWriter.save(game);
        assertEquals("Golden Apple", game.gameObjects.getAllUnclaimedGameItems().get(0).getGameItemName());
        assertEquals(10.0, game.gameObjects.getAllUnclaimedGameItems().get(0).getMarketPrice());
        assertEquals("[3, 1, 0, 1]", game.gameObjects.getAllUnclaimedGameItems().get(0).getStats().toString());
        assertEquals("A golden apple which works as a decoration", game.gameObjects.getAllUnclaimedGameItems()
                .get(0).getDescription());
    }
}
