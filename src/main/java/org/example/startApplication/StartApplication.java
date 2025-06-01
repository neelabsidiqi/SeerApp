package org.example.startApplication;
import org.example.manager.AnalysisManager;
import org.example.manager.LogManager;
import org.example.utils.LogFileHandler;
import java.util.Map;
import java.util.List;
import org.example.model.LogEntry;
import java.io.IOException;
import java.util.Scanner;
public class StartApplication {
    public static void main(String[] args)  {
        try{
            Scanner scanner = new Scanner(System.in);
            LogManager logManager = new LogManager(scanner);
            AnalysisManager analysisManager = new AnalysisManager(logManager,scanner);

            System.out.println("❤️‍🔥Welcome to Seer 🥰");
            boolean running = true;
            while(running){
                System.out.println("✨✨✨Choose an option✨✨✨");
                System.out.println("1.Add log➕");
                System.out.println("2.View logs👁️");
                System.out.println("3.Analyze patterns🔎");
                System.out.println("4.Save logs to file📁");
                System.out.println("5.Load logs form file📤");
                System.out.println("6.Exit🤗");
                System.out.print("Your coice👀: ");
                int choice = scanner.nextInt();
                System.out.println(" ");
                scanner.nextLine(); // the nextInt leaves newline character int the input buffer
                switch (choice){
                    case 1: logManager.addLogs(); break; // a method to show the log
                    case 2: logManager.viewLogs(); break;

                    case 3: analysisManager.analyzeEntries(); break;

                    case 4: LogFileHandler.saveLogsToFile(logManager.getLogsByDate());
                            break;
                    case 5:
                        System.out.print("Enter filename to load: ");
                        String fileName = scanner.nextLine();
                        Map<String, List<LogEntry>> loadedLogs = LogFileHandler.loadLogsFromFile(fileName);
                        if (!loadedLogs.isEmpty()) {
                            logManager.mergeLogs(loadedLogs);
                            System.out.println("Logs loaded successfully!");
                        } else {
                            System.out.println("No logs found or failed to load.");
                        }
                        break;

                    case 6: running = false; break;
                }
            }

        }catch (IOException e) {
            System.err.println("Error while saving logs: " + e.getMessage());
        }

    }
}