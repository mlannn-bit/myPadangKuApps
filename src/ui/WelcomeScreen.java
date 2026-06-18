package ui;

import javax.swing.*;
import java.awt.*;
import service.AdminService;
import service.ProduksiService;
import service.RegisterAndLoginService;
import service.TransaksiService;

public class WelcomeScreen {

    private RegisterAndLoginService authService;
    private AdminService adminService;
    private ProduksiService produksiService;
    private TransaksiService transaksiService;

    public WelcomeScreen(RegisterAndLoginService authService, AdminService adminService,
                         ProduksiService produksiService, TransaksiService transaksiService) {
        this.authService = authService;
        this.adminService = adminService;
        this.produksiService = produksiService;
        this.transaksiService = transaksiService;
    }

    public void show() {
        JFrame frame = new JFrame("Son Ampera");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = UIHelper.createPanel();
        GridBagConstraints gbc = UIHelper.defaultGbc();
        gbc.gridwidth = 2;

        JLabel title = new JLabel("Son Ampera", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(60, 60, 60));
        gbc.gridy = 0;
        panel.add(title, gbc);

        panel.add(UIHelper.createSubtitleLabel("Sistem Manajemen Warung"), setGbc(gbc, 1));

        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        JButton btnLogin = UIHelper.createButton("Login", UIHelper.PRIMARY);
        panel.add(btnLogin, gbc);

        gbc.gridx = 1;
        JButton btnRegister = UIHelper.createButton("Registrasi", UIHelper.SUCCESS);
        panel.add(btnRegister, gbc);

        btnLogin.addActionListener(e -> {
            frame.dispose();
            new LoginScreen(authService, adminService, produksiService, transaksiService).show();
        });

        btnRegister.addActionListener(e -> {
            frame.dispose();
            new RegisterScreen(authService, adminService, produksiService, transaksiService).show();
        });

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private GridBagConstraints setGbc(GridBagConstraints gbc, int gridy) {
        gbc.gridy = gridy;
        return gbc;
    }
}