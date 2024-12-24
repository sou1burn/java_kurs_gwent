package IO;
import java.util.List;

public class ActionLogger {
    private PlayerState player1;
    private PlayerState player2;


    public ActionLogger(PlayerState player1, PlayerState player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public PlayerState getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerState player1) {
        this.player1 = player1;
    }

    public PlayerState getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerState player2) {
        this.player2 = player2;
    }
}