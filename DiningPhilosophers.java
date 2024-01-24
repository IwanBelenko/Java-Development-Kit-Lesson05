import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Fork {
    private final Lock lock = new ReentrantLock();

    public boolean pickUp() {
        return lock.tryLock();
    }

    public void putDown() {
        lock.unlock();
    }
}

class Philosopher extends Thread {
    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;
    private int eatenMeals = 0;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void eat() {
        if (leftFork.pickUp()) {
            if (rightFork.pickUp()) {
                eatenMeals++;
                System.out.println("Philosopher " + id + " is eating. (Meal number " + eatenMeals + ")");
                rightFork.putDown();
            }
            leftFork.putDown();
        }
    }

    @Override
    public void run() {
        while (eatenMeals < 3) {
            eat();
            try {
                Thread.sleep((long)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class DiningPhilosophers {
    public static void main(String[] args) {
        int size = 5;
        Philosopher[] philosophers = new Philosopher[size];
        Fork[] forks = new Fork[size];

        for (int i = 0; i < size; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < size; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % size]);
            philosophers[i].start();
        }
    }
}
