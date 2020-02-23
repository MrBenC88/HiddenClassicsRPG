package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameObjectTest {
    private GameObject gameObject;

    @BeforeEach
    void runBefore() {
        gameObject = new GameObject();
    }

    @Test
    void testConstructor() {
        assertEquals(0, gameObject.getTotalUnClaimedGameItem());
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
