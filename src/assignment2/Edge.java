package assignment2;

/**
 * Created by Martin on 4/4/2015.
 */
public class Edge implements Comparable<Edge>{
    public Vertice source;
    public Vertice target;
    public long weight;

    public Edge() {
    }

    public Edge(Vertice source, Vertice target, Long weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public Vertice getNextVertice(Vertice vertice){
        if (source.id == vertice.id) {
            return target;
        }
        return source;
    }

    @Override
    public int compareTo(Edge edge) {
        if (weight - edge.weight < 0)
            return -1;

        if (weight - edge.weight == 0)
            return 0;

        return 1;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", target=" + target +
                ", weight=" + weight +
                '}';
    }
}
