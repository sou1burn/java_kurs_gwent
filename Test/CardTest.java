package Test;

import Model.*;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    @Test
    public void testCardProperties() {
        Card card = new Card(5, "range", "Test Card", "src/nilfs/albrix.png");

        assertEquals(5, card.getPower(), "Card power should match the assigned value");
        assertEquals("range", card.getType(), "Card type should match the assigned value");
        assertEquals("Test Card", card.getName(), "Card name should match the assigned value");
    }

    @Test
    public void testRowIndex() {
        Card meleeCard = new Card(5, "melee", "Melee Card", "src/nilfs/albrix.png");
        assertEquals(0, meleeCard.getRowIndex(), "Melee card should have row index 0");

        Card midCard = new Card(5, "mid", "Mid Card", "src/nilfs/albrix.png");
        assertEquals(1, midCard.getRowIndex(), "Mid card should have row index 1");

        Card rangeCard = new Card(5, "range", "Range Card", "src/nilfs/albrix.png");
        assertEquals(2, rangeCard.getRowIndex(), "Range card should have row index 2");
    }

    @Test
    public void testSetters() {
        Card card = new Card(7, "long", "Dandelion", "src/nilfs/albrix.png");

        card.setPower(9);
        card.setType("melee");
        card.setName("Ciri");

        assertEquals(9, card.getPower());
        assertEquals("melee", card.getType());
        assertEquals("Ciri", card.getName());
    }
}
