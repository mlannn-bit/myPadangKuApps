package service;

import java.time.format.DateTimeFormatter;

import model.ItemTransaksi;
import model.Menu;
import model.Transaksi;

public class TransaksiService {
    public boolean tambahItem(Transaksi transaksi, Menu menu, int jumlah) {

        if (jumlah <= 0) {
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

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        System.out.println("===== STRUK =====");
        System.out.println("Tanggal : " + transaksi.getDate().format(format));
        System.out.println("Kasir   :" + transaksi.getKasir().getUsername());
        System.out.println("-----------------");

        for(ItemTransaksi item : transaksi.getItems()) {
            System.out.println(item.getMenu().getProductName() 
            + " (" + item.getMenu().getProductPrice() 
            + ") x" + item.getAmount() 
            + " = " + item.getSubtotal());
        }

        System.out.println("-----------------");

        System.out.println("Total  : " + transaksi.getTotal());
    }
}
