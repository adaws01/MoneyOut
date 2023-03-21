package persistence;

import org.json.JSONObject;

/**
 * Modelled off JsonSerializationDemo from the Phase 2 EdX section
 */

//Interface for a class whose data needs to be saved and read from file.
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}