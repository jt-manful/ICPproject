package src;

import java.util.Objects;

public class Vertex {
    public int id;

    public String name;

    public int state;

    public Vertex predecessor;

    public double pathlenght;

    public boolean wasVisited;

    public Vertex(int id, String name) {
        this.id = id;
        this.name = name;
        wasVisited = false;

    }
//    public List<String> solution_path(){
//        List<String> actions = new ArrayList<>();
//        while (predecessor != null){
//            actions.add(name);
//            predecessor = predecessor.predecessor;
//
//        }
//        return actions;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return id == vertex.id && state == vertex.state && Double.compare(vertex.pathlenght, pathlenght) == 0 && Objects.equals(name, vertex.name) && Objects.equals(predecessor, vertex.predecessor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, state, predecessor, pathlenght);
    }

    @Override
    public String toString() {
        return "src.Vertex{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", predecessor=" + predecessor +
                ", pathlenght=" + pathlenght +
                '}';
    }
}
