package InitialProject.Lab00_S19.src; /**
 * Executive class
 *
 * @author: Sean Blanchard
 * @version: 1/23/2019
 */


import java.text.DecimalFormat;

public class Executive extends StaffMember
{
    private double bonus;
    private double yearly;
    public static final int MIN_BONUS = 500;
    public static final int MAX_BONUS = 5000;
    private final int NUMBER_OF_MONTHS = 12;

    public Executive(String eName, String eAddress, String ePhone,
                     String socSecNumber, double yearly)
    {
        super (eName, eAddress, ePhone, socSecNumber);
        // TODO
        bonus = 0; // Bonus has yet to be awarded
        //TODO done
    }

    public void awardBonus(double execBonus)
    {
        // TODO
        bonus = execBonus; // Awards bonus to executive
        // TODO done
    }

    //-----------------------------------------------------------------
    //  Computes and returns the pay for an executive, which is the
    //  regular employee payment plus a one-time bonus.
    //-----------------------------------------------------------------
    public double calculatePayment()
    {
        // TODO
        double payment = calculatePayment() + bonus;
        bonus = 0;
        return payment; // THIS IS A STUB
        //TODO done
    }

    public boolean equals(Object o)
    {
        boolean same = true;

        // TODO

        return same; // THIS IS A STUB
    }

    public String toString()
    {
        // TODO
        String result = super.toString();
        result += "\nBonus:" + bonus;

        return result; // THIS IS A STUB
        //TODO done
    }
}
