package Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

public class Deck
{
    private String deckName;
    private List<Card> cards;

    public Deck(String deckName) 
    {
        this.deckName = deckName;
        cards = new ArrayList<>();
    }

    public void addCard(Card card) 
    {
        cards.add(card);
    }

    public boolean removeCard(Card card) 
    {
        return cards.remove(card);
    }

    public void shuffle() 
    {
        Collections.shuffle(cards);
    }

    public Card drawCard() 
    {
        if (!cards.isEmpty()) 
        {
            return cards.remove(cards.size() - 1);
        } 
        else 
        {
            throw new IllegalStateException("Колода пуста!");
        }
    }

    public String getFraction()
    {
        return this.deckName;
    }

    public int size() 
    {
        return cards.size();
    }

    public boolean isEmpty() 
    {
        return cards.isEmpty();
    }

    public List<Card> getCards() 
    {
        return new ArrayList<>(cards);
    }

    public void clearDeck() 
    {
        cards.clear();
    }

    @JsonIgnore
    public Deck resize() {
        for (Card card : cards) {
            ImageIcon originalIcon = new ImageIcon(card.getPath());
            Image scaledImage = originalIcon.getImage().getScaledInstance(100, 110, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);
            card.setImage(resizedIcon);
        }
        return this;
    }
}
