package src;// code from  https://pencilprogrammer.com/algorithms/shortest-path-in-unweighted-graph-using-bfs/
import java.util.ArrayList;
import java.util.List;

//public class src.Node {
//    int id;
//    String name;
//    src.Node parent = null;
//    double pathlenght;
//
//    public src.Node(int id, String name, src.Node parent) {
//        this.id = id;
//        this.name = name;
//        this.parent = parent;
//    }
//
//    public List<Integer> solution_path(){
//        List<Integer> path = new ArrayList<>();
//
//        while (this.parent.id != id){
//            path.add(this.id);
//            this = this.parent;
//        }
//        return path;
//    }
//}

class Node{
    int id;
    String name;
    List<Node> neighbors;
    boolean visited = false;
    Node parent = null;

    Node(int id, String name){
        this.id = id;
        this.name = name;
        this.neighbors = new ArrayList<>();
    }

    //Method to connect nodes
    void add_neighbor(Node node){
        this.neighbors.add(node);
        node.neighbors.add(this);
    }

    //src.Node representation
    public String toString(){
        return this.name;
    }
}
