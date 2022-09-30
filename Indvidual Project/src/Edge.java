package src;

public class Edge {
    public Vertex source;
    public Vertex destination;
    private double weight = 0 ;

    public Edge(Vertex source, Vertex destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}
