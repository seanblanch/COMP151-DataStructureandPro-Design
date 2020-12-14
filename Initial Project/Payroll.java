package InitialProject.Lab00_S19.src; /**
 * Payroll class
 * @author: Sean Blanchard
 * @version: 1/23/2019
 */

import java.io.File;
import java.security.InvalidParameterException;
import java.util.*;

public class Payroll
{
    private ArrayList<StaffMember> staffList;
    private final String HOURLY = "Hourly";
    private final String EXECUTIVE = "Executive";
    private final String VOLUNTEER = "Volunteer";

    public Payroll(Scanner file)
    {

        System.out.println("---> Reading staff data from the file");



        // TODO
        Scanner s = new Scanner("staff.txt");





        System.out.println("---> Finished reading from the file\n");
    }

    public void prepareForPayDay()
    {
        Random random = new Random(101);
        // TODO

    }

    //-----------------------------------------------------------------
    //  Pays all staff members.
    //-----------------------------------------------------------------
    public double[] processPayroll()
    {

        // TODO

        return null; // THIS IS A STUB
    }

    public ArrayList<StaffMember> getStaffList()
    {

        // TODO

        return null; // THIS IS A STUB
    }

    public void displayStaffData()
    {

        // TODO
    }
}
