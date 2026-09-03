package uz.pdp.service;

import uz.pdp.model.Book;
import java.util.List;
import java.util.UUID;

public interface BookService {
    List<Book> getAllBooks();
    List<Book> getAvailableBooks();
    List<Book> getBooksByUserId(UUID userId);
    void borrowBook(UUID userId, UUID bookId);
    void returnBook(UUID userId, UUID bookId);
}