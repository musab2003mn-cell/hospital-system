package com.hospital;

/**
 * Requirement 1: Patient Records - Binary Search Tree (BST)
 *
 * Stores Patient objects keyed by Patient ID.
 * Supports: insert, search, delete, in-order traversal (ascending ID).
 */
public class PatientBST {

    // Internal node class for the BST
    private static class Node {
        Patient patient;
        Node left, right;

        Node(Patient patient) {
            this.patient = patient;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public PatientBST() {
        root = null;
    }

    /** Insert a new patient into the tree, keyed by Patient ID. */
    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private Node insertRec(Node node, Patient patient) {
        if (node == null) {
            return new Node(patient);
        }
        if (patient.getPatientId() < node.patient.getPatientId()) {
            node.left = insertRec(node.left, patient);
        } else if (patient.getPatientId() > node.patient.getPatientId()) {
            node.right = insertRec(node.right, patient);
        } else {
            System.out.println("A patient with ID " + patient.getPatientId() + " already exists. Insert skipped.");
        }
        return node;
    }

    /** Search for a patient by Patient ID. Returns the Patient or null if not found. */
    public Patient search(int patientId) {
        Node result = searchRec(root, patientId);
        return (result == null) ? null : result.patient;
    }

    private Node searchRec(Node node, int patientId) {
        if (node == null || node.patient.getPatientId() == patientId) {
            return node;
        }
        if (patientId < node.patient.getPatientId()) {
            return searchRec(node.left, patientId);
        }
        return searchRec(node.right, patientId);
    }

    /** Delete a patient by Patient ID. Returns true if a patient was found and removed. */
    public boolean delete(int patientId) {
        if (search(patientId) == null) {
            return false;
        }
        root = deleteRec(root, patientId);
        return true;
    }

    private Node deleteRec(Node node, int patientId) {
        if (node == null) {
            return null;
        }
        if (patientId < node.patient.getPatientId()) {
            node.left = deleteRec(node.left, patientId);
        } else if (patientId > node.patient.getPatientId()) {
            node.right = deleteRec(node.right, patientId);
        } else {
            // Node found: handle the three deletion cases

            // Case 1: no children
            if (node.left == null && node.right == null) {
                return null;
            }
            // Case 2: one child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // Case 3: two children - replace with the in-order successor
            // (smallest value in the right subtree)
            Node successor = findMin(node.right);
            node.patient = successor.patient;
            node.right = deleteRec(node.right, successor.patient.getPatientId());
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /** Perform an in-order traversal, printing patients in ascending order of Patient ID. */
    public void displayInOrder() {
        if (root == null) {
            System.out.println("   No patient records found.");
            return;
        }
        inOrderRec(root);
    }

    private void inOrderRec(Node node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.println("   " + node.patient);
            inOrderRec(node.right);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }
}
