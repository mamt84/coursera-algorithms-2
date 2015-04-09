package assignment1;

/**
 * Created by Martin on 4/4/2015.
 */
public class Job1 extends Job{

    public Job1() {
        super();
    }

    public Job1(Integer id, String line) {
        super(id, line);
    }

    @Override
    public Float priority(){
        return (float) (weight - lenght);
    }

    @Override
    public int compareTo(Job job) {
        if (priority() - job.priority() < 0) {
            return -1;
        }
        if (priority() - job.priority() > 0) {
            return 1;
        }

        return weight - job.weight;
    }
}
