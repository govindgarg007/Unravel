import java.util.Map;
import java.util.WeakHashMap;

/**
 * MemoryManager handles session data storage using a WeakHashMap.
 * This allows for automatic garbage collection of session data when
 * there are no strong references to the keys.
 */
public class MemoryManager {
    // A WeakHashMap to store session data. Keys are session IDs and values are byte arrays representing session data.
    private final Map<String, byte[]> sessionData = new WeakHashMap<>();




    /**
     * Adds session data to the memory manager.
     * @param sessionId The unique identifier for the session.
     * @note This method stores a 10MB byte array for each session.
     */
    public void addSessionData(String sessionId) {
        // Store a new 10MB byte array for the given session ID.
        // Using WeakHashMap allows the garbage collector to automatically
        // clean up entries when there are no strong references to the sessionId.
        sessionData.put(sessionId, new byte[10 * 1024 * 1024]);  // 10MB per session
    }

    /**
     * Removes session data from the memory manager.
     * @param sessionId The unique identifier for the session to be removed.
     */
    public void removeSessionData(String sessionId) {
        // Remove the session data associated with the given session ID.
        // If the session ID is not present, this operation has no effect.
        sessionData.remove(sessionId);
    }
}
