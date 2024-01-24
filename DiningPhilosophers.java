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
            // Think for a while after eating
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
// В этом коде класс Fork симулирует вилку, которую могут поднимать или опускать философы.
// Вилка реализована как объект блокировки Lock для обеспечения взаимного исключения.
// Класс Philosopher представляет философа, который пытается поднять обе вилки, чтобы поесть.
// Сначала он пытается поднять левую вилку, а потом, если это ему удаётся, он пытается поднять правую вилку.
// Если поднять правую вилку не удаётся, он кладёт обратно левую и пробует позже.
// Главный класс DiningPhilosophers создаёт пять философов и пять вилок, и начинает действие, создавая потоки для каждого философа.
// Каждый философ будет пытаться есть до тех пор, пока не съест три тарелки спагетти, после чего прекратит свою деятельность.
