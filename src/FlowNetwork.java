/*
 * Name: Radhika Ranasinghe
 * IIT ID: 2018199
 * UoW ID: W1761764
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class takes the role of the a flow network where it's edges has a
 * capacity and a flow.
 *
 * @author Radhika Ranasinghe
 */
public class FlowNetwork {
    private int v; // Number of vertices
    private int e; // Number of edges
    private EdgeList<FlowEdge>[] adj; // Adjacency list

    /**
     * Constructor of the FlowNetwork class
     *
     * @param fileName name of the file containing the graph
     * @throws IOException for the file reader
     */
    public FlowNetwork(String fileName) throws IOException {
        // Opening the file with graph
        File file = new File(fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));

        // Reading the first line and assigning it V
        String str = "";
        String verticesLine = br.readLine();
        String[] vert = verticesLine.split(" ");
        this.v = Integer.parseInt(vert[0]);

        // Initializing variable called 'numOfLine' and assigning 0 to it
        int numOfLines = 0;

        // Assigning an EdgeList[] with size v
        adj = new EdgeList[v];

        // Iterating through adj and assigning a new EdgeList to adj[i]
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new EdgeList<>();
        }

        // Iterating until all lines are read
        while ((str = br.readLine()) != null) {
            // Splitting the values
            String[] values = str.split(" ");

            // Passing the values into edge
            int v = Integer.parseInt(values[0]);
            int w = Integer.parseInt(values[1]);
            int edgeCapacity = Integer.parseInt(values[2]);
            addEdge(new FlowEdge(v, w, edgeCapacity));
            numOfLines++;
        }

        // Num of edges is updated
        this.e = numOfLines;
    }

    /**
     * Method to return the adj array
     *
     * @return EdgeList array
     */
    public EdgeList<FlowEdge>[] getAdj() {
        return adj;
    }

    /**
     * Method to return the vertices
     *
     * @return int V
     */
    public int getV() {
        return v;
    }

    /**
     * Method to return the Edges
     *
     * @return int E
     */
    public int getE() {
        return e;
    }

    /**
     * Method to add an edge to the FlowNetwork
     *
     * @param edge edge to be added to flow network
     */
    public void addEdge(FlowEdge edge) {
        int v = edge.getU();
        int w = edge.getV();
        adj[v].add(edge);
        adj[w].add(edge);
    }


    /**
     * Method to remove an edge from the FLowNetwork
     *
     * @param edge edge that is to be removed from the flow network
     */
    public void removeEdge(FlowEdge edge) {

        for (int i = 0; i < adj.length; i++) {
            EdgeList<FlowEdge> adjacencyList = new EdgeList<>();

            for (FlowEdge flowEdge : adj(i)) {
                if (!flowEdge.equals(edge)) {
                    adjacencyList.add(flowEdge);
                }
            }

            this.adj[i] = adjacencyList;
        }

    }

    /**
     * This method is used get all the edges involved with a vertex.
     *
     * @param vertex vertex involved
     * @return list containing two arrays, first array containing edges the starting vertex involved,
     * second array containing the edges ending vertex involved
     */
    public List[] find(int vertex) {
        List<FlowEdge> listU = new ArrayList<>();
        List<FlowEdge> listV = new ArrayList<>();
        for (int i = 0; i < adj.length; i++) {
            for (FlowEdge flowEdge : adj(i)) {
                if (flowEdge.getU() == vertex) {
                    listU.add(flowEdge);
                }
                if (flowEdge.getV() == vertex) {
                    listV.add(flowEdge);
                }
            }

        }
        List[] findArr = new List[2];
        findArr[0] = listU;
        findArr[1] = listV;
        return findArr;
    }


    /**
     * Method returning the adj
     *
     * @param v vertex involved
     * @return An Iterable
     */
    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }

    /**
     * toString method of the class FlowNetwork
     *
     * @return string containing the FlowNetwork
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(v).append(" ").append(e).append("\n");
        for (int v = 0; v < this.v; v++) {
            s.append(v).append(":  ");
            for (FlowEdge edge : adj[v]) {
                if (edge.getV() != v) s.append(edge).append("  ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * Setter of V
     *
     * @param v int containing v
     */
    public void setV(int v) {
        this.v = v;
    }

    /**
     * Setter of E
     *
     * @param e int containing E
     */
    public void setE(int e) {
        this.e = e;
    }

    /**
     * Setter of adj
     *
     * @param adj EdgeList[] containing adj
     */
    public void setAdj(EdgeList<FlowEdge>[] adj) {
        this.adj = adj;
    }
}
