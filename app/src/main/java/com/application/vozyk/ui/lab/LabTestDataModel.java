package com.application.vozyk.ui.lab;

public class LabTestDataModel {
    int day,month,year;
    String testName,doctorName,key;

    public LabTestDataModel(int day, int month, int year, String testName, String doctorName) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.testName = testName;
        this.doctorName = doctorName;

    }

    public LabTestDataModel() {
    }
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}

