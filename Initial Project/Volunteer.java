package InitialProject.Lab00_S19.src;

/**
 * Volunteer class
 * @author: Sean Blanchard
 * @version: 1/23/2019
 */

public class Volunteer extends StaffMember
{
    public Volunteer (String eName, String eAddress, String ePhone, String socSecNumber)
    {
        super (eName, eAddress, ePhone, socSecNumber);
    }

    //-----------------------------------------------------------------
    //  Returns a zero pay value for this volunteer.
    //-----------------------------------------------------------------
    public double calculatePayment()
    {
        // TODO

        return 0.0; // THIS IS A STUB
        //TODO done
    }

    public String toString()
    {
        // TODO

        return "??"; // THIS IS A STUB
    }
}
