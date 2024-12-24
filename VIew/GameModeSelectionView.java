package VIew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameModeSelectionView extends JFrame {
    private JButton playerVsPlayerButton;
    private JButton playerVsAIButton;

    public GameModeSelectionView() {
        setTitle("Выбор режима игры");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        playerVsPlayerButton = new JButton("Игрок против игрока");
        playerVsAIButton = new JButton("Игрок против ИИ");

        add(playerVsPlayerButton);
        add(playerVsAIButton);

        setSize(500, 250);
        setLocationRelativeTo(null);
        setVisible(false);
    }

    public void addPlayerVsPlayerListener(ActionListener listener) {
        playerVsPlayerButton.addActionListener(listener);
    }

    public void addPlayerVsAIListener(ActionListener listener) {
        playerVsAIButton.addActionListener(listener);
    }
}

