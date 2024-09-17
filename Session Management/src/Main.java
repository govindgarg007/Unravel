
public class Main {
    public static void main(String[] args) {
        // Create a SessionManager instance
        SessionManager sessionManager = new SessionManager();




        // Test login
        System.out.println(sessionManager.login("user1"));

        // Test session details retrieval
        System.out.println(sessionManager.getSessionDetails("user1"));

        // Test logout
        System.out.println(sessionManager.logout("user1"));

        // Try to get session details after logout (should throw an error)
        try {
            System.out.println(sessionManager.getSessionDetails("user1"));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}