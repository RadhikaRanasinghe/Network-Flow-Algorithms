public class test {
    public static void main(String[] args) {
        AdjacencyList adj = new AdjacencyList();
        adj.add(new FlowEdge(0,1,0));
        adj.add(new FlowEdge(0,2,0));
        adj.add(new FlowEdge(0,4,0));
        System.out.println(adj);

//        for (FlowEdge e: ) {
//            System.out.println(e);
//        }
    }
}
