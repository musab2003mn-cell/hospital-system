package com.hospital;

/**
 * Requirement 4: Patient Visit History - Singly Linked List
 *
 * Each patient has one of these, holding their previous hospital visits.
 * Supports: add visit, remove visit, search visit, display all visits.
 */
public class VisitLinkedList {

    // Internal node class for the singly linked list
    private static class Node {
        Visit visit;
        Node next;

        Node(Visit visit) {
            this.visit = visit;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public VisitLinkedList() {
        head = null;
        size = 0;
    }

    /** Add a new visit to the end of the list. */
    public void addVisit(Visit visit) {
        Node newNode = new Node(visit);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /** Remove a visit by its Visit ID. Returns true if removed. */
    public boolean removeVisit(int visitId) {
        if (head == null) {
            return false;
        }
        // Special case: removing the head node
        if (head.visit.getVisitId() == visitId) {
            head = head.next;
            size--;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.visit.getVisitId() == visitId) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false; // not found
    }

    /** Search for a visit by Visit ID. Returns the Visit or null if not found. */
    public Visit searchVisit(int visitId) {
        Node current = head;
        while (current != null) {
            if (current.visit.getVisitId() == visitId) {
                return current.visit;
            }
            current = current.next;
        }
        return null;
    }

    /** Display all visits in this patient's history. */
    public void displayVisits() {
        if (head == null) {
            System.out.println("   No visit history available.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.println("   " + current.visit);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }
}
