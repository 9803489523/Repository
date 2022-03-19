package Contracts;

import PeoplesInformation.Human;

import javax.xml.bind.annotation.*;
import java.util.Objects;

/**
 * abstract class of mobile connection contract
 * with field <b>id</b>,<b>startContract</b>,<b>endContract</b>,<b>numberOfContract</b>,<b>owner</b>,<b>numberOfMinutes</b>,<b>numberOfSMS</b>,<b>internetTraffic</b>
 * this class store info about contracts of mobile connection and inherits from class <b>Contract</b>
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */

@XmlRootElement(name = "MobileConnection")
@XmlType(propOrder = {"numberOfMinutes","numberOfSMS","internetTraffic"})
@XmlAccessorType(XmlAccessType.FIELD)
public class MobileConnection extends Contract{
    /**
     * this field store info about number of minutes in contract
     */
    @XmlElement
    private int numberOfMinutes;
    /**
     * this field store info about number of SMS in contract
     */
    @XmlElement
    private int numberOfSMS;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MobileConnection that = (MobileConnection) o;
        return numberOfMinutes == that.numberOfMinutes && numberOfSMS == that.numberOfSMS && internetTraffic == that.internetTraffic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfMinutes, numberOfSMS, internetTraffic);
    }

    /**
     * this field store info about number of gigabytes of internet in contract
     */
    @XmlElement
    private int internetTraffic;

    public MobileConnection(){}
    public MobileConnection(int id, int startYear, int startMonth,
                            int startDay, int endYear, int endMonth, int endDay,
                            long numberOfContract, Human owner, int numberOfMinutes,
                            int numberOfSMS, int internetTraffic)
    {
        super(id, startYear, startMonth, startDay, endYear, endMonth, endDay, numberOfContract, owner);
        this.numberOfMinutes = numberOfMinutes;
        this.numberOfSMS = numberOfSMS;
        this.internetTraffic = internetTraffic;
    }

    public int getNumberOfMinutes() {
        return numberOfMinutes;
    }

    public void setNumberOfMinutes(int numberOfMinutes) {
        this.numberOfMinutes = numberOfMinutes;
    }

    public int getNumberOfSMS() {
        return numberOfSMS;
    }

    public void setNumberOfSMS(int numberOfSMS) {
        this.numberOfSMS = numberOfSMS;
    }

    public int getInternetTraffic() {
        return internetTraffic;
    }

    public void setInternetTraffic(int internetTraffic) {
        this.internetTraffic = internetTraffic;
    }

    @Override
    public String toString() {
        return String.format("""
                        %s
                        количество минут: %s,
                        количество СМС: %s
                        %s ГБ}
                        """,super.toString(),numberOfMinutes,
                numberOfSMS,internetTraffic);
    }
}
