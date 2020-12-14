package InitialProject.Lab00_S19.src;

/**
 * HourlyEmployee class
 *
 * @author: Sean Blanchard
 * @version: 1/23/2019
 */


public class HourlyEmployee extends StaffMember
{
    private int hoursWorked;
    private double payRate;
    public final static int MIN_HOURS = 20;
    public final  static int MAX_HOURS = 100;

    public HourlyEmployee(String eName, String eAddress, String ePhone,
                          String socSecNumber, double rate)
    {
        super (eName, eAddress, ePhone, socSecNumber);
        // TODO
        hoursWorked = 0;
        payRate = 0;
        //TODO done

    }

    public void addHours(int moreHours)
    {
        // TODO
        hoursWorked += moreHours; //Adds number of hours to this StaffMember
        //TODO done
    }

    public double calculatePayment()
    {
        // TODO
        double payment = payRate * hoursWorked;
        hoursWorked = 0;
        return payment; // THIS IS A STUB
        //TODO done
    }

    public boolean equals(Object o)
    {
        boolean same = true;

        // TODO

        return same; // THIS IS A STUB
    }

    //-----------------------------------------------------------------
    //  Returns information about this hourly employee as a string.
    //-----------------------------------------------------------------
    public String toString()
    {
        // TODO
        String result = super.toString();
        result += "\nCurrent hours:" + hoursWorked;
        return result; // THIS IS A STUB
        // TODO done
    }
}
