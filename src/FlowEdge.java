/**
 * Name: Radhika Ranasinghe
 * IIT ID: 2018199
 * UoW ID: W1761764
 */
import java.util.Objects;

public class FlowEdge {
    private final int u;
    private final int v;
    private final int capacity;
    private int flow;


    public FlowEdge(int u, int v, int capacity) {
        this.u = u;
        this.v = v;
        this.capacity = capacity;
        this.flow = 0;
    }

    public FlowEdge(int u, int v, int capacity, int flow) {
        this.u = u;
        this.v = v;
        this.capacity = capacity;
        this.flow = flow;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    public int other(int vertex) {
        if (vertex == u) {
            return v;
        } else if (vertex == v) {
            return u;
        } else {
            throw new IllegalArgumentException("Invalid ending vertex");
        }
    }

    public int residualCapacityTo(int vertex) {
        if (vertex == u) {
            return flow;
        } else if (vertex == v) {
            return capacity - flow;
        } else {
            throw new IllegalArgumentException("Invalid ending vertex");
        }
    }

    public void addResidualFlowTo(int vertex, int bottleNeckCapacity) {
        if (!(bottleNeckCapacity >= 0)) {
            throw new IllegalArgumentException("Bottle Neck Capacity must be non-negative");
        }

        if (vertex == u) {
            flow -= bottleNeckCapacity; // backward edge
        } else if (vertex == v) {
            flow += bottleNeckCapacity; // forward edge
        } else {
            throw new IllegalArgumentException("Invalid ending vertex");
        }

        if (!(flow >= 0)) {
            throw new IllegalArgumentException("The Flow of the edge is negative");
        }
        if (!(flow <= capacity)) {
            throw new IllegalArgumentException("The Flow is exceeding the capacity of the edge");
        }
    }

    public String toString() {
        return String.format("%d->%d %d %d", u, v, capacity, flow);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlowEdge)) return false;
        FlowEdge flowEdge = (FlowEdge) o;
        return u == flowEdge.u && v == flowEdge.v && getCapacity() == flowEdge.getCapacity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(u, v, getCapacity(), getFlow());
    }
}
