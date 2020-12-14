package Lab08;

import java.util.*;
import java.util.concurrent.*;

/**
 * A class that represents a train in a simulation.
 *
 * @author Charles Hoot
 * @version 3/12/2019
 * @modifiedBy atb
 */
public class Train
{
    private Queue<Lab08.Passenger> onTrain;
    private int numberOnTrain;
    private int capacity;
    private int nextStation;
    private int timeToArrivalAtNextStation;
    private int trainNo;
    private static int trainsCreated = 0;

    public Train(int capacity)
    {
        this.onTrain = new ArrayDeque<>();
        this.numberOnTrain = 0;
        this.capacity = capacity;

        this.nextStation = 0;
        //the simulation will move the train so it is operational
        this.timeToArrivalAtNextStation = 1;

        trainsCreated++;
        this.trainNo = trainsCreated;
    }

    public int getTrainNo()
    {
        return this.trainNo;
    }

    public int getNextStation()
    {
        return this.nextStation;
    }

    public int getTimeToNext()
    {
        return this.timeToArrivalAtNextStation;
    }

    public void move()
    {
        this.timeToArrivalAtNextStation--;
    }

    public void updateStation(int timeToNext)
    {
        this.timeToArrivalAtNextStation = timeToNext;
        this.nextStation++;
    }

    public int unloadPassengers(int station)
    {
        int count = this.numberOnTrain;
        Lab08.Passenger passenger;
        for (int i = 0; i < count; i++)
        {
            passenger = this.onTrain.poll();
            if (passenger.getDestination() != station)
            {
                // Not there yet, put them back
                this.onTrain.offer(passenger);
            }
            else
            {
                // They arrived
                this.numberOnTrain--;
            }
        }

        int passengersLeaving = count - this.numberOnTrain;
        System.out.print("\tTrain " + this.trainNo + " is at station " + station
                +"; unloaded " + passengersLeaving);
        return passengersLeaving;
    }

    public int loadPassengers(Lab08.Station station, int clock)
    {
        int count = numberOnTrain;
        boolean passengerWaiting = station.isWaiting();
        Lab08.Passenger passenger = null;

        while ((this.numberOnTrain < this.capacity) && passengerWaiting)
        {
            passenger = station.getPassenger();
            this.onTrain.offer(passenger);
            passenger.boardTrain(clock);
            passengerWaiting = station.isWaiting();
            this.numberOnTrain++;
        }

        int passengersEntering = this.numberOnTrain - count;
        System.out.print("; loaded " + passengersEntering + " passengers");
        System.out.println("; Space left " + (this.capacity - this.numberOnTrain));

        return passengersEntering;
    }

    public String toString()
    {
        return "Train " + this.trainNo + " capacity of " + this.capacity
                + " arriving at station " + this.nextStation + " in " + this.timeToArrivalAtNextStation
                + " minutes; currently " + this.numberOnTrain + " passengers on board";
    }
}