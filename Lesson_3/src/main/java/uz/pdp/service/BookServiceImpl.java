package uz.pdp.service;

import uz.pdp.model.Book;
import uz.pdp.repository.BookRepository;

import java.util.List;
import java.util.UUID;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getAvailableBooks() {
        return getAllBooks().stream()
                .filter(book -> !book.isAvailable())
                .toList();
    }

    @Override
    public List<Book> getBooksByUserId(UUID userId) {
        return getAllBooks().stream()
                .filter(book -> !book.isAvailable() && userId.equals(book.getUserId()))
                .toList();
    }

    @Override
    public void borrowBook(UUID userId, UUID bookId) {
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Bunday kitob topilmadi!");
        }

        if (!book.isAvailable()) {
            throw new IllegalStateException("Kechirasiz, ushbu kitob allaqachon BAND qilingan!");
        }

        book.setAvailable(false);
        book.setUserId(userId);
        bookRepository.update(book);
    }

    @Override
    public void returnBook(UUID userId, UUID bookId) {
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Bunday kitob topilmadi!");
        }

        if (book.isAvailable() || !userId.equals(book.getUserId())) {
            throw new IllegalStateException("Bu kitob siz tomonidan olinmagan!");
        }

        book.setAvailable(true);
        book.setUserId(null);
        bookRepository.update(book);
    }
}