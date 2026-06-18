package service;

import model.BahanBaku;
import model.Menu;
import model.Resep;
import utils.Validator;

import java.util.ArrayList;

public class AdminService {
    private ArrayList<Menu> menuList = new ArrayList<>();
    private ArrayList<BahanBaku> ingredientList = new ArrayList<>();

    public void setMenuList(ArrayList<Menu> menuList) {
        this.menuList = menuList;
    }

    public void setIngredientList(ArrayList<BahanBaku> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public boolean addMenu(String name, int price, int stock) {
        if (!Validator.isNotEmpty(name) || !Validator.isValidPrice(price) || !Validator.isValidStock(stock)) {
            return false;
        }

        menuList.add(new Menu(name, price, 0));
        return true;
    }

    public boolean deleteMenu(String menuName) {
        for (Menu menu : menuList) {
            if (menu.getProductName().equalsIgnoreCase(menuName)) {
                menuList.remove(menu);
                return true;
            }
        }
        return false;
    }

    public boolean editMenuName(String menuName, String newName) {
        if (!Validator.isNotEmpty(newName)) {
            return false;
        }

        for (Menu menu : menuList) {
            if (menu.getProductName().equalsIgnoreCase(menuName)) {
                menu.setProductName(newName);
                return true;
            }
        }
        return false;
    }

    public boolean editMenuPrice(String menuName, int newPrice) {
        if (!Validator.isValidPrice(newPrice)) {
            return false;
        }

        for (Menu menu : menuList) {
            if (menu.getProductName().equalsIgnoreCase(menuName)) {
                menu.setProductPrice(newPrice);
                return true;
            }
        }
        return false;
    }

    public boolean editMenuStock(String menuName, int newStock) {
        if (!Validator.isValidStock(newStock)) {
            return false;
        }

        for (Menu menu : menuList) {
            if (menu.getProductName().equalsIgnoreCase(menuName)) {
                menu.setProductStock(newStock);
                return true;
            }
        }
        return false;
    }

    public boolean addIngredient(String name, int stock, String unit) {
        if (!Validator.isNotEmpty(name) || !Validator.isValidStock(stock) || !Validator.isNotEmpty(unit)) {
            return false;
        }

        ingredientList.add(new BahanBaku(name, stock, unit));
        return true;
    }

    public boolean deleteIngredient(String nameIngredient) {
        if (!Validator.isNotEmpty(nameIngredient)) {
            return false;
        }

        for (BahanBaku ingredient : ingredientList) {
            if (ingredient.getName().equalsIgnoreCase(nameIngredient)) {
                ingredientList.remove(ingredient);
                return true;
            }
        }
        return false;
    }

    public boolean editNameIngredient(String nameIngredient, String newNameIngredient) {
        if (!Validator.isNotEmpty(newNameIngredient)) {
            return false;
        }

        for (BahanBaku ingredient : ingredientList) {
            if (ingredient.getName().equalsIgnoreCase(nameIngredient)) {
                ingredient.setName(newNameIngredient);
                return true;
            }
        }

        return false;
    }

    public boolean editStockIngredient(String nameIngredient, int newStockIngredient) {
        if (!Validator.isValidStock(newStockIngredient)) {
            return false;
        }

        for (BahanBaku ingredient : ingredientList) {
            if (ingredient.getName().equalsIgnoreCase(nameIngredient)) {
                ingredient.setStock(newStockIngredient);
                return true;
            }
        }
        return false;
    }

    public boolean editUnitIngredient(String nameIngredient, String newUnitIngredient) {
        if (!Validator.isNotEmpty(newUnitIngredient)) {
            return false;
        }

        for (BahanBaku ingredient : ingredientList) {
            if (ingredient.getName().equalsIgnoreCase(nameIngredient)) {
                ingredient.setUnit(newUnitIngredient);
                return true;
            }
        }

        return false;
    }

    public boolean addRecipeToMenu(String menuName, BahanBaku ingredient, int amount) {
        if (ingredient == null || !Validator.isValidAmount(amount)) {
            return false;
        }

        for (Menu menu : menuList) {
            if (menu.getProductName().equalsIgnoreCase(menuName)) {
                menu.addRecipe(new Resep(ingredient, amount));
                return true;
            }
        }

        return false;
    }

    public boolean deleteRecipe(String menuName, BahanBaku ingredient) {
        for (Menu menu : menuList) {
            if (menu.getProductName().equalsIgnoreCase(menuName)) {
                for (Resep recipe : menu.getRecipeList()) {
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

    public ArrayList<Menu> getMenuList() {
        return menuList;
    }

    public ArrayList<BahanBaku> getIngredientList() {
        return ingredientList;
    }

    public BahanBaku findIngredientByName(String name) {
        for (BahanBaku ingredient : ingredientList) {
            if (ingredient.getName().equalsIgnoreCase(name)) {
                return ingredient;
            }
        }
        return null;
    }

    public Menu findMenuByName(String name) {
        for (Menu menu : menuList) {
            if (menu.getProductName().equalsIgnoreCase(name)) {
                return menu;
            }
        }
        return null;
    }
}