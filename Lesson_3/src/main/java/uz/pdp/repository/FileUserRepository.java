package uz.pdp.repository;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import uz.pdp.model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.IntStream;

public class FileUserRepository implements UserRepository {
    private final String files = "files/users.txt";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public List<User> findAll() {
        File file = new File(files);
        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<User>>() {}.getType();
            List<User> users = gson.fromJson(reader, listType);
            if (users.isEmpty()) {
                return null;
            }
            return users;
        } catch (IOException e) {
            throw new RuntimeException("Faylni o'qishda xatolik: " + e.getMessage());
        }
    }

    @Override
    public User findById(UUID id) {
        return findAll().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return findAll().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(User user) {
        List<User> users = findAll();
        users.add(user);
        saveAll(users);
    }

    private void saveAll(List<User> users) {
        File file = new File(files);
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            throw new RuntimeException("Faylga yozishda xatolik: " + e.getMessage());
        }
    }

    @Override
    public void update(User updatedUser) {
        List<User> users = findAll();
        IntStream.range(0, users.size())
                .filter(i -> users.get(i).getId().equals(updatedUser.getId()))
                .findFirst()
                .ifPresent(i -> users.set(i, updatedUser));
        saveAll(users);
    }
}