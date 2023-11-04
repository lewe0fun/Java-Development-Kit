package seminar5.task3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 3 бегуна должны прийти к старту гонки
 * Программа должна гарантировать, что гонка начнется только когда все три участника будут на старте
 * Программа должна отсчитать “На старт”, “Внимание”, “Марш”
 * Программа должна завершиться когда все участники закончат гонку.
 */
public class Task3 {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch START = new CountDownLatch(6);//3 в бегунах + 3 в основном процессе
        Random random =new Random();

        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " подошел к стартовой прямой");
                START.countDown();
                try {
                    START.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " финишировал");
            }, "бегун "+i).start();
        }

        while (START.getCount() > 3)
            Thread.sleep(100);

        Thread.sleep(1000);
        System.out.println("На старт!");
        START.countDown();
        Thread.sleep(1000);
        System.out.println("Внимание!");
        START.countDown();
        Thread.sleep(1000);
        System.out.println("Марш!");
        START.countDown();
    }
}
