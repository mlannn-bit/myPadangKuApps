package ui;

import data.AppData;
import model.BahanBaku;
import model.Menu;
import model.Resep;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class KelolaResepScreen extends JFrame {

    private JComboBox<String> cbMenu;
    private JComboBox<String> cbBahan;
    private JTextField txtJumlah;

    private JTable table;
    private DefaultTableModel model;

    private JButton btnTambah;
    private JButton btnHapus;
    private JButton btnRefresh;
    private JButton btnKembali;

    public KelolaResepScreen() {

        setTitle("Kelola Resep");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
        loadComboBox();
        loadData();

        setVisible(true);
    }

    private void initComponents() {

        JLabel lblTitle = new JLabel("KELOLA RESEP");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        cbMenu = new JComboBox<>();
        cbBahan = new JComboBox<>();
        txtJumlah = new JTextField();

        JPanel formPanel = new JPanel(new GridLayout(3,2,5,5));

        formPanel.add(new JLabel("Menu"));
        formPanel.add(cbMenu);

        formPanel.add(new JLabel("Bahan"));
        formPanel.add(cbBahan);

        formPanel.add(new JLabel("Jumlah"));
        formPanel.add(txtJumlah);

        model = new DefaultTableModel();

        model.addColumn("Menu");
        model.addColumn("Daftar Resep");

        table = new JTable(model);

        btnTambah = new JButton("Tambah Bahan");
        btnHapus = new JButton("Hapus Resep");
        btnRefresh = new JButton("Refresh");
        btnKembali = new JButton("Kembali");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnTambah);
        buttonPanel.add(btnHapus);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnKembali);

        setLayout(new BorderLayout());

        add(lblTitle, BorderLayout.NORTH);
        add(formPanel, BorderLayout.WEST);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnTambah.addActionListener(e -> tambahResep());

        btnHapus.addActionListener(e -> hapusResep());

        btnRefresh.addActionListener(e -> {
            loadComboBox();
            loadData();
        });

        btnKembali.addActionListener(e -> {
            dispose();
            new AdminMenuScreen();
        });
    }

    private void loadComboBox() {

        cbMenu.removeAllItems();
        cbBahan.removeAllItems();

        for(Menu menu : AppData.adminService.getMenuList()) {
            cbMenu.addItem(menu.getProductName());
        }

        for(BahanBaku bahan : AppData.adminService.getIngredientList()) {
            cbBahan.addItem(bahan.getName());
        }
    }

    private void tambahResep() {

        try {

            String menuName =
                    cbMenu.getSelectedItem().toString();

            String bahanName =
                    cbBahan.getSelectedItem().toString();

            int jumlah =
                    Integer.parseInt(txtJumlah.getText());

            BahanBaku bahan =
                    AppData.adminService.findIngredientByName(
                            bahanName
                    );

            boolean sukses =
                    AppData.adminService.addRecipeToMenu(
                            menuName,
                            bahan,
                            jumlah
                    );

            if(sukses){

                AppData.saveAll();

                JOptionPane.showMessageDialog(
                        this,
                        "Bahan berhasil ditambahkan ke resep"
                );

                txtJumlah.setText("");

                loadData();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Gagal menambahkan resep"
                );
            }

        } catch (Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    "Input tidak valid"
            );
        }
    }

    private void hapusResep() {

        int row = table.getSelectedRow();

        if(row == -1){

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih menu terlebih dahulu"
            );

            return;
        }

        String menuName =
                model.getValueAt(row,0).toString();

        Menu menu =
                AppData.adminService.findMenuByName(
                        menuName
                );

        if(menu != null){

            menu.getRecipeList().clear();

            AppData.saveAll();

            JOptionPane.showMessageDialog(
                    this,
                    "Resep berhasil dihapus"
            );

            loadData();
        }
    }

    private void loadData() {

        model.setRowCount(0);

        for(Menu menu : AppData.adminService.getMenuList()) {

            StringBuilder resepText =
                    new StringBuilder();

            for(Resep resep : menu.getRecipeList()) {

                resepText.append(
                        resep.getIngredient().getName()
                );

                resepText.append("(");

                resepText.append(
                        resep.getAmount()
                );

                resepText.append(" ");

                resepText.append(
                        resep.getIngredient().getUnit()
                );

                resepText.append("), ");
            }

            String hasil =
                    resepText.toString();

            if(hasil.endsWith(", ")) {

                hasil =
                        hasil.substring(
                                0,
                                hasil.length() - 2
                        );
            }

            model.addRow(new Object[]{
                    menu.getProductName(),
                    hasil
            });
        }
    }
}