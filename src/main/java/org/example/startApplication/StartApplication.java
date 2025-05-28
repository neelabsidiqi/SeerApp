package org.example.startApplication;

import org.example.manager.LogManager;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class StartApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LogManager logManager = new LogManager(scanner);
        System.out.println("Welcome to Seer 🥰");
        boolean running = true;

        while(running){
            System.out.println("Choose an option");
            System.out.println("1.Add log");
            System.out.println("2.View logs");
            System.out.println("3.Analyze patterns");
            System.out.println("4.Save logs to file");
            System.out.println("5.Load logs form file");
            System.out.println("6.Exit");
            System.out.print("Your coice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // the nextInt leaves newline character int the input buffer
            switch (choice){
                case 1: logManager.addLogs(); break; // a method to show the log
                case 2: logManager.viewLogs(); break;
                case 3: break;
                case 4: break;
                case 5: break;
                case 6: running = false; break;
            }
        }
    }
}