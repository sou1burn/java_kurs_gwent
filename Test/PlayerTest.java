package Test;

import Model.*;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
        private Player player;
        private List<Card> deck;

        @Before
        public void setUp() {
            deck = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                deck.add(new Card(i + 1, "melee", "Card " + (i + 1), "src/nilfs/albrix.png"));
            }

            player = new Player("North", deck, false);
        }

        @Test
        public void testAddCardToHand() {
            Card card = new Card(5, "range", "Card 5", "src/nilfs/albrix.png");
            player.addCardToHand(card);
            assertTrue(player.getHand().contains(card), "Card should be added to the hand");
        }

        @Test
        public void testRemoveCardFromHand() {
            Card card = new Card(5, "range", "Card 5", "src/nilfs/albrix.png");
            player.addCardToHand(card);
            player.removeCardFromHand(card);
            assertFalse(player.getHand().contains(card), "Card should be removed from the hand");
        }

        @Test
        public void testCalculateScore() {
            player.addPoints(10);
            player.addPoints(5);
            assertEquals(15, player.getScore(), "Player score should be the sum of added points");
        }

        @Test
        public void testHealthDecreaseOnRoundLost() {
            player.roundLost();
            assertEquals(1, player.getHealth(), "Player's health should decrease after losing a round");
        }

        @Test
        public void testFillHandTo() {
            player.fillHandTo(10);
            assertEquals(10, player.getHand().size(), "Hand should be filled up to 10 cards");
            assertEquals(5, player.getDeckSize(), "Deck size should decrease when filling hand");
        }

        @Test
        public void testUpdateRowScore() {
            List<Card> deck = new ArrayList<>();
            Player player = new Player("Northern Realms", deck, false);

            Card meleeCard = new Card(10, "melee", "Geralt", "src/nilfs/geralt.png");
            Card midCard = new Card(5, "mid", "Mage", "src/nilfs/albrix.png");

            player.updateRowScore(meleeCard);
            player.updateRowScore(midCard);

            assertEquals(10, player.getMeleeScore());
            assertEquals(5, player.getMidScore());
            assertEquals(15, player.getScore());
        }
        @Test
        public void testRoundLostAndHealth() {
            List<Card> deck = new ArrayList<>();
            Player player = new Player("Monsters", deck, true);

            player.roundLost();
            assertEquals(1, player.getHealth());

            player.roundLost();
            assertEquals(0, player.getHealth());
        }

        @Test
        public void testAddAndRemoveCardFromHand() {
            List<Card> deck = new ArrayList<>();
            Player player = new Player("Scoia'tael", deck, false);

            Card card = new Card(5, "melee", "Geralt", "src/nilfs/albrix.png");
            player.addCardToHand(card);

            assertEquals(1, player.getHand().size());
            assertEquals(card, player.getHand().get(0));

            player.removeCardFromHand(card);
            assertTrue(player.getHand().isEmpty());
    }
}
