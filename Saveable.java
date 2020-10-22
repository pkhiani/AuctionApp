package persistence;

import java.io.PrintWriter;

// Represents data that can be saved to file
// inspired by TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp

public interface Saveable {
    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    void save(PrintWriter printWriter);
}
