package com.application.vozyk.ui.meds;

import java.util.ArrayList;

public class MedsRecordHandler {
    public String key;
    String name;
    String notes;
    Boolean beforeFood;
    ArrayList<Time.AlarmBundle> reminder;

    public MedsRecordHandler(String name, String notes, Boolean beforeFood, ArrayList<Time.AlarmBundle> reminder) {
        this.name = name;
        this.notes = notes;
        this.beforeFood = beforeFood;
        this.reminder = reminder;

    }

    public MedsRecordHandler() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getBeforeFood() {
        return beforeFood;
    }

    public void setBeforeFood(Boolean beforeFood) {
        this.beforeFood = beforeFood;
    }

    public ArrayList<Time.AlarmBundle> getReminder() {
        return reminder;
    }

    public void setReminder(ArrayList<Time.AlarmBundle> reminder) {
        this.reminder = reminder;
    }

}
