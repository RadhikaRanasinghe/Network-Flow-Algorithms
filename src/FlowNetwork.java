/**
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
 * @author Radhika Ranasinghe
 */
public class FlowNetwork {
    private int v; // Number of vertices
    private int e; // Number of edges
    private EdgeList<FlowEdge>[] adj; // Adjacency list

    public FlowNetwork() {
    }

    public FlowNetwork(String fileName) throws IOException {
        File file = new File(fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String str = "";
        String verticesLine = br.readLine();
        String[] vert = verticesLine.split(" ");
        this.v = Integer.parseInt(vert[0]);

        int numOfLines = 0;

        adj = new EdgeList[v];

        for (int i = 0; i < adj.length; i++) {
            adj[i] = new EdgeList<>();
        }

        while ((str = br.readLine()) != null) {
            String[] values = str.split(" ");
            int v = Integer.parseInt(values[0]);
            int w = Integer.parseInt(values[1]);
            int edgeCapacity = Integer.parseInt(values[2]);
            addEdge(new FlowEdge(v, w, edgeCapacity));
            numOfLines++;
        }

        this.e = numOfLines;
    }

    public EdgeList<FlowEdge>[] getAdj() {
        return adj;
    }

    public int getV() {
        return v;
    }

    public int getE() {
        return e;
    }

    public void addEdge(FlowEdge edge) {
        int v = edge.getU();
        int w = edge.getV();
        adj[v].add(edge);
        adj[w].add(edge);
    }


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
     * @param vertex
     * @return
     */
    public List[] find(int vertex) {
        List<FlowEdge> listU = new ArrayList<>();
        List<FlowEdge> listV = new ArrayList<>();
        for (int i = 0; i < adj.length; i++){
            for (FlowEdge flowEdge : adj(i)) {
                if(flowEdge.getU() == vertex){
                    listU.add(flowEdge);
                }
                if(flowEdge.getV() == vertex){
                    listV.add(flowEdge);
                }
            }

        }
        List[] findArr = new List[2];
        findArr[0] = listU;
        findArr[1] = listV;
        return findArr;
    }


    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }

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

    public void setV(int v) {
        this.v = v;
    }

    public void setE(int e) {
        this.e = e;
    }

    public void setAdj(EdgeList<FlowEdge>[] adj) {
        this.adj = adj;
    }
}
