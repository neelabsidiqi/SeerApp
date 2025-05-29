package org.example.utils;
import org.example.model.LogEntry;
import java.util.*;
import java.io.*;
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
                writer.write("Date: " + entry.getKey());
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

}
