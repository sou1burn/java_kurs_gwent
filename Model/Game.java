package Model;

public class Game {

    private Player player1;
    private Player player2;
    private int round;
    private int player1RoundsWon;
    private int player2RoundsWon;

    public Game(Player player1, Player player2)
    {
        this.player1 = player1;
        this.player2 = player2;
        this.round = 1;
        this.player1RoundsWon = 0;
        this.player2RoundsWon = 0;
    }

    public void nextRound()
    {
        round++;
        player1.clearHand();
        player2.clearHand();
    }

    public int getRound()
    {
        return this.round;
    }

    public Player getPlayer1()
    {
        return player1;
    }

    public Player getPlayer2()
    {
        return player2;
    }

    
}
