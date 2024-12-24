package Model;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Round
{
    private static final int HAND_SIZE = 10;
    private Player player1;
    private Player player2;
    private List<Card> boardCardsPlayer1;
    private List<Card> boardCardsPlayer2;
    private Player currentPlayer;
    private int roundCount;

    public Round(Player p1, Player p2, int count)
    {
        this.player1 = p1;
        this.player2 = p2;
        this.boardCardsPlayer1 = new ArrayList<>();
        this.boardCardsPlayer2 = new ArrayList<>();
        this.roundCount = count;
    }

    public void fillHand(Player p)
    {
        List<Card> deck = p.getDeck();

        Random rand = new Random();

        Collections.shuffle(deck);

        for (int i = 0; i < Math.min(HAND_SIZE, deck.size()); ++i)
        {
            int idx = rand.nextInt(deck.size());
            Card gotten = deck.get(idx);

            p.addCardToHand(gotten);
            deck.remove(idx);
        }     
    }

    public void start(Player previousRoundWinner) {
        fillHand(player1);
        fillHand(player2);

        if (previousRoundWinner == null) {
            currentPlayer = (this.roundCount % 2 == 1) ? player1 : player2;
        } else {
            currentPlayer = previousRoundWinner;
        }

        player1.setOppStatus(currentPlayer == player2);
        player2.setOppStatus(currentPlayer == player1);
    }

    public void throwCard(Player p, Card card)
    {
        if (p.getHand().contains(card))
        {
            p.removeCardFromHand(card);  
            if (p == player1)
            {
                boardCardsPlayer1.add(card);  
            }
            else 
            {
                boardCardsPlayer2.add(card);  
            }
        }
    }

    public Player getWinner(Player p1, Player p2)
    {
        if (p1.getState() && p2.getState())
        {
            int sumP1 = p1.getScore();
            int sumP2 = p2.getScore();

            if (sumP1 > sumP2)
            {
                return p1;
            }

            else if (sumP1 < sumP2)
            {
                return p2;
            }
            else
            {
                String fr1 = p1.getFraction();
                String fr2 = p2.getFraction();
                if (fr1.equals(fr2) && !fr1.equals("Нильфгаард"))
                {
                    p1.roundLost();
                    p2.roundLost();
                    return null; 
                } 
                else if (!fr1.equals("Нильфгаард") && fr2.equals("Нильфгаард"))
                {
                    return p2;
                } 
                else if (fr1.equals("Нильфгаард") && !fr2.equals("Нильфгаард"))
                {
                    return p1;
                } 
                else 
                {
                    p1.roundLost();
                    p2.roundLost();
                    return null;
                }
            }
        }
        return null;
    }

    public void nextTurn() {
        if (player1.getState() && player2.getState()) {
            return;
        }
        if (currentPlayer == player1 && !player2.getState()) {
            currentPlayer = player2;
        } else if (currentPlayer == player2 && !player1.getState()){
            currentPlayer = player1;
        }
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public Player getOpponent()
    {
        return player1.isOpponent() ? player1 : player2;
    }
}
