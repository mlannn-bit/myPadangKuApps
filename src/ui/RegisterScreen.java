package ui;

import data.AppData;

import javax.swing.*;
import java.awt.*;

public class RegisterScreen extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cbRole;

    private JButton btnRegister;
    private JButton btnKembali;

    public RegisterScreen() {

        setTitle("Register");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {

        JLabel lblTitle = new JLabel("REGISTER");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel lblUsername = new JLabel("Username");
        JLabel lblPassword = new JLabel("Password");
        JLabel lblRole = new JLabel("Role");

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();

        cbRole = new JComboBox<>();
        cbRole.addItem("Admin");
        cbRole.addItem("Juru Masak");
        cbRole.addItem("Kasir");

        btnRegister = new JButton("Register");
        btnKembali = new JButton("Kembali");

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 5, 5));

        formPanel.add(lblUsername);
        formPanel.add(txtUsername);

        formPanel.add(lblPassword);
        formPanel.add(txtPassword);

        formPanel.add(lblRole);
        formPanel.add(cbRole);

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnRegister);
        buttonPanel.add(btnKembali);

        setLayout(new BorderLayout(10, 10));

        add(lblTitle, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnRegister.addActionListener(e -> register());

        btnKembali.addActionListener(e -> {
            dispose();
            new WelcomeScreen();
        });
    }

    private void register() {

        String username = txtUsername.getText();
        String password = String.valueOf(txtPassword.getPassword());

        int role = 1;

        if (cbRole.getSelectedIndex() == 0) {
            role = 1; // Admin
        } else if (cbRole.getSelectedIndex() == 1) {
            role = 2; // Juru Masak
        } else if (cbRole.getSelectedIndex() == 2) {
            role = 3; // Kasir
        }

        boolean success = AppData.authService.register(
                role,
                username,
                password
        );

        if (success) {

            AppData.saveAll();

            JOptionPane.showMessageDialog(
                    this,
                    "Register Berhasil!"
            );

            dispose();
            new WelcomeScreen();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Register Gagal!\nUsername minimal 3 karakter\nPassword minimal 5 karakter\natau username sudah digunakan."
            );
        }
    }
}