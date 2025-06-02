package org.example.manager;
import org.example.model.DatedMood;
import org.example.model.EnergyLog;
import org.example.model.LogEntry;
import org.example.model.MoodLog;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class AnalysisManager {
    private Scanner scanner;
    private LogManager logManager;

    public AnalysisManager(LogManager logManager, Scanner scanner){
        this.logManager = logManager;
        this.scanner = scanner;
    }
    public void analyzeMood(){
        Map<String, Integer> moodCounts = new HashMap<>();
        String mostCommonMood = null;
        int maxCount = 0;
        List<LogEntry> allEntries = logManager.getAllEntries();
        for(LogEntry log : allEntries){
            if(log instanceof MoodLog){
                MoodLog moodLog = (MoodLog) log;  //down casting
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
        System.out.println("🌈 Most common mood: " + mostCommonMood + "("+maxCount+")");
    }
    public void analyzeMood(String yearMonth) {

        int maxCount = 0;
        String mostCommonMood = null;
        Map<String, Integer> moodsNmonth = new HashMap<>();
        Map<String,List<LogEntry>> logswithDate = logManager.getLogsByDate();

        for(Map.Entry<String,List<LogEntry>> lwd : logswithDate.entrySet()){
            String date = lwd.getKey();
             if(date.startsWith(yearMonth)){
                 List<LogEntry> logs = lwd.getValue();
                 for(LogEntry log : logs){
                     if(log instanceof MoodLog){
                         MoodLog moodLog = (MoodLog) log;
                         String mood = moodLog.getType();
                         moodsNmonth.put(mood,moodsNmonth.getOrDefault(mood,0)+1);
                     }
                 }
             }
           for(Map.Entry<String,Integer> mood : moodsNmonth.entrySet()){
               if(mood.getValue() > maxCount){
                   mostCommonMood = mood.getKey();
                   maxCount = mood.getValue();
               }
           }
        }
        if(mostCommonMood != null){
            System.out.println("🌈 Most common mood in " + yearMonth + ": " + mostCommonMood + " ("+ maxCount + ")");
        }else{
            System.out.println("😬 No mood entries found for " + yearMonth);
        }
    }

    public void extremeMoods() {

        List<DatedMood> allMoods = new ArrayList<>(); // ✅ Correct type
        Map<String, List<LogEntry>> allEntries = logManager.getLogsByDate();

        for (Map.Entry<String, List<LogEntry>> entry : allEntries.entrySet()) {
            String date = entry.getKey();
            List<LogEntry> entryLines = entry.getValue();

            for (LogEntry log : entryLines) {
                if (log instanceof MoodLog) {
                    MoodLog moodLog = (MoodLog) log;
                    allMoods.add(new DatedMood(date, moodLog)); // ✅ This now matches the list type
                }
            }
        }

        if (allMoods.isEmpty()) {
            System.out.println("No mood entries found.");
            return;
        }

        // Sort based on mood level (ascending)
        allMoods.sort(Comparator.comparingInt(dm -> dm.getMood().getLevel()));

        DatedMood lowest = allMoods.get(0);
        DatedMood highest = allMoods.get(allMoods.size() - 1);

        System.out.println("🪫 Lowest Mood:");
        System.out.println("   Date: " + lowest.getDate() + " ➤ " + lowest.getMood());

        System.out.println("🔋 Highest Mood:");
        System.out.println("   Date: " + highest.getDate() + " ➤ " + highest.getMood());
    }

    public void averageEnergy(){
        List<LogEntry> logentries = logManager.getAllEntries();

        int totalEnergy = 0;
        int count = 0;

        for(LogEntry log : logentries){
            if(log instanceof EnergyLog){
                EnergyLog energyLog = (EnergyLog) log;
                totalEnergy += energyLog.getLevel();
                count++;
            }
        }
        if(count == 0){
            System.out.println("No energy logs found to analyze. ");
        }else{
            double average = (double) totalEnergy/count;
            System.out.println("⚡ Average energy level: " + (float)average);
        }

    }


    public void analyzeEntries(){
        System.out.println("Welcome to the lab 🔬✨");
        boolean stay = true;
        while(stay){
            System.out.println();
            System.out.println("What do you want from the lab wizard🧙🏽‍♂️: ");
            System.out.println("1. Most common mood overall🌈");
            System.out.println("2. Most common mood for a specific month 🗓️🌈");
            System.out.println("3. Days with extremely low🪫 and high🔋 mood");
            System.out.println("4. Average energy level ⚡");
            System.out.println("5. back to menu");
            System.out.print("Your choice👀:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            switch (choice){
                case 1: analyzeMood(); break;
                case 2:
                    System.out.println("Enter the month and year sweetie 🧙🏽‍♂ (YYYY-MM)️: ");
                    String date = scanner.nextLine();
                    analyzeMood(date);
                    break;
                case 3: extremeMoods(); break;
                case 4: averageEnergy(); break;
                case 5: stay = false; break;

            }
        }
    }
}
