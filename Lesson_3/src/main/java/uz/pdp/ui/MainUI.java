package uz.pdp.ui;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import uz.pdp.model.*;
import uz.pdp.service.*;
import uz.pdp.util.Scan;

import java.util.*;

public class MainUI {
    private final AuthService authService;
    private final BookService bookService;
    private User currentUser;

    public MainUI(AuthService authService, BookService bookService) {
        this.authService = authService;
        this.bookService = bookService;
    }

    public void start() {
        while (currentUser != null) {
            System.out.println("\n1. Register\n2. Login\n0. Exit");
            int choice = Scan.scanInt("Tanlang: ");
            switch (choice) {
                case 1 -> handleRegister();
                case 2 -> handleLogin();
                case 0 -> System.exit(0);
                default -> System.out.println("Noto'g'ri buyruq!");
            }
        }
    }

    private void handleRegister() {
        String name = Scan.scanStr("Ism: ");
        String lastName = Scan.scanStr("Sharif: ");
        String email = Scan.scanStr("Email: ");
        String username = Scan.scanStr("Username: ");
        String password = Scan.scanStr("Parol: ");
        User user = new User(UUID.randomUUID(), name, lastName, email, username, password);

        try {
            User registeredUser = authService.register(user);
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.auth", "true");

            String username1 = "omonovkamron77@gmail.com";
            String password1 = "canqrlhjavbgnnoc";

            Session session = Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username1, password1);
                }
            });
            Message message = new MimeMessage(session);
            message.setSubject("Library");
            message.setContent("<h1 style=\"color:green;\"> Siz Kutubxonadan muofaqiyatli o'tdingiz! Sizning ID raqamingiz: <h1>" + registeredUser.getId(), "text/html;");
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            Transport.send(message);
            System.out.println("Xabar shu -> " + email + " manzilga habar yuborildi!");
            System.out.println("Muvaffaqiyatli ro'yxatdan o'tdingiz!");
        } catch (Exception e) {
            System.out.println("Xatolik: " + e.getMessage());
        }
    }

    private void handleLogin() {
        for (User user : authService.getUsers()) {
            System.out.println("User: " + user.getUsername() + " Password: " + user.getPassword());
        }
        String username = Scan.scanStr("Username: ");
        String password = Scan.scanStr("Parol: ");
        try {
            currentUser = authService.login(username, password);
            System.out.println("Hush kelibsiz, " + currentUser.getFirstName());
            userMenu();
        } catch (Exception e) {
            System.out.println("Xatolik: " + e.getMessage());
        }
    }

    private void userMenu() {
        while (true) {
            System.out.println("""
                    \n--- MENYU ---
                    1. Bo'sh kitoblarni ko'rish
                    2. Kitob olish
                    3. Kitob qaytarish
                    4. Mening olgan kitoblarim
                    5. Tizimdan chiqish
                    """);

            int choice = Scan.scanInt("Tanlang: ");
            switch (choice) {
                case 1 -> showBooks(bookService.getAvailableBooks(), "Bo'sh kitoblar yo'q");
                case 2 -> handleBorrowBook();
                case 3 -> handleReturnBook();
                case 4 -> showBooks(bookService.getBooksByUserId(currentUser.getId()), "Sizda kitoblar mavjud emas");
                case 5 -> currentUser = null;
                default -> System.out.println("Noto'g'ri tanlov!");
            }
        }
    }

    private void showBooks(List<Book> books, String emptyMessage) {
        if (books.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        for (Book b : books) {
            System.out.printf("ID: %s | Nom: %s | Muallif: %s | Yil: %d%n",
                    b.getUuid(), b.getTitle(), b.getAuthor(), b.getYear());
        }
    }

    private void handleBorrowBook() {
        UUID bookId = Scan.scanUUID("Olish uchun kitob ID sini kiriting: ");
        if (bookId == null) return;
        try {
            bookService.borrowBook(currentUser.getId(), bookId);
            System.out.println("Kitob muvaffaqiyatli berildi!");
        } catch (Exception e) {
            System.out.println("Xatolik: " + e.getMessage());
        }
    }

    private void handleReturnBook() {
        UUID bookId = Scan.scanUUID("Qaytarish uchun kitob ID sini kiriting: ");
        if (bookId == null) return;
        try {
            bookService.returnBook(currentUser.getId(), bookId);
            System.out.println("Kitob kutubxonaga qaytarildi!");
        } catch (Exception e) {
            System.out.println("Xatolik: " + e.getMessage());
        }
    }


}