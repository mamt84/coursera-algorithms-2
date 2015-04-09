package assignment1;

/**
 * Created by Martin on 4/4/2015.
 */
public abstract class Job implements Comparable<Job> {

    public Integer id;
    public Integer weight;
    public Integer lenght;

    public Job() {
        super();
    }

    public Job(Integer id, String line) {
        this.id = id;
        String[] data = line.split(" ");
        weight = Integer.parseInt(data[0]);
        lenght = Integer.parseInt(data[1]);
    }

    public abstract Float priority();
}
