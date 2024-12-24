package VIew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import Model.*;
import java.util.List;
import java.util.Map;


public class GameFieldView extends JFrame {
    private JPanel opponentPanel;
    private JPanel playerPanel;
    private JPanel handPanel;
    private JButton passButton;
    private JButton playCardButton;
    private JButton backButton;
    private JLabel playerDeckCountLabel;
    private JLabel opponentDeckCountLabel;
    private Card selectedCard; 
    private JLabel playerLivesLabel;
    private JLabel opponentLivesLabel;
    private JPanel discardPilePanel;
    private JLabel statusLabel;
    private JPanel scorePanel;
    private JLabel playerScoreLabel;
    private JLabel opponentScoreLabel;
//    private JLabel playerFactionIcon;
//    private JLabel opponentFactionIcon;
    private JLabel playerMeleeScoreLabel;
    private JLabel playerRangedScoreLabel;
    private JLabel playerSiegeScoreLabel;
    private JLabel opponentMeleeScoreLabel;
    private JLabel opponentRangedScoreLabel;
    private JLabel opponentSiegeScoreLabel;

    public GameFieldView() {
        setTitle("Гвинт - Игровое поле");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1700, 900);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        playerMeleeScoreLabel = new JLabel("Очки: 0");
        playerRangedScoreLabel = new JLabel("Очки: 0");
        playerSiegeScoreLabel = new JLabel("Очки: 0");
        opponentMeleeScoreLabel = new JLabel("Очки: 0");
        opponentRangedScoreLabel = new JLabel("Очки: 0");
        opponentSiegeScoreLabel = new JLabel("Очки: 0");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel livesPanel = new JPanel(new GridLayout(1, 2));
        playerLivesLabel = new JLabel("Жизни: 2");
        opponentLivesLabel = new JLabel("Жизни: 2");
        livesPanel.add(playerLivesLabel);
        livesPanel.add(opponentLivesLabel);

        scorePanel = new JPanel(new GridLayout(4,1,5,5));
        scorePanel.setBorder(BorderFactory.createTitledBorder("Очки"));

        playerScoreLabel = new JLabel("Ваши очки: 0");
        opponentScoreLabel = new JLabel("Очки противника: 0");
        scorePanel.add(playerScoreLabel);
        scorePanel.add(opponentScoreLabel);

        gbc.gridy = 5; // Добавляем внизу после кнопок
        add(livesPanel, gbc);
        opponentPanel = createBattleField("Поле противника");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 0.3;
        add(opponentPanel, gbc);

        playerPanel = createBattleField("Ваше поле");
        gbc.gridy = 1;
        gbc.weighty = 0.3;
        add(playerPanel, gbc);

        handPanel = createHandPanel();
        gbc.gridy = 2;
        gbc.weighty = 0.2;
        add(handPanel, gbc);

        JPanel deckPanel = new JPanel();
        deckPanel.setLayout(new GridLayout(1, 2));
        playerDeckCountLabel = new JLabel("Карт в колоде: 10");
        opponentDeckCountLabel = new JLabel("Карт в колоде: 10");
        deckPanel.add(playerDeckCountLabel);
        deckPanel.add(opponentDeckCountLabel);
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        add(deckPanel, gbc);

        passButton = new JButton("Пропустить ход");
        playCardButton = new JButton("Играть карту");
        backButton = new JButton("В главное меню");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(passButton);
        buttonPanel.add(playCardButton);
        buttonPanel.add(backButton);

        gbc.gridy = 4;
        gbc.weighty = 0.1;
        add(buttonPanel, gbc);

        setVisible(true);
    }

    public void updateRowScores(int playerMeleeScore, int playerRangedScore, int playerSiegeScore, int opponentMeleeScore, int opponentRangedScore, int opponentSiegeScore) {
        playerMeleeScoreLabel.setText("Очки: " + playerMeleeScore);
        playerRangedScoreLabel.setText("Очки: " + playerRangedScore);
        playerSiegeScoreLabel.setText("Очки: " + playerSiegeScore);

        opponentMeleeScoreLabel.setText("Очки: " + opponentMeleeScore);
        opponentRangedScoreLabel.setText("Очки: " + opponentRangedScore);
        opponentSiegeScoreLabel.setText("Очки: " + opponentSiegeScore);
    }

    public void moveToDiscardPile(List<Card> cards) {
        for (Card card : cards) {
            ImageIcon originalIcon = new ImageIcon(card.getPath());
            Image scaledImage = originalIcon.getImage().getScaledInstance(100, 110, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);
    
            JLabel cardLabel = new JLabel(resizedIcon);
            cardLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            discardPilePanel.add(cardLabel);
        }
        discardPilePanel.revalidate();
        discardPilePanel.repaint();
    }

    private JPanel createPlayerPanel(String title) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(title));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        panel.add(createCardRow("Рукопашный бой", title.equals("Ваше поле") ? playerMeleeScoreLabel : opponentMeleeScoreLabel), gbc);
        gbc.gridy = 1;
        panel.add(createCardRow("Средний бой", title.equals("Ваше поле") ? playerRangedScoreLabel : opponentRangedScoreLabel), gbc);
        gbc.gridy = 2;
        panel.add(createCardRow("Осада", title.equals("Ваше поле") ? playerSiegeScoreLabel : opponentSiegeScoreLabel), gbc);


        return panel;
    }

    private JPanel createCardRow(String name, JLabel scoreLabel) {
        JPanel rowPanel = new JPanel(new BorderLayout());
        rowPanel.setOpaque(false);
        JLabel rowLabel = new JLabel(name);
        rowPanel.add(rowLabel, BorderLayout.WEST);
    
        JPanel cardSlots = new JPanel(new GridLayout(1, 8, 5, 5));
        cardSlots.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        rowPanel.add(cardSlots, BorderLayout.CENTER);
        rowPanel.add(scoreLabel, BorderLayout.EAST);
        return rowPanel;
    }

    private JPanel createBattleField(String playerName) {
        JPanel battleField = new JPanel(new GridLayout(3, 1, 5, 5));
        battleField.setBorder(BorderFactory.createTitledBorder("Поле игрока: " + playerName));

        battleField.add(createRow("Рукопашный бой"));
        battleField.add(createRow("Средний бой"));
        battleField.add(createRow("Осада"));

        return battleField;
    }

    private JPanel createRow(String name) {
        JPanel rowPanel = new JPanel(new BorderLayout());

        JLabel rowLabel = new JLabel(name);
        rowPanel.add(rowLabel, BorderLayout.WEST);

        JPanel cardSlots = new JPanel(new GridLayout(1, 8, 5, 5));
        cardSlots.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        rowPanel.add(cardSlots, BorderLayout.CENTER);

        JLabel scoreLabel = new JLabel("Очки: 0");
        rowPanel.add(scoreLabel, BorderLayout.EAST);

        return rowPanel;
    }

    private JPanel createHandPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 10, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Ваша рука"));
        return panel;
    }

    public void updateHand(List<Card> hand) {
        handPanel.removeAll();
        for (Card card : hand) {

            ImageIcon originalIcon = new ImageIcon(card.getPath());

            Image scaledImage = originalIcon.getImage().getScaledInstance(100, 110, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);

            JButton cardButton = new JButton(resizedIcon);
            cardButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cardButton.addActionListener(e -> selectedCard = card);
            handPanel.add(cardButton);
        }
        handPanel.revalidate();
        handPanel.repaint();
    }

    public void updateBoard(Player player, Card card) {
        JPanel targetPanel = player.isOpponent() ? opponentPanel : playerPanel;

        int rowIndex = card.getRowIndex();
        JPanel targetRow = (JPanel) ((JPanel) targetPanel.getComponent(rowIndex)).getComponent(1);
    
        if (targetRow.getComponentCount() < 8) {
            ImageIcon originalIcon = new ImageIcon(card.getPath());
            Image scaledImage = originalIcon.getImage().getScaledInstance(100, 110, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);

            JLabel cardLabel = new JLabel(resizedIcon);
            cardLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            targetRow.add(cardLabel);
            targetRow.revalidate();
            targetRow.repaint();
        }
    }
    
    public void clearBoard() {
        for (Component component : opponentPanel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel row = (JPanel) ((JPanel) component).getComponent(1);
                row.removeAll();
                row.revalidate();
                row.repaint();
            }
        }
    
        for (Component component : playerPanel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel row = (JPanel) ((JPanel) component).getComponent(1);
                row.removeAll();
                row.revalidate();
                row.repaint();
            }
        }
    }

    public void startNewRound(List<Card> newHand, List<Card> discard) {
        moveToDiscardPile(discard);

        clearBoard();

        clearHand();

        updateHand(newHand);
    }

    public void setPlayerFractions(String playerFraction, String opponentFraction) {
        playerPanel.setBorder(BorderFactory.createTitledBorder("Ваше поле: " + playerFraction));
        opponentPanel.setBorder(BorderFactory.createTitledBorder("Поле противника: " + opponentFraction));
    }
    public void setPlayerLives(String playerFraction, int playerLives, String opponentFraction, int opponentLives) {
        playerLivesLabel.setText(playerFraction + ": " + playerLives + " жизни ");
        opponentLivesLabel.setText(opponentFraction + ": " + opponentLives + " жизни");
    }

//    private JPanel createSidePanel() {
//        JPanel sidePanel = new JPanel(new GridBagLayout());
//        sidePanel.setPreferredSize(new Dimension(200, 900));
//        sidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        sidePanel.setBackground(new Color(245, 245, 245));
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//
//        JPanel playerLivesPanel = new JPanel(new BorderLayout());
//        playerLivesPanel.setOpaque(false);
//
//        JLabel playerFactionIcon = new JLabel();
//        playerFactionIcon.setHorizontalAlignment(SwingConstants.CENTER);
//        playerLivesPanel.add(playerFactionIcon, BorderLayout.NORTH);
//
//        JLabel playerLivesLabel = new JLabel("Жизни: 2");
//        playerLivesLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        playerLivesPanel.add(playerLivesLabel, BorderLayout.SOUTH);
//        sidePanel.add(playerLivesPanel, gbc);
//
//        gbc.gridy++;
//        JPanel weatherPanel = new JPanel();
//        weatherPanel.setPreferredSize(new Dimension(180, 300));
//        weatherPanel.setBorder(BorderFactory.createTitledBorder("Погодные карты"));
//        weatherPanel.setOpaque(false);
//        sidePanel.add(weatherPanel, gbc);
//
//        gbc.gridy++;
//        JPanel opponentLivesPanel = new JPanel(new BorderLayout());
//        opponentLivesPanel.setOpaque(false);
//
//        JLabel opponentFactionIcon = new JLabel();
//        opponentFactionIcon.setHorizontalAlignment(SwingConstants.CENTER);
//        opponentLivesPanel.add(opponentFactionIcon, BorderLayout.NORTH);
//
//        JLabel opponentLivesLabel = new JLabel("Жизни: 2");
//        opponentLivesLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        opponentLivesPanel.add(opponentLivesLabel, BorderLayout.SOUTH);
//        sidePanel.add(opponentLivesPanel, gbc);
//
//        this.playerLivesLabel = playerLivesLabel;
//        this.opponentLivesLabel = opponentLivesLabel;
//        this.playerFactionIcon = playerFactionIcon;
//        this.opponentFactionIcon = opponentFactionIcon;
//
//        return sidePanel;
//    }

//    public void updateFactions(String playerFactionIconPath, String opponentFactionIconPath) {
//        ImageIcon playerIcon = new ImageIcon(playerFactionIconPath);
//        Image scaledPlayerIcon = playerIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//        playerFactionIcon = new JLabel();
//        playerFactionIcon.setIcon(new ImageIcon(scaledPlayerIcon));
//
//        ImageIcon opponentIcon = new ImageIcon(opponentFactionIconPath);
//        Image scaledOpponentIcon = opponentIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//        opponentFactionIcon = new JLabel();
//        opponentFactionIcon.setIcon(new ImageIcon(scaledOpponentIcon));
//
//        revalidate();
//        repaint();
//    }

    public void clearHand() {
        handPanel.removeAll();
        handPanel.revalidate();
        handPanel.repaint();
    }

    public void updateDeckCount(int playerDeckCount, int opponentDeckCount) {
        playerDeckCountLabel.setText("Ваши карты в колоде: " + playerDeckCount);
        opponentDeckCountLabel.setText("Карты противника в колоде: " + opponentDeckCount);
    }
    
    public void updateLives(int playerLives, int opponentLives) {
        playerLivesLabel.setText("Ваши жизни: " + playerLives);
        opponentLivesLabel.setText("Жизни противника: " + opponentLives);
    }

    public void updateStatus(String message) {
        statusLabel.setText(message);
    }

    public void displayWinner(Player winner) {
        JOptionPane.showMessageDialog(this, "Победитель раунда: " + winner.getFraction());
    }

    public void displayDraw() {
        JOptionPane.showMessageDialog(this, "Ничья!");
    }

    public void displayGameOver(Player winner, Map<Integer, Integer> stats) {
        JOptionPane.showMessageDialog(this, "Игра завершена! Победитель: " + winner.getFraction());
       // WinScreenView win = new WinScreenView(winner.getFraction(), stats);
    }

    public void updatePassStatus(Player currentPlayer) {
        JOptionPane.showMessageDialog(this, currentPlayer.getFraction() + " пропустил ход.");
    }

    public void updateScores(int playerScore, int opponentScore) {
        JOptionPane.showMessageDialog(this, "Очки: Вы - " + playerScore + ", Противник - " + opponentScore);
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void addPlayCardListener(ActionListener listener) {
        playCardButton.addActionListener(listener);
    }

    public void addPassListener(ActionListener listener) {
        passButton.addActionListener(listener);
    }

    public void addBackToMenuListener(ActionListener listener) {
        backButton.addActionListener(e->{
            dispose();
            new MainMenuView();

            if (listener != null) {
                listener.actionPerformed(e);
            }
        });

    }
}
