package ui;

import javax.swing.*;
import java.awt.*;

public class WelcomeScreen extends JFrame {

    public WelcomeScreen() {

        setTitle("MY PADANGKU");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel("MY PADANGKU APPS");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JButton btnLogin = new JButton("Login");
        JButton btnRegister = new JButton("Register");

        btnLogin.addActionListener(e -> {
            dispose();
            new LoginScreen();
        });

        btnRegister.addActionListener(e -> {
            dispose();
            new RegisterScreen();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);

        setLayout(new BorderLayout());

        add(title, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}