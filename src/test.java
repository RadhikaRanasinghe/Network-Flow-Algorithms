import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
//        AdjacencyList adj = new AdjacencyList();
//        adj.add(new FlowEdge(0,1,0));
//        adj.add(new FlowEdge(0,2,0));
        FlowEdge e = new FlowEdge(0,2,4);
//        adj.add(e);
//        System.out.println(adj);

        FlowNetwork N = new FlowNetwork("InputData.txt");
        int s = 0, t = N.getV() - 1;
        FordFulkerson maxflow = new FordFulkerson(N, s, t);
        System.out.println("\nMax flow value = " + maxflow.getCurrentMaxFlow());
//        System.out.println(Arrays.toString(N.getAdj()));
        N.removeEdge(e);
        N.find(13);
//        System.out.println(Arrays.toString(N.getAdj()));
        FordFulkerson maxflow2 = new FordFulkerson(N, s, t);

        System.out.println("\nMax flow value = " + maxflow2.getCurrentMaxFlow());


    }
}
