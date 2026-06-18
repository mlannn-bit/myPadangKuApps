package model;

public class Produksi {

    private Menu menu;
    private int amount;

    public Produksi(Menu menu, int amount) {
        this.menu = menu;
        this.amount = amount;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getAmount() {
        return amount;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}