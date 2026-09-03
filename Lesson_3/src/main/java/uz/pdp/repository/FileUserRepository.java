package uz.pdp.repository;

import uz.pdp.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUserRepository implements UserRepository {

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        File file = new File("files/users.txt");
        if (!file.exists()) return users;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(", ");
                UUID uuid = UUID.fromString(split[0]);
                users.add(new User(uuid, split[1], split[2], split[3], split[4], split[5]));
            }
        } catch (IOException e) {
            throw new RuntimeException("Faylni o'qishda xatolik: " + e.getMessage());
        }
        return users;
    }

    @Override
    public User findById(UUID id) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void save(User user) {
        List<User> all = findAll();
        all.add(user);
        saveAll(user);
    }

    private static void saveAll(User user) {
        File file = new File("files/users.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(user.toString());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Faylga yozishda xatolik: " + e.getMessage());
        }
    }

    @Override
    public void update(User updatedUser) {
        List<User> users = findAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(updatedUser.getId())) {
                users.set(i, updatedUser);
                break;
            }
        }
        saveAll(updatedUser);
    }
}