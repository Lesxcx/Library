package library;

import book.Copy;
import reader.Reader;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

/**
 * Class represents borrowing
 */
public class Borrow {
    private Reader reader;
    private Copy copy;
    private LocalDate since, until, returnDate;
    public final static BigDecimal PENALTY = new BigDecimal("0.20");

    public LocalDate borrow(Reader reader, Copy copy, int numbersOfDays){
        this.reader = reader;
        this.copy = copy;
        since = LocalDate.now();
        until = since.plusDays(numbersOfDays);
        copy.setAvailable(false);
        return until;
    }

    public BigDecimal returnBook(){
        returnDate = LocalDate.now();
        copy.setAvailable(true);
        return calculatePenalty(returnDate);
    }

    public BigDecimal calculatePenalty(LocalDate date){
        Duration d = Duration.between(date, until);
        int exceeding = (int) d.toDays();
        if (exceeding < 1){
            return BigDecimal.ZERO;
        }
        return (new BigDecimal(exceeding)).multiply(PENALTY);
    }

    public Reader getReader() {
        return reader;
    }

    public Copy getCopy() {
        return copy;
    }

    public LocalDate getSince() {
        return since;
    }

    public LocalDate getUntil() {
        return until;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}
