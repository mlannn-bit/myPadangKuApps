package ui;

import javax.swing.*;
import java.awt.*;
import model.Admin;
import model.JuruMasak;
import model.Kasir;
import model.User;
import service.AdminService;
import service.ProduksiService;
import service.RegisterAndLoginService;
import service.TransaksiService;

public class LoginScreen {

    private RegisterAndLoginService authService;
    private AdminService adminService;
    private ProduksiService produksiService;
    private TransaksiService transaksiService;

    public LoginScreen(RegisterAndLoginService authService, AdminService adminService,
                       ProduksiService produksiService, TransaksiService transaksiService) {
        this.authService = authService;
        this.adminService = adminService;
        this.produksiService = produksiService;
        this.transaksiService = transaksiService;
    }

    public void show() {
        JFrame frame = new JFrame("Login - Son Ampera");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 330);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = UIHelper.createPanel();
        GridBagConstraints gbc = UIHelper.defaultGbc();

        gbc.gridy = 0;
        panel.add(UIHelper.createTitleLabel("Login"), gbc);

        gbc.gridy = 1; gbc.gridwidth = 1; gbc.gridx = 0;
        panel.add(new JLabel("Username:"), gbc);
        JTextField txtUsername = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtUsername, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);
        JPasswordField txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(txtPassword, gbc);

        JLabel lblError = UIHelper.createErrorLabel();
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(lblError, gbc);

        JButton btnLogin = UIHelper.createButton("Login", UIHelper.PRIMARY);
        gbc.gridy = 4;
        panel.add(btnLogin, gbc);

        JButton btnBack = UIHelper.createFlatButton("Kembali");
        gbc.gridy = 5;
        panel.add(btnBack, gbc);

        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                lblError.setText("Username dan password tidak boleh kosong.");
                return;
            }

            User user = authService.login(username, password);

            if (user == null) {
                lblError.setText("Username atau password salah.");
                return;
            }

            frame.dispose();

            if (user instanceof Admin) {
                new AdminMenuScreen((Admin) user, adminService, produksiService, authService, transaksiService).show();
            } else if (user instanceof JuruMasak) {
                new JuruMasakMenuScreen((JuruMasak) user, produksiService, adminService, authService, transaksiService).show();
            } else if (user instanceof Kasir) {
                new KasirMenuScreen((Kasir) user, transaksiService, adminService, authService, produksiService).show();
            }
        });

        btnBack.addActionListener(e -> {
            frame.dispose();
            new WelcomeScreen(authService, adminService, produksiService, transaksiService).show();
        });

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}