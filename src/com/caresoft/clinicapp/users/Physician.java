package com.caresoft.clinicapp.users;

import com.caresoft.clinicapp.HIPAACompliantUser;

import java.util.ArrayList;
import java.util.Date;

public class Physician extends User implements HIPAACompliantUser {
  private ArrayList<String> patientNotes;

  public Physician(Integer id) {
    super(id);
  }

  @Override
  public boolean assignPin(int pin) {
    int length = (int) (Math.log10(pin) + 1);

    if (length == 4) {
      this.pin = pin;
      return true;
    }

    return false;
  }

  @Override
  public boolean accessAuthorized(Integer confirmedAuthID) {
    return this.id.equals(confirmedAuthID);
  }

  public void newPatientNotes(String notes, String patientName, Date date) {
    String report = String.format(
      "Datetime Submitted: %s%n", date);
    report += String.format("Reported By ID: %d%n", this.id);
    report += String.format("Patient Name: %s%n", patientName);
    report += String.format("Notes: %s%n", notes);
    this.patientNotes.add(report);
  }

  public ArrayList<String> getPatientNotes() {
    return patientNotes;
  }

  public void setPatientNotes(ArrayList<String> patientNotes) {
    this.patientNotes = patientNotes;
  }
}
