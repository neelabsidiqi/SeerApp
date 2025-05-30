package org.example.utils;
import org.example.model.LogEntry;
import org.example.model.EnergyLog;
import org.example.model.TaskLog;
import org.example.model.MoodLog;



import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
//import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogFileHandler {

    /**
     *saves all log entries groupes by date into a text file names "file.text"
     * Uses a BufferedWriter to writer data efficiently, and ensures the writer
     * is properly closed to flush all content to disk, preventing empty file.
     *
     * @param lbd is logsByDate
     * @throws IOException if an I/O error occurs during writing
     */
    public static void saveLogsToFile(Map<String,List<LogEntry>> lbd) throws IOException {
        System.out.println("saving....");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt"))) {
            for (Map.Entry<String, List<LogEntry>> entry : lbd.entrySet()) {
                writer.write("Date:" + entry.getKey());
                writer.newLine();
                for (LogEntry log : entry.getValue()) {
                    writer.write("- " + log.toString());
                    writer.newLine();
                }
                writer.newLine();
            }
        } //writer automatically flushed and closed here
        System.out.println("Logs has been saved successfully to file");
    }


    public static Map<String, List<LogEntry>> loadLogsFromFile(String fileName) throws IOException {
        Map<String, List<LogEntry>> logsByDate = new HashMap<>();
        Path path = Paths.get(fileName);

        if (!Files.exists(path)) {
            System.out.println("No such file found 😢 please check the name and try again sweetie.");
            return logsByDate;
        }

        List<String> lines = Files.readAllLines(path);
        String currentDate = null;

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            if (line.startsWith("Date:")) {
                currentDate = line.substring(5).trim();
                logsByDate.putIfAbsent(currentDate, new ArrayList<>());
            } else if (currentDate != null) {
                String[] parts = line.split("\\|");

                if (parts.length == 0) continue;

                String type = parts[0];
                LogEntry entry = null;

                try {
                    switch (type) {
                        case "MoodLog":
                            entry = new MoodLog(parts[1], Integer.parseInt(parts[2]));
                            break;
                        case "EnergyLog":
                            entry = new EnergyLog(Integer.parseInt(parts[1]), parts[2]);
                            break;
                        case "TaskLog":
                            entry = new TaskLog(parts[1], parts[2]);
                            break;
                        default:
                            System.out.println("Unknown log type: " + type);
                    }
                } catch (Exception e) {
                    System.out.println("Failed to parse line: " + line);
                    e.printStackTrace();
                }

                if (entry != null) {
                    logsByDate.get(currentDate).add(entry);
                }
            }
        }
        return logsByDate;
    }



}
