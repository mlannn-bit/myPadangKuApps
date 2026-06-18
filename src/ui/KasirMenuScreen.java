package ui;

import javax.swing.*;
import java.awt.*;
import model.Kasir;
import service.AdminService;
import service.ProduksiService;
import service.RegisterAndLoginService;
import service.TransaksiService;

public class KasirMenuScreen {

    private Kasir user;
    private TransaksiService transaksiService;
    private AdminService adminService;
    private RegisterAndLoginService authService;
    private ProduksiService produksiService;

    public KasirMenuScreen(Kasir user, TransaksiService transaksiService, AdminService adminService,
                           RegisterAndLoginService authService, ProduksiService produksiService) {
        this.user = user;
        this.transaksiService = transaksiService;
        this.adminService = adminService;
        this.authService = authService;
        this.produksiService = produksiService;
    }

    public void show() {
        JFrame frame = new JFrame("Menu Kasir - Son Ampera");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 320);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = UIHelper.createPanel();
        GridBagConstraints gbc = UIHelper.defaultGbc();

        gbc.gridy = 0;
        panel.add(UIHelper.createTitleLabel("Menu Kasir"), gbc);

        gbc.gridy = 1;
        panel.add(UIHelper.createSubtitleLabel("Halo, " + user.getUsername() + " (" + user.getId() + ")"), gbc);

        JButton btnTransaksi = UIHelper.createButton("Buat Transaksi", UIHelper.PRIMARY);
        gbc.gridy = 2;
        panel.add(btnTransaksi, gbc);

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