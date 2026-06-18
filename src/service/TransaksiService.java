package service;

import model.ItemTransaksi;
import model.Menu;
import model.Transaksi;
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

}