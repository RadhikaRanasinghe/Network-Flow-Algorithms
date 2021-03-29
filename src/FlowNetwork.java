import java.io.*;

public class FlowNetwork {
    private int V; // Number of vertices
    private int E; // Number of edges
    private AdjacencyList<FlowEdge>[] adj; // Adjacency list

    public FlowNetwork(String fileName) throws IOException {
        File file = new File(fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String str = "";
        String verticesLine = br.readLine();
        String[] vert = verticesLine.split(" ");
        this.V = Integer.parseInt(vert[0]);

        int numOfLines = 0;

        adj = new AdjacencyList[V];

        for (int i = 0; i < adj.length; i++) {
            adj[i] = new AdjacencyList<>();
        }

        while ((str = br.readLine()) != null) {

            String[] values = str.split(" ");

            int v = Integer.parseInt(values[0]);
            int w = Integer.parseInt(values[1]);
            int edgeCapacity = Integer.parseInt(values[2]);
            addEdge(new FlowEdge(v, w, edgeCapacity));
            numOfLines++;
        }

        this.E = numOfLines;
    }

    public AdjacencyList<FlowEdge>[] getAdj() {
        return adj;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(FlowEdge edge) {
        int v = edge.from();

        int w = edge.to();


        adj[v].add(edge);
        adj[w].add(edge);
    }

    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" ").append(E).append("\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(":  ");
            for (FlowEdge edge : adj[v]) {
                if (edge.to() != v) s.append(edge).append("  ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
