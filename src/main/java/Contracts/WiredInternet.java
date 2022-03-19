package Contracts;

import PeoplesInformation.Human;

import javax.xml.bind.annotation.*;
import java.util.Objects;

/**
 * class of wired internet contract
 * with fields <b>id</b>,<b>startContract</b>,<b>endContract</b>,<b>numberOfContract</b>,<b>owner</b>,<b>connectionSpeed</b>
 * this class store info about contracts of wired internet and inherits from class <b>Contract</b>
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
@XmlRootElement(name = "WiredInternet")
@XmlType(propOrder = {"connectionSpeed"})
@XmlAccessorType(XmlAccessType.FIELD)
public class WiredInternet extends Contract{
    /**
     * connection speed in contract field
     */
    @XmlElement
    private int connectionSpeed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WiredInternet that = (WiredInternet) o;
        return connectionSpeed == that.connectionSpeed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), connectionSpeed);
    }

    public WiredInternet(){}

    public WiredInternet(int id, int startYear, int startMonth, int startDay,
                         int endYear, int endMonth, int endDay,
                         long numberOfContract, Human owner, int connectionSpeed)
    {
        super(id, startYear, startMonth, startDay, endYear, endMonth, endDay, numberOfContract, owner);
        this.connectionSpeed = connectionSpeed;
    }

    public int getConnectionSpeed() {
        return connectionSpeed;
    }

    public void setConnectionSpeed(int connectionSpeed) {
        this.connectionSpeed = connectionSpeed;
    }

    @Override
    public String toString() {
        return String.format("%s\nскорость: %s Мбит/с}\n",super.toString(),connectionSpeed);
    }

}
