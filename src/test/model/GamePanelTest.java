package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GamePanelTest {
    GamePanel gamePanel;
    GamePanel gamePanel2;
    GameItem item1;
    GameObject gameObject;
    ArrayList<String> testStringArray;
    HashMap<String, Long> testCharAttributes;


    @BeforeEach
    void runBefore() {
        testCharAttributes = new HashMap<>();
        testCharAttributes.put("Health",(long) 10);
        testCharAttributes.put("Defense",(long) 4);
        testCharAttributes.put("Attack",(long) 3);
        testCharAttributes.put("Speed", (long) 9);

        gamePanel = new GamePanel();
        testStringArray = new ArrayList<>();
        testStringArray.add("TestString");
        gamePanel2 = new GamePanel("Hi", 1.0, testCharAttributes,"Wanderer",
                new ArrayList<>(),
                new ArrayList<>(),new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(),new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(),new ArrayList<>(), new ArrayList<>(), new ArrayList<>() , testStringArray,
                testStringArray, testStringArray);
        gameObject = new GameObject();
        item1 = new GameItem("Test","TestDescription", 1.00,5,5,5,5);
    }

    //Tests for the second constructor, gamepanel2 and makes sure it the game panel object is constructed properly
    // with test parameters.
    @Test
    void testConstructorForParams() {
        assertTrue(gamePanel2 instanceof GamePanel);
        assertEquals("Hi",gamePanel2.character.getName());
        assertEquals(1.0,gamePanel2.character.getBalance());
        assertEquals("Wanderer",gamePanel2.character.getCharacterClass());
        assertEquals(1,gamePanel2.textCollection.getAllTextItems().size());
        assertEquals("[TestString]", gamePanel2.textCollection.getAllTextItemNames().toString());
        assertEquals("{Speed=9, Health=10, Attack=3, Defense=4}", gamePanel2.character.
                getCharacterAttributes().toString());
    }

    @Test
    void testConstructor() {
        assertEquals("",gamePanel.character.getName());
        assertEquals(0, gamePanel.inventory.getInventoryTotalItems());
        assertEquals(0,gamePanel.gameObjects.getTotalUnClaimedGameItem());
    }

    @Test
    void testAdjustAttributes() {
        gamePanel.adjustAttributes("Knight");
        assertEquals("{Speed=4, Health=200, Attack=12, Defense=30}",gamePanel.character.getCharacterAttributes().toString());
        gamePanel.adjustAttributes("Mage");
        assertEquals("{Speed=6, Health=100, Attack=20, Defense=12}",gamePanel.character.getCharacterAttributes().toString());
        gamePanel.adjustAttributes("Assassin");
        assertEquals("{Speed=8, Health=60, Attack=50, Defense=9}",gamePanel.character.getCharacterAttributes().toString());
        gamePanel.adjustAttributes("Wanderer");
        assertEquals("{Speed=99, Health=99, Attack=99, Defense=99}",gamePanel.character.getCharacterAttributes().toString());
        gamePanel.setCharacterClass(4);
        assertTrue(gamePanel.character.getCharacterClass().equals("Wanderer"));
        assertEquals("Wanderer",gamePanel.character.getCharacterClass());
        gamePanel.setCharacterClass(5);
        assertTrue(gamePanel.character.getCharacterClass().equals("Wanderer"));
        assertEquals("Wanderer",gamePanel.character.getCharacterClass());
    }

    @Test
    void testAddUnclaimedTexts() {
        gamePanel.addUnclaimedTexts();
        assertEquals(5, gamePanel.getUnclaimedTexts().size());
    }

    @Test
    void testShowTextDetails() {
        gamePanel.textCollection.addTextItem(new TextItem("name", "content","1"));
        assertEquals("Text ID: 1|\t\t\t name\n" +
                "\tContent:\tcontent\n\n", gamePanel.showTextDetails());
    }

    @Test
    void testAddNpcs() {
        gamePanel.addNpcs();
        assertEquals(1,gamePanel.getNPCs().size());

    }

    @Test
    void testUseGameItem() {
        gamePanel.useGameItem(item1);
        assertEquals("{Speed=5, Health=5, Attack=5, Defense=5}",gamePanel.character.getCharacterAttributes().toString());
    }

    @Test
    void testCreateCharacter() {
        gamePanel.createCharacter("Ben");
        assertEquals("Ben", gamePanel.getCharacterName());
    }

    @Test
    void testSetCharacterInformation() {
        gamePanel.setCharacterClass(1);
        testCreateCharacter();
        assertEquals("{Speed=0, Health=0, Attack=0, Defense=0}",gamePanel.getCharacterStats());
        assertEquals("Knight",gamePanel.getCharacterChoice());
        assertEquals("Ben",gamePanel.getCharacter().getName());
    }

    @Test
    void testShowInventoryItems() {
        assertEquals("[]",gamePanel.showInventoryItems().toString());
    }

    @Test
    void testAddRemoveGameObjectInInventory() {
        gamePanel.addGameObjectToInventory(item1,1);
        assertEquals(1,gamePanel.inventory.getInventoryTotalItems());
        gamePanel.removeGameObjectInInventory(item1,1);
        assertEquals(0,gamePanel.inventory.getInventoryTotalItems());
    }

    @Test
    void testRemoveGameObject() {
        gamePanel.gameObjects.addUnclaimedItems();
        gamePanel.removeGameObject(gamePanel.gameObjects.getUnClaimedGameItem(0));
        assertEquals(7,gamePanel.getNumberOfUnclaimedItems());
    }

    @Test
    void testShowInventoryDetails() {
        gamePanel.inventory.addInventoryItem(item1, 1);
        assertEquals("Item Number: 0|\t\t\t Test\t\tQuantity: 1\t\tDescription:\tTestDescription\t " +
                "@ $1.0\n",gamePanel.showInventoryItemDetails());
    }

    @Test
    void testAddUnclaimedItem() {
        gamePanel.addUnclaimedItems();
        assertEquals(8, gamePanel.gameObjects.getTotalUnClaimedGameItem());
    }

    @Test
    void testGetUnclaimedItemDetails() {
        gamePanel.addUnclaimedItems();
        assertEquals("Golden Apple", gamePanel.getUnclaimedItemName(0));
        assertEquals("A golden apple which works as a decoration", gamePanel.getUnclaimedItemDescription(0));
    }

    @Test
    void testGetInventoryItemObject() {
        gamePanel.inventory.addInventoryItem(item1,1);
        assertEquals(1,gamePanel.getInventoryItemObject(0).getQuantity());
    }

    @Test
    void testGetGameItemStats() {
        gamePanel.inventory.addInventoryItem(item1,1);
        assertEquals("[5, 5, 5, 5]", gamePanel.getGameItemStats(item1).toString());
    }

    @Test
    void testGetGameItemNameAndDescription() {
        gamePanel.inventory.addInventoryItem(item1,1);
        assertEquals("Name:\tTest\n" +
                "Description:\tTestDescription\n" +
                "Worth:\t1.0", gamePanel.getGameItemNameAndDescription(item1));
        assertEquals("Test", gamePanel.getGameItemName(item1));
    }

    @Test
    void setOrder() {
        ArrayList<String> npcTestLines = new ArrayList<>();
        npcTestLines.add("line1");
        npcTestLines.add("line2");
        HashMap<String, Integer> testNPCStats = new HashMap<>();
        testNPCStats.put("Health",1);
        testNPCStats.put("Attack",2);
        testNPCStats.put("Defense",3);
        testNPCStats.put("Speed",4);
        NPC n = new NPC("Test1","Enemy","Villager", "Down", npcTestLines, testNPCStats);
        assertEquals("player", gamePanel2.order(n));
    }

    @Test
    void testBattle() {
        setOrder();
        HashMap<String, Integer> testNPCStatsStrongerVer = new HashMap<>();
        testNPCStatsStrongerVer.put("Health",1000);
        testNPCStatsStrongerVer.put("Attack",2000);
        testNPCStatsStrongerVer.put("Defense",3);
        testNPCStatsStrongerVer.put("Speed",99);
        NPC t = new NPC("Test1","Enemy","Villager", "Down", new ArrayList<>(), testNPCStatsStrongerVer);
        assertEquals("npc", gamePanel2.order(t));
    }

    @Test
    void testBattlePlayerFirst() {
        ArrayList<String> npcTestLines = new ArrayList<>();
        npcTestLines.add("line1");
        npcTestLines.add("line2");
        HashMap<String, Integer> testNPCStats = new HashMap<>();
        testNPCStats.put("Health",1);
        testNPCStats.put("Attack",2);
        testNPCStats.put("Defense",3);
        testNPCStats.put("Speed",4);
        NPC n = new NPC("Test1","Enemy","Villager", "Down", npcTestLines, testNPCStats);
        assertEquals("player", gamePanel2.battle("playerFirst", testNPCStats));
    }

    @Test
    void testBattleNPCFirst() {
        ArrayList<String> npcTestLines = new ArrayList<>();
        npcTestLines.add("line1");
        npcTestLines.add("line2");
        HashMap<String, Integer> testNPCStats = new HashMap<>();
        testNPCStats.put("Health",999);
        testNPCStats.put("Attack",992);
        testNPCStats.put("Defense",3);
        testNPCStats.put("Speed",994);
        NPC n = new NPC("Test1","Enemy","Villager", "Down", npcTestLines, testNPCStats);
        assertEquals("npc", gamePanel2.battle("npcFirst", testNPCStats));
    }

}
