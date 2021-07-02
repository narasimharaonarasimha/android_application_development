package com.example.sharedpreferences3;

import java.util.List;

public class Student {
    private Integer studentID;
    private String studentName;
    private List<String> emails;

    public Student(Integer studentID, String studentName, List<String> emails) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.emails = emails;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
