package seminar5.hw;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Есть пять философов (потоки), которые могут либо обедать, либо размышлять. +
 * Каждый философ должен пообедать три раза. +
 * Каждый прием пищи длиться 500 миллисекунд +
 * После каждого приема пищи философ должен размышлять +
 * Не должно возникнуть общей блокировки +
 * Философы не должны есть больше одного раза подряд +
 * Одновременно не кушают???
 */
public class Var3 {
    static CountDownLatch eat1 = new CountDownLatch(5);
    static CountDownLatch eat2 = new CountDownLatch(5);
    static CountDownLatch eat3 = new CountDownLatch(5);
    static String eatBefore;
    static final Object fork = new Object();
    static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {

        Thread ph1 = new Thread(new Philosopher("Аристотель "));
        Thread ph2 = new Thread(new Philosopher("Платон "));
        Thread ph3 = new Thread(new Philosopher("Деметрий "));
        Thread ph4 = new Thread(new Philosopher("Теофраст "));
        Thread ph5 = new Thread(new Philosopher("Пифагор "));


        ph1.start();
        ph2.start();
        ph3.start();
        ph4.start();
        ph5.start();

    }

    public static class Philosopher implements Runnable {
        String name;

        public Philosopher(String name) {

            this.name = name;

        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10));
                eat(eat1, " завтракает...");
                skipWhoEatBefore();
                eat(eat2, " ужинает...");
                skipWhoEatBefore();
                eat(eat3, " обедает...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void eat(CountDownLatch count, String eating) throws InterruptedException {
            synchronized (fork) {
                eatBefore = name;
                System.out.println(name + eating);
                Thread.sleep(500);
                System.out.println(name + "думает... ");
            }
            count.countDown();
            count.await();
        }

        public void skipWhoEatBefore() throws InterruptedException {

            while (Objects.equals(eatBefore, name)) {
                Thread.sleep(1);
            }
        }
    }
}
