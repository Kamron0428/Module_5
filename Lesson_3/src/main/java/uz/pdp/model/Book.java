package uz.pdp.model;

import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private UUID uuid = UUID.randomUUID();
    private String title;
    private String author;
    private String genre;
    private int year;
    private int pageCount;
    private boolean isAvailable;
    private UUID userId;

    @Override
    public String toString() {
        return "%s, %s, %s, %s, %d, %d, %b, %s".formatted(
                uuid, title, author, genre, year, pageCount, isAvailable, userId);
    }
}
