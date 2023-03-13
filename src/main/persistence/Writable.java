package persistence;

import org.json.JSONObject;

/**
 * Modelled off JsonSerializationDemo from the Phase 2 EdX section
 *
 */

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}