import org.example.model.*;
import org.example.manager.*;
import org.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class LogManagerTest {

    private LogManager logManager;

    @BeforeEach
    public void setUp() {
        // Empty scanner just for constructor injection
        logManager = new LogManager(new Scanner(""));
    }

    @Test
    public void testGetLogsByDate_containsSampleLog() {
        Map<String, List<LogEntry>> logs = logManager.getLogsByDate();
        assertTrue(logs.containsKey("2024-05-25"), "✨ Sample log date should exist!");

        List<LogEntry> entries = logs.get("2024-05-25");
        assertEquals(3, entries.size(), "🌈 Sample log should have 3 entries!");
    }

    @Test
    public void testAddMood() {
        LogManager lm = new LogManager(new Scanner(""));
        lm.addMood("2025-06-04", "joyful", 9);

        List<LogEntry> logs = lm.getLogsForDate("2025-06-04");
        assertEquals(1, logs.size());
        assertTrue(logs.get(0) instanceof MoodLog);
        MoodLog mood = (MoodLog) logs.get(0);
        assertEquals("joyful", mood.getType());
        assertEquals(9, mood.getLevel());
    }

    @Test
    public void testAddEnergy() {
        LogManager lm = new LogManager(new Scanner(""));
        lm.addEnergy("2025-06-04", 5, "Feeling meh");

        List<LogEntry> logs = lm.getLogsForDate("2025-06-04");
        assertEquals(1, logs.size());
        assertTrue(logs.get(0) instanceof EnergyLog);
        EnergyLog energy = (EnergyLog) logs.get(0);
        assertEquals(5, energy.getLevel());
        assertEquals("Feeling meh", energy.getNote());
    }

    @Test
    public void testAddTask() {
        LogManager lm = new LogManager(new Scanner(""));
        lm.addTask("2025-06-04", "Write tests", "In progress");

        List<LogEntry> logs = lm.getLogsForDate("2025-06-04");
        assertEquals(1, logs.size());
        assertTrue(logs.get(0) instanceof TaskLog);
        TaskLog task = (TaskLog) logs.get(0);
        assertEquals("Write tests", task.getTask());
        assertEquals("In progress", task.getStatus());
    }

    @Test
    public void testMergeLogs_mergesWithoutReplacing() {
        Map<String, List<LogEntry>> newLogs = new HashMap<>();
        List<LogEntry> list = new ArrayList<>();
        list.add(new MoodLog("excited", 10));
        newLogs.put("2024-05-25", list);

        logManager.mergeLogs(newLogs);

        List<LogEntry> merged = logManager.getLogsForDate("2024-05-25");
        assertEquals(4, merged.size(), "Should have 4 entries after merge 💖");
    }

    @Test
    public void testIsISODate() {
        assertTrue(LogManager.isISODate("2023-10-10"), "Valid ISO date should return true");
        assertFalse(LogManager.isISODate("10-10-2023"), "Wrong format should return false");
        assertFalse(LogManager.isISODate("today"), "Random words aren't dates, silly! 🤭");
    }

    // 💗 Utility overloads for testing without scanner input:
    private static class LogManager extends org.example.manager.LogManager {
        public LogManager(Scanner scanner) {
            super(scanner);
        }

        public void addMood(String date, String moodType, int moodLevel) {
            List<LogEntry> logs = getLogsByDate().getOrDefault(date, new ArrayList<>());
            logs.add(new MoodLog(moodType, moodLevel));
            getLogsByDate().put(date, logs);
        }

        public void addEnergy(String date, int level, String note) {
            List<LogEntry> logs = getLogsByDate().getOrDefault(date, new ArrayList<>());
            logs.add(new EnergyLog(level, note));
            getLogsByDate().put(date, logs);
        }

        public void addTask(String date, String name, String status) {
            List<LogEntry> logs = getLogsByDate().getOrDefault(date, new ArrayList<>());
            logs.add(new TaskLog(name, status));
            getLogsByDate().put(date, logs);
        }
    }
}
