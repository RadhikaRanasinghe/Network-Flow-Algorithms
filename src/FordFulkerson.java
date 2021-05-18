/*
 * Name: Radhika Ranasinghe
 * IIT ID: 2018199
 * UoW ID: W1761764
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;


/**
 * This class is used for the computation of the Max-Flow in a flow network.
 *
 * @author Radhika Ranasinghe
 */
public class FordFulkerson {
    private final int V;          // number of vertices
    private boolean[] marked;     // marked[v] = true if s->v path in residual graph
    private FlowEdge[] edgeTo;    // edgeTo[v] = last edge on shortest residual s->v path
    private int currentMaxFlow;         // current value of max flow

    /**
     * This method is used to compute the maximum flow of the graph given as a parameter
     *
     * @param graph The flow network that is to be calculated from
     * @param s     the source vertex of the graph
     * @param t     the sink vertex of the graph
     */
    public FordFulkerson(FlowNetwork graph, int s, int t) {

        // The vertices of the flow network is set as the vertices
        V = graph.getV();
        System.out.println("The Augmenting Paths are as follows:");

        // Iterating until all paths are found in the flow network
        while (hasAugmentingPath(graph, s, t)) {

            // Initializing a variable called 'bottleNeckCapacity' and assigning the Maximum possible integer value to it
            int bottleNeckCapacity = Integer.MAX_VALUE;

            // Initializing a variable called 'path' to assign augmenting path found to 't'
            StringBuilder path = new StringBuilder();

            // Iterating through edgeTo[] from t node to the s node
            for (int v = t; v != s; v = edgeTo[v].other(v)) {

                // Assigning the minimum out of the residual capacity returned for the edgeTo[v] or the existing 'bottleNeckCapacity'
                bottleNeckCapacity = Math.min(bottleNeckCapacity, edgeTo[v].residualCapacityTo(v));
                path.insert(0, (" -> " + v));

            }
            path.insert(0, (s));
            // Printing the path found
            System.out.println(path);

            // Augment flow
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                // Adding the bottle neck capacity as the residual flow of the edgeTo[v]
                edgeTo[v].addResidualFlowTo(v, bottleNeckCapacity);
            }

            // The current max flow is updated with the computed bottle neck capacity
            currentMaxFlow += bottleNeckCapacity;
        }

    }

    /**
     * This method returns the maximum flow
     *
     * @return the value  of current maximum flow
     */
    public int getCurrentMaxFlow() {
        return currentMaxFlow;
    }

    /**
     * This method checks if the there are any augmenting paths in the flow network
     *
     * @param flowNetwork the flow network to be calculate from
     * @param s           the source vertex of the graph
     * @param t           the sink vertex of the graph
     * @return boolean array with if the edge is marked or not
     */
    private boolean hasAugmentingPath(FlowNetwork flowNetwork, int s, int t) {
        // Assigning a FlowEdge with the FlowNetwork Vertices to the 'edgeTo' variable
        edgeTo = new FlowEdge[flowNetwork.getV()];

        // Assigning a boolean with the FlowNetwork Vertices to the 'marked' variable
        marked = new boolean[flowNetwork.getV()];

        // Initializing variable called 'queue' and assigning a Queue data type to it
        Queue<Integer> queue = new Queue<Integer>();

        // Enqueue the source vertex
        queue.enqueue(s);

        // Marking the source vertex as 'true'
        marked[s] = true;

        // Until the queue is not empty and the target vertex is not marked 'true' while loop will run
        while (!queue.isEmpty() && !marked[t]) {

            // Initializing variable called 'v' and assigning the object returned by the dequeue method
            int v = queue.dequeue();

            // Iterating through edges starting with vertex 'v'
            for (FlowEdge edge : flowNetwork.adj(v)) {

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

    /**
     * Driver program of the class Ford-Fulkerson class
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        // Benchmark to be loaded
        String fileName = "benchmarks/test.txt";

        // Substring creation for the results file
        String resultFile = fileName.substring(11);


        FlowNetwork flowNetwork = null;
        try {
            flowNetwork = new FlowNetwork(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Printing the graph
        System.out.println("\nThe Flow Network Graph is as follows:");
        System.out.println(flowNetwork);
        int s = 0, t = Objects.requireNonNull(flowNetwork).getV() - 1;

        // Starting the timer
        long start = System.currentTimeMillis();

        // compute maximum flow
        FordFulkerson maxFlow = new FordFulkerson(flowNetwork, s, t);
        System.out.println("\nMax flow value = " + maxFlow.getCurrentMaxFlow());

        // Stopping the timer
        long finish = System.currentTimeMillis();

        // Calculating the elapsed time
        long timeElapsed = finish - start;

        System.out.println("Time Elapsed :" + timeElapsed);

        String message = "The Flow Network Graph is as follows:\n" + flowNetwork + "\nMax flow value = " + maxFlow.getCurrentMaxFlow()
                + "\nTime Elapsed :" + timeElapsed + "ms";

        // writing results to a file
        writeToFile(resultFile, message);
    }

    /**
     * Method to write to a file
     *
     * @param fileName string containing the file name
     * @param message  string containing the message to write
     */
    public static void writeToFile(String fileName, String message) {
        try {
            FileWriter fileWriter = new FileWriter("Results/" + fileName);
            fileWriter.write(message);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


