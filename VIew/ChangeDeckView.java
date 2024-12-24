package VIew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChangeDeckView extends JFrame {
    private JButton northernKingdomButton;
    private JButton nilfgaardButton;

    public ChangeDeckView() {
        setTitle("Выбор колоды");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 300);
        setLocationRelativeTo(null);

        // Панель с кнопками выбора колоды
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        northernKingdomButton = new JButton("Королевства Севера");
        nilfgaardButton = new JButton("Нильфгаард");

        buttonPanel.add(northernKingdomButton);
        buttonPanel.add(nilfgaardButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    public void addNorthernKingdomListener(ActionListener listener) {
        northernKingdomButton.addActionListener(listener);
    }

    public void addNilfgaardListener(ActionListener listener) {
        nilfgaardButton.addActionListener(listener);
    }
}
