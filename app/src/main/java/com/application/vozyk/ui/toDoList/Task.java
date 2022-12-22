package com.application.vozyk.ui.toDoList;


public class Task {
    private String name,startDate,endDate;
    private static int taskIdCounter;
    private boolean completed;
    private String taskId;

    public Task(String name, String startDate, String endDate) {
        this.name = name;
        if (startDate != null)
            this.startDate = startDate;
        if (endDate != null)
            this.endDate = endDate;
        completed = false;
        taskIdCounter ++;
        taskId = Integer.toString(taskIdCounter);
    }

    public Task() {}

    public String getTaskName() {
        return this.name;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskName(String name) {
        this.name = name;
    }

    public void setStartDate(String date) {
        this.startDate = date;
    }

    public void setEndDate(String date) {
        this.endDate = date;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public void setTaskId(String id) {this.taskId = id;}

}
