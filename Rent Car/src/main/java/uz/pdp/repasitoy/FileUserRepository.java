package uz.pdp.repasitoy;

import uz.pdp.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUserRepository {
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        File file = new File("files/users.txt");
        if (!file.exists()) return users;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] split = line.split(", ");

                UUID id = UUID.fromString(split[0]);
                String fullName = split[1];
                String phoneNumber = split[2];
                String email = split[3];
                String username = split[4];
                String password = split[5];

                users.add(new User(id, fullName, phoneNumber, email, username, password));
            }
        } catch (IOException e) {
            throw new RuntimeException("Faylni o'qishda xatolik: " + e.getMessage());
        }
        return users;
    }

    public void save(User user) {
        File file = new File("files/users.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            String userData = "%s, %s, %s, %s, %s, %s".formatted(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.getUsername(),
                    user.getPassword());

            bw.write(userData);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Faylga yozishda xatolik: " + e.getMessage());
        }
    }

    public User findByUsername(String username) {
        List<User> allUsers = findAll();
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}

