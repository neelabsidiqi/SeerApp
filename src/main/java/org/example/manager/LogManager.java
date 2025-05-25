package org.example.manager;
import org.example.model.*;
import java.util.*;
/**
 * Manages user log entries like mood, energy, and tasks for specific dates.
 * Stores logs in a map where the key is the date and the value is a list of entries.
 */

public class LogManager {
    // to avoid multiple scanners, we are doing so the class accepts one
    private Scanner scanner;
    //key is date, value is List<LogEntry>
    Map<String,List<LogEntry>> logsByDate = new HashMap<>();


    public LogManager(Scanner scanner){
        this.scanner = scanner;
    }

    /**
     * Adds logs for a specific date by prompting the user to enter types of logs.
     */
    public void addLogs(){
        System.out.println("Enter the date for this log:");
        String date = scanner.nextLine();
        boolean adding = true;

        while(adding){
            System.out.println("What would you like to log? ");
            System.out.println("1. Mood");
            System.out.println("2. Energy");
            System.out.println("3. Task");
            System.out.println("4. back to menu");
            System.out.print("Your choice:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1: addMood(date); break;
                case 2: addEnergy(date); break;
                case 3: addTask(date);break;
                case 4: adding = false; break;

            }
        }
    }
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
