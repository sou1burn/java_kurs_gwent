package IO;

import Model.Card;
import Model.Player;

import java.util.List;

public class PlayerState {
    private Player player;
    private List<Card> playedCards;
    private int score;
    private String state;

    public PlayerState(Player player, List<Card> playedCards, int score, String state) {
        this.player = player;
        this.playedCards = playedCards;
        this.score = score;
        this.state = state;
    }

    public List<Card> getHand() {
        return player.getHand();
    }

    public void setHand(List<Card> hand) {
        player.setHand(hand);
    }

    public List<Card> getPlayedCards() {
        return playedCards;
    }

    public void setPlayedCards(List<Card> playedCards) {
        this.playedCards = playedCards;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
