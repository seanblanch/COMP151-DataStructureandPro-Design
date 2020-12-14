package Lab08;

/**
 * Job representation
 *
 * @author Sean Blanchard
 * @version 3/26/2019
 */

public class Job implements Comparable<Job>
{

    private int jobNo;
    private int priority;
    private int createdAtTime;
    private int timeLeft;
    private int jobsCreated = 1;

    //Secondary Constructor

    Job(int timeLeft, int createdAtTime, int priority)
    {

        this.jobNo = jobsCreated;
        this.priority = priority;
        this.createdAtTime = createdAtTime;
        this.timeLeft = timeLeft;
        jobsCreated++;
    }

    //Compares job priorities

    public int compareTo( Job job)
    {
        return this.priority - job.priority;
    }

    //Gets the time remaining of a job object

    public int getTimeLeft()
    {
        return this.timeLeft;
    }

    //Gets the number of jobs that were created.

    public int getJobsCreated()
    {
        return this.jobsCreated;
    }

    //Update the time left of the job

    public void update(int clock)
    {
        this.timeLeft = clock;
    }

    //Checks if job is finished

    public boolean isFinished()
    {
        return this.timeLeft == 0;
    }

    //Outputs Job data

    public String toString()
    {
        return "Job #" + this.jobNo + "Priority (" + this.priority + " ) created at " + this.createdAtTime + ", time left " + this.timeLeft;
    }


}
