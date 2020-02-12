package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UserCharacterTest {
    private UserCharacter testCharacter;

    @BeforeEach
    void runBefore() {
        testCharacter = new UserCharacter();
    }

    @Test
    void testConstructor() {
        assertEquals(0.00, testCharacter.getBalance());
        assertEquals("", testCharacter.getName());
        assertEquals(4,testCharacter.getCharacterAttributes().size());
    }

    @Test
    void testSetCharacterClass() {
        testCharacter.setCharacterClass(1);
        assertEquals("Knight", testCharacter.getCharacterClass());
        testCharacter.setCharacterClass(2);
        assertEquals("Mage", testCharacter.getCharacterClass());
        testCharacter.setCharacterClass(3);
        assertEquals("Assassin", testCharacter.getCharacterClass());
        testCharacter.setCharacterClass(4);
        assertEquals("Wanderer", testCharacter.getCharacterClass());
    }

    @Test
    void testSetStats() {
        testCharacter.setAttack(1);
        testCharacter.setHealth(1);
        testCharacter.setSpeed(1);
        testCharacter.setDef(1);
        assertEquals("{Speed=1, Health=1, Attack=1, Defense=1}",
                testCharacter.getCharacterAttributes().toString());
    }

    @Test
    void testAddStats() {
        testSetStats();
        testCharacter.addSpd(1);
        testCharacter.addBalance(1);
        testCharacter.addAttack(1);
        testCharacter.addHealth(1);
        testCharacter.addDef(1);
        assertEquals("{Speed=2, Health=2, Attack=2, Defense=2}",
                testCharacter.getCharacterAttributes().toString());
        assertEquals(1, testCharacter.getBalance());
    }

    @Test
    void testRemoveStats() {
        testAddStats();
        testCharacter.removeSpd(1);
        testCharacter.removeBalance(1);
        testCharacter.removeAtk(1);
        testCharacter.removeHealth(1);
        testCharacter.removeDef(1);
        assertEquals("{Speed=1, Health=1, Attack=1, Defense=1}",
                testCharacter.getCharacterAttributes().toString());
        assertEquals(0, testCharacter.getBalance());
    }

    @Test
    void testRemoveStatsNegativeInput() {
        testAddStats();
        testCharacter.removeSpd(10);
        testCharacter.removeBalance(10);
        testCharacter.removeAtk(10);
        testCharacter.removeHealth(10);
        testCharacter.removeDef(10);
        assertEquals("{Speed=0, Health=0, Attack=0, Defense=0}",
                testCharacter.getCharacterAttributes().toString());
        assertEquals(0, testCharacter.getBalance());
    }

    @Test
    void testSetName() {
        testCharacter.setName("Ben");
        assertEquals("Ben", testCharacter.getName());
    }




}
