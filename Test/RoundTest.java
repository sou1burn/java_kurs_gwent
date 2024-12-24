package Test;

import Model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoundTest {
    private Player player1;
    private Player player2;
    private Round round;

    @Before
    public void setUp() {
        // Настраиваем игроков с тестовыми данными
        List<Card> deck1 = new ArrayList<>();
        List<Card> deck2 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            deck1.add(new Card(i + 1, "melee", "Card " + (i + 1), "src/nilfs/albrix.png"));
            deck2.add(new Card(i + 1, "mid", "Card " + (i + 1), "src/nilfs/albrix.png"));
        }

        player1 = new Player("Fractions1", deck1, false);
        player2 = new Player("Nilfgaard", deck2, true);

        round = new Round(player1, player2, 1);
    }

    @Test
    public void testFillHand() {
        round.fillHand(player1);
        assertEquals(10, player1.getHand().size(), "Player 1 hand should be filled with 10 cards");
        assertEquals(10, player1.getDeckSize(), "Deck size should reduce by 10 after drawing cards");
    }

    @Test
    public void testThrowCard() {
        round.fillHand(player1);
        Card cardToThrow = player1.getHand().get(0);
        round.throwCard(player1, cardToThrow);

        assertFalse(player1.getHand().contains(cardToThrow), "Card should be removed from the player's hand");
    }

    @Test
    public void testStartWithoutPreviousWinner() {
        round.start(null);
        assertNotNull(round.getCurrentPlayer(), "Current player should be set");
    }

    @Test
    public void testGetWinner() {
        player1.setState(true);
        player2.setState(true);
        // Симуляция результатов
        player1.addPoints(15);
        player2.addPoints(10);

        Player winner = round.getWinner(player1, player2);
        assertEquals(player1, winner, "Player 1 should win the round");
    }
}