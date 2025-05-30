package org.example.utils;
import org.example.model.LogEntry;

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

    public static void loadLogsFromFile() throws IOException{

        System.out.println("Name of your file: ");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        Path path = Paths.get(fileName);
        if(!Files.exists(path)){
            System.out.println("No such file found 😢 please check the name and try again sweetie.");
            return;
        }

        List<String> logs;
        try(Stream<String> lines = Files.lines(Paths.get(fileName))) {
            //logs = lines.collect(Collectors.toList());
            logs = lines.toList();
        }

        System.out.println(" Here are the logs form your file: ");
        for(String log: logs){
            System.out.println(log);
        }

    }

}
