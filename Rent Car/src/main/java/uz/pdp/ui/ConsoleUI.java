package uz.pdp.ui;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import uz.pdp.model.Car;
import uz.pdp.model.User;
import uz.pdp.service.AuthService;
import uz.pdp.service.RentCarService;
import uz.pdp.util.Scan;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class ConsoleUI {
    private AuthService authService;
    private RentCarService rentCarService;
    private User currentUser;

    public ConsoleUI(AuthService authService, RentCarService rentCarService) {
        this.authService = authService;
        this.rentCarService = rentCarService;
    }

    public void start() {
        while (true) {
            System.out.println("""
                    1. Register
                    2. Login
                    0. Chiqish""");
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
        String fullName = Scan.scanStr("Ism sharif: ");
        String phoneNumber = Scan.scanStr("Telefon raqam: ");
        String email = Scan.scanStr("Email: ");
        String username = Scan.scanStr("Username: ");
        String password = Scan.scanStr("Parol: ");
        User user = new User(UUID.randomUUID(), fullName, phoneNumber, email, username, password);

        try {
            User registeredUser = authService.registerUser(user);

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
            message.setSubject("Rent Car PDP G64");
            message.setContent("<h1 style=\"color:green;\"> Siz muvaffaqiyatli ro'yxatdan o'tdingiz! Sizning ID raqamingiz: <h1>"+ registeredUser.getId(), "text/html;");
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
        String username = Scan.scanStr("Username: ");
        String password = Scan.scanStr("Password: ");
        try {
            currentUser = authService.login(username, password);
            System.out.println("Hush kelibsiz, " + currentUser.getFullName());
            userMenu();
        } catch (Exception e) {
            System.out.println("Xatolik: " + e.getMessage());
        }
    }

    private void userMenu() {
        while (currentUser != null) {
            System.out.println("""
                    \n--- MENYU ---
                    1. Bo'sh avtolarni ko'rish
                    2. Avto ijaraga olish
                    3. Avto qaytarish
                    4. Men ijaraga olgan avtolar
                    5. Tizimdan chiqish
                    """);

            int choice = Scan.scanInt("Tanlang: ");
            switch (choice) {
                case 1 -> showCars(rentCarService.getAvailableCars(), "Avtolar mavjud emas");
                case 2 -> handleBorrowCar();
                case 3 -> handleReturnCar();
                case 4 -> showCars(rentCarService.getRenterId(currentUser.getId()), "Sizda avto mavjud emas");
                case 5 -> currentUser = null;
                default -> System.out.println("Noto'g'ri tanlov!");
            }
        }
    }

    private void showCars(List<Car> cars, String emptyMessage) {
        if (cars.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        for (Car car : cars) {
            System.out.printf("ID: %s | Model: %s | Brand: %s | Price: %s | Yil: %d%n",
                    car.getId(), car.getModel(), car.getBrand(), car.getPrice(), car.getYear());
        }
    }

    private void handleBorrowCar() {
        UUID carId = Scan.scanUUID("Ijaraga olish uchun avto ID sini kiriting: ");
        if (carId == null) return;
        try {
            rentCarService.borrowCar(currentUser.getId(), carId);
            System.out.println("Avto muvaffaqiyatli berildi!");
        } catch (Exception e) {
            System.out.println("Xatolik: " + e.getMessage());
        }
    }

    private void handleReturnCar() {
        UUID carId = Scan.scanUUID("Qaytarish uchun avto ID sini kiriting: ");
        if (carId == null) return;
        try {
            rentCarService.returnCar(currentUser.getId(), carId);
            System.out.println("Avto muvaffaqiyatli qaytarildi!");
        } catch (Exception e) {
            System.out.println("Xatolik: " + e.getMessage());
        }
    }
}