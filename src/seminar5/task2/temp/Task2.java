package seminar5.task2.temp;

import seminar5.task1.temp.ObjectA;
import seminar5.task1.temp.ObjectB;

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
        AtomicBoolean switcher = new AtomicBoolean(true);
        AtomicInteger a = new AtomicInteger(100);
        Object x = new Object();


        Thread threadA = new Thread(() -> {
            while (a.get() > 0) {
                synchronized (x) {
                    try {
                        Thread.sleep(1000);
                        //System.out.println("Thread A work " + switcher);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    switcher.set(!switcher.get());
                    //System.out.println(switcher);
                    if (switcher.get()) {
                        //System.out.println("ThreadA awaking ThreadB");
                        x.notifyAll();
                    }
                }
            }



        });
        Thread threadB = new Thread(() -> {
            while (a.get() > 0) {
                synchronized (x) {
                    if(!switcher.get()) {
                        try {
                            System.out.println("Thread B " + switcher);
                            x.wait();
                            System.out.println("I am sleep");
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    } else {
                        for (int i = a.get(); i >= 0; i--) {
                            if (switcher.get()) {
                                System.out.print(i);
                                a.set(i);
                                break;
                            }
                        }
                    }

                }

            }


        });

        threadA.start();
        threadB.start();
    }


}