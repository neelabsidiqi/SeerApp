package org.example.model;

public abstract class LogEntry {
    private String date;
    //the abstract class contains only the fields common to all types of logs
    /*private String mood;
    private String energy;
    private String task;*/

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public abstract String toString();


}
