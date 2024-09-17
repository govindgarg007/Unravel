public class Main {
    public static void main(String[] args) {
        // Create an instance of DeadlockSimulator
        DeadlockSimulator simulator = new DeadlockSimulator();

        // Create threads to run methods concurrently
        Thread thread1 = new Thread(() -> simulator.method1());
        Thread thread2 = new Thread(() -> simulator.method2());

        // Start the threads
        thread1.start();
        thread2.start();

        // Wait for both threads to complete
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print a message indicating that execution is complete
        System.out.println("Execution complete.");
    }
}
