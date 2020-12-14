package Lab08;

import java.util.*;

/**
 * A class that represents a train station
 * where passengers will wait.
 *
 * @author Charles Hoot
 * @modifiedBy atb
 * @version 3/12/2019
 */
public class Station
{
    private Queue<Lab08.Passenger> waiting;
    private int timeToNextStation;
    private int stationNumber;

    /**
     * Constructor for objects of class Station
     */
    public Station(int stationNumber, int timeToNext)
    {
        this.waiting = new ArrayDeque<>();
        this.timeToNextStation = timeToNext;
        this.stationNumber = stationNumber;
    }

    public void addPassenger(Passenger passenger)
    {
        this.waiting.offer(passenger);
    }

    public boolean isWaiting()
    {
        return !this.waiting.isEmpty();
    }

    public Lab08.Passenger getPassenger()
    {
        return this.waiting.poll();
    }

    public int getTimeToNextStation()
    {
        return this.timeToNextStation;
    }

    public String toString()
    {
        return "Station " + this.stationNumber + " has " + (isWaiting()?"some":"no")
                + " passengers waiting; the time to next station is "
                + this.timeToNextStation;
    }

}