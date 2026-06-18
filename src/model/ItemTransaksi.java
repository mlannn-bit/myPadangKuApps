package model;

public class ItemTransaksi {
    private Menu menu;
    private int amount;

    public ItemTransaksi(Menu menu, int amount) {
        this.menu = menu;
        this.amount = amount;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getAmount() {
        return amount;
    }

    public int getSubtotal() {
        return menu.getProductPrice() * amount;
    }
}
