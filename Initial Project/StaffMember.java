package InitialProject.Lab00_S19.src;

import java.security.InvalidParameterException;

/**
 * StaffMember class
 *
 * @author: Sean Blanchard
 * @version: 1/23/2019
 */


public abstract class StaffMember
{
    private String name;
    private String address;
    private String phone;
    private String ssn;

    public StaffMember(String eName, String eAddress, String ePhone, String socSecNumber)
    {
        // TODO
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.ssn = ssn;
        //TODO done

    }

    public String getName()
    {
        // TODO

        return "??"; // THIS IS A STUB
    }

    public String getAddress()
    {
        // TODO

        return "??"; // THIS IS A STUB
    }

    public String getPhone()
    {
        // TODO

        return "??"; // THIS IS A STUB
    }

    public void setSsn(String ssn) throws InvalidParameterException
    {
        // TODO
    }

    public String getSsn()
    {
        // TODO

        return "??"; // THIS IS A STUB
    }

    public boolean equals(Object o)
    {
        boolean same = true;

        // TODO

        return same;
    }

    public String toString()
    {
        // TODO
        String result = getClass().getName()+"\nName: " + name + "\n";
        result += "Address: " + address + "\n";
        result += "Phone: " + phone;


        return result; // THIS IS A STUB
        //TODO done
    }

    public abstract double calculatePayment();
}
