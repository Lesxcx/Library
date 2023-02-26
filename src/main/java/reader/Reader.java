package reader;

import exception.BadDataException;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Class represents reader
 */
public class Reader {
    private String name, surname, phoneNumber, PESEL, email;
    private BigDecimal balance = BigDecimal.ZERO;

    public Reader(String name, String surname, String phoneNumber, String PESEL, String email) throws BadDataException{
        this.name = name;
        this.surname = surname;
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setPESEL(PESEL);
    }
    public BigDecimal payPenalty(BigDecimal amount){
        balance = balance.subtract(amount);
        return amount;
    }
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPESEL() {
        return PESEL;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) throws BadDataException {
        // replace space with empty place
        phoneNumber.replace(" ", "");
        Pattern pattern = Pattern.compile("\\+?\\d{9,}");
        if (!pattern.matcher(phoneNumber).matches())
            throw new BadDataException("Wrong phone number.");
        this.phoneNumber = phoneNumber;
    }

    public void setPESEL(String PESEL) throws BadDataException {
        Pattern pattern = Pattern.compile("\\d{11}");
        if (!pattern.matcher(PESEL).matches())
            throw new BadDataException("Wrong PESEL");
        char[] numbers = PESEL.toCharArray();
        int controlNumber = Integer.parseInt(String.valueOf(numbers[10]));
        int[] scales = {1,3,7,9,1,3,7,9,1,3};
        int sum = 0;
        for (int i = 0; i<11; i++){
            sum += Integer.parseInt(String.valueOf(numbers[i]))*scales[i];
        }
        sum = sum%10;
        if (sum == 0){
            if (sum != controlNumber)
                throw new BadDataException("Wrong number PESEL");
        } else if ((10 - sum) != controlNumber){
            throw new BadDataException("Wrong number PESEL");
        }
        this.PESEL = PESEL;
    }

    public void setEmail(String email) throws BadDataException {
        Pattern pattern = Pattern.compile("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}");
        if (!pattern.matcher(email).matches())
            throw new BadDataException("Wrong email.");
        this.email = email;
    }

    public void setBalance(BigDecimal amount) {
        balance = balance.add(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reader reader)) return false;
        return Objects.equals(getName(), reader.getName()) && Objects.equals(getSurname(), reader.getSurname()) && Objects.equals(getPESEL(), reader.getPESEL());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getPESEL());
    }
}
