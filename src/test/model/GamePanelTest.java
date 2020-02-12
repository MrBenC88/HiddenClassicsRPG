package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GamePanelTest {
    GamePanel gamePanel;
    GameItem item1;

    @BeforeEach
    void runBefore() {
        gamePanel = new GamePanel();
        item1 = new GameItem("Test","TestDescription", 1.00,5,5,5,5);
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
        assertEquals("Wanderer",gamePanel.character.getCharacterClass());
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
        assertEquals(3,gamePanel.getNumberOfUnclaimedItems());
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
        assertEquals(4, gamePanel.gameObjects.getTotalUnClaimedGameItem());
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



}
