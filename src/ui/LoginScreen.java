package ui;

import data.AppData;
import model.Admin;
import model.JuruMasak;
import model.Kasir;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnKembali;

    public LoginScreen() {

        setTitle("Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {

        JLabel lblTitle = new JLabel("LOGIN");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel lblUsername = new JLabel("Username");
        JLabel lblPassword = new JLabel("Password");

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();

        btnLogin = new JButton("Login");
        btnKembali = new JButton("Kembali");

        JPanel formPanel = new JPanel(new GridLayout(4, 1, 5, 5));

        formPanel.add(lblUsername);
        formPanel.add(txtUsername);
        formPanel.add(lblPassword);
        formPanel.add(txtPassword);

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnLogin);
        buttonPanel.add(btnKembali);

        setLayout(new BorderLayout(10, 10));

        add(lblTitle, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnLogin.addActionListener(e -> login());

        btnKembali.addActionListener(e -> {
            dispose();
            new WelcomeScreen();
        });
    }

    private void login() {

        String username = txtUsername.getText();
        String password = String.valueOf(txtPassword.getPassword());

        User user = AppData.authService.login(username, password);

        if (user == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Username atau Password salah!"
            );
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Login Berhasil!"
        );

        dispose();

        if (user instanceof Admin) {
            new AdminMenuScreen();
        } else if (user instanceof JuruMasak) {
            new JuruMasakMenuScreen();
        } else if (user instanceof Kasir) {
            new KasirMenuScreen();
        }
    }
}