package com.hotlinedrifter.ai;

import com.badlogic.gdx.math.Vector2;

import java.util.*;

/**
 * Graph with nodes representing map coordinates.
 */
public class Graph {

    /**
     * List of nodes.
     */
    private List<Node> _nodes;

    /**
     * Creates an object of Graph.
     */
    Graph() {
        _nodes = new ArrayList<Node>();
    }

    /**
     * Finds a node in the graph.
     * @param in Map coordinates.
     * @return Node found.
     */
    private Node findNode(Vector2 in) {

        for(Node node : _nodes) {
            if(node.getInfo() == in)
                return node;
        }

        return null;
    }

    /**
     * Adds a node to the graph.
     * @param in Map coordinates.
     */
    void addNode(Vector2 in) {

        if(findNode(in) != null)
            return;

        _nodes.add(new Node(in));
    }

    /**
     * Adds edge to the graph.
     * @param src Map coordinates (source).
     * @param dst Map coordinates (destination).
     */
    void addEdge(Vector2 src, Vector2 dst) {

        Node n1 = findNode(src);
        Node n2 = findNode(dst);

        if(n1 == null || n2 == null)
            return;

        n1.addEdge(n2);
    }

    /**
     * Initializes a node, required by the path-finding algorithm.
     * @param src Map coordinates (source).
     * @return Node initialized.
     */
    private Node initSource(Vector2 src) {

        for(Node node : _nodes) {
            node.setDist(Float.MAX_VALUE);
            node.setPath(null);
        }

        Node n = findNode(src);

        if(n == null)
            return null;

        n.setDist(0f);

        return n;
    }

    /**
     * Relax a node, required by the path-finding algorithm.
     * @param s Node to relax.
     * @param d Node to compare.
     * @param e Edge between the nodes
     * @return True if relaxed, false otherwise.
     */
    private boolean relax(Node s, Node d, Edge e) {

        if(s.getDist() + e.getWeight() < d.getDist()) {
            d.setDist(s.getDist() + e.getWeight());
            d.setPath(s);
            return true;
        }

        return false;
    }

    /**
     * Path-finding algorithm. Finds the shortest distance between the initial coordinate and the rest of the nodes.
     * @param in Map coordinates (source).
     */
    void dijkstra(Vector2 in) {

        Node src = initSource(in);

        MutablePriorityQueue queue = new MutablePriorityQueue();

        queue.insert(src);

        while(!queue.empty()) {

            Node n = queue.extractMin();

            for(Edge e : n.getAdj()) {

                float oldDist = e.getDest().getDist();

                if(relax(n, e.getDest(), e)) {

                    if(oldDist == Float.MAX_VALUE)
                        queue.insert(e.getDest());
                    else
                        queue.decreaseKey(e.getDest());
                }
            }
        }
    }

    /**
     * Reconstructs the path computed, starting from the final coordinate
     * @param dest Map coordinates (destination).
     * @return Path computed by the algorithm.
     */
    List<Vector2> getPath(Vector2 dest) {

        List<Vector2> path = new ArrayList<Vector2>();

        Node n = findNode(dest);

        if(n == null || n.getDist() == Float.MAX_VALUE)
            return null;

        for(; n != null; n = n.getPath()) {
            path.add(n.getInfo());
        }

        Collections.reverse(path);

        return path;
    }
}