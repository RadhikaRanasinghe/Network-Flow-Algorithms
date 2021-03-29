import java.io.IOException;
import java.util.Scanner;

public class FordFulkerson {
    private final int V;          // number of vertices
    private boolean[] marked;     // marked[v] = true if s->v path in residual graph
    private FlowEdge[] edgeTo;    // edgeTo[v] = last edge on shortest residual s->v path
    private int currentMaxFlow;         // current value of max flow

    public FordFulkerson(FlowNetwork G, int s, int t) throws InterruptedException {
        V = G.V();
        System.out.println("The Augmenting Paths are as follows:");
        while (hasAugmentingPath(G, s, t)) {

            // compute bottleneck capacity
            int bottle = Integer.MAX_VALUE;
            StringBuilder paths= new StringBuilder();
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
                paths.insert(0, (" -> " + v));

            }
            paths.insert(0, (s));
            System.out.println(paths);

            // Augment flow
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottle);
            }

            currentMaxFlow += bottle;
        }

    }

    public int value()  {
        return currentMaxFlow;
    }

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        edgeTo = new FlowEdge[G.V()];
        marked = new boolean[G.V()];

        // breadth-first search
        Queue<Integer> queue = new Queue<Integer>();
        queue.enqueue(s);
        marked[s] = true;

        while (!queue.isEmpty() && !marked[t]) {
            int v = queue.dequeue();

            for (FlowEdge edge : G.adj(v)) {

                    int w = edge.other(v);

                    // if residual capacity from v to w
                    if (edge.residualCapacityTo(w) > 0) {
                        if (!marked[w]) {
                            edgeTo[w] = edge;
                            marked[w] = true;
                            queue.enqueue(w);
                        }
                    }

            }
        }
        return marked[t];
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter graph input file name: ");

        String fileName = sc.nextLine();

        FlowNetwork G = new FlowNetwork(fileName);
        System.out.println("\nThe Flow Network Graph is as follows:");
        System.out.println(G);
        int s = 0, t = G.V()-1;

        // compute maximum flow
        FordFulkerson maxflow = new FordFulkerson(G, s, t);
        System.out.println("\nMax flow value = " +  maxflow.value());
    }

}


