package PeoplesInformation;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

/**
 * class of testing Human
 * @author Aleksandr Nozdryuhin
 * @version 4.12
 */
public class HumanTest {
    /**
     * this test check Human class method {@link Human#ageComputation()}
     */

    @Test
    public void ageComputation() {
        int yearNow=LocalDate.now().getYear();
        int monthNow=LocalDate.now().getMonthValue();
        int dayNow=LocalDate.now().getDayOfMonth();
        Human human=new Human(1,"Иванов Олег Дмитриевич",yearNow-21,monthNow,dayNow,"1525 321 568");
        assertEquals(21,human.ageComputation());
        if(dayNow>1) {
            human.setBornDate(LocalDate.of(yearNow - 22, monthNow, dayNow - 1));
            assertEquals(22, human.ageComputation());
        }
        if(monthNow>1) {
            human.setBornDate(LocalDate.of(yearNow - 22, monthNow - 1, dayNow));
            assertEquals(22, human.ageComputation());
        }
        if(dayNow!=LocalDate.MAX.getDayOfMonth()) {
            human.setBornDate(LocalDate.of(yearNow - 22, monthNow, dayNow + 1));
            assertEquals(21, human.ageComputation());
        }
        if(monthNow<LocalDate.MAX.getMonthValue()) {
            human.setBornDate(LocalDate.of(yearNow - 22, monthNow + 1, dayNow));
            assertEquals(21, human.ageComputation());
        }

    }
}