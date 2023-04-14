package model;

/**
 * Boolean Value that expresses when the application is in the process of writing from memory.
 * Used to mute Event Logging during loading, so that only brand new Input is published to console on quit.
 */

public class Loading {
    private static Boolean loading = false;

    public static Boolean isLoading() {
        return loading;
    }

    public static void setLoading(Boolean bool) {
        loading = bool;
    }
}