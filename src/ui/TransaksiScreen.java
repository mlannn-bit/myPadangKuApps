package ui;

import data.AppData;
import model.Kasir;
import model.Menu;
import model.Transaksi;
import service.TransaksiService;
import utils.Formatter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TransaksiScreen extends JFrame {

    private JComboBox<String> cbMenu;
    private JTextField txtJumlah;

    private JTable table;
    private DefaultTableModel model;

    private JLabel lblTotal;

    private JButton btnTambah;
    private JButton btnCetak;
    private JButton btnRefresh;
    private JButton btnKembali;

    private Transaksi transaksi;
    private TransaksiService transaksiService;

    public TransaksiScreen() {

        transaksiService = AppData.transaksiService;

        Kasir kasir = new Kasir("Kasir", "12345");
        transaksi = new Transaksi(kasir);

        setTitle("Transaksi");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
        loadMenu();

        setVisible(true);
    }

    private void initComponents() {

        JLabel lblTitle = new JLabel("TRANSAKSI");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        cbMenu = new JComboBox<>();
        txtJumlah = new JTextField();

        JPanel formPanel = new JPanel(new GridLayout(2,2,5,5));

        formPanel.add(new JLabel("Menu"));
        formPanel.add(cbMenu);

        formPanel.add(new JLabel("Jumlah"));
        formPanel.add(txtJumlah);

        model = new DefaultTableModel();

        model.addColumn("Menu");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Subtotal");

        table = new JTable(model);

        lblTotal = new JLabel("Total : Rp 0");

        btnTambah = new JButton("Tambah");
        btnCetak = new JButton("Selesai");
        btnRefresh = new JButton("Refresh");
        btnKembali = new JButton("Kembali");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnTambah);
        buttonPanel.add(btnCetak);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnKembali);

        setLayout(new BorderLayout());

        add(lblTitle, BorderLayout.NORTH);
        add(formPanel, BorderLayout.WEST);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(lblTotal, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);

        btnTambah.addActionListener(e -> tambahItem());

        btnCetak.addActionListener(e -> cetakStruk());

        btnRefresh.addActionListener(e -> loadMenu());

        btnKembali.addActionListener(e -> {
            dispose();
            new KasirMenuScreen();
        });
    }

    private void loadMenu() {

        cbMenu.removeAllItems();

        for(Menu menu : AppData.adminService.getMenuList()) {
            cbMenu.addItem(menu.getProductName());
        }
    }

    private void tambahItem() {

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
                    transaksiService.tambahItem(
                            transaksi,
                            menu,
                            jumlah
                    );

            if(sukses){

                model.addRow(new Object[]{
                        menu.getProductName(),
                        Formatter.toRupiah(menu.getProductPrice()),
                        jumlah,
                        Formatter.toRupiah(
                                menu.getProductPrice() * jumlah
                        )
                });

                lblTotal.setText(
                        "Total : "
                        + Formatter.toRupiah(
                                transaksi.getTotal()
                        )
                );

                txtJumlah.setText("");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Stok tidak mencukupi"
                );
            }

        } catch (Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    "Input tidak valid"
            );
        }
    }

    private void cetakStruk() {

        if(transaksi.getItems().isEmpty()){

            JOptionPane.showMessageDialog(
                this,
                    "Belum ada transaksi"
            );

            return;
        }

        StringBuilder struk = new StringBuilder();

        struk.append("===== STRUK SON AMPERA =====\n\n");

        struk.append(
                "Tanggal : "
                + Formatter.toDate(
                        transaksi.getDate()
                )
        );

        struk.append("\n");

        struk.append(
                "Kasir : "
                + transaksi.getKasir().getUsername()
        );

        struk.append("\n");

        struk.append("---------------------------------\n");

        transaksi.getItems().forEach(item -> {

            struk.append(
                    item.getMenu().getProductName()
            );

            struk.append("\n");

            struk.append(
                    Formatter.toRupiah(
                            item.getMenu().getProductPrice()
                    )
            );

            struk.append(" x ");

            struk.append(
                    item.getAmount()
            );

            struk.append(" = ");

            struk.append(
                    Formatter.toRupiah(
                            item.getSubtotal()
                    )
            );

            struk.append("\n\n");
        });

        struk.append("---------------------------------\n");

        struk.append(
                "TOTAL : "
                + Formatter.toRupiah(
                        transaksi.getTotal()
                )
        );

        JOptionPane.showMessageDialog(
                this,
                struk.toString(),
                "Struk Transaksi",
                JOptionPane.INFORMATION_MESSAGE
        );

        model.setRowCount(0);

        transaksi = new Transaksi(
                new Kasir("Kasir", "12345")
        );

        lblTotal.setText("Total : Rp 0");
    }
}