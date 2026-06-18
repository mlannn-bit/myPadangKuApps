package model;

public abstract class User {
    private String username;
    private String password;
    private String id;

    public User(String id, String name, String password) {
        this.id = id;
        this.username = name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public boolean changePassword(String oldPass, String newPass) {
        if (password.equals(oldPass)) {
            this.password = newPass;
            return true;
        } else {
            return false;
        }
    }
}