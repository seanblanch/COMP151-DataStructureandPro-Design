package InitialProject.Lab00_S19.src; /**
 * Payroll client
 * @author: Sean Blanchard
 * @version: 1/23/2019
 */
//********************************************************************
//  PayrollClient.java
//********************************************************************

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PayrollClient
{
    //-----------------------------------------------------------------
    //  Creates a staff of employees for a firm and pays them.
    //-----------------------------------------------------------------
    public static void main (String[] args)
    {
        try
        {
            Payroll payroll = new Payroll( new Scanner(new File("staff.txt")));
            System.out.println("---> Preparing data for pay day");
            payroll.prepareForPayDay();
            System.out.println("\n*** The current staff data for the month of December 2018 ***");
            payroll.displayStaffData();
            System.out.println("---> Processing the payroll");
            double[] pay = payroll.processPayroll();
            ArrayList<StaffMember> staff = payroll.getStaffList();
            System.out.println("*** Payroll for the month of December 2018 ***");

            for (int i = 0; i < staff.size(); i++)
            {
                System.out.printf("%d. %-10s ---> %,10.2f\n", (i+1), staff.get(i).getName(), pay[i]);
            }
            System.out.println("***********************************************\n");
        }
        catch( FileNotFoundException fnfe )
        {
            System.out.println( "Unable to find input file \"staff.txt\"");
        }
        catch( IOException ioe )
        {
            ioe.printStackTrace();
        }
    }
}
