package model;

public class  Admin extends User {

    private static int counter = 1;

    public Admin(String username, String password) {
        super("A" + String.format("%03d", counter++), username, password);
    }

}