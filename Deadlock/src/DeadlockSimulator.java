import java.util.concurrent.locks.ReentrantLock;

public class DeadlockSimulator {
    private final ReentrantLock lock1 = new ReentrantLock();
    private final ReentrantLock lock2 = new ReentrantLock();

    // Method to acquire locks in a consistent order: lock1 first, then lock2
    public void method1() {
        try {
            // Attempt to acquire lock1 first
            if (lock1.tryLock()) {
                try {
                    // Attempt to acquire lock2 only if lock1 is successfully acquired
                    if (lock2.tryLock()) {
                        try {
                            System.out.println("Method1: Acquired lock1 and lock2");
                        } finally {
                            lock2.unlock(); // Release lock2 first
                        }
                    }
                } finally {
                    lock1.unlock(); // Release lock1
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to acquire locks in the same order as method1
    public void method2() {
        try {
            // Attempt to acquire lock1 first
            if (lock1.tryLock()) {
                try {
                    // Attempt to acquire lock2 only if lock1 is successfully acquired
                    if (lock2.tryLock()) {
                        try {
                            System.out.println("Method2: Acquired lock1 and lock2");
                        } finally {
                            lock2.unlock(); // Release lock2 first
                        }
                    }
                } finally {
                    lock1.unlock(); // Release lock1
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
