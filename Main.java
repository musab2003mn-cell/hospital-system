package com.hospital;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Mini Hospital Emergency Management System
 * CIT300 - Data Structures and Algorithms - Individual Mid Assignment
 *
 * Ties together all four required data structures:
 *  1. PatientBST      - patient records (Binary Search Tree)
 *  2. EmergencyQueue   - patients waiting for treatment (Queue)
 *  3. TreatmentStack   - completed treatment history (Stack)
 *  4. VisitLinkedList  - each patient's past visit history (Singly Linked List)
 */
public class Main {

    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();
    private static Scanner scanner = new Scanner(System.in);

    // Used to auto-generate Visit IDs
    private static int nextVisitId = 1;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1: registerPatient(); break;
                case 2: searchPatient(); break;
                case 3: deletePatient(); break;
                case 4: patientBST.displayInOrder(); break;

                case 5: addToEmergencyQueue(); break;
                case 6: treatNextPatient(); break;
                case 7: emergencyQueue.displayQueue(); break;

                case 8: treatmentStack.displayStack(); break;
                case 9: popTreatmentRecord(); break;

                case 10: addVisitHistory(); break;
                case 11: removeVisitHistory(); break;
                case 12: searchVisitHistory(); break;
                case 13: displayVisitHistory(); break;

                case 0:
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=================================================");
        System.out.println(" MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM");
        System.out.println("=================================================");
        System.out.println(" -- Patient Records (BST) --");
        System.out.println(" 1. Register new patient");
        System.out.println(" 2. Search patient by ID");
        System.out.println(" 3. Delete patient by ID");
        System.out.println(" 4. Display all patients (in-order by ID)");
        System.out.println(" -- Emergency Queue --");
        System.out.println(" 5. Add patient to emergency queue");
        System.out.println(" 6. Treat next patient (dequeue + push to history)");
        System.out.println(" 7. Display emergency queue");
        System.out.println(" -- Treatment History (Stack) --");
        System.out.println(" 8. Display treatment history");
        System.out.println(" 9. Pop most recent treatment record");
        System.out.println(" -- Patient Visit History (Linked List) --");
        System.out.println("10. Add visit to patient history");
        System.out.println("11. Remove visit from patient history");
        System.out.println("12. Search visit in patient history");
        System.out.println("13. Display patient visit history");
        System.out.println(" 0. Exit");
        System.out.println("=================================================");
    }

    // ---------- Patient BST operations ----------

    private static void registerPatient() {
        int id = readInt("Enter Patient ID: ");
        if (patientBST.search(id) != null) {
            System.out.println("A patient with this ID already exists.");
            return;
        }
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        int age = readInt("Enter Age: ");
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Medical Condition: ");
        String condition = scanner.nextLine();

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);
        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
        } else {
            System.out.println("Found: " + patient);
        }
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        boolean removed = patientBST.delete(id);
        System.out.println(removed ? "Patient deleted successfully." : "No patient found with ID " + id);
    }

    // ---------- Emergency Queue operations ----------

    private static void addToEmergencyQueue() {
        int id = readInt("Enter Patient ID to add to emergency queue: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No such patient in records. Please register the patient first.");
            return;
        }
        emergencyQueue.enqueue(patient);
    }

    private static void treatNextPatient() {
        Patient patient = emergencyQueue.dequeue();
        if (patient == null) {
            return;
        }
        System.out.print("Enter treatment summary: ");
        String summary = scanner.nextLine();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        TreatmentRecord record = new TreatmentRecord(patient.getPatientId(), patient.getName(), summary, timestamp);
        treatmentStack.push(record);
        System.out.println("Treatment recorded and pushed to history.");
    }

    // ---------- Treatment Stack operations ----------

    private static void popTreatmentRecord() {
        TreatmentRecord record = treatmentStack.pop();
        if (record != null) {
            System.out.println("Removed most recent record: " + record);
        }
    }

    // ---------- Visit Linked List operations ----------

    private static void addVisitHistory() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No such patient found.");
            return;
        }
        System.out.print("Enter Visit Date (e.g. 2026-08-15): ");
        String date = scanner.nextLine();
        System.out.print("Enter Doctor Name: ");
        String doctor = scanner.nextLine();
        System.out.print("Enter Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Enter Treatment: ");
        String treatment = scanner.nextLine();

        Visit visit = new Visit(nextVisitId++, date, doctor, diagnosis, treatment);
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Visit added (Visit ID: " + visit.getVisitId() + ").");
    }

    private static void removeVisitHistory() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No such patient found.");
            return;
        }
        int visitId = readInt("Enter Visit ID to remove: ");
        boolean removed = patient.getVisitHistory().removeVisit(visitId);
        System.out.println(removed ? "Visit removed." : "Visit ID not found.");
    }

    private static void searchVisitHistory() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No such patient found.");
            return;
        }
        int visitId = readInt("Enter Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);
        System.out.println(visit != null ? "Found: " + visit : "Visit ID not found.");
    }

    private static void displayVisitHistory() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No such patient found.");
            return;
        }
        System.out.println("Visit history for " + patient.getName() + ":");
        patient.getVisitHistory().displayVisits();
    }

    // ---------- Input helper ----------

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        return value;
    }
}
