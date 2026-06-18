package ui;

import data.AppData;
import model.Menu;
import model.Produksi;
import service.ProduksiService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProduksiScreen extends JFrame {

    private JComboBox<String> cbMenu;
    private JTextField txtJumlah;

    private JTable table;
    private DefaultTableModel model;

    private JButton btnProduksi;
    private JButton btnRefresh;
    private JButton btnKembali;

    private ProduksiService produksiService;

    public ProduksiScreen() {

        produksiService = AppData.produksiService;

        setTitle("Produksi Menu");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
        loadMenu();
        loadData();

        setVisible(true);
    }

    private void initComponents() {

        JLabel lblTitle = new JLabel("PRODUKSI MENU");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        cbMenu = new JComboBox<>();
        txtJumlah = new JTextField();

        JPanel formPanel = new JPanel(new GridLayout(2,2,5,5));

        formPanel.add(new JLabel("Menu"));
        formPanel.add(cbMenu);

        formPanel.add(new JLabel("Jumlah Produksi"));
        formPanel.add(txtJumlah);

        model = new DefaultTableModel();

        model.addColumn("Nama Menu");
        model.addColumn("Jumlah Produksi");

        table = new JTable(model);

        btnProduksi = new JButton("Produksi");
        btnRefresh = new JButton("Refresh");
        btnKembali = new JButton("Kembali");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnProduksi);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnKembali);

        setLayout(new BorderLayout());

        add(lblTitle, BorderLayout.NORTH);
        add(formPanel, BorderLayout.WEST);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnProduksi.addActionListener(e -> produksiMenu());

        btnRefresh.addActionListener(e -> {
            loadMenu();
            loadData();
        });

        btnKembali.addActionListener(e -> {
            dispose();
            new JuruMasakMenuScreen();
        });
    }

    private void loadMenu() {

        cbMenu.removeAllItems();

        for(Menu menu : AppData.adminService.getMenuList()) {
            cbMenu.addItem(menu.getProductName());
        }
    }

    private void produksiMenu() {

        try {

            String namaMenu =
                    cbMenu.getSelectedItem().toString();

            int jumlah =
                    Integer.parseInt(txtJumlah.getText());

            Menu menu =
                    AppData.adminService.findMenuByName(
                            namaMenu
                    );

            boolean sukses =
                    produksiService.produksiMenu(
                            menu,
                            jumlah
                    );

            if(sukses){

                JOptionPane.showMessageDialog(
                        this,
                        "Produksi berhasil"
                );

                loadData();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Stok bahan baku tidak cukup atau resep belum dibuat"
                );
            }

        } catch (Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    "Input tidak valid"
            );
        }
    }

    private void loadData() {

        model.setRowCount(0);

        for(Produksi produksi :
                produksiService.getProduksiList()) {

            model.addRow(new Object[] {
                    produksi.getMenu().getProductName(),
                    produksi.getAmount()
            });
        }
    }
}
