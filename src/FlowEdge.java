public class FlowEdge {
    private final int v;
    private final int w;
    private final int capacity;
    private int flow;


    public FlowEdge(int v, int w, int capacity) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        this.flow = 0;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    public int other(int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        } else {
            throw new IllegalArgumentException("Invalid ending vertex");
        }
    }

    public int residualCapacityTo(int vertex) {
        if (vertex == v) {
            return flow;
        } else if (vertex == w) {
            return capacity - flow;
        } else {
            throw new IllegalArgumentException("Invalid ending vertex");
        }
    }

    public void addResidualFlowTo(int vertex, double delta) {
        if (!(delta >= 0.0)) {
            throw new IllegalArgumentException("Delta must be non-negative");
        }

        if (vertex == v) {
            flow -= delta; // backward edge
        } else if (vertex == w) {
            flow += delta; // forward edge
        } else {
            throw new IllegalArgumentException("Invalid ending vertex");
        }

        if (!(flow >= 0.0)) {
            throw new IllegalArgumentException("The Flow of the edge is negative");
        }
        if (!(flow <= capacity)) {
            throw new IllegalArgumentException("The Flow is exceeding the capacity of the edge");
        }
    }

    public String toString() {
        return String.format("%d->%d %d %d", v, w, capacity, flow);
    }
}
