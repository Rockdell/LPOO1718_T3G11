package com.hotlinedrifter.ai;

/**
 * Edge of the graph.
 */
class Edge {

    /**
     * Destination node.
     */
    private Node    _dest;

    /**
     * Creates an object of Edge.
     * @param dest Destination node.
     */
    Edge(Node dest) {
        _dest = dest;
    }

    /**
     * Returns the destination of the edge.
     * @return Destination of the edge.
     */
    Node getDest() {
        return _dest;
    }

    /**
     * Return the weight of the edge.
     * @return Weight of the edge.
     */
    float getWeight() {
        return 1f;
    }
}