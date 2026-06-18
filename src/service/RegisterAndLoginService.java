package service;

import java.util.ArrayList;
import java.util.List;
import model.Admin;
import model.JuruMasak;
import model.Kasir;
import model.User;

public class RegisterAndLoginService  {

    private List<User> users = new ArrayList<>();

    public void Register(int role, String username, String password) {

        User user;

        if (role == 1) {
            user = new Admin(username, password);
        }

        else if (role == 2) {
            user = new JuruMasak(username, password);
        }

        else if (role == 3) {
            user = new Kasir(username, password);
        }

        else {
            System.out.println("Input role tidak valid.");
            return;
        }

        users.add(user);
        System.out.println("Registrasi Berhasil.");
    }

    public User Login(String username, String password) {
        for(User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }
}
