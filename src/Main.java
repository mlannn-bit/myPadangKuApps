import service.AdminService;
import service.ProduksiService;
import service.RegisterAndLoginService;
import service.TransaksiService;
import ui.WelcomeScreen;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        RegisterAndLoginService authService = new RegisterAndLoginService();
        AdminService adminService = new AdminService();
        ProduksiService produksiService = new ProduksiService();
        TransaksiService transaksiService = new TransaksiService();

        SwingUtilities.invokeLater(() ->
            new WelcomeScreen(authService, adminService, produksiService, transaksiService).show()
        );
    }
}