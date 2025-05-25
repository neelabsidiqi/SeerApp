package org.example.model;

public class TaskLog extends LogEntry{
    private String task;
    private String status;

    public TaskLog(String name, String status) {
        this.task = name;
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String name) {
        this.task = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void display() {
        System.out.println("Task: "+ task + "| Completed: " + status);
    }

}
