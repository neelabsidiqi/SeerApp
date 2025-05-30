package org.example.model;

public class EnergyLog extends LogEntry {

    private int level;
    private String note;

    public EnergyLog(int level, String note) {
        this.level = level;
        this.note = note;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "EnergyLog|"+ level + "|"+note;
    }
}
