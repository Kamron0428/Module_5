package uz.pdp.service;

import uz.pdp.model.User;

public interface AuthService {
    User registerUser(User user);
    User login(String username, String password);
}
