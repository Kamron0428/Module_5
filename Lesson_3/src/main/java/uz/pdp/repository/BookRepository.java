package uz.pdp.repository;

import uz.pdp.model.Book;
import java.util.List;
import java.util.UUID;

public interface BookRepository extends BaseRepository<Book, UUID> {
    void saveAll(List<Book> books);
}