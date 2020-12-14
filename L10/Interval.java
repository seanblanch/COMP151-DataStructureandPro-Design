package Lab10;

/**
 * A class that represents an interval in an array. It is utilized by the IntervalSearch.
 *
 * @author atb
 * @version 4/2/2019
 */
public class Interval
{
    private int first, second;
    
    public Interval(int firstItem, int secondItem)
    {
        this.first = firstItem;
        this.second = secondItem;
    } // end constructor
    
    public int getFirst()
    {
        return this.first;
    } // end getFirst
    
    public int getSecond()
    {
        return this.second;
    } // end getSecond
   
    public String toString()
    {
    return "(" + this.first + ", " + this.second + ")";
    } // end toString
} // end Interval