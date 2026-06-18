package model;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private String productName;
    private int productPrice;
    private int productStock;
    private List<Resep> recipeList = new ArrayList<>();

    public Menu(String name, int price, int stock) {
        this.productName = name;
        this.productPrice = price;
        this.productStock = stock;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public List<Resep> getRecipeList() {
        return recipeList;
    }

    public void addRecipe(Resep resep) {
        recipeList.add(resep);
    }
}