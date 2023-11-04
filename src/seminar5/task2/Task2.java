package seminar5.task2;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Создайте два потока A и B.
 * Поток A меняет значение Boolean переменной switcher с задержкой 1000 миллисекунд (true в состояние false и наоборот).
 * Поток B ожидает состояния true переменной switcher и выводит на консоль обратный отсчет от 100 с задержкой 100
 * миллисекунд и приостанавливает свое действие, как только поток A переключит switcher в состояние false.
 * Условием завершения работы потоков является достижение отсчета нулевой отметки.
 */
public class Task2 {

    public static void main(String[] args) {
        AtomicBoolean switcher = new AtomicBoolean(false);
        AtomicInteger countdown = new AtomicInteger(100);
        Object monitor = new Object();

        Thread threadA = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": started thread!!!");
            synchronized (monitor) {
                while (countdown.get() > 0) {
                    try {
                        Thread.sleep(100);
                        System.out.println(Thread.currentThread().getName() + ": 1 sec sleep");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    switcher.set(!switcher.get());
                    System.out.println(Thread.currentThread().getName() + ": ----> " + switcher);
                    if (switcher.get()) {

                        System.out.println(Thread.currentThread().getName() + ": A --> B");
                        monitor.notify();
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }monitor.notify();// for last waiter
            }
            System.out.println(Thread.currentThread().getName() + ": is end!!!");
        }, "thread A");
        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + ": started thread!!!");
            synchronized (monitor) {
                //System.out.println(Thread.currentThread().getName() + ": start synchronized");
                while (countdown.get() > 0) {
                    //System.out.println(Thread.currentThread().getName() + ": start cycle");

                    for (int i = countdown.get(); i >= 0; i--) {

                        if (switcher.get()){
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println(Thread.currentThread().getName() + ": is printed " + i);
                            countdown.set(i);
                        }
                        monitor.notify();
                        System.out.println(Thread.currentThread().getName() + ": B --> A");
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            System.out.println(Thread.currentThread().getName() + ": is end!!!");
        }, "Thread B");


        threadA.start();
        threadB.start();
    }

}
