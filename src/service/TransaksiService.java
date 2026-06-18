package service;

import model.ItemTransaksi;
import model.Menu;
import model.Transaksi;
import utils.Formatter;
import utils.Validator;

public class TransaksiService {

    public boolean tambahItem(Transaksi transaksi, Menu menu, int jumlah) {
        if (!Validator.isValidAmount(jumlah)) {
            return false;
        }

        if (menu.getProductStock() >= jumlah) {
            transaksi.getItems().add(new ItemTransaksi(menu, jumlah));
            menu.setProductStock(menu.getProductStock() - jumlah);
            return true;
        }

        return false;
    }

    public void cetakStruk(Transaksi transaksi) {
        System.out.println("===== STRUK =====");
        System.out.println("Tanggal : " + Formatter.toDate(transaksi.getDate()));
        System.out.println("Kasir   : " + transaksi.getKasir().getUsername());
        System.out.println("-----------------");

        for (ItemTransaksi item : transaksi.getItems()) {
            System.out.println(item.getMenu().getProductName()
                + " (" + Formatter.toRupiah(item.getMenu().getProductPrice()) + ")"
                + " x" + item.getAmount()
                + " = " + Formatter.toRupiah(item.getSubtotal()));
        }

        System.out.println("-----------------");
        System.out.println("Total  : " + Formatter.toRupiah(transaksi.getTotal()));
    }
}