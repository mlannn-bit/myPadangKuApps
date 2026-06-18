package ui;

import javax.swing.*;
import java.awt.*;

public class KasirMenuScreen extends JFrame {

    private JButton btnTransaksi;
    private JButton btnLogout;

    public KasirMenuScreen() {

        setTitle("Dashboard Kasir");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {

        JLabel lblTitle = new JLabel("DASHBOARD KASIR");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

        btnTransaksi = new JButton("Transaksi");
        btnLogout = new JButton("Logout");

        JPanel panel = new JPanel(new GridLayout(2,1,10,10));

        panel.add(btnTransaksi);
        panel.add(btnLogout);

        setLayout(new BorderLayout());

        add(lblTitle, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        btnTransaksi.addActionListener(e -> {
            dispose();
            new TransaksiScreen();
        });

        btnLogout.addActionListener(e -> {
            dispose();
            new WelcomeScreen();
        });
    }
}