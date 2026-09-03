package uz.pdp.service;

import uz.pdp.model.Book;
import uz.pdp.repository.BookRepository;

import java.util.ArrayList;
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
        List<Book> allBooks = getAllBooks();
        List<Book> availableBooks = new ArrayList<>();
        for (Book b : allBooks) {
            if (!b.isAvailable()) {
                availableBooks.add(b);
            }
        }
        return availableBooks;
    }

    @Override
    public List<Book> getBooksByUserId(UUID userId) {
        List<Book> allBooks = getAllBooks();
        List<Book> userBooks = new ArrayList<>();
        for (Book b : allBooks) {
            if (b.isAvailable() && userId.equals(b.getUserId())) {
                userBooks.add(b);
            }
        }
        return userBooks;
    }

    @Override
    public void borrowBook(UUID userId, UUID bookId) {
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Bunday kitob topilmadi!");
        }

        if (book.isAvailable()) {
            throw new IllegalStateException("Kechirasiz, ushbu kitob allaqachon BAND qilingan!");
        }

        book.setAvailable(true);
        book.setUserId(userId);
        bookRepository.update(book);
    }

    @Override
    public void returnBook(UUID userId, UUID bookId) {
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Bunday kitob topilmadi!");
        }

        if (!book.isAvailable() || !userId.equals(book.getUserId())) {
            throw new IllegalStateException("Bu kitob siz tomonidan olinmagan!");
        }

        book.setAvailable(false);
        book.setUserId(null);
        bookRepository.update(book);
    }
}