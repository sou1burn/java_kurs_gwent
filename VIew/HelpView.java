package VIew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HelpView extends JFrame {
    private JButton backButton;

    public HelpView() 
    {
        
        setTitle("Гвинт - Правила игры");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 400);
        setLocationRelativeTo(null);

        String rules = """
            <html>
            <h1>Правила игры Гвинт</h1>
            <ol>
                <li><b>Цель игры:</b> Победить соперника, выиграв два раунда из трёх.</li>
                <li><b>Колоды:</b> Каждая колода состоит из карт с разными способностями. 
                Игрок может выбирать карты перед началом игры.</li>
                <li><b>Раунды:</b> 
                    <ul>
                        <li>Игроки по очереди выкладывают карты на поле.</li>
                        <li>Цель - набрать больше очков силы на поле, чем соперник.</li>
                        <li>Раунд заканчивается, когда оба игрока пасуют или выкладывают все карты.</li>
                    </ul>
                </li>
                <li><b>Типы карт:</b>
                    <ul>
                        <li><b>Обычные карты:</b> Имеют значение силы.</li>
                        <li><b>Карты героев:</b> Сильные карты, на которые не действуют особые эффекты.</li>
                        <li><b>Особые карты:</b> Оказывают влияние на поле (например, погодные карты).</li>
                    </ul>
                </li>
                <li><b>Особые правила:</b>
                    <ul>
                        <li>Игроки могут пасовать, чтобы сохранить карты на следующий раунд.</li>
                        <li>Карты не восстанавливаются между раундами, так что нужно играть стратегически.</li>
                    </ul>
                </li>
            </ol>
            <p>Для более подробных правил обратитесь к игровому руководству.</p>
            </html>
            """;

        JLabel rulesLabel = new JLabel(rules);
        rulesLabel.setHorizontalAlignment(SwingConstants.LEFT);
        rulesLabel.setVerticalAlignment(SwingConstants.TOP);

        JScrollPane scrollPane = new JScrollPane(rulesLabel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Закрыть");
        closeButton.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void addBackToMenuListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}
