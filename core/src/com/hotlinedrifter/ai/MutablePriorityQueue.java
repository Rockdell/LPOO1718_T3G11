package com.hotlinedrifter.ai;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a Mutable Priority Queue.
 */
class MutablePriorityQueue    {

    /**
     * Heap.
     */
    private List<Node> _heap;

    /**
     * Creates an object of MutablePriorityQueue.
     */
    MutablePriorityQueue() {
        _heap = new ArrayList<Node>();
        _heap.add(null);
    }

    /**
     * Returns parent index.
     * @param i Child index.
     * @return Parent index of child.
     */
    private int parent(int i) {
        return i >> 1;
    }

    /**
     * Returns left child index.
     * @param i Parent index.
     * @return Left child index of parent.
     */
    private int leftChild(int i) {
        return i << 1;
    }

    /**
     * Checks if the heap is empty.
     * @return True if empty, false otherwise.
     */
    boolean empty() {
        return _heap.size() == 1;
    }

    /**
     * Extracts the minimum element from the heap.
     * @return Minimum element from the heap.
     */
    Node extractMin() {
        Node x = _heap.get(1);
        x.queueIndex = 0;
        _heap.set(1, _heap.get(_heap.size() - 1));
        _heap.remove(_heap.size() - 1);
        if (!empty())
            heapifyDown(1);
        return x;
    }

    /**
     * Inserts an element in the heap.
     * @param x Element to add.
     */
    void insert(Node x) {
        _heap.add(x);
        heapifyUp(_heap.size() - 1);
    }

    void decreaseKey(Node x) {
        heapifyUp(x.queueIndex);
    }

    /**
     * Heapifies up.
     * @param i Node to heapify.
     */
    private void heapifyUp(int i) {
        Node x = _heap.get(i);
        while (i > 1 && x.isSmaller(_heap.get(parent(i)))) {
            set(i, _heap.get(parent(i)));
            i = parent(i);
        }
        set(i, x);
    }

    /**
     * Heapifies down.
     * @param i Node to heapify
     */
    private void heapifyDown(int i) {
        Node x = _heap.get(i);
        while (true) {
            int k = leftChild(i);
            if (k >= _heap.size())
                break;
            if (k + 1 < _heap.size() && _heap.get(k + 1).isSmaller(_heap.get(k)))
                k++;
            if (!(_heap.get(k).isSmaller(x)))
                break;
            set(i, _heap.get(k));
            i = k;
        }
        set(i, x);
    }

    /**
     * Sets the index of element.
     * @param i Index
     * @param x Element.
     */
    private void set(int i, Node x) {
        _heap.set(i, x);
        x.queueIndex = i;
    }
}
