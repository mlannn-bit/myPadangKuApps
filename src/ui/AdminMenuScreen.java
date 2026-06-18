package ui;

import javax.swing.*;
import java.awt.*;

public class AdminMenuScreen extends JFrame {

    private JButton btnKelolaMenu;
    private JButton btnKelolaBahan;
    private JButton btnKelolaResep;
    private JButton btnLogout;

    public AdminMenuScreen() {

        setTitle("Dashboard Admin");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {

        JLabel lblTitle = new JLabel("DASHBOARD ADMIN");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

        btnKelolaMenu = new JButton("Kelola Menu");
        btnKelolaBahan = new JButton("Kelola Bahan Baku");
        btnKelolaResep = new JButton("Kelola Resep");
        btnLogout = new JButton("Logout");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        panel.add(btnKelolaMenu);
        panel.add(btnKelolaBahan);
        panel.add(btnKelolaResep);
        panel.add(btnLogout);

        setLayout(new BorderLayout());

        add(lblTitle, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        btnKelolaMenu.addActionListener(e -> {
            dispose();
            new KelolaMenuScreen();
        });

        btnKelolaBahan.addActionListener(e -> {
            dispose();
            new KelolaBahanBakuScreen();
        });

        btnKelolaResep.addActionListener(e -> {
            dispose();
            new KelolaResepScreen();
        });

        btnLogout.addActionListener(e -> {
            dispose();
            new WelcomeScreen();
        });
    }
}