package model;

public class BahanBaku {
    private String nameIngredient;
    private int stockIngredient;
    private String unitIngredient;

    public BahanBaku(String name, int stock, String unit) {
        this.nameIngredient = name;
        this.stockIngredient = stock;
        this.unitIngredient = unit;
    }

    public String getName() {
        return nameIngredient;
    }

    public int getStock() {
        return stockIngredient;
    }

    public String getUnit() {
        return unitIngredient;
    }

    public void setName(String name) {
        this.nameIngredient = name;
    }

    public void setStock(int stock) {
        this.stockIngredient = stock;
    }

    public void setUnit(String unit) {
        this.unitIngredient = unit;
    }
}
