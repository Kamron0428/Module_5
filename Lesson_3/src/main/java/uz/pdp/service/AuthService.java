package uz.pdp.service;

import uz.pdp.model.User;

public interface AuthService {
    User register(User user);
    User login(String username, String password);
}