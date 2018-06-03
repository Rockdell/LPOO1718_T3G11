package com.hotlinedrifter.ai;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Node of the graph.
 */
class Node {

    /**
     * Info of the node.
     */
    private Vector2     _info;

    /**
     * List of outgoing edges.
     */
    private List<Edge>  _adj;

    /**
     * Current distance.
     */
    private float       _dist;

    /**
     * Current path.
     */
    private Node        _path;

    /**
     * Queue index, required by MutablePriorityQueue.
     */
    int queueIndex = 0;

    /**
     * Creates an object of Node
     * @param info Info of the node.
     */
    Node(Vector2 info) {
        _info = info;
        _adj = new ArrayList<Edge>();
        _dist = 0;
        _path = null;
    }

    /**
     * Returns info of the node.
     * @return Info of the node
     */
    Vector2 getInfo() {
        return _info;
    }

    /**
     * Returns list of outgoing nodes.
     * @return List of outgoing nodes.
     */
    List<Edge> getAdj() {
        return _adj;
    }

    /**
     * Returns current distance.
     * @return Current distance.
     */
    float getDist() {
        return _dist;
    }

    /**
     * Returns current path.
     * @return Current path.
     */
    Node getPath() {
        return _path;
    }

    /**
     * Adds edge to a node.
     * @param node Node to connect.
     */
    void addEdge(Node node) {
        _adj.add(new Edge(node));
    }

    /**
     * Sets current distance.
     * @param dist New distance.
     */
    void setDist(float dist) {
        _dist = dist;
    }

    /**
     * Sets current path.
     * @param path New path.
     */
    void setPath(Node path) {
        _path = path;
    }

    /**
     * Compare two nodes
     * @param n Node to be compared.
     * @return True if current distance is smaller than the node's distance.
     */
    boolean isSmaller(Node n) {
        return _dist < n.getDist();
    }
}