package Lab12;

/**
 *
 *
 *
 * @author Sean Blanchard
 * @version 4/16/2019
 */

public class CarID implements Comparable<CarID> {

    private String characterSequence;
    private long numericSequence;
    public final static int CHARACTER_SEQUENCE_LENGTH = 3;
    public final static int NUMERIC_SEQUENCE_LENGTH = 14;
    private final String DEFAULT_CHARACTER_SEQUENCE = "???";
    private final long DEFAULT_NUMERIC_SEQUENCE = 1000000000000L;


    public CarID(String charSeq, Long numSeq)
    {
        setCharacterSequence(charSeq);
        setNumericSequence(numSeq);
    }

    public void setCharacterSequence(String sequence)
    {
        if(sequence.length() == CHARACTER_SEQUENCE_LENGTH)
        {
            this.characterSequence = sequence;
        }
        else
        {
            this.characterSequence = DEFAULT_CHARACTER_SEQUENCE;
        }
    }

    public void setNumericSequence(Long sequence)
    {

        if (Math.log10((double) sequence) + 1 == NUMERIC_SEQUENCE_LENGTH) {
            this.numericSequence = sequence;
        } else {
            this.numericSequence = this.DEFAULT_NUMERIC_SEQUENCE;
        }
    }

    public String getCharacterSequence()
    {
        return this.characterSequence;
    }

    public long getNumericSequence()
    {
        return this.numericSequence;
    }

    public boolean equals(Object o)
    {
        boolean same = true;
        if(o == this)
        {
            same = true;
        }
        else if(o == null || o.getClass() != this.getClass())
        {
            same = false;
        }
        else
        {

            same = this.characterSequence.equals(((CarID)o).characterSequence)
                    && this.numericSequence == ((CarID) o).numericSequence;
        }
        return same;
    }

    public int compareTo(CarID id)
    {

        int result = this.characterSequence.compareTo(id.characterSequence);
        if(result == 0)
        {
            return Long.compare(this.numericSequence, id.getNumericSequence());
        }
        return result;
    }

    public int hashCode()
    {
        int G = 31;
        return G * (this.characterSequence.hashCode() + Long.hashCode(numericSequence));
    }

    public String toString()
    {
        return "CarID[ " + this.characterSequence + " " + this.numericSequence + "]";
    }

}
