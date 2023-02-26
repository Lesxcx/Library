package book;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import exception.BadDataException;

/**
 * Class represents book
 */
public class Book {
    private final Author author;
    private final String title, publishingHouse;
    private String ISBN;

    public Book(Author author, String title, String publishingHouse, String ISBN) throws BadDataException {
        this.author = author;
        this.title = title;
        this.publishingHouse = publishingHouse;
        setISBN(ISBN);
    }

    private void setISBN(String ISBN) throws BadDataException {
        Pattern pattern = Pattern.compile("\\d{10}|\\d{13}");
        Matcher matcher = pattern.matcher(ISBN);
        if (!matcher.matches())
            throw new BadDataException("Wrong ISBN");
        this.ISBN = ISBN;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public String getISBN() {
        return ISBN;
    }
}
