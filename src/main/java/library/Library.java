package library;

import book.Book;
import book.Copy;
import exception.BadDataException;
import reader.Reader;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Library {
    public static int DAY_NUMBERS = 30;
    private final Map<Book, List<Copy>> books = new HashMap<>();
    private final Map<Reader, List<Borrow>> readers = new HashMap<>();
    public LocalDate borrow(Reader reader, Copy copy) throws BadDataException {
        Borrow borrow = new Borrow();
        LocalDate date = borrow.borrow(reader,copy,DAY_NUMBERS);
        if (!readers.containsKey(reader)){
            throw new BadDataException("Reader don't exists");
        }
        readers.get(reader).add(borrow);
        return date;
    }
    public void returnBook(Reader reader, Copy copy) throws BadDataException {
        if (!readers.containsKey(reader)){
            throw new BadDataException("Reader don't exists");
        }
        Set<Map.Entry<Reader, List<Borrow>>> list = readers.entrySet();
        for (Map.Entry<Reader, List<Borrow>> l: list){
            if (l.getKey().equals(reader)){
                List<Borrow> borrows = l.getValue();
                for(Borrow b: borrows){
                    if (b.getCopy().equals(copy)){
                        if(b.getReturnDate() != null)
                            throw new BadDataException("Book already was return!");
                        BigDecimal penalty = b.returnBook();
                        l.getKey().setBalance(penalty);
                        break;
                    }
                }
                break;
            }
        }

    }
    public Map<Book, List<Copy>> searchByAuthor(String name, String surname){
        Map<Book, List<Copy>> result = new HashMap<>();
        for(Map.Entry<Book, List<Copy>> c: books.entrySet()){
            if (c.getKey().getAuthor().getName().equals(name) &&
            c.getKey().getAuthor().getSurname().equals(surname)){
                result.put(c.getKey(), c.getValue());
            }
        }
        return result;
    }
    public Map<Book, List<Copy>> searchByTitle(String title){
        Map<Book, List<Copy>> result = new HashMap<>();
        for(Map.Entry<Book, List<Copy>> c: books.entrySet()){
            if (c.getKey().getTitle().equals(title)){
                result.put(c.getKey(), c.getValue());
            }
        }
        return result;
    }
}
