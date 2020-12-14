import java.util.*;

public class TelephoneDirectory
{
    HashMap<String, String> phoneBook;

    public TelephoneDirectory()
    {
        this.phoneBook = new HashMap<>();
    } // end default constructor


    /**
     * Task: Reads a text file of names and telephone numbers.
     *
     * @param data a text scanner for the text file of data
     */
    public void readFile(Scanner data)
    {
        while (data.hasNext())
        {
            String firstName = data.next();
            String lastName = data.next();
            String phoneNumber = data.next();
            String fullName = firstName + " " + lastName;
            System.out.println(fullName + " " + phoneNumber);
            this.phoneBook.put(fullName, phoneNumber);
        }
        data.close();
    } // end readFile


    /**
     * Task: Gets the phone number of a given person.
     */
    public String getPhoneNumber(String personName)
    {
        return this.phoneBook.get(personName);
    } // end getPhoneNumber    

    public String changePhoneNumber(String personName,
                                    String phoneNumber)
    {
        return phoneBook.put(personName, phoneNumber);
    } // end changePhoneNumber

}