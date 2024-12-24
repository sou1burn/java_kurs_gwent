package VIew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuView extends JFrame {
    private JButton startButton;
    private JButton changeDeckButton;
    private JButton helpButton;
    private JButton quitButton;
    private JLabel currentDeckLabel;

    public MainMenuView() {
        setTitle("Гвинт - Главное меню");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 строки, 1 столбец

        startButton = new JButton("Начать игру");
        changeDeckButton = new JButton("Сменить колоду");
        helpButton = new JButton("Справка");
        quitButton = new JButton("Выход");

        buttonPanel.add(startButton);
        buttonPanel.add(changeDeckButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(quitButton);

        JPanel deckPanel = new JPanel();
        deckPanel.setLayout(new BorderLayout());
        currentDeckLabel = new JLabel("Текущая колода: Не выбрана", SwingConstants.CENTER);
        currentDeckLabel.setFont(new Font("Arial", Font.BOLD, 16));
        deckPanel.add(currentDeckLabel, BorderLayout.CENTER);

        // Используем JSplitPane для разделения окна
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonPanel, deckPanel);
        splitPane.setDividerLocation(200); 
        splitPane.setEnabled(true);

        add(splitPane, BorderLayout.CENTER);
    }


    public void addStartListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }

    public void addChangeDeckListener(ActionListener listener) {
        changeDeckButton.addActionListener(listener);
    }

    public void addHelpListener(ActionListener listener) {
        helpButton.addActionListener(listener);
    }

    public void addQuitListener(ActionListener listener) {
        quitButton.addActionListener(listener);
    }

    public void updateCurrentDeck(String deckName) {
        currentDeckLabel.setText("Текущая колода: " + deckName);
    }
}
