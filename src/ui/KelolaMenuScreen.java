package ui;

import data.AppData;
import model.Menu;
import utils.Formatter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class KelolaMenuScreen extends JFrame {

    private JTextField txtNama;
    private JTextField txtHarga;

    private JTable table;
    private DefaultTableModel model;

    private JButton btnTambah;
    private JButton btnEdit;
    private JButton btnHapus;
    private JButton btnRefresh;
    private JButton btnKembali;

    public KelolaMenuScreen() {

        setTitle("Kelola Menu");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
        loadData();

        setVisible(true);
    }

    private void initComponents() {

        JLabel lblTitle = new JLabel("KELOLA MENU");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        txtNama = new JTextField();
        txtHarga = new JTextField();

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        formPanel.add(new JLabel("Nama Menu"));
        formPanel.add(txtNama);

        formPanel.add(new JLabel("Harga"));
        formPanel.add(txtHarga);

        model = new DefaultTableModel();

        model.addColumn("Nama Menu");
        model.addColumn("Harga");
        model.addColumn("Stok");

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

        btnTambah.addActionListener(e -> tambahMenu());
        btnEdit.addActionListener(e -> editMenu());
        btnHapus.addActionListener(e -> hapusMenu());
        btnRefresh.addActionListener(e -> loadData());

        btnKembali.addActionListener(e -> {
            dispose();
            new AdminMenuScreen();
        });

        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if(row != -1) {

                txtNama.setText(
                        model.getValueAt(row, 0).toString()
                );

                String harga =
                        model.getValueAt(row, 1)
                                .toString()
                                .replace("Rp", "")
                                .replace(".", "")
                                .replace(",", "")
                                .trim();

                txtHarga.setText(harga);
            }
        });
    }

    private void tambahMenu() {

        try {

            String nama = txtNama.getText();
            int harga = Integer.parseInt(txtHarga.getText());

            boolean sukses =
                    AppData.adminService.addMenu(
                            nama,
                            harga,
                            0
                    );

            if(sukses){

                AppData.saveAll();

                JOptionPane.showMessageDialog(
                        this,
                        "Menu berhasil ditambahkan"
                );

                clearForm();
                loadData();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Data tidak valid"
                );
            }

        } catch (Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    "Input tidak valid"
            );
        }
    }

    private void editMenu() {

        int row = table.getSelectedRow();

        if(row == -1){

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih menu terlebih dahulu"
            );

            return;
        }

        try {

            String namaLama =
                    model.getValueAt(row,0).toString();

            String namaBaru =
                    txtNama.getText();

            int hargaBaru =
                    Integer.parseInt(txtHarga.getText());

            AppData.adminService.editMenuName(
                    namaLama,
                    namaBaru
            );

            AppData.adminService.editMenuPrice(
                    namaBaru,
                    hargaBaru
            );

            AppData.saveAll();

            JOptionPane.showMessageDialog(
                    this,
                    "Menu berhasil diupdate"
            );

            clearForm();
            loadData();

        } catch (Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    "Input tidak valid"
            );
        }
    }

    private void hapusMenu() {

        int row = table.getSelectedRow();

        if(row == -1){

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih menu terlebih dahulu"
            );

            return;
        }

        String namaMenu =
                model.getValueAt(row,0).toString();

        boolean sukses =
                AppData.adminService.deleteMenu(
                        namaMenu
                );

        if(sukses){

            AppData.saveAll();

            JOptionPane.showMessageDialog(
                    this,
                    "Menu berhasil dihapus"
            );

            clearForm();
            loadData();
        }
    }

    private void loadData() {

        model.setRowCount(0);

        for(Menu menu : AppData.adminService.getMenuList()) {

            model.addRow(new Object[]{
                    menu.getProductName(),
                    Formatter.toRupiah(
                            menu.getProductPrice()
                    ),
                    menu.getProductStock()
            });
        }
    }

    private void clearForm() {

        txtNama.setText("");
        txtHarga.setText("");
    }
}