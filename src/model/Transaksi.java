package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transaksi {
    private List<ItemTransaksi> items = new ArrayList<>();
    private LocalDateTime date;
    private Kasir kasir;

    public Transaksi(Kasir kasir){
        this.date = LocalDateTime.now();
        this.kasir = kasir;
    }

    public List<ItemTransaksi> getItems() {
        return items;
    }

    public Kasir getKasir() {
        return kasir;
    }

    public LocalDateTime getDate() {
        return date;
    }


    public int getTotal() {
        int total = 0;

        for(ItemTransaksi item : items) {
            total += item.getSubtotal();
        }

        return total;
    }
}
