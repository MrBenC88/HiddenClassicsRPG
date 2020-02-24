package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryTest {
    private Inventory testInventory;
    private Inventory testInventory2;
    private GameItem item1;
    private GameItem item2;
    private GameItem item3;
    private ArrayList<String>itemNames;
    private ArrayList<Double>itemPrices;
    private ArrayList<ArrayList<Long>> itemStats;
    private ArrayList<String> itemDescrip;

    @BeforeEach
    void runBefore() {
        testInventory = new Inventory();
        item1 = new GameItem("1","2", 1.00, 1,1,1,1);
        item2 = new GameItem("2","3", 1.20, 2,2,2,2);
        item3 = new GameItem("3","4", 1.23, 3,3,3,3);
        itemNames = new ArrayList<>();
        itemPrices = new ArrayList<>();
        itemStats = new ArrayList<>();
        itemDescrip = new ArrayList<>();
        itemNames.add("Ben");
        itemPrices.add(1.00);
        itemDescrip.add("Test");
        ArrayList<Long> arrayOfLong = new ArrayList<>();
        arrayOfLong.add((long)1);
        arrayOfLong.add((long)2);
        arrayOfLong.add((long)3);
        arrayOfLong.add((long)4);
        itemStats.add(arrayOfLong);


        testInventory2 = new Inventory(itemNames, itemPrices,itemStats,itemDescrip);
    }

    @Test
    void testConstructor() {
        assertEquals("[]", testInventory.getAllInventoryItems().toString());
        assertEquals(0, testInventory.getInventoryTotalItems());
    }


    @Test
    void testConstructorWithParams() {
        assertEquals("[Ben]", testInventory2.getAllInventoryItemGameItemNames().toString());
        assertEquals(1, testInventory2.getInventoryTotalItems());
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
    void testAddInventoryItemSingle() {
        testInventory.addInventoryItem(item1,10);
        assertEquals(10, testInventory.getInventoryTotalItems());
    }

    @Test
    void testAddInventoryItemMoreThanMaxInventoryItemSlots() {
        testInventory.setMaxItemsPerInventorySlot(1);
        testInventory.setMaxInventoryItemSlots(1);
        testInventory.addInventoryItem(item1,1);
        assertEquals(1, testInventory.getInventoryTotalItems());
        testInventory.addInventoryItem(item1,1);
        assertEquals(2, testInventory.getInventoryTotalItems());
    }

    @Test
    void testAddNotFoundInventoryItem() {
        testInventory.addInventoryItem(item1,1);
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
    void testRemoveInventoryItemQuantityGreaterThanZero() {
        testInventory.addInventoryItem(item1,2);
        testInventory.removeInventoryItem(item1,1);
        assertEquals(1, testInventory.getInventoryTotalItems());
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
