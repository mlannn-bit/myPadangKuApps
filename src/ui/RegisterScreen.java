package ui;

import javax.swing.*;
import java.awt.*;
import service.AdminService;
import service.ProduksiService;
import service.RegisterAndLoginService;
import service.TransaksiService;

public class RegisterScreen {

    private RegisterAndLoginService authService;
    private AdminService adminService;
    private ProduksiService produksiService;
    private TransaksiService transaksiService;

    public RegisterScreen(RegisterAndLoginService authService, AdminService adminService,
                          ProduksiService produksiService, TransaksiService transaksiService) {
        this.authService = authService;
        this.adminService = adminService;
        this.produksiService = produksiService;
        this.transaksiService = transaksiService;
    }

    public void show() {
        JFrame frame = new JFrame("Registrasi - Son Ampera");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = UIHelper.createPanel();
        GridBagConstraints gbc = UIHelper.defaultGbc();

        gbc.gridy = 0;
        panel.add(UIHelper.createTitleLabel("Registrasi"), gbc);

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

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Role:"), gbc);
        String[] roles = {"Admin", "Juru Masak", "Kasir"};
        JComboBox<String> cmbRole = new JComboBox<>(roles);
        gbc.gridx = 1;
        panel.add(cmbRole, gbc);

        JLabel lblError = UIHelper.createErrorLabel();
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(lblError, gbc);

        JButton btnRegister = UIHelper.createButton("Daftar", UIHelper.SUCCESS);
        gbc.gridy = 5;
        panel.add(btnRegister, gbc);

        JButton btnBack = UIHelper.createFlatButton("Kembali");
        gbc.gridy = 6;
        panel.add(btnBack, gbc);

        btnRegister.addActionListener(e -> {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();
            int role = cmbRole.getSelectedIndex() + 1;

            if (username.isEmpty()) {
                lblError.setText("Username tidak boleh kosong.");
                return;
            }

            if (password.isEmpty()) {
                lblError.setText("Password tidak boleh kosong.");
                return;
            }

            if (password.length() < 5) {
                lblError.setText("Password minimal 5 karakter.");
                return;
            }

            if (authService.isUsernameTaken(username)) {
                lblError.setText("Username sudah digunakan.");
                return;
            }

            boolean success = authService.register(role, username, password);

            if (success) {
                JOptionPane.showMessageDialog(frame, "Registrasi berhasil! Silakan login.", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new LoginScreen(authService, adminService, produksiService, transaksiService).show();
            } else {
                lblError.setText("Registrasi gagal. Coba lagi.");
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