package assignment2;

/**
 * Created by Martin on 4/4/2015.
 */
public class Vertice implements Comparable<Vertice>{

    public Integer id;

    public long key;

    /*
    Util attributes for Kruskal implementation
     */
    public Vertice leader;
    public int componentSize;

    public Vertice() {
        leader = this;
        componentSize = 1;
    }

    public Vertice(Integer id) {
        this.id = id;
        leader = this;
        componentSize = 1;
    }

    public Vertice(Integer id, Long priority) {
        this.id = id;
        this.key = priority;
        leader = this;
        componentSize = 1;
    }

    @Override
    public int compareTo(Vertice vertice) {
        if (key - vertice.key < 0) {
            return -1;
        }
        if (key - vertice.key == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "Vertice{" +
                "id=" + id +
                ", leader=" + leader.id +
                ", componentSize=" + leader.componentSize +
                '}';
    }
}
