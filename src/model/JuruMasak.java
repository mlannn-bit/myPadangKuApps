package model;

public class JuruMasak extends User {

    private static int counter = 1;

    public JuruMasak(String username, String password) {
        super("JM" + String.format("%03d", counter++), username, password);
    }
}
