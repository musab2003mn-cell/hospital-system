package com.hospital;

/**
 * Represents a completed treatment, pushed onto the TreatmentStack
 * once a patient's emergency treatment is finished.
 */
public class TreatmentRecord {
    private int patientId;
    private String patientName;
    private String treatmentSummary;
    private String completionTime;

    public TreatmentRecord(int patientId, String patientName, String treatmentSummary, String completionTime) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatmentSummary = treatmentSummary;
        this.completionTime = completionTime;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId +
                " | Name: " + patientName +
                " | Treatment: " + treatmentSummary +
                " | Completed: " + completionTime;
    }
}
