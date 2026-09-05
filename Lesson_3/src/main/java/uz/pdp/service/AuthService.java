package uz.pdp.service;

import uz.pdp.model.User;

import java.util.List;

public interface AuthService {
    User register(User user);
    User login(String username, String password);
    List<User> getUsers();
}