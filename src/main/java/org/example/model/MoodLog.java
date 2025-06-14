package org.example.model;

public class MoodLog extends LogEntry  implements Comparable<MoodLog>{
    private String type;
    private int level;

    public MoodLog(String type, int level){
        this.type = type;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "MoodLog|"+ type + "|"+level;
    }

    @Override
    public int compareTo(MoodLog other) {
        return Integer.compare(this.level,other.level);//low to high
        //to use Collections.sort(moodLogs)
    }
}
