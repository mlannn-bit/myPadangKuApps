package service;

import model.BahanBaku;
import model.Menu;
import model.Produksi;
import model.Resep;
import utils.Validator;

import java.util.ArrayList;
import java.util.List;

public class ProduksiService {

    private List<Produksi> produksiList = new ArrayList<>();

    public boolean produksiMenu(Menu menu, int amount) {
        if (menu == null || !Validator.isValidAmount(amount)) {
            return false;
        }

        if (menu.getRecipeList().isEmpty()) {
            return false;
        }

        for (Resep resep : menu.getRecipeList()) {
            BahanBaku bahan = resep.getIngredient();
            int kebutuhanTotal = resep.getAmount() * amount;

            if (bahan.getStock() < kebutuhanTotal) {
                return false;
            }
        }

        for (Resep resep : menu.getRecipeList()) {
            BahanBaku bahan = resep.getIngredient();
            int kebutuhanTotal = resep.getAmount() * amount;
            bahan.setStock(bahan.getStock() - kebutuhanTotal);
        }

        menu.setProductStock(menu.getProductStock() + amount);
        produksiList.add(new Produksi(menu, amount));

        return true;
    }

    public boolean cekStokBahanBaku(Menu menu, int amount) {
        if (menu == null || !Validator.isValidAmount(amount)) {
            return false;
        }

        for (Resep resep : menu.getRecipeList()) {
            BahanBaku bahan = resep.getIngredient();
            int kebutuhanTotal = resep.getAmount() * amount;

            if (bahan.getStock() < kebutuhanTotal) {
                return false;
            }
        }

        return true;
    }

    public void tampilRiwayatProduksi() {
        if (produksiList.isEmpty()) {
            System.out.println("Belum ada riwayat produksi.");
            return;
        }

        System.out.println("===== RIWAYAT PRODUKSI =====");
        for (Produksi produksi : produksiList) {
            System.out.println("Menu   : " + produksi.getMenu().getProductName());
            System.out.println("Jumlah : " + produksi.getAmount() + " porsi");
            System.out.println("----------------------------");
        }
    }

    public List<Produksi> getProduksiList() {
        return produksiList;
    }
}