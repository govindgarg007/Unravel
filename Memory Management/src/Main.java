public class Main {
    public static void main(String[] args) {
        // Create an instance of MemoryManager
        MemoryManager memoryManager = new MemoryManager();

        // Add some session data
        String sessionId1 = "session1";
        String sessionId2 = "session2";

        System.out.println("Adding session data:");
        memoryManager.addSessionData(sessionId1);
        memoryManager.addSessionData(sessionId2);

        // Output to verify session data addition
        System.out.println("Session data added for sessionId1 and sessionId2.");

        // Remove one session data
        System.out.println("Removing session data for sessionId1:");
        memoryManager.removeSessionData(sessionId1);

        // Output to verify session data removal
        System.out.println("Session data removed for sessionId1.");

        // Verify the remaining session data
        // This is more illustrative as WeakHashMap does not provide direct access to the contents of the map.
        // The memory management aspect should be observed indirectly through profiling tools.

        // Simulate some delay to show potential garbage collection effect
        try {
            System.out.println("Waiting to allow garbage collection...");
            Thread.sleep(5000); // Wait for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Note: To actually check if the data was removed or garbage collected,
        // profiling tools or additional methods to inspect the WeakHashMap contents would be needed.
        System.out.println("End of main method.");
    }
}