package uz.pdp.service;

import uz.pdp.model.User;
import uz.pdp.repository.UserRepository;
import java.util.List;

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("Ushbu username band!");
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Login yoki parol noto'g'ri!");
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}