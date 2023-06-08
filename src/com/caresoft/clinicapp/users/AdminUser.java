package com.caresoft.clinicapp.users;

import com.caresoft.clinicapp.HIPAACompliantAdmin;
import com.caresoft.clinicapp.HIPAACompliantUser;

import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {
  private Integer employeeID;
  private String role;
  private ArrayList<String> securityIncidents;

  public AdminUser(Integer id, String role) {
    super(id);
    this.role = role;
    securityIncidents = new ArrayList<>();
  }

  @Override
  public ArrayList<String> reportSecurityIncidents() {
    return securityIncidents;
  }

  @Override
  public void printSecurityIncidents() {
    HIPAACompliantAdmin.super.printSecurityIncidents();
  }

  @Override
  public boolean adminQATest(ArrayList<String> expectedIncidents) {
    return HIPAACompliantAdmin.super.adminQATest(expectedIncidents);
  }

  @Override
  public boolean assignPin(int pin) {
    int length = (int) (Math.log10(pin) + 1);

    if (length >= 6) {
      this.pin = pin;
      return true;
    }

    return false;
  }

  @Override
  public boolean accessAuthorized(Integer confirmedAuthID) {
    if (!this.id.equals(confirmedAuthID)) {
      this.authIncident();
      return false;
    }

    return true;
  }

  public void newIncident(String notes) {
    String report = String.format(
      "Datetime Submitted: %s%n" +
        "Reported by ID: %d%n" +
        "Notes: %s%n",
      new Date(), this.id, notes
    );
    securityIncidents.add(report);
  }

  public void authIncident() {
    String report = String.format(
      "Datetime Submitted: %s%n," +
        "ID %d" +
        "Notes: %s%n",
      new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
    );
    securityIncidents.add(report);
  }

  public Integer getEmployeeID() {
    return employeeID;
  }

  public void setEmployeeID(Integer employeeID) {
    this.employeeID = employeeID;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public ArrayList<String> getSecurityIncidents() {
    return securityIncidents;
  }

  public void setSecurityIncidents(ArrayList<String> securityIncidents) {
    this.securityIncidents = securityIncidents;
  }
}
