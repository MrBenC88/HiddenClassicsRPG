package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryTest {
    private Inventory testInventory;
    private GameItem item1;
    private GameItem item2;
    private GameItem item3;

    @BeforeEach
    void runBefore() {
        testInventory = new Inventory();
        item1 = new GameItem("1","2", 1.00, 1,1,1,1);
        item2 = new GameItem("2","3", 1.20, 2,2,2,2);
        item3 = new GameItem("3","4", 1.23, 3,3,3,3);
    }

    @Test
    void testConstructor() {
        assertEquals("[]", testInventory.getAllInventoryItems().toString());
        assertEquals(0, testInventory.getInventoryTotalItems());
    }

    @Test
    void testAddInventoryItem() {
        testInventory.addInventoryItem(item1, 1);
        testInventory.addInventoryItem(item2, 2);
        testInventory.addInventoryItem(item3, 3);
        assertEquals(6, testInventory.getInventoryTotalItems());
        assertEquals("[1, 2, 3]", testInventory.getAllInventoryItemGameItemNames().toString());
        testInventory.addInventoryItem(item1, 9);
        assertEquals(15,testInventory.getInventoryTotalItems());
        testInventory.addInventoryItem(item1, 1);
        assertEquals(16,testInventory.getInventoryTotalItems());
        assertEquals(3,testInventory.getTotalNumberOfSpecificGameItem(item3));
    }

    @Test
    void testRemoveInventoryItem() {
        testAddInventoryItem();
        testInventory.removeInventoryItem(item1, 1);
        assertEquals(15,testInventory.getInventoryTotalItems());
        testInventory.removeInventoryItem(item1, 9);
        assertEquals(6,testInventory.getInventoryTotalItems());
        testInventory.removeInventoryItem(item1, 1);
        assertEquals( 0,testInventory.getTotalNumberOfSpecificGameItem(item1));
    }

    @Test
    void getGameItemData() {
        testInventory.addInventoryItem(item1,1);
        assertEquals(1.00, testInventory.getGameItemPrice(item1));
        assertEquals("2", testInventory.getGameItemDescription(item1));
        assertEquals("[1, 1, 1, 1]", testInventory.getGameItemStats(item1).toString());
        assertEquals("1",testInventory.getGameItemName(item1));
    }

    @Test
    void testSetInventoryConstants() {
        testInventory.setMaxInventoryItemSlots(10);
        testInventory.setMaxItemsPerInventorySlot(10);
        assertEquals(10,testInventory.getMaxItemsPerInventorySlot());
        assertEquals(10,testInventory.getMaxInventoryItemSlots());
    }
}
