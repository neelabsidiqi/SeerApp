import org.example.model.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogEntryModelTests {

    @Test
    public void testMoodLog() {
        MoodLog moodLog = new MoodLog("joyful", 9);

        assertEquals("joyful", moodLog.getType(), "Mood type should be 'joyful'");
        assertEquals(9, moodLog.getLevel(), "Mood level should be 9");

        String expectedString = "MoodLog|joyful|9";
        assertEquals(expectedString, moodLog.toString(), "toString should match expected format 💕");
    }

    @Test
    public void testEnergyLog() {
        EnergyLog energyLog = new EnergyLog(7, "Afternoon boost ✨");

        assertEquals(7, energyLog.getLevel(), "Energy level should be 7");
        assertEquals("Afternoon boost ✨", energyLog.getNote(), "Note should match input");

        String expectedString = "EnergyLog|7|Afternoon boost ✨";
        assertEquals(expectedString, energyLog.toString(), "toString should be formatted perfectly 🌈");
    }

    @Test
    public void testTaskLog() {
        TaskLog taskLog = new TaskLog("Write test cases", "in progress");

        assertEquals("Write test cases", taskLog.getTask(), "Task name should be correct");
        assertEquals("in progress", taskLog.getStatus(), "Status should match the set value");

        String expectedString = "TaskLog|Write test cases|in progress";
        assertEquals(expectedString, taskLog.toString(), "toString should sparkle with precision ✨");
    }
}
