package Controller;

import VIew.*;
import Model.Deck;
import javax.swing.JOptionPane;

public class MenuController {
    private MainMenuView mainMenu;
    private ChangeDeckView changeDeckView;
    private GameModeSelectionView gameModeSelectionView;
    private GameFieldView gameFieldView;
    private GameController gameController;
    private Deck deck1, deck2;
    private String selectedDeck1, selectedDeck2;

    public MenuController(MainMenuView mainMenu, Deck nilfs, Deck sever) {
        this.mainMenu = mainMenu;
        this.changeDeckView = new ChangeDeckView();
        this.gameModeSelectionView = new GameModeSelectionView();
        this.deck1 = nilfs;
        this.deck2 = sever;
        this.selectedDeck1 = null;
        this.selectedDeck2 = null;

        mainMenu.addStartListener(e -> openGameModeSelection());
        mainMenu.addChangeDeckListener(e -> openChangeDeckView());
        mainMenu.addHelpListener(e -> showHelp());
        mainMenu.addQuitListener(e -> System.exit(0));
    }

    private void startGame(String mode) {
        if (selectedDeck1 == null) {
            JOptionPane.showMessageDialog(mainMenu, "Не выбрана колода для игрока!");
            return;
        }

        gameModeSelectionView.dispose();
        gameFieldView = new GameFieldView();

        Deck playerDeck1 = selectedDeck1.equals("Королевства Севера") ? deck2 : deck1;
        Deck playerDeck2 = selectedDeck2.equals("Нильфгаард") ? deck1 : deck2;
        gameController = new GameController(gameFieldView, mode, playerDeck1, playerDeck2);
       // mainMenu.setVisible(false);
    }

    private void openGameModeSelection() {
        gameModeSelectionView.setVisible(true);

        gameModeSelectionView.addPlayerVsPlayerListener(e -> startGame("PvP"));
        gameModeSelectionView.addPlayerVsAIListener(e -> startGame("PvAI"));
    }

    private void openChangeDeckView() {
        changeDeckView.setVisible(true);

        changeDeckView.addNorthernKingdomListener(e -> selectDeck("Королевства Севера"));
        changeDeckView.addNilfgaardListener(e -> selectDeck("Нильфгаард"));
    }

    private void selectDeck(String deckName) {
        selectedDeck1 = deckName;
        mainMenu.updateCurrentDeck(deckName);

        if (deckName.equals("Королевства Севера")) {
            selectedDeck2 = "Нильфгаард";
        } else {
            selectedDeck2 = "Королевства Севера";
        }

        changeDeckView.setVisible(false);
    }

    private void showHelp() {
        new HelpView();
    }
}
