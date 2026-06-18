package ui;

import javax.swing.*;
import java.awt.*;
import model.Admin;
import service.AdminService;
import service.ProduksiService;
import service.RegisterAndLoginService;
import service.TransaksiService;

public class AdminMenuScreen {

    private Admin user;
    private AdminService adminService;
    private ProduksiService produksiService;
    private RegisterAndLoginService authService;
    private TransaksiService transaksiService;

    public AdminMenuScreen(Admin user, AdminService adminService, ProduksiService produksiService,
                           RegisterAndLoginService authService, TransaksiService transaksiService) {
        this.user = user;
        this.adminService = adminService;
        this.produksiService = produksiService;
        this.authService = authService;
        this.transaksiService = transaksiService;
    }

    public void show() {
        JFrame frame = new JFrame("Menu Admin - Son Ampera");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 450);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = UIHelper.createPanel();
        GridBagConstraints gbc = UIHelper.defaultGbc();

        gbc.gridy = 0;
        panel.add(UIHelper.createTitleLabel("Menu Admin"), gbc);

        gbc.gridy = 1;
        panel.add(UIHelper.createSubtitleLabel("Halo, " + user.getUsername() + " (" + user.getId() + ")"), gbc);

        String[] labels = {"Kelola Menu", "Kelola Bahan Baku", "Kelola Resep", "Produksi"};
        Color[] colors = {UIHelper.PRIMARY, UIHelper.PURPLE, UIHelper.ORANGE, UIHelper.TEAL};

        for (int i = 0; i < labels.length; i++) {
            JButton btn = UIHelper.createButton(labels[i], colors[i]);
            gbc.gridy = i + 2;
            panel.add(btn, gbc);
        }

        JButton btnLogout = UIHelper.createFlatButton("Logout");
        gbc.gridy = labels.length + 2;
        panel.add(btnLogout, gbc);

        btnLogout.addActionListener(e -> {
            frame.dispose();
            new WelcomeScreen(authService, adminService, produksiService, transaksiService).show();
        });

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}