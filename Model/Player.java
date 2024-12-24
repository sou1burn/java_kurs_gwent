package Model;
import java.util.*;

public class Player {
    private String fraction;
    private int meleeScore;
    private int midScore;
    private int longRangeScore;
    private int totalScore;
    private List<Card> deck;
    private List<Card> hand;
    private List<Card> meleeRow;
    private List<Card> midRangeRow;
    private List<Card> longRangeRow;
    private boolean passed;
    private int health;
    private boolean isOpponent;
    public static final int HAND_SIZE = 10;
    private List<Card> playedCards;

    public Player(String fraction, List<Card> deck, boolean isOpponent) {
        this.fraction = fraction;
        this.deck = deck;
        this.isOpponent = isOpponent;
        this.meleeScore = 0;
        this.midScore = 0;
        this.longRangeScore = 0;
        this.totalScore = 0;
        this.health = 2;
        this.hand = new ArrayList<>();
        this.meleeRow = new ArrayList<>();
        this.midRangeRow = new ArrayList<>();
        this.longRangeRow = new ArrayList<>();
        this.playedCards = new ArrayList<>();
    }

    public boolean isOpponent() {
        return this.isOpponent;
    }

    public void setOppStatus(boolean status){
        this.isOpponent = status;
    }

    public String getFraction() {
        return this.fraction;
    }

    public List<Card> getDeck() {
        return this.deck;
    }

    public List<Card> getHand() {
        return this.hand;
    }

    public int getScore() {
        return this.totalScore;
    }

    public void addPoints(int points) {
         this.totalScore += points;
    }

    public void addCardToHand(Card card) {
        this.hand.add(card);
    }

    public void removeCardFromHand(Card card) {
        playedCards.add(card);
        this.getHand().remove(card);
    }

    public boolean getState() {
        return this.passed;
    }

    public void setState(boolean state) {
        this.passed = state;
    }

    public int getMeleeScore() {
        return this.meleeScore;
    }

    public int getMidScore() {
        return this.midScore;
    }

    public int getLongRangeScore() {
        return this.longRangeScore;
    }

    public int getHealth() {
        return this.health;
    }

    public void roundLost() {
        this.health--;
    }

    public int getDeckSize() {
        return this.deck.size();
    }

    public boolean canPlayCard(Card playedCard) {
        return hand.contains(playedCard);
    }

    public void setHand(List<Card> hand){
        this.hand = hand;
    }

    public List<Card> getPlayedCards() {
        return playedCards;
    }

    public void clearHand() {
        this.deck.addAll(this.hand);
        this.hand.clear();

        Collections.shuffle(this.deck);
    }
    
    public void drawNewHand() {
        for (int i = 0; i < HAND_SIZE; i++) {
            if (!deck.isEmpty()) {
                hand.add(deck.get(i));
            }
        }
    }

    public void fillHandTo(int count) {
        while (this.hand.size() < count && !this.deck.isEmpty()) {
            this.hand.add(drawCardFromDeck());
        }
    }
    

    private Card drawCardFromDeck() {
        if (!deck.isEmpty()) {
            Random random = new Random();
            return deck.remove(random.nextInt(deck.size()));
        } else {
            throw new IllegalStateException("Колода пуста, невозможно взять карту!");
        }
    }

    public void updateRowScore(Card card) {
        switch (card.getRowIndex()) {
            case 0:
                meleeScore += card.getPower();
                break;
            case 1:
                midScore += card.getPower();
                break;
            case 2:
                longRangeScore += card.getPower();
                break;
            default:
                throw new IllegalArgumentException("Некорректный ряд для карты: " + card.getRowIndex());
        }

        totalScore = meleeScore + midScore + longRangeScore;
    }

    public void clearRowsPower(){
        this.meleeScore = 0;
        this.midScore = 0;
        this.longRangeScore = 0;
        this.totalScore = 0;
    }
}
