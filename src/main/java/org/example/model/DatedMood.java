package org.example.model;

public class DatedMood {
    private String date;
    private MoodLog mood;

    public DatedMood(String date, MoodLog mood) {
        this.date = date;
        this.mood = mood;
    }

    public String getDate() {
        return date;
    }

    public MoodLog getMood() {
        return mood;
    }

    @Override
    public String toString() {
        return date + " ➤ " + mood.toString();
    }
}
