package org.example.manager;
import com.fasterxml.jackson.core.JsonToken;
import org.example.model.*;
import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Manages user log entries like mood, energy, and tasks for specific dates.
 * Stores logs in a map where the key is the date and the value is a list of entries.
 */

public class LogManager {
    // to avoid multiple scanners, we are doing so the class accepts one
    private Scanner scanner;
    //key is date, value is List<LogEntry>
    Map<String,List<LogEntry>> logsByDate = new HashMap<>();
/********************************************************************************************/
    public LogManager(Scanner scanner){
        this.scanner = scanner;
        /**
         * Because of polymorphism in Java, a list of the parent type can hold any
         * object that is a subclass of LogEntry
         */
        List<LogEntry> sampleLog = new ArrayList<>();
        sampleLog.add(new MoodLog("happy", 8));
        sampleLog.add(new EnergyLog(7, "Felt good after a walk"));
        sampleLog.add(new TaskLog("Read a book", "Completed"));
        logsByDate.put("2024-05-25", sampleLog);

    }
    /********************************************************************************************/
    public Map<String, List<LogEntry>> getLogsByDate() {
        return logsByDate;
    }
    public Set<String> getAllDates(){
        return logsByDate.keySet();
    }
    public List<LogEntry> getLogsForDate(String date){
        return logsByDate.getOrDefault(date,Collections.emptyList());
    }
    public List<LogEntry> getAllEntries(){
        return logsByDate.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }
    /********************************************************************************************/
    public void mergeLogs(Map<String,List<LogEntry>> newLogs){
        for(Map.Entry<String,List<LogEntry>> entry : newLogs.entrySet()){
            String date = entry.getKey();
            List<LogEntry> newEntries = entry.getValue();

            logsByDate.putIfAbsent(date,new ArrayList<>());
            logsByDate.get(date).addAll(newEntries);
        }
    }
/********************************************************************************************/
    /**
     * viewLogs lets us view all teh logs we have made previously by date.
     * I made a variable that takes the key(date) from the map
     * and a List that contains the entry list from teh map
     * then I loop through the List to print each log.
     */
    public void viewLogs(){
        for(Map.Entry<String, List<LogEntry>> entry : logsByDate.entrySet()){

            String date = entry.getKey();
            List<LogEntry> logedEntries = entry.getValue();

            System.out.println("🐥Logs for:" + date);
            for(LogEntry log: logedEntries){
                System.out.println( "-" + log);
            }
            System.out.println("💖----------------------------------💖");
        }
    }

    //THE CODE BELLOW CONTAINS, addLog, addMood, addEnergy, addTask
/********************************************************************************************/
    /**
     * Adds logs for a specific date by prompting the user to enter types of logs.
     */
    public void addLogs() {
        String date;
        while(true){
            System.out.println("Enter the date for this log: (YYYY-MM-DD)");
            date = scanner.nextLine();
            if(isISODate(date)){
                break;
            }
        }
        boolean adding = true;
        while (adding) {
            System.out.println();
            System.out.println("What would you like to log? ");
            System.out.println("1. Mood 🌈");
            System.out.println("2. Energy 🔋");
            System.out.println("3. Task ✅");
            System.out.println("4. back to menu");
            System.out.print("Your choice👀:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            switch (choice) {
                case 1:
                    addMood(date);
                    break;
                case 2:
                    addEnergy(date);
                    break;
                case 3:
                    addTask(date);
                    break;
                case 4:
                    adding = false;
                    break;

            }
        }

    }

    public static boolean isISODate(String dateStr) {
        try {
            // This will throw an exception if not a valid ISO date
            LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("It seems like your date format is not correct 🤔");
            return false;
        }
    }
/********************************************************************************************/
    /**
     * Adds a mood entry for a specific date
     * @param date the date of the log
    */
    public void addMood(String date){
        System.out.print("Mood type: ");
        String mood = scanner.nextLine();
        System.out.print("Mood level: ");
        int moodLevel = scanner.nextInt();
        //if the date is not in the map yet, Java returns a new empty list
        //then dayLogs is now an empty list[]
        List<LogEntry> dayLogs = logsByDate.getOrDefault(date,new ArrayList<>());
        //we add the data in dayLogs
        dayLogs.add(new MoodLog(mood,moodLevel));
        //and add it to out map
        logsByDate.put(date,dayLogs);
    }
/********************************************************************************************/
    /**
     * Adds an energy entry for a specific date.
     * @param date The date of the log.
     */
    public void addEnergy(String date){
        System.out.print("Energy level (1-10): ");
        int energyLevel = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Note: ");
        String note = scanner.nextLine();

        List<LogEntry> dayLogs = logsByDate.getOrDefault(date,new ArrayList<>());
        dayLogs.add(new EnergyLog(energyLevel,note));
        logsByDate.put(date,dayLogs);
    }
/********************************************************************************************/
    /**
     * Adds a task entry for a specific date.
     * @param date The date of the log.
     */
    public void addTask(String date){
        System.out.print("Name of the Task: ");
        String name = scanner.nextLine();
        System.out.print("Status: ");
        String status = scanner.nextLine();
        List<LogEntry> dayLogs = logsByDate.getOrDefault(date,new ArrayList<>());
        dayLogs.add(new TaskLog(name,status));
        logsByDate.put(date,dayLogs);
    }

}
