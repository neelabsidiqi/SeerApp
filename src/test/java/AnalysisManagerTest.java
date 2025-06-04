import org.example.manager.*;

import org.example.model.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AnalysisManagerTest {
    private LogManager logManager;
    private AnalysisManager analysisManager;

    @BeforeEach
    public void setUp() {
        logManager = new LogManager(new Scanner(""));
        analysisManager = new AnalysisManager(logManager, new Scanner(""));
    }

    @Test
    public void testAnalyzeMood_overallMostCommonMood() {
        // Clear and set up logs manually
        logManager.getLogsByDate().clear();

        // Add moods: 3 happy, 2 sad
        addMood("2024-06-01", "happy", 7);
        addMood("2024-06-02", "happy", 8);
        addMood("2024-06-03", "happy", 6);
        addMood("2024-06-04", "sad", 3);
        addMood("2024-06-05", "sad", 2);

        // Capture output 📝
        analysisManager.analyzeMood(); // should print happy (3)
    }

    @Test
    public void testAnalyzeMood_specificMonth() {
        logManager.getLogsByDate().clear();

        addMood("2024-05-01", "grumpy", 3);
        addMood("2024-05-02", "grumpy", 4);
        addMood("2024-05-03", "calm", 8);
        addMood("2024-06-01", "grumpy", 2); // should be ignored

        analysisManager.analyzeMood("2024-05"); // should pick grumpy (2)
    }

    @Test
    public void testExtremeMoods_findsMinMax() {
        logManager.getLogsByDate().clear();

        addMood("2024-06-01", "sad", 1); // lowest
        addMood("2024-06-02", "meh", 5);
        addMood("2024-06-03", "joy", 10); // highest

        analysisManager.extremeMoods();
    }

    @Test
    public void testAverageEnergy() {
        logManager.getLogsByDate().clear();

        addEnergy("2024-06-01", 6, "okay");
        addEnergy("2024-06-02", 8, "better");
        addEnergy("2024-06-03", 10, "best");

        analysisManager.averageEnergy(); // avg should be 8.0
    }

    /*******************
     * Utility Helpers 💁🏽‍♀️💓
     *******************/
    private void addMood(String date, String type, int level) {
        List<LogEntry> list = logManager.getLogsByDate().getOrDefault(date, new ArrayList<>());
        list.add(new MoodLog(type, level));
        logManager.getLogsByDate().put(date, list);
    }

    private void addEnergy(String date, int level, String note) {
        List<LogEntry> list = logManager.getLogsByDate().getOrDefault(date, new ArrayList<>());
        list.add(new EnergyLog(level, note));
        logManager.getLogsByDate().put(date, list);
    }
}
