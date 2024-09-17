public class Main {
    public static void main(String[] args) {
        // Create an instance of the Concurrency class
        Concurrency concurrency = new Concurrency();

        // Create and add tasks with different priorities
        concurrency.produceLog(new Task("High priority task", 10));
        concurrency.produceLog(new Task("Medium priority task", 5));
        concurrency.produceLog(new Task("Low priority task", 1));
        concurrency.produceLog(new Task("Another high priority task", 8));

        // Create a Runnable to consume tasks from the queue
        Runnable consumerTask = () -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    // Consume and process tasks
                    Task task = concurrency.consumeLog();
                    System.out.println("Processed: " + task.getLog() + " with priority " + task.getPriority());
                    // Simulate processing time
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                // Restore interrupted status and exit
                Thread.currentThread().interrupt(); // Restore the interrupted status
                System.err.println("Consumer thread was interrupted and is stopping.");
            }
        };

        // Start a thread to run the consumer task
        Thread consumerThread = new Thread(consumerTask);
        consumerThread.start();

        // Allow some time for tasks to be processed
        try {
            Thread.sleep(5000); // Wait for 5 seconds to let tasks be processed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrupt the consumer thread to stop it
        consumerThread.interrupt();
    }
}
