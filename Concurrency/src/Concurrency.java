import java.util.concurrent.*;

public class Concurrency {
    // PriorityBlockingQueue to store tasks in order of their priority
    private final PriorityBlockingQueue<Task> taskQueue = new PriorityBlockingQueue<>();

    /**
     * Adds a new task to the queue.
     * @param task The task to be added to the queue.
     */
    public void produceLog(Task task) {
        // Add the task to the queue. Tasks with higher priority will be processed first.
        taskQueue.put(task);
    }

    /**
     * Retrieves and removes the highest-priority task from the queue.
     * @return The highest-priority task.

     */
    public Task consumeLog() throws InterruptedException {
        // Retrieve and remove the task with the highest priority from the queue.
        return taskQueue.take();
    }
}

// Task represents a unit of work with a priority
class Task implements Comparable<Task> {
    private final String log;  // Log message associated with the task
    private final int priority; // Priority of the task

    /**
     * Constructor to initialize a task with a log message and priority.
     * @param log The log message.
     * @param priority The priority of the task.
     */
    public Task(String log, int priority) {
        this.log = log;
        this.priority = priority;
    }

    /**
     * Gets the log message of the task.
     * @return The log message.
     */
    public String getLog() {
        return log;
    }

    /**
     * Gets the priority of the task.
     * @return The priority of the task.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Compares this task with another task based on priority.
     * @param t The other task to compare with.
     * @return A negative integer, zero, or a positive integer as this task's priority is less than,
     *         equal to, or greater than the specified task's priority.
     */
    @Override
    public int compareTo(Task t) {
        // Compare tasks based on priority (higher priority tasks are processed first)
        return Integer.compare(t.priority, this.priority);
    }

}
