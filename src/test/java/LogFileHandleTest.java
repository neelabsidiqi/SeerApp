import org.example.utils.*;

import org.example.model.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LogFileHandlerTest {
    private static final String TEST_FILE = "test_logs.txt";
    private Map<String, List<LogEntry>> sampleLogs;

    @BeforeEach
    void setUp() {
        sampleLogs = new LinkedHashMap<>();

        List<LogEntry> day1 = Arrays.asList(
                new MoodLog("happy", 8),
                new EnergyLog(6, "feeling okay"),
                new TaskLog("work", "did some coding")
        );

        List<LogEntry> day2 = Collections.singletonList(
                new MoodLog("tired", 3)
        );

        sampleLogs.put("2025-06-01", day1);
        sampleLogs.put("2025-06-02", day2);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE));
    }
    /*
    @Test
    void testSaveAndLoadLogs() throws IOException {
        // Save logs
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            for (Map.Entry<String, List<LogEntry>> entry : sampleLogs.entrySet()) {
                writer.write("Date:" + entry.getKey());
                writer.newLine();
                for (LogEntry log : entry.getValue()) {
                    writer.write(log.toString());
                    writer.newLine();
                }
                writer.newLine();
            }
        }

        // Simulate reading that file
        Map<String, List<LogEntry>> loadedLogs = LogFileHandler.loadLogsFromFile(TEST_FILE);

        // Checks:
        assertEquals(sampleLogs.size(), loadedLogs.size(), "Dates should match");

        for (String date : sampleLogs.keySet()) {
            List<LogEntry> originalList = sampleLogs.get(date);
            List<LogEntry> loadedList = loadedLogs.get(date);

            assertNotNull(loadedList, "Loaded logs should not be null for " + date);
            assertEquals(originalList.size(), loadedList.size(), "Number of entries should match for " + date);

            for (int i = 0; i < originalList.size(); i++) {
                assertEquals(originalList.get(i).getClass(), loadedList.get(i).getClass(), "Entry type should match");
            }
        }
    }*/

    @Test
    void testLoadFromMissingFile() throws IOException {
        Map<String, List<LogEntry>> result = LogFileHandler.loadLogsFromFile("nonexistent.txt");
        assertTrue(result.isEmpty(), "Should return empty map for missing file");
    }

    @Test
    void testSaveLogsActuallyCreatesFile() throws IOException {
        LogFileHandler.saveLogsToFile(sampleLogs);
        File file = new File("file.txt");
        assertTrue(file.exists(), "File should exist after saving");
        assertTrue(file.length() > 0, "File should not be empty");
        // Optional: delete it after
        file.delete();
    }
}

