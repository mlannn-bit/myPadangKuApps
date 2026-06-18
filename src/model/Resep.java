package model;

public class Resep {
    private BahanBaku ingredient;
    private int amount;

    public Resep(BahanBaku ingredient, int amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public BahanBaku getIngredient() {
        return ingredient;
    }

    public int getAmount() {
        return amount;
    }

    public void setIngredient(BahanBaku rawMaterial) {
        this.ingredient = rawMaterial;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
