package ui;

import javax.swing.*;
import java.awt.*;

public class JuruMasakMenuScreen extends JFrame {

    private JButton btnProduksi;
    private JButton btnLogout;

    public JuruMasakMenuScreen() {

        setTitle("Dashboard Juru Masak");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {

        JLabel lblTitle = new JLabel("DASHBOARD JURU MASAK");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));

        btnProduksi = new JButton("Produksi");
        btnLogout = new JButton("Logout");

        JPanel panel = new JPanel(new GridLayout(2,1,10,10));

        panel.add(btnProduksi);
        panel.add(btnLogout);

        setLayout(new BorderLayout());

        add(lblTitle, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        btnProduksi.addActionListener(e -> {
            dispose();
            new ProduksiScreen();
        });

        btnLogout.addActionListener(e -> {
            dispose();
            new WelcomeScreen();
        });
    }
}