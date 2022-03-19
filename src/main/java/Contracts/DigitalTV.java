package Contracts;

import PeoplesInformation.Human;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;
/**
 * class of digital tv contract
 * with fields <b>id</b>,<b>startContract</b>,<b>endContract</b>,<b>numberOfContract</b>,<b>owner</b>,<b>List<String></b>
 * this class store info about contracts of digital tv and inherits from class <b>Contract</b>
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */

@XmlRootElement(name = "DigitalTV")
@XmlType(propOrder = {"channels"})
@XmlAccessorType(XmlAccessType.FIELD)
public class DigitalTV extends Contract{
    /**
     * list of digital tv channels field
     */
    @XmlElement
    private List<String> channels;

    public DigitalTV(int id, int startYear,int startMonth, int startDay,
                     int endYear, int endMonth,int endDay, long numberOfContract,
                     Human owner, List<String> channels)
    {
        super(id, startYear,startMonth, startDay, endYear, endMonth, endDay, numberOfContract, owner);
        this.channels = channels;
    }
    public DigitalTV(){}
    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return String.format("%s\nсписок каналов: %s}\n",super.toString(),channels);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DigitalTV digitalTV = (DigitalTV) o;
        return Objects.equals(channels, digitalTV.channels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), channels);
    }
}
