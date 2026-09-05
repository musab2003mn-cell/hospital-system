package com.hospital;

import java.util.LinkedList;

/**
 * Requirement 2: Emergency Patient Queue - Queue (FIFO)
 *
 * Manages patients waiting for emergency treatment.
 * Supports: enqueue, dequeue, display waiting patients, empty handling.
 *
 * Implemented using a linked-list-backed queue (java.util.LinkedList as a Deque)
 * to reflect the underlying FIFO data structure explicitly.
 */
public class EmergencyQueue {

    private LinkedList<Patient> queue;

    public EmergencyQueue() {
        queue = new LinkedList<>();
    }

    /** Add a patient to the back of the waiting queue. */
    public void enqueue(Patient patient) {
        queue.addLast(patient);
        System.out.println("Patient " + patient.getPatientId() + " (" + patient.getName() + ") added to emergency queue.");
    }

    /** Remove and return the next patient for treatment (front of the queue). */
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patient to treat.");
            return null;
        }
        Patient next = queue.removeFirst();
        System.out.println("Patient " + next.getPatientId() + " (" + next.getName() + ") is now being treated.");
        return next;
    }

    /** Display all patients currently waiting, in FIFO order. */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("   Emergency queue is empty.");
            return;
        }
        int position = 1;
        for (Patient p : queue) {
            System.out.println("   " + position + ". " + p);
            position++;
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
}
