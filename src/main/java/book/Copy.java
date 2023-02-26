package book;

import java.util.Objects;

public class Copy {
    private static int nextID = 0;
    private final Book book;
    private final int iD;
    private String description;
    private boolean available;

    public Copy(Book book, String description, boolean available) {
        this.book = book;
        this.iD = nextID;
        nextID++;
        this.description = description;
        this.available = available;
    }

    public static int getNextID() {
        return nextID;
    }

    public Book getBook() {
        return book;
    }

    public int getiD() {
        return iD;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Copy copy)) return false;
        return getiD() == copy.getiD();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getiD());
    }
}
