package com.hospital;

/**
 * Requirement 3: Treatment History - Stack (LIFO)
 *
 * Stores completed treatment records.
 * Supports: push, pop, display, empty handling.
 *
 * Implemented using a custom singly linked structure internally so the
 * LIFO behaviour is explicit rather than relying on java.util.Stack.
 */
public class TreatmentStack {

    private static class Node {
        TreatmentRecord record;
        Node next;

        Node(TreatmentRecord record) {
            this.record = record;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    public TreatmentStack() {
        top = null;
        size = 0;
    }

    /** Push a newly completed treatment record onto the stack. */
    public void push(TreatmentRecord record) {
        Node newNode = new Node(record);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /** Pop (remove and return) the most recently completed treatment record. */
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty. Nothing to pop.");
            return null;
        }
        TreatmentRecord record = top.record;
        top = top.next;
        size--;
        return record;
    }

    /** Display all treatment records, most recent first. */
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("   No treatment records available.");
            return;
        }
        Node current = top;
        while (current != null) {
            System.out.println("   " + current.record);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}
