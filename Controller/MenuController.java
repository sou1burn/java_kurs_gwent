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
    private String selectedDeck1, selectedDeck2;  // Новые переменные для хранения выбранных колод

    public MenuController(MainMenuView mainMenu, Deck nilfs, Deck sever) {
        this.mainMenu = mainMenu;
        this.changeDeckView = new ChangeDeckView();
        this.gameModeSelectionView = new GameModeSelectionView();
        this.deck1 = nilfs;
        this.deck2 = sever;
        this.selectedDeck1 = null;
        this.selectedDeck2 = null;

        // Слушатели для кнопок в главном меню
        mainMenu.addStartListener(e -> openGameModeSelection());
        mainMenu.addChangeDeckListener(e -> openChangeDeckView());
        mainMenu.addHelpListener(e -> showHelp());
        mainMenu.addQuitListener(e -> System.exit(0));
    }

    private void startGame(String mode) {
        // Проверяем, выбрал ли пользователь колоды для обоих игроков
        if (selectedDeck1 == null) {
            JOptionPane.showMessageDialog(mainMenu, "Не выбрана колода для игрока!");
            return;
        }

        gameModeSelectionView.dispose(); // Закрываем окно выбора режима
        gameFieldView = new GameFieldView(); // Создаем окно игрового поля

        // Передаем выбранные колоды в контроллер игры
        Deck playerDeck1 = selectedDeck1.equals("Королевства Севера") ? deck2 : deck1;
        Deck playerDeck2 = selectedDeck2.equals("Нильфгаард") ? deck1 : deck2;
        gameController = new GameController(gameFieldView, mode, playerDeck1, playerDeck2);
        mainMenu.setVisible(false);
    }

    private void openGameModeSelection() {
        gameModeSelectionView.setVisible(true);

        // Добавляем слушателей для выбора режима игры
        gameModeSelectionView.addPlayerVsPlayerListener(e -> startGame("PvP"));
        gameModeSelectionView.addPlayerVsAIListener(e -> startGame("PvAI"));
    }

    private void openChangeDeckView() {
        changeDeckView.setVisible(true);

        // Обработчики для выбора колоды
        changeDeckView.addNorthernKingdomListener(e -> selectDeck("Королевства Севера"));
        changeDeckView.addNilfgaardListener(e -> selectDeck("Нильфгаард"));
    }

    // Метод для выбора колоды для игрока 1
    private void selectDeck(String deckName) {
        // Устанавливаем колоду для игрока 1
        selectedDeck1 = deckName;
        mainMenu.updateCurrentDeck(deckName); // Обновляем текущую колоду для игрока 1

        // Автоматически назначаем колоду для игрока 2
        if (deckName.equals("Королевства Севера")) {
            selectedDeck2 = "Нильфгаард";
        } else {
            selectedDeck2 = "Королевства Севера";
        }

        changeDeckView.setVisible(false); // Закрываем окно выбора колоды
    }

    private void showHelp() {
        new HelpView(); // Открытие окна помощи
    }
}
