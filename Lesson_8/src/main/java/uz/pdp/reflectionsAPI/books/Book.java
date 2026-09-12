package uz.pdp.reflectionsAPI.books;


import lombok.ToString;

@ToString
public class Book {
    private final String title;
    private String author;
    private int countPages;

    private Book(String title, String author, int countPages) {
        this.title = title;
        this.author = author;
        this.countPages = countPages;
    }


    private int counter(int count) {
        return count + this.countPages;
    }

    private String getTitle() {
        return title;
    }

    private String getAuthor() {
        return author;
    }

    private int getCountPages() {
        return countPages;
    }

}
