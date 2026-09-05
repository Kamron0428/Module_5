package uz.pdp;

import com.github.javafaker.Faker;
import uz.pdp.model.User;
import uz.pdp.repository.*;
import uz.pdp.service.*;
import uz.pdp.ui.MainUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new FileUserRepository();
        BookRepository bookRepository = new FileBookRepository();

        AuthService authService = new AuthServiceImpl(userRepository);
        BookService bookService = new BookServiceImpl(bookRepository);

        MainUI mainUI = new MainUI(authService, bookService);
        mainUI.start();


//        fakerUser();

    }

    private static void fakerUser() {
        File file = new File("files/users.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            Faker faker = new Faker();

            for (int i = 0; i < 10; i++) {
                User user = User.builder()
                        .id(UUID.randomUUID())
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .email(faker.internet().emailAddress())
                        .username(faker.name().username())
                        .password(faker.internet().password())
                        .build();

                bw.write(user.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Faylga yozishda xatolik: " + e.getMessage());
        }
    }
}