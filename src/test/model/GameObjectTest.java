package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GameObjectTest {
    private GameObject gameObject;
    private GameObject gameObject2;
    private ArrayList<String> strTests;
    private ArrayList<Double> gameItemDoubleTests;
    private ArrayList<ArrayList<Long>> gameItemStat;
    private ArrayList<ArrayList<String>> npcLines;
    private ArrayList<HashMap<String, Integer>> npcTestStats;

    @BeforeEach
    void runBefore() {
        npcTestStats = new ArrayList<>();
        HashMap<String, Integer> npcStat1 = new HashMap<>();
        npcStat1.put("Health", 10);
        npcStat1.put("Defense", 10);
        npcStat1.put("Attack", 10);
        npcStat1.put("Speed", 10);
        npcTestStats.add(npcStat1);

        strTests = new ArrayList<>();
        gameItemDoubleTests = new ArrayList<>();
        gameItemStat = new ArrayList<>();
        npcLines = new ArrayList<>();
        strTests.add("Test1");
        gameItemDoubleTests.add(1.0);
        ArrayList<Long>testLongArray = new ArrayList<>();
        testLongArray.add((long) 1);
        testLongArray.add((long) 1);
        testLongArray.add((long) 1);
        testLongArray.add((long) 1);
        gameItemStat.add(testLongArray);
        npcLines.add(strTests);
        gameObject = new GameObject();
        gameObject2 = new GameObject(strTests,gameItemDoubleTests,gameItemStat,strTests,
                npcLines,strTests,strTests,strTests,strTests, npcTestStats, strTests,strTests,strTests );
    }

    @Test
    void testConstructor() {
        assertEquals(0, gameObject.getTotalUnClaimedGameItem());
    }

    @Test
    void testConstructorWithParams() {
        assertEquals(0, gameObject.getTotalUnClaimedGameItem());
        assertEquals("Test1",gameObject2.getAllNPCs().get(0).getName());
        assertEquals("Test1",gameObject2.getAllNPCs().get(0).getDirection());
        assertEquals("Test1",gameObject2.getAllNPCs().get(0).getNpcType());
        assertEquals("Test1",gameObject2.getAllNPCs().get(0).getTitle());
        assertEquals("{Speed=10, Health=10, Attack=10, Defense=10}",gameObject2.getAllNPCs().get(0).
                getNpcStats().toString());
        assertEquals("[Test1]",gameObject2.getAllNPCs().get(0).getListOfNpcLines().toString());

        assertEquals("Test1",gameObject2.getAllUnclaimedTextItems().get(0).getTextItemName());
        assertEquals("Test1",gameObject2.getAllUnclaimedTextItems().get(0).getBookID());
        assertEquals("Test1",gameObject2.getAllUnclaimedTextItems().get(0).getTextItemContent());

        assertEquals("Test1",gameObject2.getAllUnclaimedGameItems().get(0).getGameItemName());
        assertEquals("Test1",gameObject2.getAllUnclaimedGameItems().get(0).getDescription());
        assertEquals("[1, 1, 1, 1]",gameObject2.getAllUnclaimedGameItems().get(0).getStats().toString());
        assertEquals(1.0,gameObject2.getAllUnclaimedGameItems().get(0).getMarketPrice());
    }

    @Test
    void testGetters() {
        gameObject.addUnclaimedItems();
        gameObject.addNpcs();
        gameObject.addUnclaimedTextItems();
        for (TextItem i: gameObject.getAllUnclaimedTextItems()) {
            assertEquals(i.getTextItemName(), gameObject.getUnclaimedTextItemName(i));
            assertEquals(i.getBookID(), gameObject.getUnclaimedTextItemBookId(i));
            assertEquals(i.getTextItemContent(), gameObject.
                    getUnclaimedTextItemContent(i));
        }
        for (NPC n: gameObject.getAllNPCs()) {
            assertEquals(n.getName(), gameObject.getNpcName(n));
            assertEquals(n.getNpcStats(), gameObject.getNpcStat(n));
            assertEquals(n.getDirection(), gameObject.getNpcDirection(n));
            assertEquals(n.getListOfNpcLines(), gameObject.getListOfNpcLines(n));
            assertEquals(n.getNpcType(), gameObject.getNpcType(n));
            assertEquals(n.getTitle(), gameObject.getNpcTitle(n));
        }
    }

    @Test
    void testGetNPC() {
        gameObject.addNpcs();
        assertEquals("NPC1",gameObject.getNPC(0).getName());
    }

    @Test
    void testGetUnclaimedTextItem() {
        gameObject.addUnclaimedTextItems();
        assertEquals("Book of Recursion", gameObject.getUnClaimedTextItem(0).getTextItemName());
        assertEquals("Trust the natural recursion", gameObject.getUnClaimedTextItem(0).getTextItemContent());
        assertEquals("1", gameObject.getUnClaimedTextItem(0).getBookID());
    }

    @Test
    void testShuffleUnclaimedGameItems() {
        gameObject.addUnclaimedItems();
        ArrayList<GameItem>listOfUnShuffledUnclaimedGameItems = new ArrayList<>();
        ArrayList<GameItem>listOfShuffledUnclaimedGameItems = new ArrayList<>();
        for (GameItem i: gameObject.getAllUnclaimedGameItems()) {
            listOfUnShuffledUnclaimedGameItems.add(i);
        }
        gameObject.shuffleUnclaimedGameItems();
        for (GameItem i: gameObject.getAllUnclaimedGameItems()) {
            listOfShuffledUnclaimedGameItems.add(i);
        }
        assertFalse(listOfUnShuffledUnclaimedGameItems.equals(listOfShuffledUnclaimedGameItems));
    }

    @Test
    void testAddUnclaimedGameItems() {
        gameObject.addUnclaimedItems();
        assertEquals(8,gameObject.getTotalUnClaimedGameItem());
    }

    @Test
    void testRemoveUnclaimedGameItems() {
        testAddUnclaimedGameItems();
        GameItem item1 = gameObject.getUnClaimedGameItem(1);
        gameObject.removeUnClaimedGameItem(item1);
        assertEquals(7, gameObject.getTotalUnClaimedGameItem());
    }
}
