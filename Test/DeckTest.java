package Test;
import Model.*;
import org.junit.Test;
import org.junit.Before;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    private Deck deck;

    @Before
    public void setUp() {
        deck = new Deck("Test Deck");
        for (int i = 0; i < 10; i++) {
            deck.addCard(new Card(i + 1, "type", "Card " + (i + 1), "src/nilfs/albrix.png"));
        }
    }

    @Test
    public void testAddCard() {
        Card newCard = new Card(11, "range", "New Card", "src/nilfs/albrix.png");
        deck.addCard(newCard);
        assertEquals(11, deck.size(), "Deck size should increase after adding a card");
    }

    @Test
    public void testRemoveCard() {
        Card card = deck.getCards().get(0);
        boolean removed = deck.removeCard(card);
        assertTrue(removed, "Card should be removed from the deck");
        assertEquals(9, deck.size(), "Deck size should decrease after removing a card");
    }

    @Test
    public void testShuffle() {
        List<Card> beforeShuffle = deck.getCards();
        deck.shuffle();
        List<Card> afterShuffle = deck.getCards();
        assertNotEquals(beforeShuffle, afterShuffle, "Deck should change order after shuffle");
    }

    @Test
    public void testDrawCard() {
        Card drawnCard = deck.drawCard();
        assertEquals(9, deck.size(), "Deck size should decrease after drawing a card");
        assertNotNull(drawnCard, "Drawn card should not be null");
    }

    @Test
    public void testIsEmpty() {
        deck.clearDeck();
        assertTrue(deck.isEmpty(), "Deck should be empty after clearing all cards");
    }
}
