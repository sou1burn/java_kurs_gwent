package VIew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class WinScreenView extends JFrame {
    private JButton playAgainButton;
    private JButton backToMenuButton;
    private JLabel winnerLabel;
    private JTextArea roundStatsArea;

    public WinScreenView(String winner, Map<Integer, Integer> roundStats) {
        setTitle("Результаты игры");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        winnerLabel = new JLabel("Победитель: " + winner, SwingConstants.CENTER);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(winnerLabel, BorderLayout.NORTH);

        StringBuilder statsBuilder = new StringBuilder();
        statsBuilder.append("Результаты по раундам:\n");
        int roundNumber = 1;
        for (Map.Entry<Integer, Integer> entry : roundStats.entrySet()) {
            statsBuilder.append("Раунд ").append(roundNumber).append(": ")
                    .append("Игрок 1: ").append(entry.getKey()).append(", ")
                    .append("Игрок 2: ").append(entry.getValue()).append("\n");
            roundNumber++;
        }

        roundStatsArea = new JTextArea(statsBuilder.toString());
        roundStatsArea.setEditable(false);
        roundStatsArea.setLineWrap(true);
        roundStatsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(roundStatsArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        playAgainButton = new JButton("Сыграть еще раз");
        backToMenuButton = new JButton("В главное меню");
        buttonPanel.add(playAgainButton);
        buttonPanel.add(backToMenuButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true); // Отображаем окно
    }

    public void addPlayAgainListener(ActionListener listener) {
        playAgainButton.addActionListener(listener);
    }

    public void addBackToMenuListener(ActionListener listener) {
        backToMenuButton.addActionListener(listener);
    }
}