package model;

public class Kasir extends User {
    public static int counter = 1;

    public Kasir(String username, String password) {
        super("K" + String.format("%03d",counter++), username, password);
    } 
}
