package uz.pdp.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import uz.pdp.model.Book;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.IntStream;

public class FileBookRepository implements BookRepository {
    private final String files = "files/books.txt";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public List<Book> findAll() {
        File file = new File(files);
        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Book>>() {}.getType();
            List<Book> books = gson.fromJson(reader, listType);
            if (books!= null) {
                return books;
            }
            return new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Faylni o'qishda xatolik: " + e.getMessage());
        }
    }

    @Override
    public Book findById(UUID id) {
        return findAll().stream()
                .filter(book -> book.getUuid().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Book book) {
        List<Book> books = findAll();
        books.add(book);
        saveAll(books);
    }

    @Override
    public void saveAll(List<Book> books) {
        File file = new File(files);
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(books, writer);
        } catch (IOException e) {
            throw new RuntimeException("Faylga yozishda xatolik: " + e.getMessage());
        }
    }

    @Override
    public void update(Book updatedBook) {
        List<Book> books = findAll();
        IntStream.range(0, books.size())
                .filter(i -> books.get(i).getUuid().equals(updatedBook.getUuid()))
                .findFirst()
                .ifPresent(i -> books.set(i, updatedBook));
        saveAll(books);
    }

}