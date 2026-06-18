package ui;

import javax.swing.*;
import java.awt.*;
import model.JuruMasak;
import service.AdminService;
import service.ProduksiService;
import service.RegisterAndLoginService;
import service.TransaksiService;

public class JuruMasakMenuScreen {

    private JuruMasak user;
    private ProduksiService produksiService;
    private AdminService adminService;
    private RegisterAndLoginService authService;
    private TransaksiService transaksiService;

    public JuruMasakMenuScreen(JuruMasak user, ProduksiService produksiService, AdminService adminService,
                                RegisterAndLoginService authService, TransaksiService transaksiService) {
        this.user = user;
        this.produksiService = produksiService;
        this.adminService = adminService;
        this.authService = authService;
        this.transaksiService = transaksiService;
    }

    public void show() {
        JFrame frame = new JFrame("Menu Juru Masak - Son Ampera");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 320);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = UIHelper.createPanel();
        GridBagConstraints gbc = UIHelper.defaultGbc();

        gbc.gridy = 0;
        panel.add(UIHelper.createTitleLabel("Menu Juru Masak"), gbc);

        gbc.gridy = 1;
        panel.add(UIHelper.createSubtitleLabel("Halo, " + user.getUsername() + " (" + user.getId() + ")"), gbc);

        JButton btnProduksi = UIHelper.createButton("Produksi Menu", UIHelper.TEAL);
        gbc.gridy = 2;
        panel.add(btnProduksi, gbc);

        JButton btnLogout = UIHelper.createFlatButton("Logout");
        gbc.gridy = 3;
        panel.add(btnLogout, gbc);

        btnLogout.addActionListener(e -> {
            frame.dispose();
            new WelcomeScreen(authService, adminService, produksiService, transaksiService).show();
        });

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}