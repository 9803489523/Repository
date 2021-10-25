package PeoplesInformation;

import java.util.Objects;
/**
 * class of humans passport
 * with fields <b>serial</b>,<b>number</b>
 * this class store info about person
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */

public class Passport {
    /**
     * info about serial of passport
     */
    private String serial;
    /**
     * info about number of passport
     */
    private String number;

    public Passport(String serial, String number) {
        this.serial = serial;
        this.number = number;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return serial.equals(passport.serial) && number.equals(passport.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serial, number);
    }

    @Override
    public String toString() {
        return String.format("%s %s",serial,number);
    }
}
