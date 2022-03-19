package PeoplesInformation;

import java.time.LocalDate;
import java.util.Objects;
/**
 * class of human
 * with fields <b>id</b>,<b>fio</b>,<b>bornDate</b>,<b>passport</b>,<b>age</b>
 * this class store info about person
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */

public class Human {
    /**
     * field of id human
     */
    private int id;
    /**
     * field of human full name
     */
    private String fio;
    /**
     * field of born date human
     */
    private LocalDate bornDate;
    /**
     * field of human passport
     */
    private String passport;
    /**
     * field of human age
     * this field count in method {@link Human#ageComputation()}
     */
    private int age;

    public Human(int id, String fio, int bornYear,int bornMonth,
                 int bornDay, String passport)
    {
        this.id = id;
        this.fio = fio;
        this.bornDate = LocalDate.of(bornYear,bornMonth,bornDay);
        this.passport = passport;
        age=ageComputation();
    }

    /**
     * method, which count human age based on local date
     * @return age of human
     */
    public int ageComputation(){
        if(LocalDate.now().getMonthValue()>bornDate.getMonthValue())
            return LocalDate.now().getYear()-bornDate.getYear();
        else{
            if(LocalDate.now().getMonthValue()<bornDate.getMonthValue())
                return LocalDate.now().getYear()-bornDate.getYear()-1;
            else{
                if(LocalDate.now().getDayOfMonth()>=bornDate.getDayOfMonth())
                    return LocalDate.now().getYear()-bornDate.getYear();
                else
                    return LocalDate.now().getYear()-bornDate.getYear()-1;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Human() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return id == human.id && age == human.age && fio.equals(human.fio) && bornDate.equals(human.bornDate) && passport.equals(human.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, bornDate, passport, age);
    }

    @Override
    public String toString() {
        String ageString;
        if(age>10&&age<20)
            ageString="лет";
        else {
            if (age % 10 == 1)
                ageString = "год";
            else if (age % 10 == 2 || age % 10 == 3 || age % 10 == 4)
                ageString = "года";
            else
                ageString = "лет";
        }
        return String.format("%s, %s %s, %s",fio,age,ageString,passport);
    }
}
