package org.example.manager;
import org.example.model.LogEntry;
import org.example.model.MoodLog;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * analyze the most common mood overall
 * days with extremely high or low mood
 * average energy level
 * average energy level for a specific month
 * notes associated with low energy
 * frequency of a specific word
 * most common task status
 */
public class AnalysisManager {
    private Scanner scanner;
    private LogManager logManager;

    public AnalysisManager(LogManager logManager, Scanner scanner){
        this.logManager = logManager;
        this.scanner = scanner;
    }
    public void analyzeEntries(){
        System.out.println("Welcome to the lab 🔬✨");
        boolean stay = true;
        while(stay){
            System.out.println();
            System.out.println("What do you want from the lab wizard🧙🏽‍♂️: ");
            System.out.println("1. Most common mood 🌈");
            System.out.println("2. Days with extremely low🪫 and high🔋 mood");
            System.out.println("3. Average energy level ⚡");
            System.out.println("4. Notes associated with lowest and highest energy 🧘🏽‍♀️");
            System.out.println("5. back to menu");
            System.out.print("Your choice👀:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            switch (choice){
                case 1: analyzeMood(); break;
                case 2: break;
                case 3: break;
                case 4: break;
                case 5: stay = false; break;

            }
        }
    }

    public void analyzeMood(){
        Map<String, List<LogEntry>> logs = logManager.getLogsByDate();
        Map<String, Integer> moodCounts = new HashMap<>();
        String mostCommonMood = null;
        int maxCount = 0;
        for(Map.Entry<String,List<LogEntry>> entry : logs.entrySet()){
            String date = entry.getKey();
            List<LogEntry> entries = entry.getValue();
            for(LogEntry log : entries){
                if(log instanceof MoodLog){
                    MoodLog moodLog = (MoodLog) log; //down casting
                    String mood = moodLog.getType();
                     //get the current count of this mood if it exists, or 0 if it doesn't
                     //then add 1 and store it.
                    moodCounts.put(mood,moodCounts.getOrDefault(mood,0)+1);
                }
            }
            for(Map.Entry<String,Integer> mood : moodCounts.entrySet()){
                if(mood.getValue() > maxCount){
                    mostCommonMood = mood.getKey();
                    maxCount = mood.getValue();
                }
            }
        }
        System.out.println("🌈 Most common mood: " + mostCommonMood + "("+maxCount+")");
    }


}
