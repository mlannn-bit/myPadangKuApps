package service;

import model.BahanBaku;
import model.Menu;
import model.Resep;

import java.util.ArrayList;

public class AdminService {
    private ArrayList<Menu> menuList = new ArrayList<>();
    private ArrayList<BahanBaku> ingredientList;
    
    public boolean addMenu(String name, int price, int stock) {
        if (name.trim().isEmpty() || price <= 0 || stock < 0) {
            return false;
        }

        menuList.add(new Menu(name, price, stock));
        return true;
    }

    public boolean deleteMenu(String menuName) {
        for(Menu menu: menuList) {
            if (menu.getProductName().equalsIgnoreCase(menuName)) {
                menuList.remove(menu);
                return true;
            }
        }
        return false;
    }

    public boolean editMemuName(String menuName, String newName) {

        if (newName.trim().isEmpty()) {
            return false;
        }

        for(Menu menu : menuList) {
            if (menu.getProductName().equalsIgnoreCase(menuName)) {

                menu.setProductName(newName);
                return true;
            }
        }
        return false;
    }

    public boolean editMenuPrice(String menuName, int newPrice) {

        if (newPrice <= 0) {
            return false;
        }

        for(Menu menu : menuList) {
            if (menu.getProductName().equalsIgnoreCase(menuName)) {
                
                menu.setProductPrice(newPrice);
                return true;
            }
        }
        return false;
    }

    public boolean editMenuStock(String menuName, int newStock) {
        
        if (newStock < 0) {
            return false;
        }

        for(Menu menu: menuList) {
            if (menu.getProductName().equalsIgnoreCase(menuName)) {

                menu.setProductStock(newStock);
                return true;
            }
        }
        return false;
    }

    public boolean addIngredient(String name, int stock, String unit) {
        if (name.trim().isEmpty() || stock < 0 || unit.trim().isEmpty()) {
            return false;
        }

        ingredientList.add(new BahanBaku(name, stock, unit));
        return true;
    }

    public boolean deleteIngredient(String nameIngredient) {
        if (nameIngredient.trim().isEmpty()) {
            return false;
        }

        for(BahanBaku ingredient : ingredientList) {
            if (ingredient.getName().equalsIgnoreCase(nameIngredient)) {
                ingredientList.remove(ingredient);
                return true;
            }
        }
        return false;
    }

    public boolean editNameIngredient(String nameIngredient, String newNameIngredient) {
        if (newNameIngredient.trim().isEmpty()) {
            return false;
        }

        for(BahanBaku ingredient : ingredientList) {
            if (ingredient.getName().equalsIgnoreCase(nameIngredient)) {
                ingredient.setName(newNameIngredient);
                return true;
            }
        }

        return false;
    }

    public boolean editStockIngredient(String nameIngredient, int newStockIngredient) {
        if (newStockIngredient < 0) {
            return false;
        }

        for(BahanBaku ingredient : ingredientList) {
            if (ingredient.getName().equalsIgnoreCase(nameIngredient)) {
                ingredient.setStock(newStockIngredient);
                return true;
            }
        }
        return false;
    }

    public boolean editUnitIngredient(String nameIngredient, String newUnitIngredient) {
        if (newUnitIngredient.trim().isEmpty()) {
            return false;
        }

        for(BahanBaku ingredient : ingredientList) {
            if (ingredient.getName().equalsIgnoreCase(nameIngredient)) {
                ingredient.setUnit(newUnitIngredient);
                return true;
            }
        }

        return false;
    }

    public boolean addRecipeToMenu(String menuName, BahanBaku ingredient, int amount) {
        if (ingredient == null || amount <= 0) {
            return false;
        }

        for(Menu menu : menuList) {
            if (menu.getProductName().equalsIgnoreCase(menuName)) {
                menu.addRepice(new Resep(ingredient, amount));
                return true;
            }
        }

        return false;
    }

    public boolean deleteRecipe(String menuName, BahanBaku ingredient) {
        for(Menu menu : menuList) {
            if (menu.getProductName().equalsIgnoreCase(menuName)) {
                for(Resep recipe : menu.getRecipeList()) {
                    if (recipe.getIngredient().equals(ingredient)) {
                        menu.getRecipeList().remove(recipe);
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
}
