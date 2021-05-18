/*
 * Name: Radhika Ranasinghe
 * IIT ID: 2018199
 * UoW ID: W1761764
 */

/**
 * This class takes the role of an edge of a FlowNetwork
 */
public class FlowEdge {
    private final int u;
    private final int v;
    private final int capacity;
    private int flow;


    /**
     * Constructor of the FlowEdge class
     *
     * @param u        starting vertex
     * @param v        ending vertex
     * @param capacity capacity of the edge
     */
    public FlowEdge(int u, int v, int capacity) {
        this.u = u;
        this.v = v;
        this.capacity = capacity;
        this.flow = 0;
    }

    /**
     * Constructor of the FlowEdge class
     *
     * @param u        starting vertex
     * @param v        ending vertex
     * @param capacity capacity of the edge
     * @param flow     flow of the edge
     */
    public FlowEdge(int u, int v, int capacity, int flow) {
        this.u = u;
        this.v = v;
        this.capacity = capacity;
        this.flow = flow;
    }

    /**
     * Getter of U
     *
     * @return int containing U
     */
    public int getU() {
        return u;
    }

    /**
     * Getter of V
     *
     * @return int containing V
     */
    public int getV() {
        return v;
    }

    /**
     * Getter of the capacity
     *
     * @return int containing capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Getter of the flow
     *
     * @return int containing the flow
     */
    public int getFlow() {
        return flow;
    }

    /**
     * Returns the opposite vertex
     *
     * @param vertex vertex to be checked
     * @return opposite vertex
     */
    public int other(int vertex) {
        if (vertex == u) {
            return v;
        } else if (vertex == v) {
            return u;
        } else {
            throw new IllegalArgumentException("Invalid ending vertex");
        }
    }

    /**
     * Method checking if its a forward flow or a backward flow
     *
     * @param vertex vertex of the path to checked
     * @return int containing the flow
     */
    public int residualCapacityTo(int vertex) {
        if (vertex == u) {
            return flow;
        } else if (vertex == v) {
            return capacity - flow;
        } else {
            throw new IllegalArgumentException("Invalid ending vertex");
        }
    }

    /**
     * Method adding the bottle neck capacity to the flow
     *
     * @param vertex             vertex to be checked
     * @param bottleNeckCapacity bottle neck capacity to be added
     */
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

    /**
     * toString method of the FlowEdge
     *
     * @return string containing the flowEdge
     */
    public String toString() {
        return String.format("%d->%d %d %d", u, v, capacity, flow);
    }

    /**
     * equals method of the FlowEdge
     *
     * @param o object to be checked
     * @return a boolean containing if its equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlowEdge)) return false;
        FlowEdge flowEdge = (FlowEdge) o;
        return u == flowEdge.u && v == flowEdge.v && getCapacity() == flowEdge.getCapacity();
    }

}
