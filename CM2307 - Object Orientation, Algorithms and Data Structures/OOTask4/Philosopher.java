import java.util.concurrent.locks.*;

// The Philosopher class implements the Runnable interface, which allows it to be run in a separate thread. Each philosopher has a 
// unique number, two forks (left and right), and a lock.
// In the run method, each philosopher repeatedly performs the following steps:
//     Think for a while.
//     Attempt to pick up the left and right forks. If one or both forks are not available, release the lock and try again later.
//     If both forks are available, mark them as in use.
//     Eat for a while.
//     Put down both forks and mark them as available again.
// The Lock object is used to ensure that no other philosopher can acquire a fork while a philosopher is in the process of acquiring it.
// The Fork class has a single boolean field inUse, which is initially set to false to indicate that the fork is available. The 
// isAvailable and setAvailable methods are synchronized to prevent multiple philosophers from modifying the state of the fork at the 
// same time.

public class Philosopher implements Runnable {

    private int philosopherNumber;
    private Fork leftFork;
    private Fork rightFork;
    private final Lock lock;



    public Philosopher(Fork left, Fork right, int philNumber ,Lock lock) {
        leftFork = left;
        rightFork = right;
        philosopherNumber = philNumber;
        this.lock = lock;
    }
    private void doAction(String action) throws InterruptedException {
        System.out.println("Philosopher number " + philosopherNumber + " time: " + System.nanoTime() + ": " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }
    
    public void run() {
        try {
            while (true) {

                // thinking
                doAction("Thinking");
                System.out.println("Philosopher number " + philosopherNumber + " is ready to pick up fork.");
                
                // Acquire the lock and both forks
                lock.lock();
                try {
                    while (!leftFork.isAvailable() || !rightFork.isAvailable()) {
                        // At least one fork is not available, release the lock and try again later
                        
                        lock.unlock();
                        Thread.sleep(((int) (Math.random() * 100)));
                        lock.lock();
                    }
                    // Both forks are available, mark them as in use
                    leftFork.setAvailable(false);
                    rightFork.setAvailable(false);
                } finally {
                    // Release the lock
                    lock.unlock();
                }
                
                // Eat for a while
                doAction("Picking up left fork");
                doAction("Picking up right fork");
                doAction("Eating");
                
                
                // Release both forks
                lock.lock();
                try {
                    leftFork.setAvailable(true);
                    doAction("Putting down left fork");
                    rightFork.setAvailable(true);
                    doAction("Putting down right fork");
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}