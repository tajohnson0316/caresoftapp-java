package com.caresoft.clinicapp;

import java.util.ArrayList;
public interface HIPAACompliantAdmin {

  ArrayList<String> reportSecurityIncidents();

  // DEFAULT IMPLEMENTED METHODS
  // Used to print security incidents.
  default void printSecurityIncidents() {
    System.out.println(reportSecurityIncidents());
  }

  // QA Test, PASS/FAIL of reports against QA expected results
  default boolean adminQATest(ArrayList<String> expectedIncidents) {
    if (reportSecurityIncidents().equals(expectedIncidents)) {
      System.out.println("PASS");
    }
    else {
      System.out.println("FAIL");
    }
    return reportSecurityIncidents().equals(expectedIncidents);
  }

}

