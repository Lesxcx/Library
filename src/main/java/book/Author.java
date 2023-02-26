package book;

/**
 * Class represent author of the book
 */
public class Author {
    private final String name, surname;
    private Integer bornYear;

    public Author(String name, String surname, Integer bornYear) {
        this.name = name;
        this.surname = surname;
        this.bornYear = bornYear;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getBornYear() {
        return bornYear;
    }

    public void setBornYear(Integer bornYear) {
        this.bornYear = bornYear;
    }
}
