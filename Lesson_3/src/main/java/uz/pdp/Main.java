package uz.pdp;

import uz.pdp.repository.*;
import uz.pdp.service.*;
import uz.pdp.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new FileUserRepository();
        BookRepository bookRepository = new FileBookRepository();

        AuthService authService = new AuthServiceImpl(userRepository);
        BookService bookService = new BookServiceImpl(bookRepository);

        ConsoleUI consoleUI = new ConsoleUI(authService, bookService);
        consoleUI.start();
    }
}