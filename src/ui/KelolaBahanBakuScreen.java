package ui;

import data.AppData;
import model.BahanBaku;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class KelolaBahanBakuScreen extends JFrame {

    private JTextField txtNama;
    private JTextField txtStok;
    private JTextField txtSatuan;

    private JButton btnTambah;
    private JButton btnEdit;
    private JButton btnHapus;
    private JButton btnRefresh;
    private JButton btnKembali;

    private JTable table;
    private DefaultTableModel model;

    public KelolaBahanBakuScreen() {

        setTitle("Kelola Bahan Baku");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
        loadData();

        setVisible(true);
    }

    private void initComponents() {

        JLabel lblTitle = new JLabel("KELOLA BAHAN BAKU");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        txtNama = new JTextField();
        txtStok = new JTextField();
        txtSatuan = new JTextField();

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        formPanel.add(new JLabel("Nama Bahan"));
        formPanel.add(txtNama);

        formPanel.add(new JLabel("Stok"));
        formPanel.add(txtStok);

        formPanel.add(new JLabel("Satuan"));
        formPanel.add(txtSatuan);

        model = new DefaultTableModel();
        model.addColumn("Nama");
        model.addColumn("Stok");
        model.addColumn("Satuan");

        table = new JTable(model);

        btnTambah = new JButton("Tambah");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnRefresh = new JButton("Refresh");
        btnKembali = new JButton("Kembali");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnTambah);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnHapus);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnKembali);

        setLayout(new BorderLayout());

        add(lblTitle, BorderLayout.NORTH);
        add(formPanel, BorderLayout.WEST);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnTambah.addActionListener(e -> tambahBahan());
        btnEdit.addActionListener(e -> editBahan());
        btnHapus.addActionListener(e -> hapusBahan());
        btnRefresh.addActionListener(e -> loadData());

        btnKembali.addActionListener(e -> {
            dispose();
            new AdminMenuScreen();
        });
    }

    private void tambahBahan() {

        try {

            boolean sukses =
                    AppData.adminService.addIngredient(
                            txtNama.getText(),
                            Integer.parseInt(txtStok.getText()),
                            txtSatuan.getText()
                    );

            if (sukses) {

                AppData.saveAll();

                JOptionPane.showMessageDialog(
                        this,
                        "Bahan baku berhasil ditambahkan"
                );

                txtNama.setText("");
                txtStok.setText("");
                txtSatuan.setText("");

                loadData();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Data tidak valid"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Stok harus berupa angka"
            );
        }
    }

    private void editBahan() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data terlebih dahulu"
            );

            return;
        }

        String namaLama =
                model.getValueAt(row, 0).toString();

        String namaBaru =
                JOptionPane.showInputDialog(
                        this,
                        "Nama Baru"
                );

        String stokBaru =
                JOptionPane.showInputDialog(
                        this,
                        "Stok Baru"
                );

        String satuanBaru =
                JOptionPane.showInputDialog(
                        this,
                        "Satuan Baru"
                );

        try {

            AppData.adminService.editNameIngredient(
                    namaLama,
                    namaBaru
            );

            AppData.adminService.editStockIngredient(
                    namaBaru,
                    Integer.parseInt(stokBaru)
            );

            AppData.adminService.editUnitIngredient(
                    namaBaru,
                    satuanBaru
            );

            AppData.saveAll();

            JOptionPane.showMessageDialog(
                    this,
                    "Data berhasil diupdate"
            );

            loadData();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Input tidak valid"
            );
        }
    }

    private void hapusBahan() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data terlebih dahulu"
            );

            return;
        }

        String nama =
                model.getValueAt(row, 0).toString();

        boolean sukses =
                AppData.adminService.deleteIngredient(
                        nama
                );

        if (sukses) {

            AppData.saveAll();

            JOptionPane.showMessageDialog(
                    this,
                    "Data berhasil dihapus"
            );

            loadData();
        }
    }

    private void loadData() {

        model.setRowCount(0);

        for (BahanBaku bahan :
                AppData.adminService.getIngredientList()) {

            model.addRow(new Object[]{
                    bahan.getName(),
                    bahan.getStock(),
                    bahan.getUnit()
            });
        }
    }
}