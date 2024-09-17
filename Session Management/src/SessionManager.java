import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    // ConcurrentHashMap to store user sessions with userId as the key and sessionId as the value.
    private final ConcurrentHashMap<String, String> sessions = new ConcurrentHashMap<>();
    // ReentrantLock to ensure thread safety when modifying the sessions map.
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Logs in a user by creating a new session.
     * @param userId The ID of the user trying to log in.
     * @return A message indicating whether the login was successful or if the user is already logged in.
     */
    public String login(String userId) {
        lock.lock(); // Acquire the lock to ensure exclusive access to the sessions map.
        try {
            // Check if the user is already logged in.
            if (sessions.containsKey(userId)) {
                return "User already logged in.";
            }
            // Generate a new session ID and store it in the sessions map.
            String sessionId = "SESSION_" + UUID.randomUUID().toString();
            sessions.put(userId, sessionId);
            return "Login successful. Session ID: " + sessionId;
        } finally {
            lock.unlock(); // Release the lock after the operation is complete.
        }
    }

    /**
     * Logs out a user by removing their session.
     * @param userId The ID of the user trying to log out.
     * @return A message indicating whether the logout was successful or if the user was not logged in.
     */
    public String logout(String userId) {
        lock.lock(); // Acquire the lock to ensure exclusive access to the sessions map.
        try {
            // Check if the user is logged in.
            if (!sessions.containsKey(userId)) {
                return "User not logged in.";
            }
            // Remove the user's session from the sessions map.
            sessions.remove(userId);
            return "Logout successful.";
        } finally {
            lock.unlock(); // Release the lock after the operation is complete.
        }
    }

    /**
     * Retrieves the session details for a given user.
     * @param userId The ID of the user whose session details are being requested.
     * @return A message containing the session ID for the user.
     * @throws RuntimeException If the user is not logged in or their session is not found.
     */
    public String getSessionDetails(String userId) {
        // Check if the user has a session.
        if (!sessions.containsKey(userId)) {
            throw new RuntimeException("Session not found for user " + userId);
        }
        // Return the session ID for the user.
        return "Session ID for user " + userId + ": " + sessions.get(userId);
    }
}
