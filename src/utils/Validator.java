package utils;

public class Validator {

    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isPositive(int value) {
        return value > 0;
    }

    public static boolean isNotNegative(int value) {
        return value >= 0;
    }

    public static boolean isValidPassword(String password) {
        return isNotEmpty(password) && password.length() >= 5;
    }

    public static boolean isValidUsername(String username) {
        return isNotEmpty(username) && username.length() >= 3;
    }

    public static boolean isValidPrice(int price) {
        return price > 0;
    }

    public static boolean isValidStock(int stock) {
        return stock >= 0;
    }

    public static boolean isValidAmount(int amount) {
        return amount > 0;
    }
}
