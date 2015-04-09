package assignment1;

/**
 * Created by Martin on 4/4/2015.
 */
public class Job2 extends Job {

    public Job2(Integer id, String line) {
        super(id, line);
    }

    @Override
    public Float priority() {
        return ((float)weight)/((float)lenght);
    }

    @Override
    public int compareTo(Job job) {
        if (priority() - job.priority() < 0) {
            return -1;
        }
        if (priority() - job.priority() == 0) {
            return 0;
        }
        return 1;
    }
}
