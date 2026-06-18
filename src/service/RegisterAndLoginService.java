package service;

import java.util.ArrayList;
import java.util.List;
import model.Admin;
import model.JuruMasak;
import model.Kasir;
import model.User;
import utils.Validator;

public class RegisterAndLoginService {

    private List<User> users = new ArrayList<>();

    public boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean register(int role, String username, String password) {
        if (!Validator.isValidUsername(username) || !Validator.isValidPassword(password)) {
            return false;
        }

        if (isUsernameTaken(username)) {
            return false;
        }

        User user;

        if (role == 1) {
            user = new Admin(username, password);
        } else if (role == 2) {
            user = new JuruMasak(username, password);
        } else if (role == 3) {
            user = new Kasir(username, password);
        } else {
            return false;
        }

        users.add(user);
        return true;
    }

    public User login(String username, String password) {
        if (!Validator.isNotEmpty(username) || !Validator.isNotEmpty(password)) {
            return null;
        }

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }
}