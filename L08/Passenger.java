package Lab08;

/**
 * A class that represents a train passenger.
 *
 * @author Charles Hoot
 * @modifiedBy atb
 * @version 3/12/2019
 */
public class Passenger
{
    private int startedWaiting;
    private int boardedAt;
    private boolean boardedTrain;
    private int destination;
    private int startStation;

    Passenger(int startStation, int destination, int createdAt)
    {
        this.startedWaiting = createdAt;
        this.startStation = startStation;
        this.destination = destination;
        this.boardedTrain = false;
    }

    public int getDestination()
    {
        return this.destination;
    }

    public void boardTrain(int clock)
    {
        this.boardedAt = clock;
        this.boardedTrain = true;
    }

    public int waitTime(int clock)
    {
        int result = clock - this.startedWaiting;
        if(this.boardedTrain)
            result = this.boardedAt - this.startedWaiting;

        return result;
    }

    public boolean boarded()
    {
        return this.boardedTrain;
    }

    public String toString()
    {
        return "Passenger arrived at time marker " + this.startedWaiting + " at station " + this.startStation + " heading to " + this.destination;
    }
}
