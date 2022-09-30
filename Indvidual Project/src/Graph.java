package src;

import java.util.*;

public class Graph {
    public static final int max_vertices = 15000;
    public static double[][] adjacencyMatrix;
    public static  Vertex[] vertices;
    static int vertex_count = 0;
    int edge_count = 0;


    public static final int TEMPORARY = 1;
    public static final int PERMANENT = 2;
    public static final int INFINITY = 9999;
    public static final int NIL = -1;

    HashMap<Integer, Integer> predecessor = new HashMap<>();


    public Graph() {
        adjacencyMatrix = new double[max_vertices][max_vertices];
        vertices = new Vertex[max_vertices];

    }


    /**
     * The function takes in an integer id and a string name and creates a new vertex object with the given id and name and
     * adds it to the vertices array
     *
     * @param id The id of the vertex.
     * @param name The name of the vertex
     */
    public void addVertex(int id,String name) {

        vertices[vertex_count++] = new Vertex(id, name);

    }


    /**
     * This function prints the vertices of the graph
     */
    public void printVertex(){
        for(int i = 0; i < vertex_count ; i++) {
            System.out.println(vertices[i].toString());
        }
    }

    /**
     * > Given a vertex name, return the vertex id
     *
     * @param source The source vertex from which the shortest path is to be found.
     * @return The id of the vertex.
     */
    private int getId(String source)  {
        for(int i = 0; i < vertex_count ; i++) {
            if (source.equals(vertices[i].name)) {
                return vertices[i].id;
            }
        }
        throw new RuntimeException("Invalid src.Vertex");
    }


    /**
     * > The function takes in a vertex id and returns the name of the vertex
     *
     * @param source The source vertex from which we want to find the shortest path.
     * @return The name of the vertex
     */
    private String getName(int source){
        for(int i = 0; i < vertex_count ; i++) {
            if (source == vertices[i].id) {
                return vertices[i].name;
            }
        }
        throw new RuntimeException("Invalid src.Vertex");
    }


    /**
     * > This function returns the vertex object with the given id
     *
     * @param source The source vertex from which the shortest path is to be found.
     * @return The vertex with the given id.
     */
    private Vertex getVetex(int source){
        for(int i = 0; i < vertex_count ; i++) {
            if (source == vertices[i].id) {
                return vertices[i];
            }
        }
        throw new RuntimeException("Invalid src.Vertex");
    }


    /**
     * The function takes in the source and destination node ids and the weight of the edge as input and adds the edge to
     * the adjacency matrix
     *
     * @param source_id the id of the source node
     * @param des_id the destination vertex
     * @param weight the weight of the edge
     */
    public void addEdge(int source_id, int des_id, double weight) {

        if(source_id == des_id) {throw new IllegalArgumentException("Invalid edge");}
        else{
            adjacencyMatrix[source_id][des_id]= weight;
            //adjacencyMatrix[des_id][source_id] = weight;
            edge_count++;
        }
    }


    /**
     * If the value in the adjacency matrix at the intersection of the source and destination is not zero, then the source
     * and destination are adjacent.
     *
     * @param source The source node
     * @param destination The destination node.
     * @return The adjacency matrix.
     */
    public static boolean isAdjacent(int source, int destination) {
        return (adjacencyMatrix[source][destination] != 0);
    }


    /**
     * The function takes in a vertex id and returns an array list of all the vertices that are adjacent to the vertex
     *
     * @param vertex_id The id of the vertex whose successors you want to find.
     * @return The successors of the vertex with the given id.
     */
    public ArrayList<Integer> getSuccessors(int vertex_id){
        ArrayList<Integer> succesors = new ArrayList<>();
        for (int i =0; i < vertex_count; i++){

            if (isAdjacent(vertex_id,vertices[i].id)){
                //System.out.println(vertices[i].name);
            succesors.add(vertices[i].id);
            }
        }
        return succesors;
    }


    /**
     * It prints the path from the source to the destination.
     *
     * @param destnation the destination node
     * @return The path from the source to the destination.
     */
    public List<String> printPath(int destnation) {
        List<String> path = new ArrayList<>();
        path.add(getName(destnation));
        int current_node = destnation;

        while (predecessor.containsKey(current_node)){
            current_node = predecessor.get(current_node);
            if (current_node ==0){
                break;
            }
            path.add(getName(current_node));
        }
        Collections.reverse(path);
        return path;
    }


    /**
     * The function takes in a source and destination vertex and returns a list of vertices that form the shortest path
     * between the source and destination vertex
     *
     * @param source_id the id of the source vertex
     * @param des_id The destination vertex
     * @return The method returns a list of strings that contains the path from the source to the destination.
     */
    public List<String> searchAlgorithm(int source_id, int des_id){

        predecessor.put(source_id, 0);

        if(source_id==des_id){
            return printPath(des_id);
        }
        boolean[] isVisited = new boolean[max_vertices];
        isVisited[source_id] = true;

        ArrayList<Integer> queue = new ArrayList<>();

        queue.add(source_id);
        while(!queue.isEmpty()){
            int currentvertex = queue.remove(0);

            if (currentvertex == des_id){
                return printPath(des_id);
            }
            ArrayList<Integer> successors = getSuccessors(currentvertex);
            for (Integer successor : successors) {

                if (!isVisited[successor] && !queue.contains(successor)) {
                    if (!predecessor.containsKey(successor)) {
                        predecessor.put(successor, currentvertex);
                    } else {
                        predecessor.replace(successor, currentvertex);
                    }
                    isVisited[successor] = true;
                    queue.add(successor);
                }
            }
        }
        return null;
    }

}




