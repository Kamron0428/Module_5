package uz.pdp.service;

import uz.pdp.model.User;
import uz.pdp.repasitoy.FileUserRepository;

public class AuthServiceImpl implements AuthService {
    private FileUserRepository fileUserRepository;

    public AuthServiceImpl(FileUserRepository fileUserRepository) {
        this.fileUserRepository = fileUserRepository;
    }


    public User registerUser(User user) {
        User existingUser = fileUserRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("Ushbu username band!");
        }
        fileUserRepository.save(user);
        return user;
    }


    @Override
    public User login(String username, String password) {
        User user = fileUserRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Login yoki parol noto'g'ri!");
        }
        return user;
    }
}
