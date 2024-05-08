import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {

    public static void main(String[] args) {
        // number of philosophers and forks
        final int problemSize = 5;  
        Fork leftFork;
        Fork rightFork;

        Philosopher[] philosophers = new Philosopher[problemSize];  // create an array to hold the philosophers
        Fork[] forks = new Fork[problemSize];                       // create an array to hold the forks
        Lock lock = new ReentrantLock();                            // create a lock to ensure mutual exclusion
        for (int i = 0; i < problemSize; i++) {
            forks[i] = new Fork();                                  // initialize each fork object
        }
        // create a thread for each philosopher
        for (int i = 0; i < problemSize; i++) {                     // set the left fork to the current index
            leftFork = forks[i];                                    // set the right fork to the next index
            rightFork = forks[(i + 1) % problemSize];               // create a philosopher object with left and right forks
            philosophers[i] = new Philosopher(leftFork, rightFork, i + 1, lock);    // create a thread for the philosopher
            Thread t = new Thread(philosophers[i]);                 // start the thread
            t.start();
        }
    }
}