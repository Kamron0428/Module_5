package uz.pdp.repository;

import uz.pdp.model.Book;
import java.io.*;
import java.util.*;

public class FileBookRepository implements BookRepository {


    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        File file = new File("files/books.txt");
        if (!file.exists()) return books;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] split = line.split(", ");
                UUID uuid = UUID.fromString(split[0]);
                String title = split[1];
                String author = split[2];
                String genre = split[3];
                int year = Integer.parseInt(split[4]);
                int pageCount = Integer.parseInt(split[5]);
                boolean available = Boolean.parseBoolean(split[6]);
                UUID userId = null;
                if (!split[7].equals("null")) {
                    userId = UUID.fromString(split[7]);
                }

                books.add(new Book(uuid, title, author, genre, year, pageCount, available, userId));
            }
        } catch (IOException e) {
            throw new RuntimeException("Faylni o'qishda xatolik: " + e.getMessage());
        }
        return books;
    }

    @Override
    public Book findById(UUID id) {
        List<Book> books = findAll();
        for (Book book : books) {
            if (book.getUuid().equals(id)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void save(Book book) {
        List<Book> books = findAll();
        books.add(book);
        saveAll(books);
    }

    @Override
    public void saveAll(List<Book> books) {
        File file = new File("files/books.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Book book : books) {
                bw.write(book.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Faylga yozishda xatolik: " + e.getMessage());
        }
    }


    @Override
    public void update(Book updatedBook) {
        List<Book> books = findAll();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getUuid().equals(updatedBook.getUuid())) {
                books.set(i, updatedBook);
                break;
            }
        }
        saveAll(books);
    }

}