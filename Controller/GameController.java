package Controller;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.HashMap;
import java.util.Map;
import Model.*;
import VIew.GameFieldView;
import VIew.MainMenuView;
import VIew.WinScreenView;
import IO.*;

public class GameController {
    private GameFieldView gameFieldView;
    private String gameMode;
    private Player p1, p2;
    private Round round;
    private Deck deck1, deck2;

    private int p1RoundScore = 0;
    private int p2RoundScore = 0;
    private int roundCount = 1;
    private Player winner;
    private Map<Integer, Integer>  roundStats;
    private WinScreenView winScreenView;
    private Map<String, ActionLogger> gameLogs;

    public GameController(GameFieldView gameFieldView, String gameMode, Deck deck1, Deck deck2) {
        this.gameFieldView = gameFieldView;
        this.gameMode = gameMode;
        this.roundStats = new HashMap<>();
        this.deck1 = deck1;
        this.deck2 = deck2;
        this.gameLogs = new HashMap<>();
        deck1.shuffle();
        deck2.shuffle();
        deck1.resize();
        deck2.resize();
        //src/assets/nilf.jpg
        //src/assets/sever.png
        gameFieldView.updateFactions("src/assets/nilf.jpg", "src/assets/sever.png");
        gameFieldView.setPlayerFractions(deck1.getFraction(), deck2.getFraction());
        this.p1 = new Player(deck1.getFraction(), deck1.getCards(), false);
        this.p2 = new Player(deck2.getFraction(), deck2.getCards(), true);
        this.round = new Round(p1, p2, roundCount);
        gameFieldView.setPlayerLives(deck1.getFraction(), p1.getHealth(), deck2.getFraction(), p2.getHealth());

        round.start(null);

        gameFieldView.addPassListener(e -> passTurn());
        gameFieldView.addPlayCardListener(e -> playCard());
        gameFieldView.addBackToMenuListener(e -> backToMenu());

        gameFieldView.updateHand(p1.getHand());
        gameFieldView.updateDeckCount(p1.getDeckSize(), p2.getDeckSize());
    }

    private void playCard() {
        Player currentPlayer = round.getCurrentPlayer();
    
        if (currentPlayer.getState()) {
            JOptionPane.showMessageDialog(gameFieldView, "Вы спасовали! Ожидайте завершения раунда.");
            return;
        }
    
        Card playedCard = gameFieldView.getSelectedCard();
    
        if (playedCard != null && currentPlayer.getHand().contains(playedCard)) {
            round.throwCard(currentPlayer, playedCard);
            gameFieldView.updateBoard(currentPlayer, playedCard);

            if (currentPlayer.equals(p1)) {
                p1RoundScore += playedCard.getPower();
            } else {
                p2RoundScore += playedCard.getPower();
            }
            currentPlayer.updateRowScore(playedCard);
            gameFieldView.updateRowScores(p1.getMeleeScore(), p1.getMidScore(), p1.getLongRangeScore(), p2.getMeleeScore(), p2.getMidScore(), p2.getLongRangeScore());
            gameFieldView.updateScores(p1RoundScore, p2RoundScore);

            gameFieldView.updateHand(currentPlayer.getHand());
            logRoundState();

            if (checkRoundEnd()) {
                determineWinner();
            } else if (!round.getOpponent().getState()) {
                round.nextTurn();
                gameFieldView.updateHand(round.getCurrentPlayer().getHand());
            }
        } else {
            JOptionPane.showMessageDialog(gameFieldView,"Выберите доступную карту из руки!");
        }
    }
    

    private void passTurn() {
        Player currentPlayer = round.getCurrentPlayer();
        currentPlayer.setState(true);
        gameFieldView.updatePassStatus(currentPlayer);
        logRoundState();

        if (p1.getState() && p2.getState() || (p1.getHand().isEmpty() && p2.getHand().isEmpty())) {
            determineWinner();
        } else {
            round.nextTurn();
            gameFieldView.updateHand(round.getCurrentPlayer().getHand());
        }
    }
    

    private boolean checkRoundEnd() {
        logRoundState();

        return (p1.getState() && p2.getState()) || (p1.getHand().isEmpty() && p2.getHand().isEmpty());
    }

    private void determineWinner() {

        if (p1RoundScore > p2RoundScore) {
            gameFieldView.displayWinner(p1);
            p2.roundLost();
        } else if (p2RoundScore > p1RoundScore) {
            gameFieldView.displayWinner(p2);
            p1.roundLost();
        } else if (p2RoundScore == p1RoundScore && p1.getFraction().equals("Нильфгаард") && !p2.getFraction().equals("Нильфгаард")){
            p2.roundLost();
            gameFieldView.displayWinner(p1);
        } else if (p2RoundScore == p1RoundScore && !p1.getFraction().equals("Нильфгаард") && p2.getFraction().equals("Нильфгаард")){
            p1.roundLost();
            gameFieldView.displayWinner(p2);
        } else if (p1RoundScore == p2RoundScore && p1.getFraction().equals("Нильфгаард") && p2.getFraction().equals("Нильфгаард")){
            p1.roundLost();
            p2.roundLost();
            gameFieldView.displayDraw();
        }

        this.roundStats.put(p1RoundScore, p2RoundScore);

        p1.addPoints(p1RoundScore);
        p2.addPoints(p2RoundScore);
        logRoundState();

        if (p1.getHealth() <= 0 || p2.getHealth() <= 0) {
            gameOver();
        } else {
            startNewRound();
        }
    }

    private void startNewRound() {
        if (p1.getDeckSize() == 0 || p2.getDeckSize() == 0) {
            gameOver();
            return;
        }

        this.roundCount++;

        Player lastRoundWinner = null;
        if (p1RoundScore > p2RoundScore) {
            lastRoundWinner = p1;
        } else if (p2RoundScore > p1RoundScore) {
            lastRoundWinner = p2;
        }

        p1.clearHand();
        p2.clearHand();

        round = new Round(p1, p2, roundCount);
        round.start(lastRoundWinner);
        gameFieldView.setPlayerLives(deck1.getFraction(), p1.getHealth(), deck2.getFraction(), p2.getHealth());
        gameFieldView.clearBoard();
        gameFieldView.clearHand();
        p1.setState(false);
        p2.setState(false);

        p1.fillHandTo(10);
        p2.fillHandTo(10);

        p1.clearRowsPower();
        p2.clearRowsPower();
        p1RoundScore = 0;
        p2RoundScore = 0;

        gameFieldView.updateRowScores(p1.getMeleeScore(), p1.getMidScore(), p1.getLongRangeScore(), p2.getMeleeScore(), p2.getMidScore(), p2.getLongRangeScore());
        gameFieldView.updateHand(round.getCurrentPlayer().getHand());
        gameFieldView.updateDeckCount(p1.getDeckSize(), p2.getDeckSize());
        gameFieldView.updateScores(p1RoundScore, p2RoundScore);
        gameFieldView.clearBoard();
    }
    

    private void backToMenu() {
        //gameFieldView.dispose();
        gameFieldView.addBackToMenuListener(null);
    }

    private void gameOver() {
        Player winner = p1.getHealth() > 0 ? p1 : p2;
        this.winner = winner;
        gameFieldView.displayGameOver(winner, this.roundStats);
        endGame(winner, this.roundStats);
        SaveLoadManager mng = new SaveLoadManager();
        mng.saveLogsToFile("log.json", gameLogs);
    }

    public void endGame(Player gameWinner, Map<Integer, Integer> gameRoundStats) {

        if (gameFieldView != null) {
            gameFieldView.dispose();
        }

        this.winner = gameWinner;
        this.roundStats = gameRoundStats;
        logRoundState();

        winScreenView = new WinScreenView(winner.getFraction(), roundStats);
        winScreenView.addPlayAgainListener(e -> restartGame());
        winScreenView.addBackToMenuListener(e -> backToMainMenu());
    }

    private void restartGame() {

        if (winScreenView != null) {
            winScreenView.dispose();
        }

        gameFieldView = new GameFieldView();

        new GameController(gameFieldView, gameMode, deck1, deck2);
    }

    private void backToMainMenu() {

        if (winScreenView != null) {
            winScreenView.dispose();
        }

        MainMenuView mainMenuView = new MainMenuView();
        new MenuController(mainMenuView, deck1, deck2);
        mainMenuView.setVisible(true);
    }

    private void logRoundState() {
        ActionLogger log = new ActionLogger(
                new PlayerState(
                        p1,
                        p1.getPlayedCards(),
                        p1RoundScore,
                        p1.getState() ? "passed" : "active"
                ),
                new PlayerState(
                        p2,
                        p2.getPlayedCards(),
                        p2RoundScore,
                        p2.getState() ? "passed" : "active"
                )
        );

        gameLogs.put("round" + roundCount, log);
    }
}

