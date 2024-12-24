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
        setSize(500, 400); // Размер окна
        setLocationRelativeTo(null); // Центрирование окна на экране
        setLayout(new BorderLayout());

        // Создаем верхний блок с результатом
        winnerLabel = new JLabel("Победитель: " + winner, SwingConstants.CENTER);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(winnerLabel, BorderLayout.NORTH);

        // Преобразуем карту статистики в читаемую строку
        StringBuilder statsBuilder = new StringBuilder();
        statsBuilder.append("Результаты по раундам:\n");
        int roundNumber = 1;
        for (Map.Entry<Integer, Integer> entry : roundStats.entrySet()) {
            statsBuilder.append("Раунд ").append(roundNumber).append(": ")
                    .append("Игрок 1: ").append(entry.getKey()).append(", ")
                    .append("Игрок 2: ").append(entry.getValue()).append("\n");
            roundNumber++;
        }

        // Создаем область для отображения результатов статистики
        roundStatsArea = new JTextArea(statsBuilder.toString());
        roundStatsArea.setEditable(false); // Отключаем возможность редактирования пользователем
        roundStatsArea.setLineWrap(true); // Разбивка строк
        roundStatsArea.setWrapStyleWord(true); // Перенос по словам
        JScrollPane scrollPane = new JScrollPane(roundStatsArea); // Добавляем прокрутку
        add(scrollPane, BorderLayout.CENTER);

        // Создаем нижнюю панель с кнопками
        JPanel buttonPanel = new JPanel(new FlowLayout());
        playAgainButton = new JButton("Сыграть еще раз");
        backToMenuButton = new JButton("В главное меню");
        buttonPanel.add(playAgainButton);
        buttonPanel.add(backToMenuButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true); // Отображаем окно
    }

    // Добавление слушателя для кнопки «Сыграть еще раз»
    public void addPlayAgainListener(ActionListener listener) {
        playAgainButton.addActionListener(listener);
    }

    // Добавление слушателя для кнопки «В главное меню»
    public void addBackToMenuListener(ActionListener listener) {
        backToMenuButton.addActionListener(listener);
    }
}